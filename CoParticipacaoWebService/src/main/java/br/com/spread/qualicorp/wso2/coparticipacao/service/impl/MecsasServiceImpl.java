package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.BeneficiarioNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.DependenteDuplicated;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularDuplicated;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.impl.SpreadsheetProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.DependenteBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.DesconhecidoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.TitularBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.service.TitularDetailBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.MecsasService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Qualifier("MecsasService")
@Service
@Transactional(value = AbstractService.JDBC_TX)
public class MecsasServiceImpl implements MecsasService, SpreadsheetProcessorListener {

	private static final Logger LOGGER = LogManager.getLogger(MecsasServiceImpl.class);

	@Autowired
	private TitularService titularService;

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Autowired
	private DependenteService dependenteService;

	@Autowired
	private TitularDetailService titularDetailService;

	@Autowired
	private DependenteDetailService dependenteDetailService;
	
	@Autowired
	private TitularBatchService titularBatchService;
	
	@Autowired
	private DependenteBatchService dependenteBatchService;
	
	@Autowired
	private DesconhecidoBatchService desconhecidoBatchService;

	public void processLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi;
		TitularUi titularUi = null;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = beneficiarioService.createBeneficiarioFromMecsas(coParticipacaoContext);

			if (BeneficiarioType.TITULAR.equals(beneficiarioUi.getType())) {
				LOGGER.info("Processing titular:");

				titularUi = beneficiarioService.createTitular(coParticipacaoContext, beneficiarioUi);

				if (titularUi == null) {
					desconhecidoService.createDesconhecido(coParticipacaoContext, beneficiarioUi);
				} else {
					coParticipacaoContext.setTitularUi(titularUi);

					if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
						titularDetailService.storeDetails(coParticipacaoContext, titularUi);
					}
				}
			} else {
				LOGGER.info("Processing beneficiario:");

				titularUi = coParticipacaoContext.getTitularUi();

				if (titularUi != null) {
					LOGGER.info(
							"Using Titular [{}] with Matricula [{}] and CPF [{}]:",
							titularUi.getNameTitular(),
							titularUi.getMatricula(),
							titularUi.getCpf());

					dependenteUi = beneficiarioService.createDependente(coParticipacaoContext, beneficiarioUi);

					if (dependenteUi == null) {
						desconhecidoService.createDesconhecido(coParticipacaoContext, beneficiarioUi);
					} else {
						if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
							dependenteDetailService.storeDetails(coParticipacaoContext, dependenteUi);
						}
					}
				} else {
					desconhecidoService.createDesconhecido(coParticipacaoContext, beneficiarioUi);

					throw new TitularNotFoundException("Should exists a line for Titular before its Dependente:");
				}
			}

			LOGGER.info("END");
		} catch (DependenteDuplicated e) {
			LOGGER.info(e.getMessage());
		} catch (TitularDuplicated e) {
			LOGGER.info(e.getMessage());
		} catch (BeneficiarioNotFoundException e) {
			LOGGER.debug(e.getMessage());
		} catch (TitularNotFoundException e) {
			LOGGER.debug(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public boolean validateLine(String line, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		if (StringUtils.isNotBlank(line)) {
			return true;
		}

		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void afterProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Process ending and sending data to database:");

			LOGGER.info(
					"Storing titular [{}] and dependentes data:",
					coParticipacaoContext.getBunker().getTitularUis().size());

			//titularService.saveBatch(coParticipacaoContext.getBunker().getTitularUis());
			//dependenteService.saveBatch(coParticipacaoContext.getBunker().getDependenteUis());
			
			titularBatchService.saveBatch(coParticipacaoContext.getBunker().getTitularUis());
			dependenteBatchService.saveBatch(coParticipacaoContext.getBunker().getDependenteUis());

			LOGGER.info(
					"Storing desconhecidos [{}] data:",
					coParticipacaoContext.getBunker().getDesconhecidoUis().size());
			//desconhecidoService.saveBatch(coParticipacaoContext.getBunker().getDesconhecidoUis());
			desconhecidoBatchService.saveBatch(coParticipacaoContext.getBunker().getDesconhecidoUis());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void beforeProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		ArquivoInputUi arquivoInputUi;

		try {
			LOGGER.info("BEGIN");

			arquivoInputUi = coParticipacaoContext.getArquivoInputUi();

			LOGGER.info(
					"Starting process [{}] to load benefiets from assets file:",
					arquivoInputUi.getUseType().getDescription());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public boolean validateSheet(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			// MECSAS no momento lê apenas a primeira pasta:
			if (NumberUtils.INTEGER_ZERO.equals(coParticipacaoContext.getCurrentSheet())) {
				return true;
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

}

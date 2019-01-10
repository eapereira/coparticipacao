package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DependenteBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.DesconhecidoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.TitularBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.BeneficiarioNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.DependenteDuplicated;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularDuplicated;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.io.ProcessLineResult;
import br.com.spread.qualicorp.wso2.coparticipacao.io.SpreadsheetProcessorListener;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.MecsasService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Qualifier("MecsasService")
@Service
public class MecsasServiceImpl implements MecsasService, SpreadsheetProcessorListener {

	private static final Logger LOGGER = LogManager.getLogger(MecsasServiceImpl.class);

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Autowired
	private TitularBatchService titularBatchService;

	@Autowired
	private DependenteBatchService dependenteBatchService;

	@Autowired
	private DesconhecidoBatchService desconhecidoBatchService;

	public ProcessLineResult processLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi;
		TitularUi titularUi = null;
		ProcessLineResult processLineResult = ProcessLineResult.READ_NEXT;

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
					}
				} else {
					desconhecidoService.createDesconhecido(coParticipacaoContext, beneficiarioUi);

					throw new TitularNotFoundException("Should exists a line for Titular before its Dependente:");
				}
			}

			LOGGER.info("END");
			return processLineResult;
		} catch (DependenteDuplicated e) {
			LOGGER.info(e.getMessage());
			return processLineResult;
		} catch (TitularDuplicated e) {
			LOGGER.info(e.getMessage());
			return processLineResult;
		} catch (BeneficiarioNotFoundException e) {
			LOGGER.debug(e.getMessage());
			return processLineResult;
		} catch (TitularNotFoundException e) {
			LOGGER.debug(e.getMessage());
			return processLineResult;
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

	public void afterProcess(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Process ending and sending data to database:");

			LOGGER.info("Storing Titulars: ...... [{}]", coParticipacaoContext.getBunker().getTitularUis().size());
			LOGGER.info("Storing Dependetes: .... [{}]", coParticipacaoContext.getBunker().getDependenteUis().size());

			titularBatchService.saveBatch(coParticipacaoContext, coParticipacaoContext.getBunker().getTitularUis());
			dependenteBatchService
					.saveBatch(coParticipacaoContext, coParticipacaoContext.getBunker().getDependenteUis());

			LOGGER.info(
					"Storing desconhecidos [{}] data:",
					coParticipacaoContext.getBunker().getDesconhecidoUis().size());
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
		ArquivoInputSheetUi arquivoInputSheetUi;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info(
					"Validating if sheet.ID[{}] with sheet.NAME[{}] will be accepted:",
					coParticipacaoContext.getCurrentSheet(),
					coParticipacaoContext.getSpreadsheetContext().getSheetName());

			if (!coParticipacaoContext.getMapArquivoInputSheetUi().isEmpty()) {
				arquivoInputSheetUi = coParticipacaoContext.getMapArquivoInputSheetUi()
						.get(coParticipacaoContext.getCurrentSheet());

				if (arquivoInputSheetUi != null) {
					LOGGER.info("Sheet.ID[{}] accepted:", coParticipacaoContext.getCurrentSheet());
					return true;
				}
			} else if (NumberUtils.INTEGER_ZERO.equals(coParticipacaoContext.getCurrentSheet())) {
				// MECSAS no momento lê apenas a primeira pasta:
				LOGGER.info("Sheet.ID[{}] accepted:", coParticipacaoContext.getCurrentSheet());
				return true;
			}

			LOGGER.info("Sheet.ID[{}] rejected:", coParticipacaoContext.getCurrentSheet());
			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

}

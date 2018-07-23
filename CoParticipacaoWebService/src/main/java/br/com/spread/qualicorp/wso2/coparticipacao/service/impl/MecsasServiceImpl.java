package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.IsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.MecsasService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.TitularService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class MecsasServiceImpl implements MecsasService {

	private static final Logger LOGGER = LogManager.getLogger(MecsasServiceImpl.class);

	private TitularUi titularUi;

	@Autowired
	private TitularService titularService;

	@Autowired
	private DesconhecidoService desconhecidoService;

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Autowired
	private IsentoService isentoService;

	public void processLine(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");

			if (isentoService.hasIsento(coParticipacaoContext)) {
				LOGGER.info("Stating Isento processing:");

				isentoService.processIsento(coParticipacaoContext);
			} else {
				beneficiarioUi = beneficiarioService.createBeneficiarioFromMecsas(coParticipacaoContext);

				if (BeneficiarioType.TITULAR.equals(beneficiarioUi.getType())) {
					LOGGER.info("Processing titular:");

					titularUi = beneficiarioService.createTitular(beneficiarioUi, coParticipacaoContext);
					storeTitular(titularUi, coParticipacaoContext);
				} else {
					LOGGER.info("Processing beneficiario:");

					dependenteUi = beneficiarioService.createDependente(beneficiarioUi, coParticipacaoContext);

					if (titularUi != null) {
						storeDependente(dependenteUi, coParticipacaoContext);
					} else {
						throw new TitularNotFoundException("Should exists a line for Titular before its Dependente:");
					}
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void storeTitular(TitularUi titularUi, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (validateTitular(titularUi)) {
				titularUi.setEmpresa(coParticipacaoContext.getEmpresaUi());

				if (titularUi.getId() == null) {
					coParticipacaoContext.addTitular(titularUi);
				}
			} else {
				desconhecidoService.createDesconhecido(coParticipacaoContext);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void storeDependente(DependenteUi dependenteUi, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			dependenteUi.setTitular(titularUi);

			if (validateDependente(dependenteUi)) {
				if (dependenteUi.getId() == null) {
					titularUi.addDependente(dependenteUi);
					coParticipacaoContext.addDependente(dependenteUi);
				}
			} else {
				desconhecidoService.createDesconhecido(coParticipacaoContext);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean validateTitular(TitularUi titularUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(titularUi.getCpf())) {
				if (StringUtils.isNotBlank(titularUi.getNameTitular())) {
					if (titularUi.getMatricula() != null) {
						return true;
					}
				}
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean validateDependente(DependenteUi dependenteUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(dependenteUi.getCpf())) {
				if (StringUtils.isNotBlank(dependenteUi.getNameDependente())) {
					if (dependenteUi.getMatricula() != null) {
						if (dependenteUi.getTitular() != null) {
							return true;
						}
					}
				}
			}

			LOGGER.info("END");
			return false;
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

			LOGGER.info("Storing titular and dependente data:");

			titularService.saveBatch(coParticipacaoContext.getBunker().getTitularUis());

			LOGGER.info("Storing desconhecidos data:");
			desconhecidoService.saveBatch(coParticipacaoContext.getBunker().getDesconhecidoUis());

			LOGGER.info("Storing Isentos to database:");
			isentoService.saveIsentos(coParticipacaoContext);

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

			LOGGER.info("Starting process [{}] to load benefiets from assets file:", arquivoInputUi.getUseType().getDescription());

			if (isentoService.hasIsento(coParticipacaoContext)) {
				isentoService.deleteByMesAndAno(coParticipacaoContext.getEmpresaUi(), coParticipacaoContext.getMes(), coParticipacaoContext.getAno());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

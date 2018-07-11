package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DesconhecidoService;
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

	private static final Logger LOGGER = LogManager
			.getLogger(MecsasServiceImpl.class);

	private TitularUi titularUi;

	@Autowired
	private TitularService titularService;

	@Autowired
	private DesconhecidoService desconhecidoService;

	public void processLine(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		Object value;
		BeneficiarioType beneficiarioType = null;

		try {
			LOGGER.info("BEGIN");

			value = coParticipacaoContext.getMapLine().get("GP");

			if (value != null) {
				beneficiarioType = BeneficiarioType.parse((Integer) value);
			} else {
				beneficiarioType = BeneficiarioType.TITULAR;
			}

			if (BeneficiarioType.TITULAR.equals(beneficiarioType)) {
				LOGGER.info("Processing titular:");

				titularUi = new TitularUi();
				storeTitular(titularUi, coParticipacaoContext);
			} else {
				LOGGER.info("Processing beneficiario:");

				if (titularUi != null) {
					storeDependente(titularUi, coParticipacaoContext);
				} else {
					throw new TitularNotFoundException(
							"Should exists a line for Titular before its Dependente:");
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void storeTitular(
			TitularUi titularUi,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		List<InputTitularUi> inputTitularUis;
		Object value;

		try {
			LOGGER.info("BEGIN");

			for (ArquivoInputColsDefUi arquivoInputColsDefUi : coParticipacaoContext
					.getArquivoInputColsDefUis()) {
				value = coParticipacaoContext.getMapLine()
						.get(arquivoInputColsDefUi.getNameColumn());

				LOGGER.debug(
						"Column [{}] with value [{}]:",
						arquivoInputColsDefUi.getNameColumn(),
						value);
				inputTitularUis = coParticipacaoContext.getInputTitularUis();

				if (!inputTitularUis.isEmpty()) {
					for (InputTitularUi inputTitularUi : inputTitularUis) {
						if (inputTitularUi.getArquivoInputColsDef().getId()
								.equals(arquivoInputColsDefUi.getId())) {
							LOGGER.info(
									"Column [{}] ==> mapped to Titular[{}]:",
									arquivoInputColsDefUi.getNameColumn(),
									inputTitularUi.getTitularColsDef()
											.getNameColumn());

							storeInputTitularValue(
									coParticipacaoContext,
									titularUi,
									inputTitularUi,
									value);
						}
					}
				} else {
					LOGGER.info(
							"There is no columns mapped to InputTitular for ArquivoInput [{}]:",
							coParticipacaoContext.getArquivoInputUi()
									.getDescrArquivo());
				}
			}

			if (validateTitular(titularUi)) {
				titularUi.setEmpresa(coParticipacaoContext.getEmpresaUi());

				if (titularUi.getId() == null) {
					titularUi.setUserCreated(coParticipacaoContext.getUser());
				}

				titularUi.setUserAltered(coParticipacaoContext.getUser());

				coParticipacaoContext.addTitular(titularUi);
			} else {
				desconhecidoService
						.createDesconhecido(coParticipacaoContext);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void storeDependente(
			TitularUi titularUi,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		Object value;
		List<InputDependenteUi> inputDependenteUis;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");

			dependenteUi = new DependenteUi();
			dependenteUi.setTitular(titularUi);

			for (ArquivoInputColsDefUi arquivoInputColsDefUi : coParticipacaoContext
					.getArquivoInputColsDefUis()) {
				value = coParticipacaoContext.getMapLine()
						.get(arquivoInputColsDefUi.getNameColumn());

				LOGGER.debug(
						"Column [{}] with value [{}]:",
						arquivoInputColsDefUi.getNameColumn(),
						value);

				inputDependenteUis = coParticipacaoContext
						.getInputDependenteUis();

				if (!inputDependenteUis.isEmpty()) {
					for (InputDependenteUi inputDependenteUi : inputDependenteUis) {
						if (inputDependenteUi.getArquivoInputColsDef().getId()
								.equals(arquivoInputColsDefUi.getId())) {
							LOGGER.info(
									"Column [{}] ==> mapped to Dependente[{}]:",
									arquivoInputColsDefUi.getNameColumn(),
									inputDependenteUi.getDependenteColsDef()
											.getNameColumn());

							storeInputDependenteValue(
									coParticipacaoContext,
									dependenteUi,
									inputDependenteUi,
									value);
						}
					}
				} else {
					LOGGER.info(
							"Column [{}] is not mapped into Dependente:",
							arquivoInputColsDefUi.getNameColumn());
				}
			}

			if (validateDependente(dependenteUi)) {
				titularUi.addDependente(dependenteUi);
				coParticipacaoContext.addDependente(dependenteUi);
			} else {
				desconhecidoService
						.createDesconhecido(coParticipacaoContext);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean validateTitular(TitularUi titularUi)
			throws ServiceException {
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

	private boolean validateDependente(DependenteUi dependenteUi)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (StringUtils.isNotBlank(dependenteUi.getCpf())) {
				if (StringUtils.isNotBlank(dependenteUi.getNameDependente())) {
					if (dependenteUi.getTitular() != null) {
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

	private void storeInputDependenteValue(
			CoParticipacaoContext coParticipacaoContext,
			DependenteUi dependenteUi,
			InputDependenteUi inputDependenteUi,
			Object value) throws ServiceException {
		DependenteColType dependenteColType;
		DependenteUi dependenteUiStored;

		try {
			LOGGER.info("BEGIN");

			if (value != null) {
				dependenteColType = DependenteColType.parseByDescription(
						inputDependenteUi.getDependenteColsDef()
								.getNameColumn());
				if (DependenteColType.TP_DEPENDENTE.equals(dependenteColType)) {
					dependenteUi.setTpDependente(
							BeneficiarioType.parse((Integer) value));
				} else if (DependenteColType.NM_DEPENDENTE
						.equals(dependenteColType)) {
					dependenteUi.setNameDependente((String) value);
				} else if (DependenteColType.NR_CPF.equals(dependenteColType)) {
					dependenteUiStored = coParticipacaoContext
							.findDependenteByCpf(value.toString());

					if (dependenteUiStored != null) {
						LOGGER.info(
								"The dependente [{}] with cpf [{}] already exists:",
								dependenteUiStored.getNameDependente(),
								dependenteUiStored.getCpf());
						dependenteUi.setId(dependenteUiStored.getId());
						dependenteUi.setUserCreated(
								dependenteUiStored.getUserCreated());
						dependenteUi
								.setCreated(dependenteUiStored.getCreated());
						dependenteUi
								.setAltered(dependenteUiStored.getCreated());
					} else {
						dependenteUi.setUserCreated(
								coParticipacaoContext.getUser());
						dependenteUi.setUserAltered(
								coParticipacaoContext.getUser());
						dependenteUi.setCreated(LocalDateTime.now());
						dependenteUi.setAltered(LocalDateTime.now());
					}

					dependenteUi.setCpf(value.toString());
				} else if (DependenteColType.DT_NASCIMENTO
						.equals(dependenteColType)) {
					dependenteUi.setDtNascimento((LocalDate) value);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void storeInputTitularValue(
			CoParticipacaoContext coParticipacaoContext,
			TitularUi titularUi,
			InputTitularUi inputTitularUi,
			Object value) throws ServiceException {
		TitularColType titularColType;
		TitularUi titularUiStored;

		try {
			LOGGER.info("BEGIN");

			if (value != null) {
				titularColType = TitularColType.parseByDescription(
						inputTitularUi.getTitularColsDef().getNameColumn());
				if (TitularColType.NM_MATRICULA.equals(titularColType)) {
					titularUi.setMatricula((Integer) value);
				} else if (TitularColType.NM_TITULAR.equals(titularColType)) {
					titularUi.setNameTitular((String) value);
				} else if (TitularColType.NR_CPF.equals(titularColType)) {
					titularUiStored = coParticipacaoContext
							.findTitularByCpf(value.toString());

					if (titularUiStored != null) {
						LOGGER.info(
								"The titular [{}] with cpf [{}] already exists:",
								titularUiStored.getNameTitular(),
								titularUiStored.getCpf());
						titularUi.setId(titularUiStored.getId());
						titularUi.setUserCreated(
								titularUiStored.getUserCreated());
						titularUi.setUserAltered(
								coParticipacaoContext.getUser());
						titularUi.setCreated(titularUiStored.getCreated());
						titularUi.setAltered(titularUiStored.getCreated());
					} else {
						titularUi.setUserCreated(
								coParticipacaoContext.getUser());
						titularUi.setUserAltered(
								coParticipacaoContext.getUser());
						titularUi.setCreated(LocalDateTime.now());
						titularUi.setAltered(LocalDateTime.now());
					}

					titularUi.setCpf(value.toString());
				} else if (TitularColType.DT_NASCIMENTO
						.equals(titularColType)) {
					titularUi.setDtNascimento((LocalDate) value);
				} else if (TitularColType.DT_ADMISSAO.equals(titularColType)) {
					titularUi.setDtAdmissao((LocalDate) value);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public boolean validateLine(
			String line,
			CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		if (StringUtils.isNotBlank(line)) {
			return true;
		}

		return false;
	}

	public void afterProcess(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Process ending and sending data to database:");

			LOGGER.info("Storing titular and dependente data:");
			titularService
					.save(coParticipacaoContext.getBunker().getTitularUis());

			LOGGER.info("Storing desconhecidos data:");
			desconhecidoService.save(
					coParticipacaoContext.getBunker().getDesconhecidoUis());

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void beforeProcess(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
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

}

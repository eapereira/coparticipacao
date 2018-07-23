package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.LancamentoDetailService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {

	private static final Logger LOGGER = LogManager.getLogger(BeneficiarioServiceImpl.class);

	@Autowired
	private LancamentoDetailService lancamentoDetailService;

	public boolean validateBeneficiario(CoParticipacaoContext coParticipacaoContext, LancamentoUi lancamentoUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi;
		List<BeneficiarioColsUi> beneficiarioColsUis;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColsUis = coParticipacaoContext.getBeneficiarioColsUis();

			if (lancamentoUi.getTitular() == null) {
				if (!beneficiarioColsUis.isEmpty()) {
					beneficiarioUi = createBeneficiario(beneficiarioColsUis, lancamentoUi);

					if (validateBeneficiario(beneficiarioUi)) {
						if (BeneficiarioType.TITULAR.equals(beneficiarioUi.getType())) {
							lancamentoUi.setTitular(createTitular(coParticipacaoContext, beneficiarioUi));
						} else {
							dependenteUi = createDependente(coParticipacaoContext, beneficiarioUi);

							lancamentoUi.setTitular(dependenteUi.getTitular());
							lancamentoUi.setDependente(dependenteUi);
						}

						return true;
					}
				}
			} else {
				return true;
			}

			LOGGER.info("Não foram encontrados beneficiários para a linha [{}]", coParticipacaoContext.getCurrentLine());

			LOGGER.info("END");
			return false;
		} catch (TitularNotFoundException e) {
			LOGGER.info(e.getMessage());
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private DependenteUi createDependente(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException, TitularNotFoundException {
		DependenteUi dependenteUi;
		DependenteUi dependenteStored;
		TitularUi titularUi = null;

		try {
			LOGGER.info("BEGIN");

			dependenteUi = new DependenteUi();
			dependenteUi.setNameDependente(beneficiarioUi.getNameDependente());
			dependenteUi.setCpf(beneficiarioUi.getCpf().toString());
			dependenteUi.setTpDependente(beneficiarioUi.getType());
			dependenteUi.setMatricula(beneficiarioUi.getMatricula());

			if (beneficiarioUi.getMatriculaTitular() != null) {
				titularUi = coParticipacaoContext.findTitularByMatricula(beneficiarioUi.getMatriculaTitular());
			} else if (beneficiarioUi.getCpfTitular() != null) {
				titularUi = coParticipacaoContext.findTitularByCpf(beneficiarioUi.getCpfTitular().toString());
			} else if (StringUtils.isNotBlank(beneficiarioUi.getNameTitular())) {
				titularUi = coParticipacaoContext.findTitularByName(beneficiarioUi.getNameTitular());
			}

			if (titularUi == null) {
				dependenteStored = coParticipacaoContext.findDependenteByCpf(beneficiarioUi.getCpf().toString());

				if (dependenteStored != null) {
					dependenteUi.setId(dependenteStored.getId());
					titularUi = (TitularUi) dependenteStored.getTitular();
				}
			}

			if (titularUi != null) {
				titularUi.addDependente(dependenteUi);
			} else {
				LOGGER.info("Dependente [{}] does not have any Titular:", dependenteUi.getNameDependente());

				throw new TitularNotFoundException("Dependente [%s] does not have any Titular:", dependenteUi.getNameDependente());
			}

			dependenteUi.setUserAltered(coParticipacaoContext.getUser());
			dependenteUi.setUserCreated(coParticipacaoContext.getUser());

			LOGGER.info("END");
			return dependenteUi;
		} catch (TitularNotFoundException e) {
			LOGGER.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean validateBeneficiario(BeneficiarioUi beneficiarioUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (beneficiarioUi.getCpf() != null && StringUtils.isNotBlank(beneficiarioUi.getNameDependente())
					&& StringUtils.isNotBlank(beneficiarioUi.getNameTitular()) && beneficiarioUi.getMatricula() != null) {
				LOGGER.info(
						"All field need to create a Beneficiario are provided for [{}] with CPF[{}]:",
						beneficiarioUi.getNameDependente(),
						beneficiarioUi.getCpf());

				if (beneficiarioUi.getNameTitular().equals(beneficiarioUi.getNameDependente())) {
					beneficiarioUi.setType(BeneficiarioType.TITULAR);
				} else {
					beneficiarioUi.setType(BeneficiarioType.OUTROS);
				}

				return true;
			} else {
				if (beneficiarioUi.getMatriculaTitular() != null && StringUtils.isNotBlank(beneficiarioUi.getNameDependente())) {
					if (beneficiarioUi.getMatriculaTitular() != null && beneficiarioUi.getMatricula() == null) {
						beneficiarioUi.setType(BeneficiarioType.TITULAR);
					} else {
						beneficiarioUi.setType(BeneficiarioType.OUTROS);
					}

					return true;
				}
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private BeneficiarioUi createBeneficiario(List<BeneficiarioColsUi> beneficiarioColsUis, LancamentoUi lancamentoUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		Object value;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = new BeneficiarioUi();

			for (BeneficiarioColsUi beneficiarioColsUi : beneficiarioColsUis) {
				value = lancamentoDetailService.getFieldValue(beneficiarioColsUi.getArquivoInputColsDef(), lancamentoUi);

				if (value != null) {
					setValueField(beneficiarioColsUi.getBeneficiarioColType(), beneficiarioUi, value);
				}
			}

			LOGGER.info("END");
			return beneficiarioUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void setValueField(BeneficiarioColType beneficiarioColType, BeneficiarioUi beneficiarioUi, Object value) throws ServiceException {
		BeneficiarioType beneficiarioType;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Storing value[{}] to Beneficiario property [{}]", value, beneficiarioColType.getDescription());

			if (BeneficiarioColType.TP_BENEFICIARIO.equals(beneficiarioColType)) {
				if (value != null) {
					beneficiarioType = BeneficiarioType.parse((Integer) value);
				} else {
					beneficiarioType = BeneficiarioType.TITULAR;
				}

				beneficiarioUi.setType(beneficiarioType);
			} else if (BeneficiarioColType.NR_MATRICULA_DEPENDENTE.equals(beneficiarioColType)) {
				beneficiarioUi.setMatricula((Long) value);
			} else if (BeneficiarioColType.NR_CPF_DEPENDENTE.equals(beneficiarioColType)) {
				beneficiarioUi.setCpf((Long) value);
			} else if (BeneficiarioColType.NM_BENEFICARIO_TITULAR.equals(beneficiarioColType)) {
				beneficiarioUi.setNameTitular((String) value);
			} else if (BeneficiarioColType.NM_BENEFICIARIO_DEPENDENTE.equals(beneficiarioColType)) {
				beneficiarioUi.setNameDependente((String) value);
			} else if (BeneficiarioColType.NR_CPF_TITULAR.equals(beneficiarioColType)) {
				beneficiarioUi.setCpfTitular((Long) value);
			} else if (BeneficiarioColType.NR_MATRICULA_TITULAR.equals(beneficiarioColType)) {
				beneficiarioUi.setMatriculaTitular((Long) value);
			} else if (BeneficiarioColType.DT_NASCIMENTO.equals(beneficiarioColType)) {
				beneficiarioUi.setDtNascimento((LocalDate) value);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public BeneficiarioUi createBeneficiarioFromMecsas(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		List<BeneficiarioColsUi> beneficiarioColsUis;
		Object value;
		BeneficiarioUi beneficiarioUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColsUis = coParticipacaoContext.getBeneficiarioColsUis();

			LOGGER.info("Creating Beneficiario:");
			beneficiarioUi = new BeneficiarioUi();

			for (BeneficiarioColsUi beneficiarioColsUi : beneficiarioColsUis) {
				value = coParticipacaoContext.getColumnValue((ArquivoInputColsDefUi) beneficiarioColsUi.getArquivoInputColsDef());

				LOGGER.info("Retrieving value [{}] from column [{}]", value, beneficiarioColsUi.getArquivoInputColsDef().getNameColumn());

				setValueField(beneficiarioColsUi.getBeneficiarioColType(), beneficiarioUi, value);
			}

			LOGGER.info("END");
			return beneficiarioUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private Titular createTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi) throws ServiceException {
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			titularUi = new TitularUi();
			titularUi.setNameTitular(beneficiarioUi.getNameTitular());
			titularUi.setCpf(beneficiarioUi.getCpfTitular().toString());
			titularUi.setMatricula(beneficiarioUi.getMatriculaTitular());
			titularUi.setEmpresa(coParticipacaoContext.getEmpresaUi());

			titularUi.setUserAltered(coParticipacaoContext.getUser());
			titularUi.setUserCreated(coParticipacaoContext.getUser());

			coParticipacaoContext.addTitular(titularUi);

			LOGGER.info("END");
			return titularUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public TitularUi createTitular(BeneficiarioUi beneficiarioUi, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		TitularUi titularUi = null;

		try {
			LOGGER.info("BEGIN");

			if (beneficiarioUi.getCpfTitular() != null) {
				titularUi = coParticipacaoContext.findTitularByCpf(beneficiarioUi.getCpfTitular().toString());
			}

			if (titularUi == null) {
				titularUi = new TitularUi();

				if (beneficiarioUi.getCpfTitular() != null) {
					titularUi.setCpf(beneficiarioUi.getCpfTitular().toString());
				}

				titularUi.setMatricula(beneficiarioUi.getMatriculaTitular());
				titularUi.setNameTitular(beneficiarioUi.getNameTitular());
				titularUi.setDtAdmissao(beneficiarioUi.getDtAdmissao());
				titularUi.setDtNascimento(beneficiarioUi.getDtNascimento());

				titularUi.setUserAltered(coParticipacaoContext.getUser());
				titularUi.setUserCreated(coParticipacaoContext.getUser());
			} else {
				titularUi.setUserAltered(coParticipacaoContext.getUser());
			}

			LOGGER.info("END");
			return titularUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public DependenteUi createDependente(BeneficiarioUi beneficiarioUi, CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		DependenteUi dependenteUi = null;

		try {
			LOGGER.info("BEGIN");

			if (beneficiarioUi.getCpfTitular() != null) {
				dependenteUi = coParticipacaoContext.findDependenteByCpf(beneficiarioUi.getCpfTitular().toString());
			}

			if (dependenteUi == null) {
				dependenteUi = new DependenteUi();

				if (beneficiarioUi.getCpfTitular() != null) {
					dependenteUi.setCpf(beneficiarioUi.getCpfTitular().toString());
				}

				dependenteUi.setDtNascimento(beneficiarioUi.getDtNascimento());
				dependenteUi.setNameDependente(beneficiarioUi.getNameTitular());
				dependenteUi.setMatricula(beneficiarioUi.getMatriculaTitular());
				dependenteUi.setTpDependente(beneficiarioUi.getType());

				dependenteUi.setUserAltered(coParticipacaoContext.getUser());
				dependenteUi.setUserCreated(coParticipacaoContext.getUser());
			} else {
				dependenteUi.setUserAltered(coParticipacaoContext.getUser());
			}

			LOGGER.info("END");
			return dependenteUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

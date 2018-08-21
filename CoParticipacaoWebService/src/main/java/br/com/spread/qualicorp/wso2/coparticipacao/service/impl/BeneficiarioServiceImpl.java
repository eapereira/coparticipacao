package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.BeneficiarioNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.DependenteDuplicated;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularDuplicated;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
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

	@Autowired
	private EmpresaService empresaService;

	public boolean validateBeneficiario(CoParticipacaoContext coParticipacaoContext, LancamentoUi lancamentoUi)
			throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi;
		List<BeneficiarioColsUi> beneficiarioColsUis;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColsUis = coParticipacaoContext.getBeneficiarioColsUis();

			if (lancamentoUi.getTitular() == null) {
				if (!beneficiarioColsUis.isEmpty()) {
					beneficiarioUi = createBeneficiario(beneficiarioColsUis, lancamentoUi);

					/*
					 * Guardando para caso seja inválidado, criar um
					 * Desconhecido:
					 */
					coParticipacaoContext.setBeneficiarioUi(beneficiarioUi);

					LOGGER.info(
							"Validating Dependente[{}] with NR_CPF[{}] and using NR_MATRICULA[{}]:",
							beneficiarioUi.getNameBeneficiario(),
							beneficiarioUi.getCpf(),
							beneficiarioUi.getMatricula());

					// Apenas o MECSAS tem o Tipo de Beneficiário:
					if (isTitular(coParticipacaoContext, beneficiarioUi)) {
						lancamentoUi.setTitular(createTitular(coParticipacaoContext, beneficiarioUi));
					} else {
						dependenteUi = createDependente(coParticipacaoContext, beneficiarioUi);

						if (dependenteUi != null) {
							lancamentoUi.setTitular(dependenteUi.getTitular());
							lancamentoUi.setDependente(dependenteUi);
						}
					}
				} else {
					LOGGER.info(
							"BeneficiarioCols must be mapped to this ArquivoInput, otherwise we cannot read Beneficiario data:");
				}
			}

			if (lancamentoUi.getTitular() != null) {
				LOGGER.info("END");
				return true;
			} else {
				LOGGER.info(
						"Não foram encontrados beneficiários para a linha [{}]",
						coParticipacaoContext.getCurrentLine());

				LOGGER.info("END");
				return false;
			}
		} catch (BeneficiarioNotFoundException e) {
			LOGGER.info(e.getMessage());
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean validateBeneficiario(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (beneficiarioUi.getType() == null) {
				if (isTitular(coParticipacaoContext, beneficiarioUi)) {
					beneficiarioUi.setType(BeneficiarioType.TITULAR);
				} else {
					beneficiarioUi.setType(BeneficiarioType.OUTROS);
				}
			}

			if (BeneficiarioType.TITULAR.equals(beneficiarioUi.getType())) {
				if (StringUtils.isNotBlank(beneficiarioUi.getNameBeneficiario()) && beneficiarioUi.getCpf() != null
						&& beneficiarioUi.getMatricula() != null) {
					return true;
				}
			} else {
				if (StringUtils.isNotBlank(beneficiarioUi.getNameBeneficiario())
						&& beneficiarioUi.getMatricula() != null) {
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

	private BeneficiarioUi createBeneficiario(List<BeneficiarioColsUi> beneficiarioColsUis, LancamentoUi lancamentoUi)
			throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		Object value;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = new BeneficiarioUi();

			for (BeneficiarioColsUi beneficiarioColsUi : beneficiarioColsUis) {
				value = lancamentoDetailService
						.getFieldValue(beneficiarioColsUi.getArquivoInputColsDef(), lancamentoUi);

				LOGGER.debug(
						"Building Beneficiario from Lancamento field [{}] with value [{}]:",
						beneficiarioColsUi.getArquivoInputColsDef().getNameColumn(),
						value);

				if (value != null) {
					if (isNotZero(value)) {
						setValueField(beneficiarioColsUi.getBeneficiarioColType(), beneficiarioUi, value);
					}
				}
			}

			LOGGER.info("END");
			return beneficiarioUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean isNotZero(Object value) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (value == null) {
				return false;
			} else if (value instanceof String) {
				if (StringUtils.isBlank((String) value)) {
					return false;
				} else if ("0".equals(value) || "0.0".equals(value)) {
					return false;
				}
			} else if (NumberUtils.INTEGER_ZERO.equals(value)) {
				return false;
			} else if (NumberUtils.LONG_ZERO.equals(value)) {
				return false;
			} else if (NumberUtils.DOUBLE_ZERO.equals(value)) {
				return false;
			}

			LOGGER.info("END");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void setValueField(BeneficiarioColType beneficiarioColType, BeneficiarioUi beneficiarioUi, Object value)
			throws ServiceException {
		BeneficiarioType beneficiarioType;

		try {
			LOGGER.info("BEGIN");

			if (value != null) {
				LOGGER.info(
						"Storing value[{}] to Beneficiario property [{}]",
						value,
						beneficiarioColType.getDescription());

				if (BeneficiarioColType.TP_BENEFICIARIO.equals(beneficiarioColType)) {
					beneficiarioType = BeneficiarioType.parse((Integer) value);
					beneficiarioUi.setType(beneficiarioType);
				} else if (BeneficiarioColType.NR_MATRICULA.equals(beneficiarioColType)) {
					beneficiarioUi.setMatricula((Long) value);
				} else if (BeneficiarioColType.NR_CPF.equals(beneficiarioColType)) {
					beneficiarioUi.setCpf((Long) value);
				} else if (BeneficiarioColType.NM_BENEFICIARIO.equals(beneficiarioColType)) {
					beneficiarioUi.setNameBeneficiario(((String) value));
				} else if (BeneficiarioColType.DT_NASCIMENTO.equals(beneficiarioColType)) {
					beneficiarioUi.setDtNascimento((LocalDate) value);
				} else if (BeneficiarioColType.DT_ADMISSAO.equals(beneficiarioColType)) {
					beneficiarioUi.setDtAdmissao((LocalDate) value);
				} else if (BeneficiarioColType.NM_LABEL.equals(beneficiarioColType)) {
					beneficiarioUi.setLabel((String) value);
				} else if (BeneficiarioColType.NR_REFERENCE_CODE.equals(beneficiarioColType)) {
					beneficiarioUi.setReferenceCode((Long) value);
				} else if (BeneficiarioColType.NR_DIGITO_CPF.equals(beneficiarioColType)) {
					beneficiarioUi.setDigitoCpf((Integer) value);
				} else if (BeneficiarioColType.NR_MATRICULA_EMPRESA.equals(beneficiarioColType)) {
					beneficiarioUi.setMatriculaEmpresa((Long) value);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public BeneficiarioUi createBeneficiarioFromMecsas(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		List<BeneficiarioColsUi> beneficiarioColsUis;
		Object value;
		BeneficiarioUi beneficiarioUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColsUis = coParticipacaoContext.getBeneficiarioColsUis();

			LOGGER.info("Creating Beneficiario:");
			beneficiarioUi = new BeneficiarioUi();

			for (BeneficiarioColsUi beneficiarioColsUi : beneficiarioColsUis) {
				value = coParticipacaoContext
						.getColumnValue((ArquivoInputColsDefUi) beneficiarioColsUi.getArquivoInputColsDef());

				LOGGER.info(
						"Retrieving value [{}] from column [{}]",
						value,
						beneficiarioColsUi.getArquivoInputColsDef().getNameColumn());

				if (isNotZero(value)) {
					setValueField(beneficiarioColsUi.getBeneficiarioColType(), beneficiarioUi, value);
				}
			}

			LOGGER.info(
					"Current Beneficiario [{}] with MATRICULA [{}] and CPF[{}]:",
					beneficiarioUi.getNameBeneficiario(),
					beneficiarioUi.getMatricula(),
					beneficiarioUi.getCpf());

			if (isTitular(coParticipacaoContext, beneficiarioUi)) {
				LOGGER.info(
						"Beneficiario [{}] with MATRICULA [{}] and CPF[{}] is Titular:",
						beneficiarioUi.getNameBeneficiario(),
						beneficiarioUi.getMatricula(),
						beneficiarioUi.getCpf());

				beneficiarioUi.setType(BeneficiarioType.TITULAR);
			} else {
				if (!UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
					beneficiarioUi.setType(BeneficiarioType.OUTROS);
				}
			}

			LOGGER.info("END");
			return beneficiarioUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private boolean isTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		ContratoUi contratoUi;
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			contratoUi = coParticipacaoContext.getContratoUi();

			if (UseType.MECSAS.equals(contratoUi.getUseType())) {
				if (BeneficiarioType.TITULAR.equals(beneficiarioUi.getType()) || beneficiarioUi.getType() == null) {
					LOGGER.info("END");
					return true;
				}
			} else {
				titularUi = findTitular(coParticipacaoContext, beneficiarioUi);

				if (UseType.NAO_LOCALIZADO.equals(contratoUi.getUseType())) {
					if (titularUi == null) {
						titularUi = coParticipacaoContext.findTitularByMatricula(beneficiarioUi.getMatricula());

						/*
						 * Se o Beneficiário não for um Titular e não existe no
						 * banco de dados e também não possuir um Títular, então
						 * ele próprio é um Títular, pois não foi encontrado
						 * pelo processo MECSAS e FATUCOPA:
						 */
						if (titularUi == null) {
							LOGGER.info("END");
							return true;
						}
					} else {
						LOGGER.info("END");
						return true;
					}
				} else {
					/*
					 * Se o beneficiario tiver um títular, então ele é
					 * dependente:
					 */
					if (titularUi != null) {
						LOGGER.info("END");
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

	public TitularUi createTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException, TitularDuplicated {
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			titularUi = findTitular(coParticipacaoContext, beneficiarioUi);

			if (titularUi == null) {
				if (empresaService.canAutomaticallyCreateBeneficiario(coParticipacaoContext)) {
					if (validateBeneficiario(coParticipacaoContext, beneficiarioUi)) {
						/*
						 * Vamos verificar se já não existe um títular com o
						 * mesmo CPF:
						 */
						if (!isTitularCpfInUse(coParticipacaoContext, beneficiarioUi)) {
							LOGGER.info(
									"Titular [{}] with CPF [{}] and Matricula [{}] will be created:",
									beneficiarioUi.getNameBeneficiario(),
									beneficiarioUi.getCpf(),
									beneficiarioUi.getMatricula());

							titularUi = new TitularUi();
							titularUi.setNameTitular(beneficiarioUi.getNameBeneficiario());

							if (!NumberUtils.LONG_ZERO.equals(beneficiarioUi.getCpf())) {
								if (beneficiarioUi.getDigitoCpf() != null) {
									titularUi.setCpf(concatTitularCpf(beneficiarioUi));
								} else {
									titularUi.setCpf(beneficiarioUi.getCpf());
								}
							}

							titularUi.setMatricula(beneficiarioUi.getMatricula());
							titularUi.setMatriculaEmpresa(beneficiarioUi.getMatriculaEmpresa());
							titularUi.setDtNascimento(beneficiarioUi.getDtNascimento());
							titularUi.setDtAdmissao(beneficiarioUi.getDtAdmissao());
							titularUi.setEmpresa(coParticipacaoContext.getEmpresaUi());

							titularUi.setUserAltered(coParticipacaoContext.getUser());
							titularUi.setUserCreated(coParticipacaoContext.getUser());

							coParticipacaoContext.getTitularUis().add(titularUi);
							coParticipacaoContext.addTitular(titularUi);
						}
					}
				}
			} else {
				if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
					coParticipacaoContext.setTitularUi(titularUi);

					/*
					 * Se estamos num processo MECSAS e encontramos o Titular,
					 * então significa que ele esta duplicado no arquivo de
					 * entrada e já foi processado:
					 */
					throw new TitularDuplicated(beneficiarioUi);
				}

				updateTitular(coParticipacaoContext, titularUi, beneficiarioUi);
			}

			LOGGER.info("END");
			return titularUi;
		} catch (TitularDuplicated e) {
			LOGGER.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean isTitularCpfInUse(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			titularUi = coParticipacaoContext.findTitularByCpf(beneficiarioUi.getCpf());

			if (titularUi != null) {
				LOGGER.info(
						"There's already a Titular using NR_CPF[{}] and NR_MATRICULA[{}]:",
						titularUi.getCpf(),
						titularUi.getMatricula());
				return true;
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public DependenteUi createDependente(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException, BeneficiarioNotFoundException, DependenteDuplicated {
		DependenteUi dependenteUi = null;
		TitularUi titularUi = null;

		try {
			LOGGER.info("BEGIN");

			dependenteUi = findDependente(coParticipacaoContext, beneficiarioUi);

			if (dependenteUi == null) {
				if (empresaService.canAutomaticallyCreateBeneficiario(coParticipacaoContext)) {
					if (validateBeneficiario(coParticipacaoContext, beneficiarioUi)) {
						if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
							titularUi = coParticipacaoContext.getTitularUi();
						} else {
							titularUi = coParticipacaoContext.findTitularByMatricula(beneficiarioUi.getMatricula());
						}

						if (titularUi != null) {
							LOGGER.info(
									"Using Titular [{}] with Matricula [{}] and CPF [{}]:",
									titularUi.getNameTitular(),
									titularUi.getMatricula(),
									titularUi.getCpf());

							LOGGER.info(
									"Adding Dependente [{}] with CPF [{}] and Matricula [{}] not found in database:",
									beneficiarioUi.getNameBeneficiario(),
									beneficiarioUi.getCpf(),
									beneficiarioUi.getMatricula());

							dependenteUi = new DependenteUi();
							dependenteUi.setNameDependente(beneficiarioUi.getNameBeneficiario());

							if (beneficiarioUi.getDigitoCpf() != null) {
								dependenteUi.setCpf(concatDependenteCpf(beneficiarioUi));
							} else {
								dependenteUi.setCpf(beneficiarioUi.getCpf());
							}

							dependenteUi.setTpDependente(beneficiarioUi.getType());
							dependenteUi.setMatricula(beneficiarioUi.getMatricula());
							dependenteUi.setDtNascimento(beneficiarioUi.getDtNascimento());

							dependenteUi.setUserAltered(coParticipacaoContext.getUser());
							dependenteUi.setUserCreated(coParticipacaoContext.getUser());

							titularUi.addDependente(dependenteUi);
							coParticipacaoContext.getDependenteUis().add(dependenteUi);
							coParticipacaoContext.addDependente(dependenteUi);
						}
					}
				} else {
					throw new BeneficiarioNotFoundException(beneficiarioUi);
				}
			} else {
				if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
					/*
					 * Se estamos num processo MECSAS e encontramos o
					 * Dependente, então significa que ele esta duplicado no
					 * arquivo de entrada e já foi processado:
					 */
					throw new DependenteDuplicated(beneficiarioUi);
				}
			}

			if (dependenteUi == null) {
				LOGGER.info("Dependente [{}] does not have any Titular:", beneficiarioUi.getNameBeneficiario());
			} else {
				updateDependente(coParticipacaoContext, dependenteUi, beneficiarioUi);
			}

			LOGGER.info("END");
			return dependenteUi;
		} catch (DependenteDuplicated e) {
			LOGGER.info(e.getMessage());
			throw e;
		} catch (BeneficiarioNotFoundException e) {
			LOGGER.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private Long concatTitularCpf(BeneficiarioUi beneficiarioUi) throws ServiceException {
		Long cpf;

		try {
			LOGGER.info("BEGIN");

			cpf = (beneficiarioUi.getCpf() * 100) + beneficiarioUi.getDigitoCpf();

			LOGGER.debug(
					"Binding the CPF [{}] to its digit [{}] to form a complete number [{}]:",
					beneficiarioUi.getCpf(),
					beneficiarioUi.getDigitoCpf(),
					cpf);

			LOGGER.info("END");
			return cpf;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private Long concatDependenteCpf(BeneficiarioUi beneficiarioUi) throws ServiceException {
		Long cpf;

		try {
			LOGGER.info("BEGIN");

			cpf = (beneficiarioUi.getCpf() * 100) + beneficiarioUi.getDigitoCpf();

			LOGGER.debug(
					"Binding the CPF [{}] to its digit [{}] to form a complete number [{}]:",
					beneficiarioUi.getCpf(),
					beneficiarioUi.getDigitoCpf(),
					cpf);

			LOGGER.info("END");
			return cpf;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void updateTitular(
			CoParticipacaoContext coParticipacaoContext,
			TitularUi titularUi,
			BeneficiarioUi beneficiarioUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (UseType.MECSAS2.equals(coParticipacaoContext.getContratoUi().getUseType())
					|| UseType.NAO_LOCALIZADO.equals(coParticipacaoContext.getContratoUi().getUseType())) {
				if (!NumberUtils.LONG_ZERO.equals(beneficiarioUi.getCpf())) {
					LOGGER.debug("Updating Titular field CPF with value [{}]:", beneficiarioUi.getCpf());
					titularUi.setCpf(beneficiarioUi.getCpf());
				}

				if (beneficiarioUi.getDtNascimento() != null) {
					LOGGER.debug(
							"Updating Titular field DT_NASCIMENTO with value [{}]:",
							beneficiarioUi.getDtNascimento());
					titularUi.setDtNascimento(beneficiarioUi.getDtNascimento());
				}

				if (beneficiarioUi.getDtAdmissao() != null) {
					LOGGER.debug("Updating Titular field DT_ADMISSAO with value [{}]:", beneficiarioUi.getDtAdmissao());
					titularUi.setDtAdmissao(beneficiarioUi.getDtAdmissao());
				}

				if (StringUtils.isNotBlank(beneficiarioUi.getLabel())) {
					LOGGER.debug("Updating Titular field LABEL with value [{}]:", beneficiarioUi.getLabel());
					titularUi.setLabel(beneficiarioUi.getLabel());
				}

				if (beneficiarioUi.getReferenceCode() != null) {
					LOGGER.debug(
							"Updating Titular field NR_REF_CODE with value [{}]:",
							beneficiarioUi.getReferenceCode());
					titularUi.setReferenceCode(beneficiarioUi.getReferenceCode());
				}

				if (beneficiarioUi.getMatriculaEmpresa() != null) {
					LOGGER.debug(
							"Updating Titular field NR_MATRICULA_EMPRESA with value [{}]:",
							beneficiarioUi.getMatriculaEmpresa());
					titularUi.setMatriculaEmpresa(beneficiarioUi.getMatriculaEmpresa());
				}

				if (titularUi.getId() != null) {
					titularUi.setUserAltered(coParticipacaoContext.getUser());

					coParticipacaoContext.addTitular(titularUi);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void updateDependente(
			CoParticipacaoContext coParticipacaoContext,
			DependenteUi dependenteUi,
			BeneficiarioUi beneficiarioUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (UseType.MECSAS2.equals(coParticipacaoContext.getContratoUi().getUseType())
					|| UseType.NAO_LOCALIZADO.equals(coParticipacaoContext.getContratoUi().getUseType())) {
				if (!NumberUtils.LONG_ZERO.equals(beneficiarioUi.getCpf())) {
					LOGGER.debug("Updating Dependente field CPF with value [{}]:", beneficiarioUi.getCpf());
					dependenteUi.setCpf(beneficiarioUi.getCpf());
				}

				if (beneficiarioUi.getDtNascimento() != null) {
					LOGGER.debug(
							"Updating Dependente field DT_NASCIMENTO with value [{}]:",
							beneficiarioUi.getDtNascimento());
					dependenteUi.setDtNascimento(beneficiarioUi.getDtNascimento());
				}

				if (StringUtils.isNotBlank(beneficiarioUi.getLabel())) {
					LOGGER.debug("Updating Dependente field LABEL with value [{}]:", beneficiarioUi.getLabel());
					dependenteUi.setLabel(beneficiarioUi.getLabel());
				}

				if (beneficiarioUi.getReferenceCode() != null) {
					LOGGER.debug(
							"Updating Dependente field NR_REF_CODE with value [{}]:",
							beneficiarioUi.getReferenceCode());
					dependenteUi.setReferenceCode(beneficiarioUi.getReferenceCode());
				}

				if (beneficiarioUi.getMatriculaEmpresa() != null) {
					LOGGER.debug(
							"Updating Dependente field NR_MATRICULA_EMPRESA with value [{}]:",
							beneficiarioUi.getMatriculaEmpresa());
					dependenteUi.setMatriculaEmpresa(beneficiarioUi.getMatriculaEmpresa());
				}

				if (dependenteUi.getId() != null) {
					dependenteUi.setUserAltered(coParticipacaoContext.getUser());

					coParticipacaoContext.addDependente(dependenteUi);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private TitularUi findTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		TitularUi titularUi = null;

		try {
			LOGGER.info("BEGIN");

			titularUi = coParticipacaoContext
					.findTitularByCpfAndName(beneficiarioUi.getCpf(), beneficiarioUi.getNameBeneficiario());

			if (titularUi == null) {
				titularUi = coParticipacaoContext.findTitularByMatriculaAndName(
						beneficiarioUi.getMatricula(),
						beneficiarioUi.getNameBeneficiario());
			}

			LOGGER.info("END");
			return titularUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public DependenteUi findDependente(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		DependenteUi dependenteUi = null;

		try {
			LOGGER.info("BEGIN");

			dependenteUi = coParticipacaoContext
					.findDependenteByCpfAndName(beneficiarioUi.getCpf(), beneficiarioUi.getNameBeneficiario());

			if (dependenteUi == null) {
				dependenteUi = coParticipacaoContext.findDependenteByMatriculaAndName(
						beneficiarioUi.getMatricula(),
						beneficiarioUi.getNameBeneficiario());
			}

			LOGGER.info("END");
			return dependenteUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}

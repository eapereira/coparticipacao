package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CreateBeneficiarioType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DadosBancarios;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Endereco;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.EnderecoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.GrauEscolaridadeType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Rg;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.SexoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Telefone;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Transferencia;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UFType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterColumnUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.BeneficiarioNotFoundException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.DependenteDuplicated;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.TitularDuplicated;
import br.com.spread.qualicorp.wso2.coparticipacao.service.BeneficiarioService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.spreadsheet.NumberUtils2;
import br.com.spread.qualicorp.wso2.coparticipacao.util.BeneficiarioDetailHelper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {

	private static final Logger LOGGER = LogManager.getLogger(BeneficiarioServiceImpl.class);

	@Autowired
	private EmpresaService empresaService;

	public boolean validateBeneficiario(
			CoParticipacaoContext coParticipacaoContext,
			LancamentoDetailUi lancamentoDetailUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");

			if (lancamentoDetailUi.getTitularUi() == null) {
				beneficiarioUi = createBeneficiario(coParticipacaoContext, lancamentoDetailUi);

				/*
				 * Guardando para caso seja inválidado, criar um Desconhecido:
				 */
				coParticipacaoContext.setBeneficiarioUi(beneficiarioUi);

				LOGGER.info(
						"Validating Beneficiario[{}] with NR_CPF[{}] and using NR_MATRICULA[{}]:",
						beneficiarioUi.getNameBeneficiario(),
						beneficiarioUi.getCpf(),
						beneficiarioUi.getMatricula());

				// Apenas o MECSAS tem o Tipo de Beneficiário:
				if (isTitular(coParticipacaoContext, beneficiarioUi)) {
					lancamentoDetailUi.setTitularUi(createTitular(coParticipacaoContext, beneficiarioUi));
				} else {
					dependenteUi = createDependente(coParticipacaoContext, beneficiarioUi);

					if (dependenteUi != null) {
						lancamentoDetailUi.setTitularUi((TitularUi) dependenteUi.getTitular());
						lancamentoDetailUi.setDependenteUi(dependenteUi);
					}
				}
			}

			if (lancamentoDetailUi.getTitularUi() != null) {
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
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();

			if (beneficiarioUi.getType() == null) {
				if (isTitular(coParticipacaoContext, beneficiarioUi)) {
					beneficiarioUi.setType(BeneficiarioType.TITULAR);
				} else {
					beneficiarioUi.setType(BeneficiarioType.OUTROS);
				}
			}

			if (BeneficiarioType.TITULAR.equals(beneficiarioUi.getType())) {
				if ((StringUtils.isNotBlank(beneficiarioUi.getNameTitular())
						|| StringUtils.isNotBlank(beneficiarioUi.getNameBeneficiario()))
						&& beneficiarioUi.getMatricula() != null) {
					if (empresaUi.isAcceptTitularWithoutCpf()) {
						LOGGER.info("END");
						return true;
					} else {
						if (beneficiarioUi.getCpf() != null) {
							if (!NumberUtils.LONG_ZERO.equals(beneficiarioUi.getCpf())) {
								LOGGER.info("END");
								return true;
							}
						}
					}
				}
			} else {
				if (StringUtils.isNotBlank(beneficiarioUi.getNameBeneficiario())
						&& beneficiarioUi.getMatricula() != null) {
					LOGGER.info("END");
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

	private BeneficiarioUi createBeneficiario(
			CoParticipacaoContext coParticipacaoContext,
			LancamentoDetailUi lancamentoDetailUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = new BeneficiarioUi();
			beneficiarioUi.setCpf(lancamentoDetailUi.getCpf());
			beneficiarioUi.setMatricula(lancamentoDetailUi.getMatriculaDependente());
			beneficiarioUi.setMatriculaTitular(lancamentoDetailUi.getMatriculaTitular());
			beneficiarioUi.setMatriculaEmpresa(lancamentoDetailUi.getMatriculaEmpresa());
			beneficiarioUi.setNameBeneficiario(lancamentoDetailUi.getNameBeneficiario());
			beneficiarioUi.setNameTitular(lancamentoDetailUi.getNameTitular());
			beneficiarioUi.setDtNascimento(lancamentoDetailUi.getDtNascimento());

			beneficiarioUi.setContrato(lancamentoDetailUi.getContratoUi());
			beneficiarioUi.setCdContrato(lancamentoDetailUi.getCdContrato());

			beneficiarioUi.setContrato(lancamentoDetailUi.getContratoUi());
			beneficiarioUi.setCdContrato(lancamentoDetailUi.getCdContrato());

			beneficiarioUi.getBeneficiarioDetail().setCdUsuario(lancamentoDetailUi.getCdUsuario());
			beneficiarioUi.getBeneficiarioDetail().setSubFatura(lancamentoDetailUi.getSubFatura());
			beneficiarioUi.getBeneficiarioDetail().setMatriculaEspecial(lancamentoDetailUi.getMatriculaEspecial());
			beneficiarioUi.getBeneficiarioDetail().setLocal(lancamentoDetailUi.getLocal());

			if (beneficiarioUi.getMatricula() == null) {
				beneficiarioUi.setMatricula(beneficiarioUi.getMatriculaTitular());
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
					beneficiarioUi.setMatriculaTitular((Long) value);
				} else if (BeneficiarioColType.NR_CPF.equals(beneficiarioColType)) {
					beneficiarioUi.setCpf((Long) value);
				} else if (BeneficiarioColType.NR_CPF_DEPENDENTE.equals(beneficiarioColType)) {
					beneficiarioUi.setCpf((Long) value);
				} else if (BeneficiarioColType.NM_BENEFICIARIO.equals(beneficiarioColType)) {
					beneficiarioUi.setNameBeneficiario(((String) value));
				} else if (BeneficiarioColType.NM_TITULAR.equals(beneficiarioColType)) {
					beneficiarioUi.setNameTitular(((String) value));
				} else if (BeneficiarioColType.DT_NASCIMENTO.equals(beneficiarioColType)) {
					beneficiarioUi.setDtNascimento((LocalDate) value);
				} else if (BeneficiarioColType.DT_ADMISSAO.equals(beneficiarioColType)) {
					beneficiarioUi.setDtAdmissao((LocalDate) value);
				} else if (BeneficiarioColType.DT_DEMISSAO.equals(beneficiarioColType)) {
					beneficiarioUi.setDtDemissao((LocalDate) value);
				} else if (BeneficiarioColType.NM_LABEL.equals(beneficiarioColType)) {
					beneficiarioUi.setLabel((String) value);
				} else if (BeneficiarioColType.NR_REFERENCE_CODE.equals(beneficiarioColType)) {
					beneficiarioUi.setReferenceCode((Long) value);
				} else if (BeneficiarioColType.NR_DIGITO_CPF.equals(beneficiarioColType)) {
					beneficiarioUi.setDigitoCpf((Integer) value);
				} else if (BeneficiarioColType.NR_MATRICULA_EMPRESA.equals(beneficiarioColType)) {
					beneficiarioUi.setMatriculaEmpresa((Long) value);
				} else if (BeneficiarioColType.CD_CONTRATO.equals(beneficiarioColType)) {
					beneficiarioUi.setCdContrato((String) value);
				} else {
					defineBeneficiarioDetails(beneficiarioUi, beneficiarioColType, value);
				}
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void defineBeneficiarioDetails(
			BeneficiarioUi beneficiarioUi,
			BeneficiarioColType beneficiarioColType,
			Object value) throws ServiceException {
		BeneficiarioDetail beneficiarioDetail = null;
		Telefone telefone;
		Endereco endereco;
		Rg rg;
		DadosBancarios dadosBancarios;
		Transferencia transferencia;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("BeneficiarioDetail.[{}] with value [{}]:", beneficiarioColType.getDescription(), value);

			if (beneficiarioUi.getBeneficiarioDetail() == null) {
				beneficiarioDetail = new BeneficiarioDetail();
				beneficiarioUi.setBeneficiarioDetail(beneficiarioDetail);
			} else {
				beneficiarioDetail = beneficiarioUi.getBeneficiarioDetail();
			}

			telefone = beneficiarioDetail.getTelefone();
			rg = beneficiarioDetail.getRg();
			dadosBancarios = beneficiarioDetail.getDadosBancarios();
			transferencia = beneficiarioDetail.getTransferencia();
			endereco = beneficiarioDetail.getEndereco();

			if (BeneficiarioColType.DF.equals(beneficiarioColType)) {
				beneficiarioDetail.setDf((Integer) value);
			} else if (BeneficiarioColType.RDP.equals(beneficiarioColType)) {
				beneficiarioDetail.setRdp((Integer) value);
			} else if (BeneficiarioColType.NR_LOCAL.equals(beneficiarioColType)) {
				beneficiarioDetail.setLocal((Integer) value);
			} else if (BeneficiarioColType.CD_CATEGORIA.equals(beneficiarioColType)) {
				beneficiarioDetail.setCategoria((Integer) value);
			} else if (BeneficiarioColType.SETOR.equals(beneficiarioColType)) {
				beneficiarioDetail.setSetor((String) value);
			} else if (BeneficiarioColType.ES.equals(beneficiarioColType)) {
				beneficiarioDetail.setEs((String) value);
			} else if (BeneficiarioColType.CD_PLANO.equals(beneficiarioColType)) {
				beneficiarioDetail.setPlano((String) value);
			} else if (BeneficiarioColType.DT_INCLUSAO.equals(beneficiarioColType)) {
				beneficiarioDetail.setDtInclusao((LocalDate) value);
			} else if (BeneficiarioColType.SEXO.equals(beneficiarioColType)) {
				beneficiarioDetail.setSexo(SexoType.parse((String) value));
			} else if (BeneficiarioColType.PERMANENCIA.equals(beneficiarioColType)) {
				beneficiarioDetail.setPermanencia((String) value);
			} else if (BeneficiarioColType.DT_REF.equals(beneficiarioColType)) {
				beneficiarioDetail.setDtReferencia((LocalDate) value);
			} else if (BeneficiarioColType.BANCO.equals(beneficiarioColType)) {
				dadosBancarios.setBanco((Integer) value);
			} else if (BeneficiarioColType.AGENCIA.equals(beneficiarioColType)) {
				dadosBancarios.setAgencia((String) value);
			} else if (BeneficiarioColType.AGENCIA_DG.equals(beneficiarioColType)) {
				dadosBancarios.setAgenciaDg((String) value);
			} else if (BeneficiarioColType.CONTA_CORRENTE.equals(beneficiarioColType)) {
				dadosBancarios.setContaCorrente((String) value);
			} else if (BeneficiarioColType.CPF_CC.equals(beneficiarioColType)) {
				dadosBancarios.setCpf((Long) value);
			} else if (BeneficiarioColType.NM_TITULAR_CC.equals(beneficiarioColType)) {
				dadosBancarios.setNameTitular((String) value);
			} else if (BeneficiarioColType.CD_CARDIRF.equals(beneficiarioColType)) {
				beneficiarioDetail.setCardif((String) value);
			} else if (BeneficiarioColType.NR_CEP.equals(beneficiarioColType)) {
				endereco.setCep((Integer) value);
			} else if (BeneficiarioColType.TP_LOGRADOURO.equals(beneficiarioColType)) {
				endereco.setType(EnderecoType.parse((String) value));
			} else if (BeneficiarioColType.NM_LOGRADOURO.equals(beneficiarioColType)) {
				endereco.setLogradouro((String) value);
			} else if (BeneficiarioColType.NUM.equals(beneficiarioColType)) {
				endereco.setNumero((Integer) value);
			} else if (BeneficiarioColType.COMPL.equals(beneficiarioColType)) {
				endereco.setComplemento((String) value);
			} else if (BeneficiarioColType.BAIRRO.equals(beneficiarioColType)) {
				endereco.setBairro((String) value);
			} else if (BeneficiarioColType.MUNICIPIO.equals(beneficiarioColType)) {
				endereco.setMunicipio((String) value);
			} else if (BeneficiarioColType.UF.equals(beneficiarioColType)) {
				endereco.setUf(UFType.parse((String) value));
			} else if (BeneficiarioColType.TEL_RESIDENCIAL.equals(beneficiarioColType)) {
				telefone.setResidencial((Long) value);
			} else if (BeneficiarioColType.TEL_COMERCIAL.equals(beneficiarioColType)) {
				telefone.setComercial((Long) value);
			} else if (BeneficiarioColType.TEL_CELULAR.equals(beneficiarioColType)) {
				telefone.setCelular((Long) value);
			} else if (BeneficiarioColType.NM_MAE.equals(beneficiarioColType)) {
				rg.setMae((String) value);
			} else if (BeneficiarioColType.NR_RG.equals(beneficiarioColType)) {
				rg.setNumero((String) value);
			} else if (BeneficiarioColType.ORGAO_EMISSOR_RG.equals(beneficiarioColType)) {
				rg.setOrgaoEmissao((String) value);
			} else if (BeneficiarioColType.PAIS_RG.equals(beneficiarioColType)) {
				rg.setPais((String) value);
			} else if (BeneficiarioColType.DT_EMISSAO_RG.equals(beneficiarioColType)) {
				rg.setDtEmissao((LocalDate) value);
			} else if (BeneficiarioColType.UF_RG.equals(beneficiarioColType)) {
				rg.setUf(UFType.parse((String) value));
			} else if (BeneficiarioColType.PIS.equals(beneficiarioColType)) {
				beneficiarioDetail.setPis((String) value);
			} else if (BeneficiarioColType.CNS.equals(beneficiarioColType)) {
				beneficiarioDetail.setCns((String) value);
			} else if (BeneficiarioColType.EMAIL.equals(beneficiarioColType)) {
				beneficiarioDetail.setEmail((String) value);
			} else if (BeneficiarioColType.GRAU_ESCOLARIDADE.equals(beneficiarioColType)) {
				beneficiarioDetail.setGrauEscolaridade(GrauEscolaridadeType.parse((Integer) value));
			} else if (BeneficiarioColType.RENDA_FAMILIAR.equals(beneficiarioColType)) {
				beneficiarioDetail.setRendaFamiliar((BigDecimal) value);
			} else if (BeneficiarioColType.CD_PROFISSAO.equals(beneficiarioColType)) {
				beneficiarioDetail.setCdProfissao((Integer) value);
			} else if (BeneficiarioColType.CD_PAIS_ORIGEM.equals(beneficiarioColType)) {
				beneficiarioDetail.setCdPaisOrigem((Integer) value);
			} else if (BeneficiarioColType.DT_EXCLUSAO.equals(beneficiarioColType)) {
				beneficiarioDetail.setDtExclusao((LocalDate) value);
			} else if (BeneficiarioColType.CD_MOTIVO_EXCLUSAO.equals(beneficiarioColType)) {
				beneficiarioDetail.setCdMotivoExclusao((Integer) value);
			} else if (BeneficiarioColType.CD_OPERACAO.equals(beneficiarioColType)) {
				beneficiarioDetail.setCdOperacao((Integer) value);
			} else if (BeneficiarioColType.CD_EMPRESA_TRANSF.equals(beneficiarioColType)) {
				transferencia.setCdEmpresaTransferencia((Integer) value);
			} else if (BeneficiarioColType.NR_MATRICULA_TRANSF.equals(beneficiarioColType)) {
				transferencia.setCdMatriculaTransferencia((Long) value);
			} else if (BeneficiarioColType.NR_LOCAL_TRANSF.equals(beneficiarioColType)) {
				transferencia.setLocalTransferecia((Integer) value);
			} else if (BeneficiarioColType.NR_CATEGORIA_TRANSF.equals(beneficiarioColType)) {
				transferencia.setCategoriaTransferencia((Integer) value);
			} else if (BeneficiarioColType.CD_PLANO_TRANSF.equals(beneficiarioColType)) {
				transferencia.setPlanoTransferecia((String) value);
			} else if (BeneficiarioColType.MOTIVO_REMISSAO.equals(beneficiarioColType)) {
				transferencia.setMotivoRemissao((String) value);
			} else if (BeneficiarioColType.NR_CPF_NOVO_TITULAR.equals(beneficiarioColType)) {
				transferencia.setCpfNovoTitular((Long) value);
			} else if (BeneficiarioColType.QTDE_PERM_MESES.equals(beneficiarioColType)) {
				transferencia.setQtdePermanenciaMeses((Integer) value);
			} else if (BeneficiarioColType.RDP_NOVO_TITULAR.equals(beneficiarioColType)) {
				transferencia.setRdpNovoTitular((Integer) value);
			} else if (BeneficiarioColType.DT_INICIO_TRANSF.equals(beneficiarioColType)) {
				transferencia.setDtInicioTransferencia((LocalDate) value);
			} else if (BeneficiarioColType.CD_STATUS.equals(beneficiarioColType)) {
				transferencia.setCdStatus((String) value);
			} else if (BeneficiarioColType.CD_ERROR.equals(beneficiarioColType)) {
				transferencia.setCdError((String) value);
			} else if (BeneficiarioColType.CD_DV.equals(beneficiarioColType)) {
				transferencia.setCdDv((Integer) value);
			} else if (BeneficiarioColType.BLOQ_EMPRESA_INADIMPLENCIA.equals(beneficiarioColType)) {
				if ("S".equals((String) value)) {
					beneficiarioDetail.setBloqEmpresaInadimplencia(Boolean.TRUE);
				} else {
					beneficiarioDetail.setBloqEmpresaInadimplencia(Boolean.FALSE);
				}
			} else if (BeneficiarioColType.CPT.equals(beneficiarioColType)) {
				beneficiarioDetail.setCdProduto((String) value);
			} else if (BeneficiarioColType.CD_EMPRESA_TITULAR.equals(beneficiarioColType)) {
				transferencia.setCdEmpresaTitular((Integer) value);
			} else if (BeneficiarioColType.DIF_MATRICULA_TITULAR.equals(beneficiarioColType)) {
				transferencia.setDiferenciadorMatriculaTitular((String) value);
			} else if (BeneficiarioColType.NR_TITULO_ELEITOR.equals(beneficiarioColType)) {
				beneficiarioDetail.setTituloEleitor((String) value);
			} else if (BeneficiarioColType.NR_RIC.equals(beneficiarioColType)) {
				beneficiarioDetail.setRic((String) value);
			} else if (BeneficiarioColType.NR_CERTIDAO_NASCIMENTO.equals(beneficiarioColType)) {
				beneficiarioDetail.setCertidaoNascimento((String) value);
			} else if (BeneficiarioColType.NR_CARTEIRA_IDENT.equals(beneficiarioColType)) {
				beneficiarioDetail.setCarteiraIdentificacao((String) value);
			} else if (BeneficiarioColType.IND_SEGURADO_CONTRIBUTARIO.equals(beneficiarioColType)) {
				beneficiarioDetail.setIndicadorSeguradoContributario((String) value);
			} else if (BeneficiarioColType.IND_COND_EX_EMPREGADO.equals(beneficiarioColType)) {
				beneficiarioDetail.setIndicadorExEmpregado((String) value);
			} else if (BeneficiarioColType.IND_PERM_PLANO.equals(beneficiarioColType)) {
				beneficiarioDetail.setIndicadorPermanenciaPlano((String) value);
			} else if (BeneficiarioColType.QTDE_MESES_CONTRIB.equals(beneficiarioColType)) {
				beneficiarioDetail.setQtdeMesesContribuicao((Integer) value);
			} else if (BeneficiarioColType.NM_BENEFICIARIO_COMPLETO.equals(beneficiarioColType)) {
				beneficiarioDetail.setNameCompletoBeneficiario((String) value);
			} else if (BeneficiarioColType.IND_TITULAR_REMIDO.equals(beneficiarioColType)) {
				beneficiarioDetail.setIndicadorTitularRemido((String) value);
			} else if (BeneficiarioColType.EMAIL_SEGURADORA.equals(beneficiarioColType)) {
				beneficiarioDetail.setEmailSeguradora((String) value);
			} else if (BeneficiarioColType.IND_PORTABILIDADE_01.equals(beneficiarioColType)) {
				beneficiarioDetail.setIndicadorPoretabilidade1((String) value);
			} else if (BeneficiarioColType.IND_PORTABILIDADE_02.equals(beneficiarioColType)) {
				beneficiarioDetail.setIndicadorPoretabilidade2((String) value);
			} else if (BeneficiarioColType.IND_CARENCIA.equals(beneficiarioColType)) {
				beneficiarioDetail.setIndicadorCarencia((String) value);
			} else if (BeneficiarioColType.CD_PRODUTO.equals(beneficiarioColType)) {
				beneficiarioDetail.setCdProduto((String) value);
			} else if (BeneficiarioColType.CD_IND_PRODUTO_ANTERIOR_SAS.equals(beneficiarioColType)) {
				beneficiarioDetail.setCdPlanoAnteriorSas((String) value);
			} else if (BeneficiarioColType.CID01.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid01((String) value);
			} else if (BeneficiarioColType.CID02.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid02((String) value);
			} else if (BeneficiarioColType.CID03.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid03((String) value);
			} else if (BeneficiarioColType.CID04.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid04((String) value);
			} else if (BeneficiarioColType.CID05.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid05((String) value);
			} else if (BeneficiarioColType.CID06.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid06((String) value);
			} else if (BeneficiarioColType.CID07.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid07((String) value);
			} else if (BeneficiarioColType.CID08.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid08((String) value);
			} else if (BeneficiarioColType.CID09.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid09((String) value);
			} else if (BeneficiarioColType.CID10.equals(beneficiarioColType)) {
				beneficiarioDetail.setCid10((String) value);
			} else if (BeneficiarioColType.IBGE.equals(beneficiarioColType)) {
				beneficiarioDetail.setIbge((String) value);
			} else if (BeneficiarioColType.CBO.equals(beneficiarioColType)) {
				beneficiarioDetail.setCbo((String) value);
			} else if (BeneficiarioColType.DIF_TRANSF.equals(beneficiarioColType)) {
				beneficiarioDetail.setDifTransferencia((String) value);
			} else if (BeneficiarioColType.DESCR_PROFISSAO.equals(beneficiarioColType)) {
				beneficiarioDetail.setProfissao((String) value);
			} else if (BeneficiarioColType.NR_MATRICULA_ESPECIAL.equals(beneficiarioColType)) {
				beneficiarioDetail.setMatriculaEspecial((String) value);
			} else if (BeneficiarioColType.VL_FATOR_MODERADOR.equals(beneficiarioColType)) {
				beneficiarioDetail.setFatorModerador((BigDecimal) value);
			} else if (BeneficiarioColType.NR_SUBFATURA.equals(beneficiarioColType)) {
				beneficiarioDetail.setSubFatura((Integer) value);
			} else if (BeneficiarioColType.VL_FATOR_MODERADOR_INSS.equals(beneficiarioColType)) {
				beneficiarioDetail.setFatorModeradorInss((BigDecimal) value);
			} else if (BeneficiarioColType.VL_INSS.equals(beneficiarioColType)) {
				beneficiarioDetail.setValorInss((BigDecimal) value);
			} else if (BeneficiarioColType.VL_ALIQUOTA_INSS.equals(beneficiarioColType)) {
				beneficiarioDetail.setValorAliquotaInss((BigDecimal) value);
			} else if (BeneficiarioColType.VL_LIQUIDO_SINISTRO.equals(beneficiarioColType)) {
				beneficiarioDetail.setValorLiquidoSinistro((BigDecimal) value);
			} else if (BeneficiarioColType.IND_EVENTO.equals(beneficiarioColType)) {
				beneficiarioDetail.setIndicadorEvento((Integer) value);
			} else if (BeneficiarioColType.CD_USUARIO.equals(beneficiarioColType)) {
				beneficiarioDetail.setCdUsuario((String) value);
			} else if (BeneficiarioColType.NR_CERTIFICADO.equals(beneficiarioColType)) {
				beneficiarioDetail.setCertificado((Long) value);
			} else if (BeneficiarioColType.NM_ALIAS_01.equals(beneficiarioColType)) {
				beneficiarioDetail.setAlias01((String) value);
			} else if (BeneficiarioColType.NM_ALIAS_02.equals(beneficiarioColType)) {
				beneficiarioDetail.setAlias02((String) value);
			} else if (BeneficiarioColType.NM_ALIAS_03.equals(beneficiarioColType)) {
				beneficiarioDetail.setAlias03((String) value);
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
		BeneficiarioUi beneficiarioUi;
		EmpresaUi empresaUi;
		DependenteUi dependenteUi;
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioColsUis = coParticipacaoContext
					.listBeneficiarioColsBySheetId(coParticipacaoContext.getCurrentSheet());
			empresaUi = coParticipacaoContext.getEmpresaUi();

			beneficiarioUi = createBeneficiario(coParticipacaoContext, beneficiarioColsUis);

			LOGGER.info(
					"Current Titular[{}] and Beneficiario [{}] with MATRICULA [{}] and CPF[{}]:",
					beneficiarioUi.getNameTitular(),
					beneficiarioUi.getNameBeneficiario(),
					beneficiarioUi.getMatricula(),
					beneficiarioUi.getCpf());

			defineContrato(coParticipacaoContext, beneficiarioUi);

			if (isTitular(coParticipacaoContext, beneficiarioUi)) {
				LOGGER.info(
						"TitularUi[{}] and Beneficiario [{}] with MATRICULA [{}] and CPF[{}] is Titular:",
						beneficiarioUi.getNameTitular(),
						beneficiarioUi.getNameBeneficiario(),
						beneficiarioUi.getMatricula(),
						beneficiarioUi.getCpf());

				beneficiarioUi.setType(BeneficiarioType.TITULAR);

				if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
					beneficiarioUi.setNameTitular(beneficiarioUi.getNameBeneficiario());
				}
			} else {
				LOGGER.info(
						"Beneficiario [{}] with MATRICULA [{}] and CPF[{}] is Dependente:",
						beneficiarioUi.getNameBeneficiario(),
						beneficiarioUi.getMatricula(),
						beneficiarioUi.getCpf());

				if (!UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
					beneficiarioUi.setType(BeneficiarioType.OUTROS);

					if (UseType.MECSAS2.equals(coParticipacaoContext.getContratoUi().getUseType())) {
						dependenteUi = findDependente(coParticipacaoContext, beneficiarioUi);

						if (dependenteUi != null) {
							titularUi = (TitularUi) dependenteUi.getTitular();

							beneficiarioUi.setNameTitular(titularUi.getNameTitular());
							beneficiarioUi.setMatricula(titularUi.getMatricula());

							coParticipacaoContext.setTitularUi(titularUi);
						}
					}
				}
			}

			LOGGER.debug(
					"BeneficiarioUi[{}] with CD_CONTRATO[{}]:",
					beneficiarioUi.getNameBeneficiario(),
					beneficiarioUi.getCdContrato());

			if (StringUtils.isNotBlank(beneficiarioUi.getCdContrato()) && beneficiarioUi.getContrato() == null) {
				for (Contrato contrato : coParticipacaoContext.getEmpresaUi().getContratos()) {
					if (contrato.getCdContrato().equals(beneficiarioUi.getCdContrato())) {
						beneficiarioUi.setContrato(contrato);
						break;
					}
				}

				// Momentâneo:
				if (beneficiarioUi.getContrato() == null) {
					for (Contrato contrato : coParticipacaoContext.getEmpresaUi().getContratos()) {
						if (UseType.FATUCOPA.equals(contrato.getUseType())) {
							beneficiarioUi.setContrato(contrato);
							break;
						}
					}
				}
			}

			if (beneficiarioUi.getContrato() != null) {
				LOGGER.debug(
						"Using ContratoUi[{}] for TitularUi[{}] and Beneficiario[{}] with NR_MATRICULA[{}] and NR_CPF[{}]:",
						beneficiarioUi.getContrato().getCdContrato(),
						beneficiarioUi.getNameTitular(),
						beneficiarioUi.getNameBeneficiario(),
						beneficiarioUi.getMatricula(),
						beneficiarioUi.getCpf());
			} else {
				if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
					throw new BeneficiarioNotFoundException(
							"BeneficiarioUi[%s] has no ContratoUi defined for CD_CONTRATO[%s]",
							beneficiarioUi.getNameBeneficiario(),
							beneficiarioUi.getCdContrato());
				}
			}

			if (empresaUi.isAcceptTitularWithoutCpf()) {
				/*
				 * O número do CPF esta NULL, então devemos deixar como ZERO:
				 */
				if (beneficiarioUi.getCpf() == null) {
					beneficiarioUi.setCpf(NumberUtils.LONG_ZERO);
				}
			}

			coParticipacaoContext.setBeneficiarioUi(beneficiarioUi);

			LOGGER.info("END");
			return beneficiarioUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	private void defineContrato(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		ContratoUi contratoUi = null;
		ArquivoInputSheetUi arquivoInputSheetUi;
		String cdContrato = null;

		try {
			LOGGER.info("BEGIN");

			if (beneficiarioUi.getCdContrato() != null) {
				cdContrato = beneficiarioUi.getCdContrato().toUpperCase();
			}

			/*
			 * Se a pasta possuir informações do ContratoUi para ser utilizado
			 * com o BeneficiarioUi, então iremos carrega-lo no BeneficiarioUi,
			 * caso contrário devemos usar o ContratoUi expecifido no
			 * ArquivoInputSheetUi que esta sendo usado pela pasta da planilha:
			 */
			if (StringUtils.isNotBlank(beneficiarioUi.getCdContrato())) {
				for (Contrato contrato : coParticipacaoContext.getEmpresaUi().getContratos()) {
					if (contrato.getCdContrato().equals(cdContrato) || contrato.getNameContrato().equals(cdContrato)) {
						contratoUi = (ContratoUi) contrato;
						break;
					}
				}
			}

			if (contratoUi == null) {
				arquivoInputSheetUi = coParticipacaoContext.getArquivoInputSheet();
				contratoUi = (ContratoUi) arquivoInputSheetUi.getContrato();
			}

			/*
			 * Agora vamos usar o ContratoUi que deve ser definido ao
			 * BeneficiarioUi:
			 */
			beneficiarioUi.setCdContrato(contratoUi.getCdContrato());
			beneficiarioUi.setContrato(contratoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private BeneficiarioUi createBeneficiario(
			CoParticipacaoContext coParticipacaoContext,
			List<BeneficiarioColsUi> beneficiarioColsUis) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		Object value;
		RegisterColumnUi RegisterColumnUi;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating Beneficiario:");
			beneficiarioUi = new BeneficiarioUi();

			for (BeneficiarioColsUi beneficiarioColsUi : beneficiarioColsUis) {
				RegisterColumnUi = (RegisterColumnUi) beneficiarioColsUi.getRegisterColumn();

				LOGGER.debug("Using mapped column[{}]:", RegisterColumnUi.getNameColumn());
				value = coParticipacaoContext.getColumnValue(RegisterColumnUi);

				LOGGER.info("Retrieving value [{}] from column [{}]", value, RegisterColumnUi.getNameColumn());

				if (isNotZero(value)) {
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

	private boolean isTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		ContratoUi contratoUi;
		TitularUi titularUi;
		DependenteUi dependenteUi;
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			contratoUi = coParticipacaoContext.getContratoUi();
			empresaUi = coParticipacaoContext.getEmpresaUi();

			if (UseType.MECSAS.equals(contratoUi.getUseType())
					|| (UseType.MECSAS2.equals(contratoUi.getUseType()) && empresaUi.isCreateBeneficiarioFromMecsas2())
					|| (UseType.ISENTO.equals(contratoUi.getUseType()) && empresaUi.isUpdateBeneficiarioFromIsento())) {
				if (beneficiarioUi.getType() == null) {
					if (StringUtils.isBlank(beneficiarioUi.getNameTitular())) {
						beneficiarioUi.setType(BeneficiarioType.TITULAR);

						LOGGER.info("END");
						return true;
					} else if (beneficiarioUi.getNameTitular().equals(beneficiarioUi.getNameBeneficiario())
							|| StringUtils.isBlank(beneficiarioUi.getNameBeneficiario())) {
						beneficiarioUi.setType(BeneficiarioType.TITULAR);

						LOGGER.info("END");
						return true;
					}
				} else if (BeneficiarioType.TITULAR.equals(beneficiarioUi.getType())) {
					LOGGER.info("END");
					return true;
				}
			} else {
				if (UseType.NAO_LOCALIZADO.equals(contratoUi.getUseType())) {
					/*
					 * Se o Beneficiário não for um Titular e não existe no
					 * banco de dados e também não possuir um Títular, então ele
					 * próprio é um Títular, pois não foi encontrado pelo
					 * processo MECSAS e FATUCOPA:
					 */
					if (StringUtils.isBlank(beneficiarioUi.getNameBeneficiario())) {
						throw new BeneficiarioNotFoundException("The column[NM_BENEFICIARIO] must be defined:");
					}
				}

				/*
				 * Para o BeneficiarioUi, aqui iremos localizar o seu TitularUi
				 * ou o títular pode ser o próprio BeneficiarioUi:
				 */
				titularUi = findTitular(coParticipacaoContext, beneficiarioUi);

				/*
				 * Se localizarmos um DependenteUi com os dados do
				 * BeneficiarioUi, então ele é um dependente:
				 */
				dependenteUi = findDependente(coParticipacaoContext, beneficiarioUi);

				if (dependenteUi == null) {
					if (titularUi != null) {
						if (titularUi.getMatriculaEmpresa() != null && beneficiarioUi.getMatriculaEmpresa() != null) {
							if (titularUi.getMatriculaEmpresa().equals(beneficiarioUi.getMatriculaEmpresa())) {
								LOGGER.info("END");
								return true;
							}
						}

						if (StringUtils.isNotBlank(beneficiarioUi.getNameBeneficiario())) {
							if (titularUi.getNameTitular().equals(beneficiarioUi.getNameBeneficiario())) {
								LOGGER.info("END");
								return true;
							}
						} else {
							if (titularUi.getNameTitular().equals(beneficiarioUi.getNameTitular())) {
								LOGGER.info("END");
								return true;
							}
						}

						if (StringUtils.isNotBlank(titularUi.getBeneficiarioDetail().getAlias01())) {
							if (titularUi.getBeneficiarioDetail().getAlias01()
									.equals(beneficiarioUi.getNameBeneficiario())) {
								LOGGER.info("END");
								return true;
							}
						}

						if (StringUtils.isNotBlank(titularUi.getBeneficiarioDetail().getAlias02())) {
							if (titularUi.getBeneficiarioDetail().getAlias02()
									.equals(beneficiarioUi.getNameBeneficiario())) {
								LOGGER.info("END");
								return true;
							}
						}

						if (StringUtils.isNotBlank(titularUi.getBeneficiarioDetail().getAlias03())) {
							if (titularUi.getBeneficiarioDetail().getAlias03()
									.equals(beneficiarioUi.getNameBeneficiario())) {
								LOGGER.info("END");
								return true;
							}
						}
					} else {
						if (StringUtils.isNotBlank(beneficiarioUi.getNameTitular())) {
							if (beneficiarioUi.getNameTitular().equals(beneficiarioUi.getNameBeneficiario())) {
								LOGGER.info("END");
								return true;
							}
						} else {
							if (UseType.NAO_LOCALIZADO.equals(contratoUi.getUseType())) {
								/*
								 * Se o BeneficiarioUi não possui TitularUi e
								 * não existe um DependenteUi, então ele próprio
								 * é o TitularUi:
								 */
								LOGGER.info("END");
								return true;
							}
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

	public TitularUi createTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException, TitularDuplicated {
		TitularUi titularUi;
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			titularUi = findTitular(coParticipacaoContext, beneficiarioUi);
			empresaUi = coParticipacaoContext.getEmpresaUi();

			if (titularUi == null) {
				if (empresaService.canAutomaticallyCreateBeneficiario(coParticipacaoContext)) {
					if (validateBeneficiario(coParticipacaoContext, beneficiarioUi)) {
						/*
						 * Vamos verificar se já não existe um títular com o
						 * mesmo CPF:
						 */
						if (!isTitularCpfInUse(coParticipacaoContext, beneficiarioUi)
								&& !isMatriculaInUse(coParticipacaoContext, beneficiarioUi)) {
							titularUi = new TitularUi();

							if (StringUtils.isNotBlank(beneficiarioUi.getNameTitular())) {
								titularUi.setNameTitular(beneficiarioUi.getNameTitular());
							} else {
								titularUi.setNameTitular(beneficiarioUi.getNameBeneficiario());
							}

							if (beneficiarioUi.getCpf() != null
									&& !NumberUtils.LONG_ZERO.equals(beneficiarioUi.getCpf())) {
								if (beneficiarioUi.getDigitoCpf() != null) {
									titularUi.setCpf(concatTitularCpf(beneficiarioUi));
								} else {
									titularUi.setCpf(beneficiarioUi.getCpf());
								}
							} else {
								if (empresaUi.isAcceptTitularWithoutCpf()) {
									titularUi.setCpf(NumberUtils.LONG_ZERO);
								}
							}

							if (beneficiarioUi.getMatricula() == null) {
								titularUi.setMatricula(NumberUtils.LONG_ZERO);
							} else {
								titularUi.setMatricula(beneficiarioUi.getMatricula());
							}

							LOGGER.info(
									"Titular [{}] with CPF [{}] and Matricula [{}] will be created:",
									titularUi.getNameTitular(),
									titularUi.getCpf(),
									titularUi.getMatricula());

							titularUi.setContrato(beneficiarioUi.getContrato());
							titularUi.setMatriculaEmpresa(beneficiarioUi.getMatriculaEmpresa());
							titularUi.setDtNascimento(beneficiarioUi.getDtNascimento());
							titularUi.setDtAdmissao(beneficiarioUi.getDtAdmissao());
							titularUi.setDtDemissao(beneficiarioUi.getDtDemissao());
							titularUi.setReferenceCode(beneficiarioUi.getReferenceCode());
							titularUi.setBeneficiarioDetail(beneficiarioUi.getBeneficiarioDetail());

							/*
							 * Se a empresa estiver configurada para adicionar
							 * beneficiários a partir dos arquivos FATUCOPA,
							 * existe a opção de marcar este beneficiário como
							 * demitidos se for o caso, assim teremos como
							 * diferenciar que eles não foram encontrados no
							 * MECSAS. Usado pelo processo HAOC:
							 */
							if (UseType.FATUCOPA.equals(coParticipacaoContext.getContratoUi().getUseType())) {
								if (empresaUi.isCreateBeneficiarioFromFatucopa()) {
									if (CreateBeneficiarioType.ADD_AS_DEMITIDO
											.equals(empresaUi.getCreateBeneficiarioType())) {
										titularUi.setDtDemissao(LocalDate.now());
									}
								}
							}

							titularUi.setUserAltered(coParticipacaoContext.getUser());
							titularUi.setUserCreated(coParticipacaoContext.getUser());

							coParticipacaoContext.addTitular(titularUi);

							LOGGER.info(
									"Titular [{}] with CPF [{}] and Matricula [{}] created:",
									titularUi.getNameTitular(),
									titularUi.getCpf(),
									titularUi.getMatricula());
						} else {
							LOGGER.info(
									"Titular [{}] with CPF [{}] and Matricula [{}] cannot be created because NR_CPF is already in use:",
									beneficiarioUi.getNameTitular(),
									beneficiarioUi.getCpf(),
									beneficiarioUi.getMatricula());
						}
					}
				}
			} else {
				if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())
						|| UseType.MECSAS2.equals(coParticipacaoContext.getContratoUi().getUseType())
						|| UseType.NAO_LOCALIZADO.equals(coParticipacaoContext.getContratoUi().getUseType())) {
					coParticipacaoContext.setTitularUi(titularUi);

					/*
					 * Se estamos num processo MECSAS e encontramos o Titular,
					 * então significa que ele esta duplicado no arquivo de
					 * entrada e já foi processado:
					 */
					if (!titularUi.isMarkedForUpdated() && titularUi.getId() != null) {
						titularUi.setMarkedForUpdated(true);
						updateTitular(coParticipacaoContext, titularUi, beneficiarioUi);
						coParticipacaoContext.addTitular(titularUi);
					} else {
						throw new TitularDuplicated(beneficiarioUi);
					}
				} else {
					if (isFatucopaEnabledToUpdateBeneficiario(coParticipacaoContext)) {
						updateTitular(coParticipacaoContext, titularUi, beneficiarioUi);
						coParticipacaoContext.addTitular(titularUi);
					}
				}
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

	private boolean isMatriculaInUse(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			titularUi = coParticipacaoContext
					.findTitularByMatriculaAndCdContrato(beneficiarioUi.getMatricula(), beneficiarioUi.getCdContrato());

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

	private boolean isTitularCpfInUse(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException {
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			if (!NumberUtils.LONG_ZERO.equals(beneficiarioUi.getCpf())) {
				if (StringUtils.isNotBlank(beneficiarioUi.getNameTitular())) {
					titularUi = coParticipacaoContext
							.findTitularByCpfAndName(beneficiarioUi.getCpf(), beneficiarioUi.getNameTitular());
				} else {
					titularUi = coParticipacaoContext
							.findTitularByCpfAndName(beneficiarioUi.getCpf(), beneficiarioUi.getNameBeneficiario());
				}

				if (titularUi != null) {
					LOGGER.info(
							"There's already a Titular using NR_CPF[{}] and NR_MATRICULA[{}]:",
							titularUi.getCpf(),
							titularUi.getMatricula());
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

	public DependenteUi createDependente(CoParticipacaoContext coParticipacaoContext, BeneficiarioUi beneficiarioUi)
			throws ServiceException, BeneficiarioNotFoundException, DependenteDuplicated {
		DependenteUi dependenteUi = null;
		TitularUi titularUi = null;
		BeneficiarioUi beneficiarioTitular;

		try {
			LOGGER.info("BEGIN");

			dependenteUi = findDependente(coParticipacaoContext, beneficiarioUi);

			if (dependenteUi == null) {
				if (empresaService.canAutomaticallyCreateBeneficiario(coParticipacaoContext)) {
					if (validateBeneficiario(coParticipacaoContext, beneficiarioUi)) {
						if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
							// titularUi = coParticipacaoContext.getTitularUi();
							titularUi = findTitular(coParticipacaoContext, beneficiarioUi);
						} else {
							if (beneficiarioUi.getMatriculaTitular() != null) {
								titularUi = coParticipacaoContext
										.findTitularByMatricula(beneficiarioUi.getMatriculaTitular());

								if (titularUi == null) {
									beneficiarioTitular = new BeneficiarioUi(beneficiarioUi);
									beneficiarioTitular.setType(BeneficiarioType.TITULAR);

									if (StringUtils.isBlank(beneficiarioTitular.getNameTitular())) {
										beneficiarioTitular.setNameTitular(beneficiarioUi.getNameBeneficiario());
									}

									titularUi = createTitular(coParticipacaoContext, beneficiarioTitular);

									coParticipacaoContext.setTitularUi(titularUi);
								}
							} else {
								titularUi = coParticipacaoContext.findTitularByMatricula(beneficiarioUi.getMatricula());
							}
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
							dependenteUi.setMatriculaEmpresa(beneficiarioUi.getMatriculaEmpresa());
							dependenteUi.setDtNascimento(beneficiarioUi.getDtNascimento());
							dependenteUi.setReferenceCode(beneficiarioUi.getReferenceCode());
							dependenteUi.setBeneficiarioDetail(beneficiarioUi.getBeneficiarioDetail());

							dependenteUi.setUserAltered(coParticipacaoContext.getUser());
							dependenteUi.setUserCreated(coParticipacaoContext.getUser());

							titularUi.addDependente(dependenteUi);
							coParticipacaoContext.addDependente(dependenteUi);
						} else {
							/*
							 * Como este Beneficiário não existe no banco e
							 * também não possui Títular, mas a Empresa esta
							 * configurada para criar os Beneficiários
							 * automaticamente, vamos transformá-lo em Titular:
							 */
							LOGGER.info(
									"BeneficiarioUi [{}] with CPF [{}] and Matricula [{}] cannot be created as a DependenteUi, no TitularUi found:",
									beneficiarioUi.getNameBeneficiario(),
									beneficiarioUi.getCpf(),
									beneficiarioUi.getMatricula());
							throw new BeneficiarioNotFoundException(
									"BeneficiarioUi [%s] with CPF [%s] and Matricula [%s] cannot be created as a DependenteUi, no TitularUi found:",
									beneficiarioUi.getNameBeneficiario(),
									beneficiarioUi.getCpf(),
									beneficiarioUi.getMatricula());
						}
					}
				} else {
					throw new BeneficiarioNotFoundException(beneficiarioUi);
				}
			} else {
				if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())
						|| UseType.MECSAS2.equals(coParticipacaoContext.getContratoUi().getUseType())
						|| UseType.NAO_LOCALIZADO.equals(coParticipacaoContext.getContratoUi().getUseType())) {
					/*
					 * Se estamos num processo MECSAS e encontramos o
					 * Dependente, então significa que ele esta duplicado no
					 * arquivo de entrada e já foi processado:
					 */
					if (!dependenteUi.isMarkedForUpdated() && dependenteUi.getId() != null) {
						dependenteUi.setMarkedForUpdated(true);
						updateDependente(coParticipacaoContext, dependenteUi, beneficiarioUi);
					} else {
						throw new DependenteDuplicated(beneficiarioUi);
					}
				} else {
					if (isFatucopaEnabledToUpdateBeneficiario(coParticipacaoContext)) {
						updateDependente(coParticipacaoContext, dependenteUi, beneficiarioUi);
					}
				}
			}

			if (dependenteUi == null) {
				LOGGER.info("Dependente [{}] does not have any Titular:", beneficiarioUi.getNameBeneficiario());
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

	private boolean isFatucopaEnabledToUpdateBeneficiario(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();

			if (UseType.FATUCOPA.equals(coParticipacaoContext.getContratoUi().getUseType())) {
				if (empresaUi.isUpdateBeneficiarioFromFatucopa()) {
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

			if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())
					|| UseType.MECSAS2.equals(coParticipacaoContext.getContratoUi().getUseType())
					|| UseType.NAO_LOCALIZADO.equals(coParticipacaoContext.getContratoUi().getUseType())
					|| isFatucopaEnabledToUpdateBeneficiario(coParticipacaoContext)
					|| isIsentoEnabledToUpdateBeneficiario(coParticipacaoContext)) {
				if (!UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())) {
					if (NumberUtils2.isNotZero(beneficiarioUi.getCpf())) {
						LOGGER.debug("Updating Titular field CPF with value [{}]:", beneficiarioUi.getCpf());
						titularUi.setCpf(beneficiarioUi.getCpf());
					}
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

				if (beneficiarioUi.getDtDemissao() != null) {
					LOGGER.debug("Updating Titular field DT_DEMISSAO with value [{}]:", beneficiarioUi.getDtDemissao());
					titularUi.setDtDemissao(beneficiarioUi.getDtDemissao());
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

				if (NumberUtils2.isNotZero(beneficiarioUi.getMatriculaEmpresa())) {
					LOGGER.debug(
							"Updating Titular field NR_MATRICULA_EMPRESA with value [{}]:",
							beneficiarioUi.getMatriculaEmpresa());
					titularUi.setMatriculaEmpresa(beneficiarioUi.getMatriculaEmpresa());
				}

				if (titularUi.getId() != null) {
					titularUi.setUserAltered(coParticipacaoContext.getUser());

					coParticipacaoContext.addTitular(titularUi);
				}

				BeneficiarioDetailHelper.copyBeneficiarioDetail(
						titularUi.getBeneficiarioDetail(),
						beneficiarioUi.getBeneficiarioDetail());

				titularUi.setUserAltered(coParticipacaoContext.getUser());

				coParticipacaoContext.addTitular(titularUi);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private boolean isIsentoEnabledToUpdateBeneficiario(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();

			if (UseType.ISENTO.equals(coParticipacaoContext.getContratoUi().getUseType())) {
				if (empresaUi.isUpdateBeneficiarioFromIsento()) {
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

	private void updateDependente(
			CoParticipacaoContext coParticipacaoContext,
			DependenteUi dependenteUi,
			BeneficiarioUi beneficiarioUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())
					|| UseType.MECSAS2.equals(coParticipacaoContext.getContratoUi().getUseType())
					|| UseType.NAO_LOCALIZADO.equals(coParticipacaoContext.getContratoUi().getUseType())
					|| isFatucopaEnabledToUpdateBeneficiario(coParticipacaoContext)) {
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

				BeneficiarioDetailHelper.copyBeneficiarioDetail(
						dependenteUi.getBeneficiarioDetail(),
						beneficiarioUi.getBeneficiarioDetail());

				dependenteUi.setUserAltered(coParticipacaoContext.getUser());

				coParticipacaoContext.addDependente(dependenteUi);
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

			if (StringUtils.isNotBlank(beneficiarioUi.getNameTitular())) {
				titularUi = coParticipacaoContext
						.findTitularByCpfAndName(beneficiarioUi.getCpf(), beneficiarioUi.getNameTitular());

				if (titularUi == null) {
					titularUi = coParticipacaoContext.findTitularByMatriculaAndName(
							beneficiarioUi.getMatricula(),
							beneficiarioUi.getNameTitular());

					if (titularUi == null) {
						titularUi = coParticipacaoContext.findTitularByMatriculaEmpresaAndName(
								beneficiarioUi.getMatriculaEmpresa(),
								beneficiarioUi.getNameTitular());
					}
				}
			}

			if (titularUi == null) {
				if (StringUtils.isNotBlank(beneficiarioUi.getNameBeneficiario())) {
					titularUi = coParticipacaoContext
							.findTitularByCpfAndName(beneficiarioUi.getCpf(), beneficiarioUi.getNameBeneficiario());

					if (titularUi == null) {
						titularUi = coParticipacaoContext.findTitularByMatriculaAndName(
								beneficiarioUi.getMatricula(),
								beneficiarioUi.getNameBeneficiario());

						if (titularUi == null) {
							titularUi = coParticipacaoContext.findTitularByMatriculaEmpresaAndName(
									beneficiarioUi.getMatriculaEmpresa(),
									beneficiarioUi.getNameBeneficiario());
						}
					}
				}
			}

			if (titularUi == null && beneficiarioUi.getCdContrato() != null) {
				titularUi = coParticipacaoContext.findTitularByMatriculaAndCdContrato(
						beneficiarioUi.getMatricula(),
						beneficiarioUi.getCdContrato());
			}

			/*
			 * Em caso que existam diferenças nos nomes dos beneficiários entro
			 * os arquivos de entrada, mas o valor do NR_CPF e NR_MATRICULA
			 * estejam corretos, preferencialmente existam diferenças entre os
			 * CPFs dos dependentes, podesse usar este recurso:
			 */
			if (titularUi == null) {
				if (beneficiarioUi.getMatriculaEmpresa() != null) {
					titularUi = coParticipacaoContext.findTitularByMatriculaAndMatriculaEmpresa(
							beneficiarioUi.getMatricula(),
							beneficiarioUi.getMatriculaEmpresa());

					if (titularUi == null
							&& coParticipacaoContext.getEmpresaUi().isSearchBeneficiarioByMatriculaEmpresa()) {
						titularUi = coParticipacaoContext
								.findTitularByMatriculaEmpresa(beneficiarioUi.getMatriculaEmpresa());
					}
				}
			}

			if (titularUi == null) {
				titularUi = coParticipacaoContext.findTitularByMatricula(beneficiarioUi.getMatricula());
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
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			dependenteUi = coParticipacaoContext
					.findDependenteByCpfAndName(beneficiarioUi.getCpf(), beneficiarioUi.getNameBeneficiario());

			if (dependenteUi == null) {
				dependenteUi = coParticipacaoContext.findDependenteByMatriculaAndName(
						beneficiarioUi.getMatricula(),
						beneficiarioUi.getNameBeneficiario());
			}

			if (dependenteUi == null) {
				titularUi = findTitular(coParticipacaoContext, beneficiarioUi);

				if (titularUi != null) {
					for (Dependente dependente : titularUi.getDependentes()) {
						LOGGER.debug("Comparing with DependenteUi[{}]:", dependente.getNameDependente());

						if (dependente.getNameDependente().equals(beneficiarioUi.getNameBeneficiario())) {
							dependenteUi = (DependenteUi) dependente;
							break;
						} else if (StringUtils.isNotBlank(dependente.getBeneficiarioDetail().getAlias01())) {
							if (dependente.getBeneficiarioDetail().getAlias01()
									.equals(beneficiarioUi.getNameBeneficiario())) {
								dependenteUi = (DependenteUi) dependente;
								break;
							}
						} else if (StringUtils.isNotBlank(dependente.getBeneficiarioDetail().getAlias02())) {
							if (dependente.getBeneficiarioDetail().getAlias02()
									.equals(beneficiarioUi.getNameBeneficiario())) {
								dependenteUi = (DependenteUi) dependente;
								break;
							}
						} else if (StringUtils.isNotBlank(dependente.getBeneficiarioDetail().getAlias03())) {
							if (dependente.getBeneficiarioDetail().getAlias03()
									.equals(beneficiarioUi.getNameBeneficiario())) {
								dependenteUi = (DependenteUi) dependente;
								break;
							}
						}
					}
				}
			}

			/*
			 * Em caso que existam diferenças nos nomes dos beneficiários entro
			 * os arquivos de entrada, mas o valor do NR_CPF e NR_MATRICULA
			 * estejam corretos, preferencialmente existam diferenças entre os
			 * CPFs dos dependentes, podesse usar este recurso:
			 */
			if (dependenteUi == null) {
				if (beneficiarioUi.getMatriculaEmpresa() != null) {
					dependenteUi = coParticipacaoContext.findDependenteByMatriculaAndMatriculaEmpresa(
							beneficiarioUi.getMatricula(),
							beneficiarioUi.getMatriculaEmpresa());

					if (dependenteUi == null
							&& coParticipacaoContext.getEmpresaUi().isSearchBeneficiarioByMatriculaEmpresa()) {
						dependenteUi = coParticipacaoContext
								.findDependenteByMatriculaEmpresa(beneficiarioUi.getMatriculaEmpresa());
					}
				}
			}

			LOGGER.info("END");
			return dependenteUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public DependenteUi findDependente(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = new BeneficiarioUi();
			beneficiarioUi.setNameBeneficiario(beneficiarioIsentoUi.getName());
			beneficiarioUi.setNameTitular(beneficiarioIsentoUi.getNameTitular());
			beneficiarioUi.setCpf(beneficiarioIsentoUi.getCpf());
			beneficiarioUi.setMatricula(beneficiarioIsentoUi.getMatricula());
			beneficiarioUi.setMatriculaEmpresa(beneficiarioIsentoUi.getMatriculaEmpresa());

			dependenteUi = findDependente(coParticipacaoContext, beneficiarioUi);

			LOGGER.info("END");
			return dependenteUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public TitularUi findTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioIsentoUi beneficiarioIsentoUi)
			throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		TitularUi titularUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = new BeneficiarioUi();
			beneficiarioUi.setNameBeneficiario(beneficiarioIsentoUi.getName());
			beneficiarioUi.setNameTitular(beneficiarioIsentoUi.getNameTitular());
			beneficiarioUi.setCpf(beneficiarioIsentoUi.getCpf());
			beneficiarioUi.setMatricula(beneficiarioIsentoUi.getMatricula());
			beneficiarioUi.setMatriculaEmpresa(beneficiarioIsentoUi.getMatriculaEmpresa());

			titularUi = findTitular(coParticipacaoContext, beneficiarioUi);

			LOGGER.info("END");
			return titularUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public boolean isTitular(CoParticipacaoContext coParticipacaoContext, BeneficiarioIsentoUi beneficiarioIsentoUi)
			throws ServiceException {
		BeneficiarioUi beneficiarioUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = new BeneficiarioUi();
			beneficiarioUi.setNameBeneficiario(beneficiarioIsentoUi.getName());
			beneficiarioUi.setNameTitular(beneficiarioIsentoUi.getNameTitular());
			beneficiarioUi.setCpf(beneficiarioIsentoUi.getCpf());
			beneficiarioUi.setMatricula(beneficiarioIsentoUi.getMatricula());
			beneficiarioUi.setMatriculaEmpresa(beneficiarioIsentoUi.getMatriculaEmpresa());

			if (isTitular(coParticipacaoContext, beneficiarioUi)) {
				LOGGER.info("END");
				return true;
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public TitularUi createTitular(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		TitularUi titularUi = null;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = createBeneficiario(coParticipacaoContext, beneficiarioIsentoUi);

			titularUi = createTitular(coParticipacaoContext, beneficiarioUi);

			LOGGER.info("END");
			return titularUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public DependenteUi createDependente(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;
		DependenteUi dependenteUi = null;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = createBeneficiario(coParticipacaoContext, beneficiarioIsentoUi);

			dependenteUi = createDependente(coParticipacaoContext, beneficiarioUi);

			LOGGER.info("END");
			return dependenteUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private BeneficiarioUi createBeneficiario(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = new BeneficiarioUi();
			beneficiarioUi.setNameBeneficiario(beneficiarioIsentoUi.getName());

			if (StringUtils.isNotBlank(beneficiarioIsentoUi.getNameTitular())) {
				beneficiarioUi.setNameTitular(beneficiarioIsentoUi.getNameTitular());
			} else {
				beneficiarioUi.setNameTitular(beneficiarioIsentoUi.getName());
			}

			beneficiarioUi.setContrato(coParticipacaoContext.getArquivoInputSheet().getContrato());
			beneficiarioUi.setCpf(beneficiarioIsentoUi.getCpf());
			beneficiarioUi.setMatricula(beneficiarioIsentoUi.getMatricula());
			beneficiarioUi.setMatriculaEmpresa(beneficiarioIsentoUi.getMatriculaEmpresa());

			beneficiarioUi.getBeneficiarioDetail().setPlano(beneficiarioIsentoUi.getPlano());

			LOGGER.info("END");
			return beneficiarioUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateTitular(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			TitularUi titularUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = createBeneficiario(coParticipacaoContext, beneficiarioIsentoUi);

			updateTitular(coParticipacaoContext, titularUi, beneficiarioUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateDependente(
			CoParticipacaoContext coParticipacaoContext,
			BeneficiarioIsentoUi beneficiarioIsentoUi,
			DependenteUi dependenteUi) throws ServiceException {
		BeneficiarioUi beneficiarioUi;

		try {
			LOGGER.info("BEGIN");

			beneficiarioUi = createBeneficiario(coParticipacaoContext, beneficiarioIsentoUi);

			updateDependente(coParticipacaoContext, dependenteUi, beneficiarioUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

package br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.impl.preparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DadosBancarios;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Endereco;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Rg;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Telefone;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Transferencia;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class BeneficiarioSetter<ENTITY extends AbstractDomain> extends PreparedStatementSetterAdapter<ENTITY> {

	private static final int COL_DF = 10;
	private static final int COL_RDP = 11;
	private static final int COL_NR_LOCAL = 12;
	private static final int COL_CD_CATEGORIA = 13;
	private static final int COL_SETOR = 14;
	private static final int COL_ES = 15;
	private static final int COL_CD_PLANO = 16;
	private static final int COL_DT_INCLUSAO = 17;
	private static final int COL_SEXO = 18;
	private static final int COL_PERMANENCIA = 19;
	private static final int COL_DT_REF = 20;
	private static final int COL_BANCO = 21;
	private static final int COL_AGENCIA = 22;
	private static final int COL_AGENCIA_DG = 23;
	private static final int COL_CONTA_CORRENTE = 24;
	private static final int COL_CPF_CC = 25;
	private static final int COL_NM_TITULAR_CC = 26;
	private static final int COL_CD_CARDIRF = 27;
	private static final int COL_NR_CEP = 28;
	private static final int COL_TP_LOGRADOURO = 29;
	private static final int COL_NM_LOGRADOURO = 30;
	private static final int COL_NUM = 31;
	private static final int COL_COMPL = 32;
	private static final int COL_BAIRRO = 33;
	private static final int COL_MUNICIPIO = 34;
	private static final int COL_UF = 35;
	private static final int COL_TEL_RESIDENCIAL = 36;
	private static final int COL_TEL_COMERCIAL = 37;
	private static final int COL_TEL_CELULAR = 38;
	private static final int COL_NM_MAE = 39;
	private static final int COL_NR_RG = 40;
	private static final int COL_ORGAO_EMISSOR_RG = 41;
	private static final int COL_PAIS_RG = 42;
	private static final int COL_DT_EMISSAO_RG = 43;
	private static final int COL_UF_RG = 44;
	private static final int COL_PIS = 45;
	private static final int COL_CNS = 46;
	private static final int COL_EMAIL = 47;
	private static final int COL_GRAU_ESCOLARIDADE = 48;
	private static final int COL_RENDA_FAMILIAR = 49;
	private static final int COL_CD_PROFISSAO = 50;
	private static final int COL_CD_PAIS_ORIGEM = 51;
	private static final int COL_DT_EXCLUSAO = 52;
	private static final int COL_CD_MOTIVO_EXCLUSAO = 53;
	private static final int COL_CD_OPERACAO = 54;
	private static final int COL_CD_EMPRESA_TRANSF = 55;
	private static final int COL_NR_MATRICULA_TRANSF = 56;
	private static final int COL_NR_LOCAL_TRANSF = 57;
	private static final int COL_NR_CATEGORIA_TRANSF = 58;
	private static final int COL_CD_PLANO_TRANSF = 59;
	private static final int COL_MOTIVO_REMISSAO = 60;
	private static final int COL_NR_CPF_NOVO_TITULAR = 61;
	private static final int COL_QTDE_PERM_MESES = 62;
	private static final int COL_RDP_NOVO_TITULAR = 63;
	private static final int COL_DT_INICIO_TRANSF = 64;
	private static final int COL_CD_STATUS = 65;
	private static final int COL_CD_ERROR = 66;
	private static final int COL_CD_DV = 67;
	private static final int COL_BLOQ_EMPRESA_INADIMPLENCIA = 68;
	private static final int COL_CPT = 69;
	private static final int COL_CD_EMPRESA_TITULAR = 70;
	private static final int COL_DIF_MATRICULA_TITULAR = 71;
	private static final int COL_NR_TITULO_ELEITOR = 72;
	private static final int COL_NR_RIC = 73;
	private static final int COL_NR_CERTIDAO_NASCIMENTO = 74;
	private static final int COL_NR_CARTEIRA_IDENT = 75;
	private static final int COL_IND_SEGURADO_CONTRIBUTARIO = 76;
	private static final int COL_IND_COND_EX_EMPREGADO = 77;
	private static final int COL_IND_PERM_PLANO = 78;
	private static final int COL_QTDE_MESES_CONTRIB = 79;
	private static final int COL_NM_BENEFICIARIO_COMPLETO = 80;
	private static final int COL_IND_TITULAR_REMIDO = 81;
	private static final int COL_EMAIL_SEGURADORA = 82;
	private static final int COL_IND_PORTABILIDADE_01 = 83;
	private static final int COL_IND_PORTABILIDADE_02 = 84;
	private static final int COL_IND_CARENCIA = 85;
	private static final int COL_CD_PRODUTO = 86;
	private static final int COL_CD_IND_PRODUTO_ANTERIOR_SAS = 87;
	private static final int COL_CID01 = 88;
	private static final int COL_CID02 = 89;
	private static final int COL_CID03 = 90;
	private static final int COL_CID04 = 91;
	private static final int COL_CID05 = 92;
	private static final int COL_CID06 = 93;
	private static final int COL_CID07 = 94;
	private static final int COL_CID08 = 95;
	private static final int COL_CID09 = 96;
	private static final int COL_CID10 = 97;
	private static final int COL_IBGE = 98;
	private static final int COL_CBO = 99;
	private static final int COL_DIF_TRANSF = 100;
	private static final int COL_DESCR_PROFISSAO = 101;
	private static final int COL_NR_MATRICULA_ESPECIAL = 102;
	private static final int COL_VL_FATOR_MODERADOR = 103;
	private static final int COL_SUBFATURA = 104;

	public BeneficiarioSetter(SetterAdapterType setterAdapterType, ENTITY entity) {
		super(setterAdapterType, entity);
	}

	protected void setBeneficiarioDetailValues(PreparedStatement ps, BeneficiarioDetail beneficiarioDetail)
			throws SQLException {
		DadosBancarios dadosBancarios;
		Endereco endereco;
		Telefone telefone;
		Rg rg;
		Transferencia transferencia;

		if (beneficiarioDetail == null) {
			setBeneficiarioDetailValues(ps, new BeneficiarioDetail());
		} else {
			dadosBancarios = beneficiarioDetail.getDadosBancarios();
			endereco = beneficiarioDetail.getEndereco();
			telefone = beneficiarioDetail.getTelefone();
			rg = beneficiarioDetail.getRg();
			transferencia = beneficiarioDetail.getTransferencia();

			// BeneficiarioDetail:
			setInt(ps, COL_DF, beneficiarioDetail.getDf());
			setInt(ps, COL_RDP, beneficiarioDetail.getRdp());
			setInt(ps, COL_NR_LOCAL, beneficiarioDetail.getLocal());
			setInt(ps, COL_CD_CATEGORIA, beneficiarioDetail.getCategoria());
			setString(ps, COL_SETOR, beneficiarioDetail.getSetor());
			setString(ps, COL_ES, beneficiarioDetail.getEs());
			setString(ps, COL_CD_PLANO, beneficiarioDetail.getPlano());
			setDate(ps, COL_DT_INCLUSAO, beneficiarioDetail.getDtInclusao());

			if (beneficiarioDetail.getSexo() != null) {
				setString(ps, COL_SEXO, beneficiarioDetail.getSexo().getId());
			} else {
				setString(ps, COL_SEXO, null);
			}

			setString(ps, COL_PERMANENCIA, beneficiarioDetail.getPermanencia());
			setDate(ps, COL_DT_REF, beneficiarioDetail.getDtReferencia());

			setDadosBancarios(ps, dadosBancarios);

			setString(ps, COL_CD_CARDIRF, beneficiarioDetail.getCardif());

			setEndereco(ps, endereco);
			setTelefone(ps, telefone);
			setRg(ps, rg);

			setString(ps, COL_PIS, beneficiarioDetail.getPis());
			setString(ps, COL_CNS, beneficiarioDetail.getCns());
			setString(ps, COL_EMAIL, beneficiarioDetail.getEmail());

			if (beneficiarioDetail.getGrauEscolaridade() != null) {
				setInt(ps, COL_GRAU_ESCOLARIDADE, beneficiarioDetail.getGrauEscolaridade().getId());
			} else {
				setInt(ps, COL_GRAU_ESCOLARIDADE, null);
			}

			setBigDecimal(ps, COL_RENDA_FAMILIAR, beneficiarioDetail.getRendaFamiliar());
			setInt(ps, COL_CD_PROFISSAO, beneficiarioDetail.getCdProfissao());
			setInt(ps, COL_CD_PAIS_ORIGEM, beneficiarioDetail.getCdPaisOrigem());
			setDate(ps, COL_DT_EXCLUSAO, beneficiarioDetail.getDtExclusao());
			setInt(ps, COL_CD_MOTIVO_EXCLUSAO, beneficiarioDetail.getCdMotivoExclusao());
			setInt(ps, COL_CD_OPERACAO, beneficiarioDetail.getCdOperacao());

			setTransferencia(ps, transferencia);

			setBoolean(ps, COL_BLOQ_EMPRESA_INADIMPLENCIA, beneficiarioDetail.getBloqEmpresaInadimplencia());
			setString(ps, COL_NR_TITULO_ELEITOR, beneficiarioDetail.getTituloEleitor());
			setString(ps, COL_NR_RIC, beneficiarioDetail.getRic());
			setString(ps, COL_NR_CERTIDAO_NASCIMENTO, beneficiarioDetail.getCertidaoNascimento());
			setString(ps, COL_NR_CARTEIRA_IDENT, beneficiarioDetail.getCarteiraIdentificacao());
			setString(ps, COL_IND_SEGURADO_CONTRIBUTARIO, beneficiarioDetail.getIndicadorSeguradoContributario());
			setString(ps, COL_IND_COND_EX_EMPREGADO, beneficiarioDetail.getIndicadorExEmpregado());
			setString(ps, COL_IND_PERM_PLANO, beneficiarioDetail.getIndicadorPermanenciaPlano());
			setInt(ps, COL_QTDE_MESES_CONTRIB, beneficiarioDetail.getQtdeMesesContribuicao());
			setString(ps, COL_NM_BENEFICIARIO_COMPLETO, beneficiarioDetail.getNameCompletoBeneficiario());
			setString(ps, COL_IND_TITULAR_REMIDO, beneficiarioDetail.getIndicadorTitularRemido());
			setString(ps, COL_EMAIL_SEGURADORA, beneficiarioDetail.getEmailSeguradora());
			setString(ps, COL_IND_PORTABILIDADE_01, beneficiarioDetail.getIndicadorPoretabilidade1());
			setString(ps, COL_IND_PORTABILIDADE_02, beneficiarioDetail.getIndicadorPoretabilidade2());
			setString(ps, COL_IND_CARENCIA, beneficiarioDetail.getIndicadorCarencia());
			setString(ps, COL_CD_PRODUTO, beneficiarioDetail.getCdProduto());
			setString(ps, COL_CD_IND_PRODUTO_ANTERIOR_SAS, beneficiarioDetail.getCdPlanoAnteriorSas());
			setString(ps, COL_CID01, beneficiarioDetail.getCid01());
			setString(ps, COL_CID02, beneficiarioDetail.getCid01());
			setString(ps, COL_CID03, beneficiarioDetail.getCid01());
			setString(ps, COL_CID04, beneficiarioDetail.getCid01());
			setString(ps, COL_CID05, beneficiarioDetail.getCid01());
			setString(ps, COL_CID06, beneficiarioDetail.getCid01());
			setString(ps, COL_CID07, beneficiarioDetail.getCid01());
			setString(ps, COL_CID08, beneficiarioDetail.getCid01());
			setString(ps, COL_CID09, beneficiarioDetail.getCid01());
			setString(ps, COL_CID10, beneficiarioDetail.getCid01());
			setString(ps, COL_IBGE, beneficiarioDetail.getIbge());
			setString(ps, COL_CBO, beneficiarioDetail.getCbo());
			setString(ps, COL_DIF_TRANSF, beneficiarioDetail.getDifTransferencia());

			setString(ps, COL_DESCR_PROFISSAO, beneficiarioDetail.getProfissao());
			setLong(ps, COL_NR_MATRICULA_ESPECIAL, beneficiarioDetail.getMatriculaEspecial());
			setBigDecimal(ps, COL_VL_FATOR_MODERADOR, beneficiarioDetail.getFatorModerador());
			setString(ps, COL_SUBFATURA, beneficiarioDetail.getSubFatura());
		}
	}

	private void setRg(PreparedStatement ps, Rg rg) throws SQLException {
		if (rg != null) {
			setString(ps, COL_NM_MAE, rg.getMae());
			setString(ps, COL_NR_RG, rg.getNumero());
			setString(ps, COL_ORGAO_EMISSOR_RG, rg.getOrgaoEmissao());
			setString(ps, COL_PAIS_RG, rg.getPais());
			setDate(ps, COL_DT_EMISSAO_RG, rg.getDtEmissao());

			if (rg.getUf() != null) {
				setString(ps, COL_UF_RG, rg.getUf().getId());
			} else {
				setString(ps, COL_UF_RG, null);
			}
		} else {
			setString(ps, COL_NM_MAE, null);
			setString(ps, COL_NR_RG, null);
			setString(ps, COL_ORGAO_EMISSOR_RG, null);
			setString(ps, COL_PAIS_RG, null);
			setDate(ps, COL_DT_EMISSAO_RG, null);
			setString(ps, COL_UF_RG, null);
		}
	}

	private void setTransferencia(PreparedStatement ps, Transferencia transferencia) throws SQLException {
		if (transferencia != null) {
			setInt(ps, COL_CD_EMPRESA_TRANSF, transferencia.getCdEmpresaTransferencia());
			setLong(ps, COL_NR_MATRICULA_TRANSF, transferencia.getCdMatriculaTransferencia());
			setInt(ps, COL_NR_LOCAL_TRANSF, transferencia.getLocalTransferecia());
			setInt(ps, COL_NR_CATEGORIA_TRANSF, transferencia.getCategoriaTransferencia());
			setString(ps, COL_CD_PLANO_TRANSF, transferencia.getPlanoTransferecia());
			setString(ps, COL_MOTIVO_REMISSAO, transferencia.getMotivoRemissao());
			setLong(ps, COL_NR_CPF_NOVO_TITULAR, transferencia.getCpfNovoTitular());
			setInt(ps, COL_QTDE_PERM_MESES, transferencia.getQtdePermanenciaMeses());
			setInt(ps, COL_RDP_NOVO_TITULAR, transferencia.getRdpNovoTitular());
			setDate(ps, COL_DT_INICIO_TRANSF, transferencia.getDtInicioTransferencia());
			setString(ps, COL_CD_STATUS, transferencia.getCdStatus());
			setString(ps, COL_CD_ERROR, transferencia.getCdError());
			setInt(ps, COL_CD_DV, transferencia.getCdDv());
			setString(ps, COL_CPT, transferencia.getCpt());
			setInt(ps, COL_CD_EMPRESA_TITULAR, transferencia.getCdEmpresaTitular());
			setString(ps, COL_DIF_MATRICULA_TITULAR, transferencia.getDiferenciadorMatriculaTitular());
		} else {
			setInt(ps, COL_CD_EMPRESA_TRANSF, null);
			setLong(ps, COL_NR_MATRICULA_TRANSF, null);
			setInt(ps, COL_NR_LOCAL_TRANSF, null);
			setInt(ps, COL_NR_CATEGORIA_TRANSF, null);
			setString(ps, COL_CD_PLANO_TRANSF, null);
			setString(ps, COL_MOTIVO_REMISSAO, null);
			setLong(ps, COL_NR_CPF_NOVO_TITULAR, null);
			setInt(ps, COL_QTDE_PERM_MESES, null);
			setInt(ps, COL_RDP_NOVO_TITULAR, null);
			setDate(ps, COL_DT_INICIO_TRANSF, null);
			setString(ps, COL_CD_STATUS, null);
			setString(ps, COL_CD_ERROR, null);
			setInt(ps, COL_CD_DV, null);
			setString(ps, COL_CPT, null);
			setInt(ps, COL_CD_EMPRESA_TITULAR, null);
			setString(ps, COL_DIF_MATRICULA_TITULAR, null);
		}
	}

	private void setTelefone(PreparedStatement ps, Telefone telefone) throws SQLException {
		if (telefone != null) {
			setLong(ps, COL_TEL_RESIDENCIAL, telefone.getResidencial());
			setLong(ps, COL_TEL_COMERCIAL, telefone.getComercial());
			setLong(ps, COL_TEL_CELULAR, telefone.getCelular());
		} else {
			setLong(ps, COL_TEL_RESIDENCIAL, null);
			setLong(ps, COL_TEL_COMERCIAL, null);
			setLong(ps, COL_TEL_CELULAR, null);
		}
	}

	private void setEndereco(PreparedStatement ps, Endereco endereco) throws SQLException {
		if (endereco != null) {
			if (endereco.getType() != null) {
				setString(ps, COL_TP_LOGRADOURO, endereco.getType().getId());
			} else {
				setString(ps, COL_TP_LOGRADOURO, null);
			}

			setString(ps, COL_NM_LOGRADOURO, endereco.getLogradouro());
			setInt(ps, COL_NUM, endereco.getNumero());
			setString(ps, COL_COMPL, endereco.getComplemento());
			setString(ps, COL_BAIRRO, endereco.getBairro());
			setString(ps, COL_MUNICIPIO, endereco.getMunicipio());

			if (endereco.getUf() != null) {
				setString(ps, COL_UF, endereco.getUf().getId());
			} else {
				setString(ps, COL_UF, null);
			}

			setInt(ps, COL_NR_CEP, endereco.getCep());
		} else {
			setString(ps, COL_TP_LOGRADOURO, null);
			setString(ps, COL_NM_LOGRADOURO, null);
			setInt(ps, COL_NUM, null);
			setString(ps, COL_COMPL, null);
			setString(ps, COL_BAIRRO, null);
			setString(ps, COL_MUNICIPIO, null);
			setString(ps, COL_UF, null);
			setInt(ps, COL_NR_CEP, null);
		}
	}

	protected void setDadosBancarios(PreparedStatement ps, DadosBancarios dadosBancarios) throws SQLException {
		if (dadosBancarios != null) {
			setInt(ps, COL_BANCO, dadosBancarios.getBanco());
			setString(ps, COL_AGENCIA, dadosBancarios.getAgencia());
			setString(ps, COL_AGENCIA_DG, dadosBancarios.getAgenciaDg());
			setString(ps, COL_CONTA_CORRENTE, dadosBancarios.getContaCorrente());
			setLong(ps, COL_CPF_CC, dadosBancarios.getCpf());
			setString(ps, COL_NM_TITULAR_CC, dadosBancarios.getNameTitular());
		} else {
			setInt(ps, COL_BANCO, null);
			setString(ps, COL_AGENCIA, null);
			setString(ps, COL_AGENCIA_DG, null);
			setString(ps, COL_CONTA_CORRENTE, null);
			setLong(ps, COL_CPF_CC, null);
			setString(ps, COL_NM_TITULAR_CC, null);
		}
	}
}

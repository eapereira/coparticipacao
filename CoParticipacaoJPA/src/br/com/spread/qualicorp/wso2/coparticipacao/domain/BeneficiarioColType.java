package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * <ul>
 * <li>1 - TP_BENEFICIARIO</li>
 * <li>2 - NR_MATRICULA_TITULAR</li>
 * <li>3 - NR_MATRICULA_DEPENDENTE</li>
 * <li>4 - NM_BENEFICIARIO_TITULAR</li>
 * <li>5 - NM_BENEFICIARIO_DEPENDENTE</li>
 * <li>6 - DT_NASCIMENTO</li>
 * <li>7 - NR_CPF_TITULAR</li>
 * <li>8 - NR_CPF_DEPENDENTE</li>
 * <ul>
 * </br>
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum BeneficiarioColType {
									TP_BENEFICIARIO(1, "TP_BENEFICIARIO"),
									NR_MATRICULA(2, "NR_MATRICULA"),
									NM_BENEFICIARIO(3, "NM_BENEFICIARIO"),
									DT_NASCIMENTO(4, "DT_NASCIMENTO"),
									NR_CPF(5, "NR_CPF"),
									DT_ADMISSAO(6, "DT_ADMISSAO"),
									NM_LABEL(7, "NM_LABEL"),
									NR_REFERENCE_CODE(8, "NR_REFERENCE_CODE"),
									NR_DIGITO_CPF(9, "NR_DIGITO_CPF"),
									NR_MATRICULA_EMPRESA(10, "NR_MATRICULA_EMPRESA"),
									DT_DEMISSAO(11, "DT_DEMISSAO"),
									NM_TITULAR(12, "NM_TITULAR"),
									DF(13, "DF"),
									RDP(14, "RDP"),
									NR_LOCAL(15, "NR_LOCAL"),
									CD_CATEGORIA(16, "CD_CATEGORIA"),
									SETOR(17, "SETOR"),
									ES(18, "ES"),
									CD_PLANO(19, "CD_PLANO"),
									DT_INCLUSAO(20, "DT_INCLUSAO"),
									SEXO(21, "SEXO"),
									PERMANENCIA(22, "PERMANENCIA"),
									DT_REF(23, "DT_REF"),
									BANCO(24, "BANCO"),
									AGENCIA(25, "AGENCIA"),
									AGENCIA_DG(26, "AGENCIA_DG"),
									CONTA_CORRENTE(27, "CONTA_CORRENTE"),
									CPF_CC(28, "CPF_CC"),
									NM_TITULAR_CC(29, "NM_TITULAR_CC"),
									CD_CARDIRF(30, "CD_CARDIRF"),
									NR_CEP(31, "NR_CEP"),
									TP_LOGRADOURO(32, "TP_LOGRADOURO"),
									NM_LOGRADOURO(33, "NM_LOGRADOURO"),
									NUM(34, "NUM"),
									COMPL(35, "COMPL"),
									BAIRRO(36, "BAIRRO"),
									MUNICIPIO(37, "MUNICIPIO"),
									UF(38, "UF"),
									TEL_RESIDENCIAL(39, "TEL_RESIDENCIAL"),
									TEL_COMERCIAL(40, "TEL_COMERCIAL"),
									TEL_CELULAR(41, "TEL_CELULAR"),
									NM_MAE(42, "NM_MAE"),
									NR_RG(43, "NR_RG"),
									ORGAO_EMISSOR_RG(44, "ORGAO_EMISSOR_RG"),
									PAIS_RG(45, "PAIS_RG"),
									DT_EMISSAO_RG(46, "DT_EMISSAO_RG"),
									UF_RG(47, "UF_RG"),
									PIS(48, "PIS"),
									CNS(49, "CNS"),
									EMAIL(50, "EMAIL"),
									GRAU_ESCOLARIDADE(51, "GRAU_ESCOLARIDADE"),
									RENDA_FAMILIAR(52, "RENDA_FAMILIAR"),
									CD_PROFISSAO(53, "CD_PROFISSAO"),
									CD_PAIS_ORIGEM(54, "CD_PAIS_ORIGEM"),
									DT_EXCLUSAO(55, "DT_EXCLUSAO"),
									CD_MOTIVO_EXCLUSAO(56, "CD_MOTIVO_EXCLUSAO"),
									CD_OPERACAO(57, "CD_OPERACAO"),
									CD_EMPRESA_TRANSF(58, "CD_EMPRESA_TRANSF"),
									NR_MATRICULA_TRANSF(59, "NR_MATRICULA_TRANSF"),
									NR_LOCAL_TRANSF(60, "NR_LOCAL_TRANSF"),
									NR_CATEGORIA_TRANSF(61, "NR_CATEGORIA_TRANSF"),
									CD_PLANO_TRANSF(62, "CD_PLANO_TRANSF"),
									MOTIVO_REMISSAO(63, "MOTIVO_REMISSAO"),
									NR_CPF_NOVO_TITULAR(64, "NR_CPF_NOVO_TITULAR"),
									QTDE_PERM_MESES(65, "QTDE_PERM_MESES"),
									RDP_NOVO_TITULAR(66, "RDP_NOVO_TITULAR"),
									DT_INICIO_TRANSF(67, "DT_INICIO_TRANSF"),
									CD_STATUS(68, "CD_STATUS"),
									CD_ERROR(69, "CD_ERROR"),
									CD_DV(70, "CD_DV"),
									BLOQ_EMPRESA_INADIMPLENCIA(71, "BLOQ_EMPRESA_INADIMPLENCIA"),
									CPT(72, "CPT"),
									CD_EMPRESA_TITULAR(73, "CD_EMPRESA_TITULAR"),
									DIF_MATRICULA_TITULAR(74, "DIF_MATRICULA_TITULAR"),
									NR_TITULO_ELEITOR(75, "NR_TITULO_ELEITOR"),
									NR_RIC(76, "NR_RIC"),
									NR_CERTIDAO_NASCIMENTO(77, "NR_CERTIDAO_NASCIMENTO"),
									NR_CARTEIRA_IDENT(78, "NR_CARTEIRA_IDENT"),
									IND_SEGURADO_CONTRIBUTARIO(79, "IND_SEGURADO_CONTRIBUTARIO"),
									IND_COND_EX_EMPREGADO(80, "IND_COND_EX_EMPREGADO"),
									IND_PERM_PLANO(81, "IND_PERM_PLANO"),
									QTDE_MESES_CONTRIB(82, "QTDE_MESES_CONTRIB"),
									NM_BENEFICIARIO_COMPLETO(83, "NM_BENEFICIARIO_COMPLETO"),
									IND_TITULAR_REMIDO(84, "IND_TITULAR_REMIDO"),
									EMAIL_SEGURADORA(85, "EMAIL_SEGURADORA"),
									IND_PORTABILIDADE_01(86, "IND_PORTABILIDADE_01"),
									IND_PORTABILIDADE_02(87, "IND_PORTABILIDADE_02"),
									IND_CARENCIA(88, "IND_CARENCIA"),
									CD_PRODUTO(89, "CD_PRODUTO"),
									CD_IND_PRODUTO_ANTERIOR_SAS(90, "CD_IND_PRODUTO_ANTERIOR_SAS"),
									CID01(91, "CID01"),
									CID02(92, "CID02"),
									CID03(93, "CID03"),
									CID04(94, "CID04"),
									CID05(95, "CID05"),
									CID06(96, "CID06"),
									CID07(97, "CID07"),
									CID08(98, "CID08"),
									CID09(99, "CID09"),
									CID10(100, "CID10"),
									IBGE(101, "IBGE"),
									CBO(102, "CBO"),
									DIF_TRANSF(103, "DIF_TRANSF"),
									DESCR_PROFISSAO(104, "DESCR_PROFISSAO"),
									NR_MATRICULA_ESPECIAL(105, "NR_MATRICULA_ESPECIAL"),
									VL_FATOR_MODERADOR(106, "VL_FATOR_MODERADOR"),
									CD_CONTRATO(107, "CD_CONTRATO"),
									NR_SUBFATURA(108, "NR_SUBFATURA"),
									VL_FATOR_MODERADOR_INSS(109, "VL_FATOR_MODERADOR_INSS"),
									VL_ALIQUOTA_INSS(110, "VL_ALIQUOTA_INSS"),
									VL_INSS(111, "VL_INSS"),
									VL_LIQUIDO_SINISTRO(112, "VL_LIQUIDO_SINISTRO"),
									IND_EVENTO(113, "IND_EVENTO"),
									CD_USUARIO(114, "CD_USUARIO"),
									NR_CERTIFICADO(115, "NR_CERTIFICADO"),
									NM_ALIAS_01(116, "NM_ALIAS_01"),
									NM_ALIAS_02(117, "NM_ALIAS_01"),
									NM_ALIAS_03(118, "NM_ALIAS_01");

	private Integer id;

	private String description;

	private BeneficiarioColType(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static BeneficiarioColType parse(Integer id) {
		for (BeneficiarioColType mecsasColType : BeneficiarioColType.values()) {
			if (mecsasColType.getId().equals(id)) {
				return mecsasColType;
			}
		}

		return null;
	}

}

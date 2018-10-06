package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.GrauEscolaridadeTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.SexoTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.YesOrNotConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Embeddable
public class BeneficiarioDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3211769447490784528L;

	private Integer df;

	private Integer rdp;

	private Integer local;

	private Integer categoria;

	private String setor;

	private String es;

	private Integer plano;

	private LocalDate dtInclusao;

	private SexoType sexo;

	private String permanencia;

	private LocalDate dtReferencia;

	private DadosBancarios dadosBancarios;

	private String cardif;

	private Endereco endereco;

	private Telefone telefone;

	private Rg rg;

	private String pis;

	private String cns;

	private String email;

	private GrauEscolaridadeType grauEscolaridade;

	private BigDecimal rendaFamiliar;

	private Integer cdProfissao;

	private Integer cdPaisOrigem;

	private LocalDate dtExclusao;

	private Integer cdMotivoExclusao;

	private Integer cdOperacao;

	private Transferencia transferencia;

	private Boolean bloqEmpresaInadimplencia;

	private String tituloEleitor;

	private String ric;

	private String certidaoNascimento;

	private String carteiraIdentificacao;

	private String indicadorSeguradoContributario;

	private String indicadorExEmpregado;

	private String indicadorPermanenciaPlano;

	private Integer qtdeMesesContribuicao;

	private String nameCompletoBeneficiario;

	private String indicadorTitularRemido;

	private String emailSeguradora;

	private String indicadorPoretabilidade1;

	private String indicadorPoretabilidade2;

	private String indicadorCarencia;

	private String cdProduto;

	private String cdPlanoAnteriorSas;

	private String cid01;
	private String cid02;
	private String cid03;
	private String cid04;
	private String cid05;
	private String cid06;
	private String cid07;
	private String cid08;
	private String cid09;
	private String cid10;

	private String ibge;

	private String cbo;

	private String difTransferencia;

	public BeneficiarioDetail() {
		super();

		setEndereco(new Endereco());
		setDadosBancarios(new DadosBancarios());
		setRg(new Rg());
		setTransferencia(new Transferencia());
		setTelefone(new Telefone());
	}

	@Column(name = "NR_DF")
	public Integer getDf() {
		return df;
	}

	public void setDf(Integer df) {
		this.df = df;
	}

	@Column(name = "NR_RDP")
	public Integer getRdp() {
		return rdp;
	}

	public void setRdp(Integer rdp) {
		this.rdp = rdp;
	}

	@Column(name = "NR_LOCAL")
	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	@Column(name = "CD_CATEGORIA")
	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	@Column(name = "NM_SETOR")
	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	@Column(name = "ES")
	public String getEs() {
		return es;
	}

	public void setEs(String es) {
		this.es = es;
	}

	@Column(name = "CD_PLANO")
	public Integer getPlano() {
		return plano;
	}

	public void setPlano(Integer plano) {
		this.plano = plano;
	}

	@Column(name = "DT_INCLUSAO")
	public LocalDate getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(LocalDate dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	@Convert(converter = SexoTypeConverter.class)
	@Column(name = "TP_SEXO")
	public SexoType getSexo() {
		return sexo;
	}

	public void setSexo(SexoType sexo) {
		this.sexo = sexo;
	}

	@Column(name = "PERMANENCIA")
	public String getPermanencia() {
		return permanencia;
	}

	public void setPermanencia(String permanencia) {
		this.permanencia = permanencia;
	}

	@Column(name = "DT_REF")
	public LocalDate getDtReferencia() {
		return dtReferencia;
	}

	public void setDtReferencia(LocalDate dtReferencia) {
		this.dtReferencia = dtReferencia;
	}

	@Embedded
	public DadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	@Column(name = "CD_CARDIF")
	public String getCardif() {
		return cardif;
	}

	public void setCardif(String cardif) {
		this.cardif = cardif;
	}

	@Embedded
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	@Embedded
	public Rg getRg() {
		return rg;
	}

	public void setRg(Rg rg) {
		this.rg = rg;
	}

	@Column(name = "PIS")
	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	@Column(name = "CNS")
	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Convert(converter = GrauEscolaridadeTypeConverter.class)
	@Column(name = "TP_GRAU_ESCOLARIDADE")
	public GrauEscolaridadeType getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public void setGrauEscolaridade(GrauEscolaridadeType grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	@Column(name = "VL_RENDA_FAMILIAR")
	public BigDecimal getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(BigDecimal rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	@Column(name = "CD_PROFISSAO")
	public Integer getCdProfissao() {
		return cdProfissao;
	}

	public void setCdProfissao(Integer cdProfissao) {
		this.cdProfissao = cdProfissao;
	}

	@Column(name = "CD_PAIS_ORIGEM")
	public Integer getCdPaisOrigem() {
		return cdPaisOrigem;
	}

	public void setCdPaisOrigem(Integer cdPaisOrigem) {
		this.cdPaisOrigem = cdPaisOrigem;
	}

	@Column(name = "DT_EXCLUSAO")
	public LocalDate getDtExclusao() {
		return dtExclusao;
	}

	public void setDtExclusao(LocalDate dtExclusao) {
		this.dtExclusao = dtExclusao;
	}

	@Column(name = "CD_MOTIVO_EXCLUSAO")
	public Integer getCdMotivoExclusao() {
		return cdMotivoExclusao;
	}

	public void setCdMotivoExclusao(Integer cdMotivoExclusao) {
		this.cdMotivoExclusao = cdMotivoExclusao;
	}

	@Column(name = "CD_OPERACAO")
	public Integer getCdOperacao() {
		return cdOperacao;
	}

	public void setCdOperacao(Integer cdOperacao) {
		this.cdOperacao = cdOperacao;
	}

	@Embedded
	public Transferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}

	@Convert(converter = YesOrNotConverter.class)
	@Column(name = "BLOQ_EMPRESA_INADIMPLENCIA")
	public Boolean getBloqEmpresaInadimplencia() {
		return bloqEmpresaInadimplencia;
	}

	public void setBloqEmpresaInadimplencia(Boolean bloqEmpresaInadimplencia) {
		this.bloqEmpresaInadimplencia = bloqEmpresaInadimplencia;
	}

	@Column(name = "NR_TITULO_ELEITOR")
	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	@Column(name = "NR_RIC")
	public String getRic() {
		return ric;
	}

	public void setRic(String ric) {
		this.ric = ric;
	}

	@Column(name = "NR_CERTIDAO_NASCIMENTO")
	public String getCertidaoNascimento() {
		return certidaoNascimento;
	}

	public void setCertidaoNascimento(String certidaoNascimento) {
		this.certidaoNascimento = certidaoNascimento;
	}

	@Column(name = "NR_CARTEIRA_IDENT")
	public String getCarteiraIdentificacao() {
		return carteiraIdentificacao;
	}

	public void setCarteiraIdentificacao(String carteiraIdentificacao) {
		this.carteiraIdentificacao = carteiraIdentificacao;
	}

	@Column(name = "IND_SEGURADO_CONTRIBUTARIO")
	public String getIndicadorSeguradoContributario() {
		return indicadorSeguradoContributario;
	}

	public void setIndicadorSeguradoContributario(String indicadorSeguradoContributario) {
		this.indicadorSeguradoContributario = indicadorSeguradoContributario;
	}

	@Column(name = "IND_COND_EX_EMPREGADO")
	public String getIndicadorExEmpregado() {
		return indicadorExEmpregado;
	}

	public void setIndicadorExEmpregado(String indicadorExEmpregado) {
		this.indicadorExEmpregado = indicadorExEmpregado;
	}

	@Column(name = "IND_PERM_PLANO")
	public String getIndicadorPermanenciaPlano() {
		return indicadorPermanenciaPlano;
	}

	public void setIndicadorPermanenciaPlano(String indicadorPermanenciaPlano) {
		this.indicadorPermanenciaPlano = indicadorPermanenciaPlano;
	}

	@Column(name = "QTDE_MESES_CONTRIB")
	public Integer getQtdeMesesContribuicao() {
		return qtdeMesesContribuicao;
	}

	public void setQtdeMesesContribuicao(Integer qtdeMesesContribuicao) {
		this.qtdeMesesContribuicao = qtdeMesesContribuicao;
	}

	@Column(name = "NM_BENEFICIARIO_COMPLETO")
	public String getNameCompletoBeneficiario() {
		return nameCompletoBeneficiario;
	}

	public void setNameCompletoBeneficiario(String nameCompletoBeneficiario) {
		this.nameCompletoBeneficiario = nameCompletoBeneficiario;
	}

	@Column(name = "IND_TITULAR_REMIDO")
	public String getIndicadorTitularRemido() {
		return indicadorTitularRemido;
	}

	public void setIndicadorTitularRemido(String indicadorTitularRemido) {
		this.indicadorTitularRemido = indicadorTitularRemido;
	}

	@Column(name = "EMAIL_SEGURADORA")
	public String getEmailSeguradora() {
		return emailSeguradora;
	}

	public void setEmailSeguradora(String emailSeguradora) {
		this.emailSeguradora = emailSeguradora;
	}

	@Column(name = "IND_PORTABILIDADE_01")
	public String getIndicadorPoretabilidade1() {
		return indicadorPoretabilidade1;
	}

	public void setIndicadorPoretabilidade1(String indicadorPoretabilidade1) {
		this.indicadorPoretabilidade1 = indicadorPoretabilidade1;
	}

	@Column(name = "IND_PORTABILIDADE_02")
	public String getIndicadorPoretabilidade2() {
		return indicadorPoretabilidade2;
	}

	public void setIndicadorPoretabilidade2(String indicadorPoretabilidade2) {
		this.indicadorPoretabilidade2 = indicadorPoretabilidade2;
	}

	@Column(name = "IND_CARENCIA")
	public String getIndicadorCarencia() {
		return indicadorCarencia;
	}

	public void setIndicadorCarencia(String indicadorCarencia) {
		this.indicadorCarencia = indicadorCarencia;
	}

	@Column(name = "CD_PRODUTO")
	public String getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(String cdProduto) {
		this.cdProduto = cdProduto;
	}

	@Column(name = "CD_IND_PLANO_ANTERIOR_SAS")
	public String getCdPlanoAnteriorSas() {
		return cdPlanoAnteriorSas;
	}

	public void setCdPlanoAnteriorSas(String cdPlanoAnteriorSas) {
		this.cdPlanoAnteriorSas = cdPlanoAnteriorSas;
	}

	@Column(name = "CID01")
	public String getCid01() {
		return cid01;
	}

	public void setCid01(String cid01) {
		this.cid01 = cid01;
	}

	@Column(name = "CID02")
	public String getCid02() {
		return cid02;
	}

	public void setCid02(String cid02) {
		this.cid02 = cid02;
	}

	@Column(name = "CID03")
	public String getCid03() {
		return cid03;
	}

	public void setCid03(String cid03) {
		this.cid03 = cid03;
	}

	@Column(name = "CID04")
	public String getCid04() {
		return cid04;
	}

	public void setCid04(String cid04) {
		this.cid04 = cid04;
	}

	@Column(name = "CID05")
	public String getCid05() {
		return cid05;
	}

	public void setCid05(String cid05) {
		this.cid05 = cid05;
	}

	@Column(name = "CID06")
	public String getCid06() {
		return cid06;
	}

	public void setCid06(String cid06) {
		this.cid06 = cid06;
	}

	@Column(name = "CID07")
	public String getCid07() {
		return cid07;
	}

	public void setCid07(String cid07) {
		this.cid07 = cid07;
	}

	@Column(name = "CID08")
	public String getCid08() {
		return cid08;
	}

	public void setCid08(String cid08) {
		this.cid08 = cid08;
	}

	@Column(name = "CID09")
	public String getCid09() {
		return cid09;
	}

	public void setCid09(String cid09) {
		this.cid09 = cid09;
	}

	@Column(name = "CID10")
	public String getCid10() {
		return cid10;
	}

	public void setCid10(String cid10) {
		this.cid10 = cid10;
	}

	@Column(name = "IBGE")
	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	@Column(name = "CBO")
	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo;
	}

	@Column(name = "DIF_TRANSF")
	public String getDifTransferencia() {
		return difTransferencia;
	}

	public void setDifTransferencia(String difTransferencia) {
		this.difTransferencia = difTransferencia;
	}
}

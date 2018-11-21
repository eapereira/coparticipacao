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

	private String plano;

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

	private String profissao;
	private Long matriculaEspecial;
	private BigDecimal fatorModerador;

	private Integer subFatura;

	private BigDecimal fatorModeradorInss;

	private BigDecimal valorInss;
	private BigDecimal valorAliquotaInss;
	private BigDecimal valorLiquidoSinistro;
	
	private Integer indicadorEvento;

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
	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
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

	@Column(name = "DESCR_PROFISSAO")
	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	@Column(name = "NR_MATRICULA_ESPECIAL")
	public Long getMatriculaEspecial() {
		return matriculaEspecial;
	}

	public void setMatriculaEspecial(Long matriculaEspecial) {
		this.matriculaEspecial = matriculaEspecial;
	}

	@Column(name = "VL_FATOR_MODERADOR")
	public BigDecimal getFatorModerador() {
		return fatorModerador;
	}

	public void setFatorModerador(BigDecimal fatorModerador) {
		this.fatorModerador = fatorModerador;
	}

	@Column(name = "NR_SUBFATURA")
	public Integer getSubFatura() {
		return subFatura;
	}

	public void setSubFatura(Integer subFatura) {
		this.subFatura = subFatura;
	}

	@Column(name = "VL_FATOR_MODERADOR_INSS")
	public BigDecimal getFatorModeradorInss() {
		return fatorModeradorInss;
	}

	public void setFatorModeradorInss(BigDecimal fatorModeradorInss) {
		this.fatorModeradorInss = fatorModeradorInss;
	}

	@Column(name = "VL_INSS")
	public BigDecimal getValorInss() {
		return valorInss;
	}

	public void setValorInss(BigDecimal valorInss) {
		this.valorInss = valorInss;
	}

	@Column(name = "VL_LIQUIDO_SINISTRO")
	public BigDecimal getValorLiquidoSinistro() {
		return valorLiquidoSinistro;
	}

	public void setValorLiquidoSinistro(BigDecimal valorLiquidoSinistro) {
		this.valorLiquidoSinistro = valorLiquidoSinistro;
	}

	@Column(name = "VL_ALIQUOTA_INSS")
	public BigDecimal getValorAliquotaInss() {
		return valorAliquotaInss;
	}

	public void setValorAliquotaInss(BigDecimal valorAliquotaInss) {
		this.valorAliquotaInss = valorAliquotaInss;
	}

	@Column(name = "IND_EVENTO")
	public Integer getIndicadorEvento() {
		return indicadorEvento;
	}

	public void setIndicadorEvento(Integer indicadorEvento) {
		this.indicadorEvento = indicadorEvento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloqEmpresaInadimplencia == null) ? 0 : bloqEmpresaInadimplencia.hashCode());
		result = prime * result + ((cardif == null) ? 0 : cardif.hashCode());
		result = prime * result + ((carteiraIdentificacao == null) ? 0 : carteiraIdentificacao.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((cbo == null) ? 0 : cbo.hashCode());
		result = prime * result + ((cdMotivoExclusao == null) ? 0 : cdMotivoExclusao.hashCode());
		result = prime * result + ((cdOperacao == null) ? 0 : cdOperacao.hashCode());
		result = prime * result + ((cdPaisOrigem == null) ? 0 : cdPaisOrigem.hashCode());
		result = prime * result + ((cdPlanoAnteriorSas == null) ? 0 : cdPlanoAnteriorSas.hashCode());
		result = prime * result + ((cdProduto == null) ? 0 : cdProduto.hashCode());
		result = prime * result + ((cdProfissao == null) ? 0 : cdProfissao.hashCode());
		result = prime * result + ((certidaoNascimento == null) ? 0 : certidaoNascimento.hashCode());
		result = prime * result + ((cid01 == null) ? 0 : cid01.hashCode());
		result = prime * result + ((cid02 == null) ? 0 : cid02.hashCode());
		result = prime * result + ((cid03 == null) ? 0 : cid03.hashCode());
		result = prime * result + ((cid04 == null) ? 0 : cid04.hashCode());
		result = prime * result + ((cid05 == null) ? 0 : cid05.hashCode());
		result = prime * result + ((cid06 == null) ? 0 : cid06.hashCode());
		result = prime * result + ((cid07 == null) ? 0 : cid07.hashCode());
		result = prime * result + ((cid08 == null) ? 0 : cid08.hashCode());
		result = prime * result + ((cid09 == null) ? 0 : cid09.hashCode());
		result = prime * result + ((cid10 == null) ? 0 : cid10.hashCode());
		result = prime * result + ((cns == null) ? 0 : cns.hashCode());
		result = prime * result + ((dadosBancarios == null) ? 0 : dadosBancarios.hashCode());
		result = prime * result + ((df == null) ? 0 : df.hashCode());
		result = prime * result + ((difTransferencia == null) ? 0 : difTransferencia.hashCode());
		result = prime * result + ((dtExclusao == null) ? 0 : dtExclusao.hashCode());
		result = prime * result + ((dtInclusao == null) ? 0 : dtInclusao.hashCode());
		result = prime * result + ((dtReferencia == null) ? 0 : dtReferencia.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emailSeguradora == null) ? 0 : emailSeguradora.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((es == null) ? 0 : es.hashCode());
		result = prime * result + ((fatorModerador == null) ? 0 : fatorModerador.hashCode());
		result = prime * result + ((fatorModeradorInss == null) ? 0 : fatorModeradorInss.hashCode());
		result = prime * result + ((grauEscolaridade == null) ? 0 : grauEscolaridade.hashCode());
		result = prime * result + ((ibge == null) ? 0 : ibge.hashCode());
		result = prime * result + ((indicadorCarencia == null) ? 0 : indicadorCarencia.hashCode());
		result = prime * result + ((indicadorEvento == null) ? 0 : indicadorEvento.hashCode());
		result = prime * result + ((indicadorExEmpregado == null) ? 0 : indicadorExEmpregado.hashCode());
		result = prime * result + ((indicadorPermanenciaPlano == null) ? 0 : indicadorPermanenciaPlano.hashCode());
		result = prime * result + ((indicadorPoretabilidade1 == null) ? 0 : indicadorPoretabilidade1.hashCode());
		result = prime * result + ((indicadorPoretabilidade2 == null) ? 0 : indicadorPoretabilidade2.hashCode());
		result = prime * result
				+ ((indicadorSeguradoContributario == null) ? 0 : indicadorSeguradoContributario.hashCode());
		result = prime * result + ((indicadorTitularRemido == null) ? 0 : indicadorTitularRemido.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((matriculaEspecial == null) ? 0 : matriculaEspecial.hashCode());
		result = prime * result + ((nameCompletoBeneficiario == null) ? 0 : nameCompletoBeneficiario.hashCode());
		result = prime * result + ((permanencia == null) ? 0 : permanencia.hashCode());
		result = prime * result + ((pis == null) ? 0 : pis.hashCode());
		result = prime * result + ((plano == null) ? 0 : plano.hashCode());
		result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
		result = prime * result + ((qtdeMesesContribuicao == null) ? 0 : qtdeMesesContribuicao.hashCode());
		result = prime * result + ((rdp == null) ? 0 : rdp.hashCode());
		result = prime * result + ((rendaFamiliar == null) ? 0 : rendaFamiliar.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((ric == null) ? 0 : ric.hashCode());
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((subFatura == null) ? 0 : subFatura.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tituloEleitor == null) ? 0 : tituloEleitor.hashCode());
		result = prime * result + ((transferencia == null) ? 0 : transferencia.hashCode());
		result = prime * result + ((valorAliquotaInss == null) ? 0 : valorAliquotaInss.hashCode());
		result = prime * result + ((valorInss == null) ? 0 : valorInss.hashCode());
		result = prime * result + ((valorLiquidoSinistro == null) ? 0 : valorLiquidoSinistro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeneficiarioDetail other = (BeneficiarioDetail) obj;
		if (bloqEmpresaInadimplencia == null) {
			if (other.bloqEmpresaInadimplencia != null)
				return false;
		} else if (!bloqEmpresaInadimplencia.equals(other.bloqEmpresaInadimplencia))
			return false;
		if (cardif == null) {
			if (other.cardif != null)
				return false;
		} else if (!cardif.equals(other.cardif))
			return false;
		if (carteiraIdentificacao == null) {
			if (other.carteiraIdentificacao != null)
				return false;
		} else if (!carteiraIdentificacao.equals(other.carteiraIdentificacao))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (cbo == null) {
			if (other.cbo != null)
				return false;
		} else if (!cbo.equals(other.cbo))
			return false;
		if (cdMotivoExclusao == null) {
			if (other.cdMotivoExclusao != null)
				return false;
		} else if (!cdMotivoExclusao.equals(other.cdMotivoExclusao))
			return false;
		if (cdOperacao == null) {
			if (other.cdOperacao != null)
				return false;
		} else if (!cdOperacao.equals(other.cdOperacao))
			return false;
		if (cdPaisOrigem == null) {
			if (other.cdPaisOrigem != null)
				return false;
		} else if (!cdPaisOrigem.equals(other.cdPaisOrigem))
			return false;
		if (cdPlanoAnteriorSas == null) {
			if (other.cdPlanoAnteriorSas != null)
				return false;
		} else if (!cdPlanoAnteriorSas.equals(other.cdPlanoAnteriorSas))
			return false;
		if (cdProduto == null) {
			if (other.cdProduto != null)
				return false;
		} else if (!cdProduto.equals(other.cdProduto))
			return false;
		if (cdProfissao == null) {
			if (other.cdProfissao != null)
				return false;
		} else if (!cdProfissao.equals(other.cdProfissao))
			return false;
		if (certidaoNascimento == null) {
			if (other.certidaoNascimento != null)
				return false;
		} else if (!certidaoNascimento.equals(other.certidaoNascimento))
			return false;
		if (cid01 == null) {
			if (other.cid01 != null)
				return false;
		} else if (!cid01.equals(other.cid01))
			return false;
		if (cid02 == null) {
			if (other.cid02 != null)
				return false;
		} else if (!cid02.equals(other.cid02))
			return false;
		if (cid03 == null) {
			if (other.cid03 != null)
				return false;
		} else if (!cid03.equals(other.cid03))
			return false;
		if (cid04 == null) {
			if (other.cid04 != null)
				return false;
		} else if (!cid04.equals(other.cid04))
			return false;
		if (cid05 == null) {
			if (other.cid05 != null)
				return false;
		} else if (!cid05.equals(other.cid05))
			return false;
		if (cid06 == null) {
			if (other.cid06 != null)
				return false;
		} else if (!cid06.equals(other.cid06))
			return false;
		if (cid07 == null) {
			if (other.cid07 != null)
				return false;
		} else if (!cid07.equals(other.cid07))
			return false;
		if (cid08 == null) {
			if (other.cid08 != null)
				return false;
		} else if (!cid08.equals(other.cid08))
			return false;
		if (cid09 == null) {
			if (other.cid09 != null)
				return false;
		} else if (!cid09.equals(other.cid09))
			return false;
		if (cid10 == null) {
			if (other.cid10 != null)
				return false;
		} else if (!cid10.equals(other.cid10))
			return false;
		if (cns == null) {
			if (other.cns != null)
				return false;
		} else if (!cns.equals(other.cns))
			return false;
		if (dadosBancarios == null) {
			if (other.dadosBancarios != null)
				return false;
		} else if (!dadosBancarios.equals(other.dadosBancarios))
			return false;
		if (df == null) {
			if (other.df != null)
				return false;
		} else if (!df.equals(other.df))
			return false;
		if (difTransferencia == null) {
			if (other.difTransferencia != null)
				return false;
		} else if (!difTransferencia.equals(other.difTransferencia))
			return false;
		if (dtExclusao == null) {
			if (other.dtExclusao != null)
				return false;
		} else if (!dtExclusao.equals(other.dtExclusao))
			return false;
		if (dtInclusao == null) {
			if (other.dtInclusao != null)
				return false;
		} else if (!dtInclusao.equals(other.dtInclusao))
			return false;
		if (dtReferencia == null) {
			if (other.dtReferencia != null)
				return false;
		} else if (!dtReferencia.equals(other.dtReferencia))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailSeguradora == null) {
			if (other.emailSeguradora != null)
				return false;
		} else if (!emailSeguradora.equals(other.emailSeguradora))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (es == null) {
			if (other.es != null)
				return false;
		} else if (!es.equals(other.es))
			return false;
		if (fatorModerador == null) {
			if (other.fatorModerador != null)
				return false;
		} else if (!fatorModerador.equals(other.fatorModerador))
			return false;
		if (fatorModeradorInss == null) {
			if (other.fatorModeradorInss != null)
				return false;
		} else if (!fatorModeradorInss.equals(other.fatorModeradorInss))
			return false;
		if (grauEscolaridade != other.grauEscolaridade)
			return false;
		if (ibge == null) {
			if (other.ibge != null)
				return false;
		} else if (!ibge.equals(other.ibge))
			return false;
		if (indicadorCarencia == null) {
			if (other.indicadorCarencia != null)
				return false;
		} else if (!indicadorCarencia.equals(other.indicadorCarencia))
			return false;
		if (indicadorEvento == null) {
			if (other.indicadorEvento != null)
				return false;
		} else if (!indicadorEvento.equals(other.indicadorEvento))
			return false;
		if (indicadorExEmpregado == null) {
			if (other.indicadorExEmpregado != null)
				return false;
		} else if (!indicadorExEmpregado.equals(other.indicadorExEmpregado))
			return false;
		if (indicadorPermanenciaPlano == null) {
			if (other.indicadorPermanenciaPlano != null)
				return false;
		} else if (!indicadorPermanenciaPlano.equals(other.indicadorPermanenciaPlano))
			return false;
		if (indicadorPoretabilidade1 == null) {
			if (other.indicadorPoretabilidade1 != null)
				return false;
		} else if (!indicadorPoretabilidade1.equals(other.indicadorPoretabilidade1))
			return false;
		if (indicadorPoretabilidade2 == null) {
			if (other.indicadorPoretabilidade2 != null)
				return false;
		} else if (!indicadorPoretabilidade2.equals(other.indicadorPoretabilidade2))
			return false;
		if (indicadorSeguradoContributario == null) {
			if (other.indicadorSeguradoContributario != null)
				return false;
		} else if (!indicadorSeguradoContributario.equals(other.indicadorSeguradoContributario))
			return false;
		if (indicadorTitularRemido == null) {
			if (other.indicadorTitularRemido != null)
				return false;
		} else if (!indicadorTitularRemido.equals(other.indicadorTitularRemido))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (matriculaEspecial == null) {
			if (other.matriculaEspecial != null)
				return false;
		} else if (!matriculaEspecial.equals(other.matriculaEspecial))
			return false;
		if (nameCompletoBeneficiario == null) {
			if (other.nameCompletoBeneficiario != null)
				return false;
		} else if (!nameCompletoBeneficiario.equals(other.nameCompletoBeneficiario))
			return false;
		if (permanencia == null) {
			if (other.permanencia != null)
				return false;
		} else if (!permanencia.equals(other.permanencia))
			return false;
		if (pis == null) {
			if (other.pis != null)
				return false;
		} else if (!pis.equals(other.pis))
			return false;
		if (plano == null) {
			if (other.plano != null)
				return false;
		} else if (!plano.equals(other.plano))
			return false;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		if (qtdeMesesContribuicao == null) {
			if (other.qtdeMesesContribuicao != null)
				return false;
		} else if (!qtdeMesesContribuicao.equals(other.qtdeMesesContribuicao))
			return false;
		if (rdp == null) {
			if (other.rdp != null)
				return false;
		} else if (!rdp.equals(other.rdp))
			return false;
		if (rendaFamiliar == null) {
			if (other.rendaFamiliar != null)
				return false;
		} else if (!rendaFamiliar.equals(other.rendaFamiliar))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (ric == null) {
			if (other.ric != null)
				return false;
		} else if (!ric.equals(other.ric))
			return false;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		if (sexo != other.sexo)
			return false;
		if (subFatura == null) {
			if (other.subFatura != null)
				return false;
		} else if (!subFatura.equals(other.subFatura))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tituloEleitor == null) {
			if (other.tituloEleitor != null)
				return false;
		} else if (!tituloEleitor.equals(other.tituloEleitor))
			return false;
		if (transferencia == null) {
			if (other.transferencia != null)
				return false;
		} else if (!transferencia.equals(other.transferencia))
			return false;
		if (valorAliquotaInss == null) {
			if (other.valorAliquotaInss != null)
				return false;
		} else if (!valorAliquotaInss.equals(other.valorAliquotaInss))
			return false;
		if (valorInss == null) {
			if (other.valorInss != null)
				return false;
		} else if (!valorInss.equals(other.valorInss))
			return false;
		if (valorLiquidoSinistro == null) {
			if (other.valorLiquidoSinistro != null)
				return false;
		} else if (!valorLiquidoSinistro.equals(other.valorLiquidoSinistro))
			return false;
		return true;
	}

}

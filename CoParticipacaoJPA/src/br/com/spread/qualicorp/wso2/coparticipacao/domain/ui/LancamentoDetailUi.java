package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ValorType;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class LancamentoDetailUi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2153157832283657772L;

	private String nameBeneficiario;
	private Long matriculaDependente;
	private Long matriculaTitular;
	private Long cpf;
	private BigDecimal valorPrincipal;
	private Integer mes;
	private Integer ano;
	private LocalDate dtMovimento;
	private ValorType valorType;
	private LocalDate dtNascimento;
	
	private String cdContrato;

	private ContratoUi contratoUi;
	private TitularUi titularUi;
	private DependenteUi dependenteUi;
	
	private String nameTitular;
	
	private BigDecimal valorReembolso;
	private BigDecimal valorParticipacao;
	
	private String cdUsuario;
	
	private LocalDate dtUtilizacao;
	
	private Integer subFatura;
	
	private String descrUtilizacao;
	
	private String matriculaEspecial;

	private Integer local;
	
	private Long matriculaEmpresa;
	
	public LancamentoDetailUi() {
		super();
	}

	public Long getMatriculaDependente() {
		return matriculaDependente;
	}

	public void setMatriculaDependente(Long matriculaDependente) {
		this.matriculaDependente = matriculaDependente;
	}

	public Long getMatriculaTitular() {
		return matriculaTitular;
	}

	public void setMatriculaTitular(Long matriculaTitular) {
		this.matriculaTitular = matriculaTitular;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public LocalDate getDtMovimento() {
		return dtMovimento;
	}

	public void setDtMovimento(LocalDate dtMovimento) {
		this.dtMovimento = dtMovimento;
	}

	public ValorType getValorType() {
		return valorType;
	}

	public void setValorType(ValorType valorType) {
		this.valorType = valorType;
	}

	public ContratoUi getContratoUi() {
		return contratoUi;
	}

	public void setContratoUi(ContratoUi contratoUi) {
		this.contratoUi = contratoUi;
	}

	public String getNameBeneficiario() {
		return nameBeneficiario;
	}

	public void setNameBeneficiario(String nameBeneficiario) {
		this.nameBeneficiario = nameBeneficiario;
	}

	public TitularUi getTitularUi() {
		return titularUi;
	}

	public void setTitularUi(TitularUi titularUi) {
		this.titularUi = titularUi;
	}

	public DependenteUi getDependenteUi() {
		return dependenteUi;
	}

	public void setDependenteUi(DependenteUi dependenteUi) {
		this.dependenteUi = dependenteUi;
	}

	public String getNameTitular() {
		return nameTitular;
	}

	public void setNameTitular(String nameTitular) {
		this.nameTitular = nameTitular;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public BigDecimal getValorReembolso() {
		return valorReembolso;
	}

	public void setValorReembolso(BigDecimal valorReembolso) {
		this.valorReembolso = valorReembolso;
	}

	public BigDecimal getValorParticipacao() {
		return valorParticipacao;
	}

	public void setValorParticipacao(BigDecimal valorParticipacao) {
		this.valorParticipacao = valorParticipacao;
	}

	public String getCdContrato() {
		return cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public LocalDate getDtUtilizacao() {
		return dtUtilizacao;
	}

	public void setDtUtilizacao(LocalDate dtUtilizacao) {
		this.dtUtilizacao = dtUtilizacao;
	}

	public Integer getSubFatura() {
		return subFatura;
	}

	public void setSubFatura(Integer subFatura) {
		this.subFatura = subFatura;
	}

	public String getDescrUtilizacao() {
		return descrUtilizacao;
	}

	public void setDescrUtilizacao(String utilizacao) {
		this.descrUtilizacao = utilizacao;
	}

	public String getMatriculaEspecial() {
		return matriculaEspecial;
	}

	public void setMatriculaEspecial(String matriculaEspecial) {
		this.matriculaEspecial = matriculaEspecial;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public Long getMatriculaEmpresa() {
		return matriculaEmpresa;
	}

	public void setMatriculaEmpresa(Long matriculaEmpresa) {
		this.matriculaEmpresa = matriculaEmpresa;
	}

}

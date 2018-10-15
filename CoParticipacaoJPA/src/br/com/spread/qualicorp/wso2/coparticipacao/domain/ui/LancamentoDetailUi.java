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

	private ContratoUi contratoUi;
	private TitularUi titularUi;
	private DependenteUi dependenteUi;
	
	private String nameTitular;

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

}

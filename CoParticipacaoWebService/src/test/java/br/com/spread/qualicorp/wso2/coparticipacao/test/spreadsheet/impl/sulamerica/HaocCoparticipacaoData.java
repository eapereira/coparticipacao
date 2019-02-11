package br.com.spread.qualicorp.wso2.coparticipacao.test.spreadsheet.impl.sulamerica;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class HaocCoparticipacaoData {

	private Long cdTitular;
	private Long cdDependente;
	private String nameBeneficiario;
	private BigDecimal valorPrincipal;
	private Integer local;	
	private LocalDate dtNascimento;
	private Long cpf;
	private Long matricula;
	
	public HaocCoparticipacaoData() {
		
	}

	public Long getCdTitular() {
		return cdTitular;
	}

	public void setCdTitular(Long cdTitular) {
		this.cdTitular = cdTitular;
	}

	public Long getCdDependente() {
		return cdDependente;
	}

	public void setCdDependente(Long cdDependente) {
		this.cdDependente = cdDependente;
	}

	public String getNameBeneficiario() {
		return nameBeneficiario;
	}

	public void setNameBeneficiario(String nameBeneficiario) {
		this.nameBeneficiario = nameBeneficiario;
	}

	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
}

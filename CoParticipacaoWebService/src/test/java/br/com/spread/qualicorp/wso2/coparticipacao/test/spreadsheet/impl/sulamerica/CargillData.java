package br.com.spread.qualicorp.wso2.coparticipacao.test.spreadsheet.impl.sulamerica;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class CargillData {

	private String cdContrato;
	private Long matriculaEmpresa;
	private Long cpf;
	private String nameBeneficiario;
	private String cdVerba;
	private Integer cdPlano;
	private String sinal;
	private LocalDate dtAdmissao;
	private BigDecimal valorPrincipal;

	public CargillData() {

	}

	public String getCdContrato() {
		return cdContrato;
	}

	public void setCdContrato(String cdContrato) {
		this.cdContrato = cdContrato;
	}

	public Long getMatriculaEmpresa() {
		return matriculaEmpresa;
	}

	public void setMatriculaEmpresa(Long matriculaEmpresa) {
		this.matriculaEmpresa = matriculaEmpresa;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNameBeneficiario() {
		return nameBeneficiario;
	}

	public void setNameBeneficiario(String nameBeneficiario) {
		this.nameBeneficiario = nameBeneficiario;
	}

	public String getCdVerba() {
		return cdVerba;
	}

	public void setCdVerba(String cdVerba) {
		this.cdVerba = cdVerba;
	}

	public Integer getCdPlano() {
		return cdPlano;
	}

	public void setCdPlano(Integer cdPlano) {
		this.cdPlano = cdPlano;
	}

	public String getSinal() {
		return sinal;
	}

	public void setSinal(String sinal) {
		this.sinal = sinal;
	}

	public LocalDate getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(LocalDate dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public BigDecimal getValorPrincipal() {
		return valorPrincipal;
	}

	public void setValorPrincipal(BigDecimal valorPrincipal) {
		this.valorPrincipal = valorPrincipal;
	}
}

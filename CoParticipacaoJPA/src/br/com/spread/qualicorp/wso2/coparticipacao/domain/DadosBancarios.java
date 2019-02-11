package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Embeddable
public class DadosBancarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1395522438040326306L;

	private Integer banco;
	private String agencia;
	private String agenciaDg;

	private String contaCorrente;

	private Long cpf;

	private String nameTitular;

	public DadosBancarios() {

	}

	@Column(name = "BANCO")
	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	@Column(name = "AGENCIA")
	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	@Column(name = "AGENCIA_DG")
	public String getAgenciaDg() {
		return agenciaDg;
	}

	public void setAgenciaDg(String AgenciaDg) {
		this.agenciaDg = AgenciaDg;
	}

	@Column(name = "CONTA_CORRENTE")
	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	@Column(name = "NR_CPF_CC")
	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	@Column(name = "NM_TITULAR_CC")
	public String getNameTitular() {
		return nameTitular;
	}

	public void setNameTitular(String nameTitular) {
		this.nameTitular = nameTitular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((agenciaDg == null) ? 0 : agenciaDg.hashCode());
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((contaCorrente == null) ? 0 : contaCorrente.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((nameTitular == null) ? 0 : nameTitular.hashCode());
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
		DadosBancarios other = (DadosBancarios) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (agenciaDg == null) {
			if (other.agenciaDg != null)
				return false;
		} else if (!agenciaDg.equals(other.agenciaDg))
			return false;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (contaCorrente == null) {
			if (other.contaCorrente != null)
				return false;
		} else if (!contaCorrente.equals(other.contaCorrente))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (nameTitular == null) {
			if (other.nameTitular != null)
				return false;
		} else if (!nameTitular.equals(other.nameTitular))
			return false;
		return true;
	}

}

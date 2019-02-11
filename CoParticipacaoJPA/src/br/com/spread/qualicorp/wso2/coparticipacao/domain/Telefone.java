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
public class Telefone implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4606468662289887949L;

	private Long comercial;
	private Long residencial;
	private Long celular;
		
	public Telefone() {
		
	}

	@Column(name="TEL_COMERCIAL")
	public Long getComercial() {
		return comercial;
	}

	public void setComercial(Long comercial) {
		this.comercial = comercial;
	}

	@Column(name="TEL_RESIDENCIAL")
	public Long getResidencial() {
		return residencial;
	}

	public void setResidencial(Long residencial) {
		this.residencial = residencial;
	}

	@Column(name="TEL_CELULAR")
	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((comercial == null) ? 0 : comercial.hashCode());
		result = prime * result + ((residencial == null) ? 0 : residencial.hashCode());
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
		Telefone other = (Telefone) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (comercial == null) {
			if (other.comercial != null)
				return false;
		} else if (!comercial.equals(other.comercial))
			return false;
		if (residencial == null) {
			if (other.residencial != null)
				return false;
		} else if (!residencial.equals(other.residencial))
			return false;
		return true;
	}
}

package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.UFTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Embeddable
public class Rg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4633203314693217325L;

	private String numero;

	private String orgaoEmissao;

	private LocalDate dtEmissao;

	private String pais;

	private UFType uf;

	private String mae;

	public Rg() {

	}

	@Column(name = "NR_RG")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "NM_ORGAO_EMISSAO_RG")
	public String getOrgaoEmissao() {
		return orgaoEmissao;
	}

	public void setOrgaoEmissao(String orgaoEmissao) {
		this.orgaoEmissao = orgaoEmissao;
	}

	@Column(name = "DT_EMISSAO_RG")
	public LocalDate getDtEmissao() {
		return dtEmissao;
	}

	public void setDtEmissao(LocalDate dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	@Column(name = "NM_PAIS_RG")
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Convert(converter=UFTypeConverter.class)
	@Column(name = "UF_RG")
	public UFType getUf() {
		return uf;
	}

	public void setUf(UFType uf) {
		this.uf = uf;
	}

	@Column(name = "NM_MAE")
	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtEmissao == null) ? 0 : dtEmissao.hashCode());
		result = prime * result + ((mae == null) ? 0 : mae.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((orgaoEmissao == null) ? 0 : orgaoEmissao.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Rg other = (Rg) obj;
		if (dtEmissao == null) {
			if (other.dtEmissao != null)
				return false;
		} else if (!dtEmissao.equals(other.dtEmissao))
			return false;
		if (mae == null) {
			if (other.mae != null)
				return false;
		} else if (!mae.equals(other.mae))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (orgaoEmissao == null) {
			if (other.orgaoEmissao != null)
				return false;
		} else if (!orgaoEmissao.equals(other.orgaoEmissao))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (uf != other.uf)
			return false;
		return true;
	}
}

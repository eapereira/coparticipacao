package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.EnderecoTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.UFTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Embeddable
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 114081190211155892L;

	private EnderecoType type;

	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String municipio;
	private UFType uf;
	
	private Integer cep;

	public Endereco() {

	}

	@Convert(converter = EnderecoTypeConverter.class)
	@Column(name = "TP_LOGRADOURO")
	public EnderecoType getType() {
		return type;
	}

	public void setType(EnderecoType type) {
		this.type = type;
	}

	@Column(name = "NM_LOGRADOURO")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "NUM_LOGRADOURO")
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Column(name = "COMPL")
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Column(name = "NM_BAIRRO")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "NM_MUNICIPIO")
	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Convert(converter = UFTypeConverter.class)
	@Column(name = "UF")
	public UFType getUf() {
		return uf;
	}

	public void setUf(UFType uf) {
		this.uf = uf;
	}

	@Column(name = "NR_CEP")
	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}
}

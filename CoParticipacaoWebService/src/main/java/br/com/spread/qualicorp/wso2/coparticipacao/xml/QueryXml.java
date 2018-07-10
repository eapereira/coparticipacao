package br.com.spread.qualicorp.wso2.coparticipacao.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@XmlRootElement(name = "query")
@XmlAccessorType (XmlAccessType.FIELD)
public class QueryXml {
	
	@XmlAttribute
	private String id;
	
	@XmlValue
	private String value;

	public QueryXml() {

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

package br.com.spread.qualicorp.wso2.coparticipacao.xml.execucao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@XmlRootElement(name = "execucao")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExecucaoXml {

	@XmlAttribute(name = "id")
	private Long execucaoId;

	public ExecucaoXml() {

	}

	public Long getExecucaoId() {
		return execucaoId;
	}

	public void setExecucaoId(Long execucaoId) {
		this.execucaoId = execucaoId;
	}
}

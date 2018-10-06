package br.com.spread.qualicorp.wso2.coparticipacao.xml.execucao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobXml {

	@XmlElement(name = "execucao")
	private List<ExecucaoXml> execucaoXmls;

	public JobXml() {
		execucaoXmls = new ArrayList<ExecucaoXml>();
	}

	public List<ExecucaoXml> getExecucaoXmls() {
		return execucaoXmls;
	}

	public void setExecucaoXmls(List<ExecucaoXml> execucaoXmls) {
		this.execucaoXmls = execucaoXmls;
	}

	public void addExecucao(ExecucaoUi execucaoUi) {
		ExecucaoXml execucaoXml;

		if (execucaoUi != null) {
			execucaoXml = new ExecucaoXml();
			execucaoXml.setExecucaoId(execucaoUi.getId());

			getExecucaoXmls().add(execucaoXml);
		}
	}
}

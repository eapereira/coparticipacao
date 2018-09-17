package br.com.spread.qualicorp.wso2.coparticipacao.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@XmlRootElement(name = "queries")
@XmlAccessorType (XmlAccessType.FIELD)
public class QueriesXml {
	
	@XmlElement(name = "query")
	private List<QueryXml> queries;
	
	public QueriesXml() {

	}

	public List<QueryXml> getQueries() {
		return queries;
	}

	public void setQueries(List<QueryXml> queries) {
		this.queries = queries;
	}
}

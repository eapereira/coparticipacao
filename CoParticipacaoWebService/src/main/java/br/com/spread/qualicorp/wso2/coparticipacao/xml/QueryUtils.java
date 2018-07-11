package br.com.spread.qualicorp.wso2.coparticipacao.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class QueryUtils {
	private static final Logger LOGGER = LogManager.getLogger(QueryUtils.class);

	private static final String QUERIES_FILE = "/%s.xml";

	private static final String QUERY_DIR = "/mappers";

	private Map<String, String> mapQueries;

	private String queryDir;

	public QueryUtils(String fileName) throws CoParticipacaoException {
		this.mapQueries = new HashMap<String, String>();
		this.queryDir = QUERY_DIR;

		loadQueries(fileName);
	}

	public QueryUtils(String fileName, String queryDir)
			throws CoParticipacaoException {
		this.mapQueries = new HashMap<String, String>();
		this.queryDir = queryDir;

		loadQueries(fileName);
	}

	private void loadQueries(String fileName) throws CoParticipacaoException {
		JAXBContext jaxbContext;
		Unmarshaller jaxbUnmarshaller;
		QueriesXml queriesXml;
		InputStream is;
		String queriesFile;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating Query Maps for JDBC-DAOs:");
			jaxbContext = JAXBContext.newInstance(QueriesXml.class);

			jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			LOGGER.info("Loading queries XML file:");
			queriesFile = String
					.format(queryDir.concat(QUERIES_FILE), fileName);
			is = getClass().getResourceAsStream(queriesFile);

			LOGGER.info("Unmarshalling XML file");
			if (is != null) {
				queriesXml = (QueriesXml) jaxbUnmarshaller.unmarshal(is);

				if (!queriesXml.getQueries().isEmpty()) {
					LOGGER.info("Storing queries into map:");
					for (QueryXml queryXml : queriesXml.getQueries()) {
						mapQueries.put(queryXml.getId(), queryXml.getValue());
					}
				} else {
					LOGGER.info(
							"There is no queries in [resources/{}]",
							queriesFile);
				}
			} else {
				throw new CoParticipacaoException(
						"Queries file [%s] not found.",
						queriesFile);
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public String getQueryById(String id) throws CoParticipacaoException {
		if (mapQueries.containsKey(id)) {
			return mapQueries.get(id);
		} else {
			throw new CoParticipacaoException("Query [%s] not found:", id);
		}
	}
}

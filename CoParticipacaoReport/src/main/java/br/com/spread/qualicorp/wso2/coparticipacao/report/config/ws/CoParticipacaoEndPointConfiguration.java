package br.com.spread.qualicorp.wso2.coparticipacao.report.config.ws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.report.ws.CoParticipacaoReportEndpoint;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Configuration
public class CoParticipacaoEndPointConfiguration {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoEndPointConfiguration.class);

	@Bean(name = "coparticipacaoReport")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema) throws CoParticipacaoException {
		DefaultWsdl11Definition wsdl11Definition;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating definitions for CoParticipacaoReportEndpoint:");
			wsdl11Definition = new DefaultWsdl11Definition();
			wsdl11Definition.setPortTypeName(CoParticipacaoReportEndpoint.PORT_NAME);
			wsdl11Definition.setLocationUri("/ws");
			wsdl11Definition.setTargetNamespace(CoParticipacaoReportEndpoint.NAMESPACE);
			wsdl11Definition.setSchema(xsdSchema);

			LOGGER.info("END");
			return wsdl11Definition;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean(name = "coParticipacaoSchema")
	public XsdSchema coParticipacaoSchema() throws CoParticipacaoException {
		SimpleXsdSchema xsdSchema;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Loading coparticipacaoReport.xsd:");
			xsdSchema = new SimpleXsdSchema(new ClassPathResource("coparticipacaoReport.xsd"));

			LOGGER.info("END");
			return xsdSchema;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}
}

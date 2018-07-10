package br.com.spread.qualicorp.wso2.coparticipacao.config.ws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.ws.CoParticipacaoEndPoint;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Configuration
public class CoParticipacaoEndPointConfiguration {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoEndPointConfiguration.class);

	@Bean(name = "coparticipacao")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema) throws CoParticipacaoException {
		DefaultWsdl11Definition wsdl11Definition;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Creating definitions for CoParticipacaoEndpoint:");
			wsdl11Definition = new DefaultWsdl11Definition();
			wsdl11Definition.setPortTypeName(CoParticipacaoEndPoint.PORT_NAME);
			wsdl11Definition.setLocationUri("/ws");
			wsdl11Definition.setTargetNamespace(CoParticipacaoEndPoint.NAMESPACE);
			wsdl11Definition.setSchema(xsdSchema);

			LOGGER.info("END");
			return wsdl11Definition;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean(name="coParticipacaoSchema")
	public XsdSchema coParticipacaoSchema() throws CoParticipacaoException {
		SimpleXsdSchema xsdSchema;

		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Loading coparticipacao.xsd:");
			xsdSchema = new SimpleXsdSchema(new ClassPathResource("coparticipacao.xsd"));

			LOGGER.info("END");
			return xsdSchema;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}
}

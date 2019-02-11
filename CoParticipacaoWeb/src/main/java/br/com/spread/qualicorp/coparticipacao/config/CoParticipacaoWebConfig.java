package br.com.spread.qualicorp.coparticipacao.config;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Configuration
@ComponentScan(basePackages = "br.com.spread.qualicorp")
@EnableAutoConfiguration
public class CoParticipacaoWebConfig {
	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoWebConfig.class);

	@Autowired
	private Environment env;

	@Bean
	public MessageSource messageSource() throws CoParticipacaoException {
		ReloadableResourceBundleMessageSource messageSource;

		try {
			LOGGER.info("BEGIN");

			messageSource = new ReloadableResourceBundleMessageSource();
			messageSource.setBasenames("messages");
			messageSource.setDefaultEncoding("UTF-8");

			LOGGER.info("END");
			return messageSource;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("en-US"));
		return resolver;
	}
}

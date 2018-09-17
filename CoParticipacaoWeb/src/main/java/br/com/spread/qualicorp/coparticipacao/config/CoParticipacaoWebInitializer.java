package br.com.spread.qualicorp.coparticipacao.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.sun.faces.config.FacesInitializer;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Configuration
public class CoParticipacaoWebInitializer extends FacesInitializer implements WebApplicationInitializer {

	private static final Logger LOGGER = LogManager.getLogger(CoParticipacaoWebInitializer.class);

	/**
	 * Método para inicializar o JSF.
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext;
		LOGGER.info("BEGIN");
		servletContext.setInitParameter("spring.profiles.active", "desenv");

		applicationContext = new AnnotationConfigWebApplicationContext();

		applicationContext.register(CoParticipacaoWebConfig.class);
		servletContext.addListener(new ContextLoaderListener(applicationContext));
		servletContext.addListener(new RequestContextListener());

		// servletContext.setInitParameter("primefaces.THEME", "cupertino");
		// servletContext.setInitParameter("primefaces.THEME", "afterdark");
		// servletContext.setInitParameter("primefaces.THEME", "redmond");
		// servletContext.setInitParameter("primefaces.THEME", "humanity");
		// servletContext.setInitParameter("primefaces.THEME", "sunny");
		servletContext.setInitParameter("primefaces.THEME", "black-tie");

		servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD", "client");
		servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
		servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
		servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");

		Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);

		LOGGER.info("END");
	}
}

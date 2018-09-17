package br.com.spread.qualicorp.coparticipacao.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.coparticipacao.exception.BeanException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class FacesUtils {

	private static final Logger LOGGER = LogManager.getLogger(FacesUtils.class);

	public static void addError(String message) throws BeanException {
		addError(message, null);
	}

	public static void addError(String message, Object... params) throws BeanException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage;

		try {
			LOGGER.info("BEGIN");

			facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", String.format(message, params));
			facesContext.addMessage(null, facesMessage);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public static void addWarn(String message) throws BeanException {
		addWarn(message, null);
	}

	public static void addWarn(String message, Object... params) throws BeanException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage;

		try {
			LOGGER.info("BEGIN");

			facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", String.format(message, params));
			facesContext.addMessage(null, facesMessage);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public static void addInfo(String message) throws BeanException {
		addInfo(message, null);
	}

	public static void addInfo(String message, Object... params) throws BeanException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage;

		try {
			LOGGER.info("BEGIN");

			facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", String.format(message, params));
			facesContext.addMessage(null, facesMessage);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

}

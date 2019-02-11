package br.com.spread.qualicorp.coparticipacao.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.coparticipacao.bean.ContratoBean;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class ContratoConverter implements Converter {

	private static final Logger LOGGER = LogManager.getLogger(ContratoConverter.class);

	@Autowired
	private ContratoBean contratoBean;

	private static final Pattern REGEXP_CONTRATO = Pattern.compile("^(.+)(\\W\\-\\W)(.+)$");

	private static final int GROUP_NAME_CONTRATO = 3;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		Matcher matcher;

		try {
			if (value != null) {
				matcher = REGEXP_CONTRATO.matcher(value);

				if (matcher.find()) {
					return contratoBean.findByNameContrato(matcher.group(GROUP_NAME_CONTRATO));
				}
			}

			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		ContratoUi contratoUi;

		if (value != null && value instanceof ContratoUi) {
			contratoUi = ((ContratoUi) value);

			return String.format("%s - %s", contratoUi.getDescription(), contratoUi.getNameContrato());
		}

		return null;
	}

}

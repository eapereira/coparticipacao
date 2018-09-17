package br.com.spread.qualicorp.coparticipacao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.coparticipacao.bean.OperadoraBean;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.OperadoraUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class OperadoraConverter implements Converter {

	private static final Logger LOGGER = LogManager.getLogger(OperadoraConverter.class);

	@Autowired
	private OperadoraBean operadoraBean;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		try {
			if (value != null) {
				return operadoraBean.findByName(value);
			}

			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		try {
			if (value != null && value instanceof OperadoraUi) {
				return ((OperadoraUi) value).getNameOperadora();
			}

			return StringUtils.EMPTY;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

}

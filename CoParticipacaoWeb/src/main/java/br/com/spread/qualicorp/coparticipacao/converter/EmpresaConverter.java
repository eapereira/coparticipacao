package br.com.spread.qualicorp.coparticipacao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.coparticipacao.bean.EmpresaBean;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class EmpresaConverter implements Converter {

	@Autowired
	private EmpresaBean empresaBean;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null) {
			return empresaBean.findByName(value);
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value != null && value instanceof EmpresaUi) {
			return ((EmpresaUi) value).getNameEmpresa();
		}

		return StringUtils.EMPTY;
	}

}

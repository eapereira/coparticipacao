package br.com.spread.qualicorp.coparticipacao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.coparticipacao.bean.ArquivoInputBean;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Component
public class ArquivoInputConverter implements Converter{

	@Autowired
	private ArquivoInputBean arquivoInputBean; 
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}

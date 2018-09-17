package br.com.spread.qualicorp.coparticipacao.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.spread.qualicorp.coparticipacao.exception.BeanException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.OperadoraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.OperadoraService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Controller
@SessionScope
public class OperadoraBean extends AbstractBean {

	private static final Logger LOGGER = LogManager.getLogger(OperadoraBean.class);

	@Autowired
	private OperadoraService operadoraService;

	private List<OperadoraUi> operadoraUis;

	@PostConstruct
	public void initBean() throws BeanException {
		try {
			operadoraUis = operadoraService.listAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public OperadoraUi findById(Long operadoraId) throws BeanException {
		for (OperadoraUi operadoraUi : operadoraUis) {
			if (operadoraUi.getId().equals(operadoraId)) {
				return operadoraUi;
			}
		}

		return null;
	}

	public List<OperadoraUi> getOperadoraUis() {
		return operadoraUis;
	}

	public void setOperadoraUis(List<OperadoraUi> operadoraUis) {
		this.operadoraUis = operadoraUis;
	}

	public OperadoraUi findByName(String nameOperadora) {
		for (OperadoraUi operadoraUi : operadoraUis) {
			if (operadoraUi.getNameOperadora().equals(nameOperadora)) {
				return operadoraUi;
			}
		}

		return null;
	}
}

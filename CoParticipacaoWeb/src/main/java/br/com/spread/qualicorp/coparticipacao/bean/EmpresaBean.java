package br.com.spread.qualicorp.coparticipacao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.spread.qualicorp.coparticipacao.exception.BeanException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.OperadoraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Controller
@SessionScope
public class EmpresaBean extends AbstractBean {

	private static final Logger LOGGER = LogManager.getLogger(EmpresaBean.class);

	@Autowired
	private EmpresaService empresaService;

	private OperadoraUi operadoraUi;

	private EmpresaUi empresaUi;

	private List<EmpresaUi> empresaUis;

	@PostConstruct
	public void initBean() throws BeanException {
		try {
			if (operadoraUi != null) {
				empresaUis = empresaService.listByOperadoraId(operadoraUi);
			} else {
				empresaUis = new ArrayList<>();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public List<EmpresaUi> getEmpresaUis() {
		return empresaUis;
	}

	public void setEmpresaUis(List<EmpresaUi> empresaUis) {
		this.empresaUis = empresaUis;
	}

	public OperadoraUi getOperadoraUi() {
		return operadoraUi;
	}

	public void setOperadoraUi(OperadoraUi operadoraUi) {
		this.operadoraUi = operadoraUi;
	}

	public EmpresaUi findById(Long empresaId) {
		for (EmpresaUi empresaUi : empresaUis) {
			if (empresaUi.getId().equals(empresaId)) {
				return empresaUi;
			}
		}

		return null;
	}

	public EmpresaUi getEmpresaUi() {
		return empresaUi;
	}

	public void setEmpresaUi(EmpresaUi empresaUi) {
		this.empresaUi = empresaUi;
	}

	public boolean isDisableSelectEmpresa() throws BeanException {
		try {
			LOGGER.info("BEGIN");

			if (getOperadoraUi() != null) {
				return false;
			}

			LOGGER.info("END");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public void onChangeOperadora(ValueChangeEvent valueChangeEvent) throws BeanException {
		try {
			LOGGER.info("BEGIN");

			if (valueChangeEvent.getNewValue() != null && valueChangeEvent.getNewValue() instanceof OperadoraUi) {
				setOperadoraUi((OperadoraUi) valueChangeEvent.getNewValue());

				empresaUis = empresaService.listByOperadoraId(getOperadoraUi());
			} else {
				empresaUis = new ArrayList<>();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public EmpresaUi findByName(String nameEmpresa) {
		for (EmpresaUi empresaUi : empresaUis) {
			if (empresaUi.getNameEmpresa().equals(nameEmpresa)) {
				return empresaUi;
			}
		}

		return null;
	}
}

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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Controller
@SessionScope
public class ContratoBean extends AbstractBean {

	private static final Logger LOGGER = LogManager.getLogger(ContratoBean.class);

	@Autowired
	private ContratoService contratoService;

	private List<ContratoUi> contratoUis;

	private EmpresaUi empresaUi;

	@PostConstruct
	public void initBean() throws BeanException {
		contratoUis = new ArrayList<>();
	}

	public void onChangeEmpresa(ValueChangeEvent valueChangeEvent) throws BeanException {
		try {
			LOGGER.info("BEGIN");

			if (valueChangeEvent.getNewValue() != null && valueChangeEvent.getNewValue() instanceof EmpresaUi) {
				setEmpresaUi((EmpresaUi) valueChangeEvent.getNewValue());

				updateContratoUis();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public void updateContratoUis() throws BeanException {
		try {
			LOGGER.info("BEGIN");

			if (getEmpresaUi() != null) {
				contratoUis = contratoService.listByEmpresaId(getEmpresaUi());
			} else {
				contratoUis = new ArrayList<>();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public List<ContratoUi> getContratoUis() {
		return contratoUis;
	}

	public void setContratoUis(List<ContratoUi> contratoUis) {
		this.contratoUis = contratoUis;
	}

	public EmpresaUi getEmpresaUi() {
		return empresaUi;
	}

	public void setEmpresaUi(EmpresaUi empresaUi) {
		this.empresaUi = empresaUi;
	}

	public boolean isDisabledContrato() {
		LOGGER.info("BEGIN");

		if (getEmpresaUi() != null) {
			LOGGER.info("END");
			return false;
		}

		LOGGER.info("END");
		return true;
	}

	public ContratoUi findByNameContrato(String nameContrato) throws BeanException {
		try {
			LOGGER.info("BEGIN");
			LOGGER.info("Finding ContratoUi with NAME-CONTRATO [{}]:", nameContrato);

			for (ContratoUi contratoUi : getContratoUis()) {
				if (contratoUi.getNameContrato().equals(nameContrato)) {
					LOGGER.info("END");
					return contratoUi;
				}
			}

			LOGGER.info("END");
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}
}

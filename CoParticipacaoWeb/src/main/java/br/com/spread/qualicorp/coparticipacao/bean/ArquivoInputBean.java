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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Controller
@SessionScope
public class ArquivoInputBean extends AbstractBean {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoInputBean.class);

	@Autowired
	private ArquivoInputService arquivoInputService;

	private List<ArquivoInputUi> arquivoInputUis;

	private ArquivoInputUi arquivoInputUi;

	private EmpresaUi empresaUi;

	@PostConstruct
	public void initBean() throws BeanException {
		try {
			arquivoInputUis = new ArrayList<>();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public void onChangeEmpresa(ValueChangeEvent valueChangeEvent) throws BeanException {
		try {
			LOGGER.info("BEGIN");

			if (valueChangeEvent.getNewValue() != null && valueChangeEvent.getNewValue() instanceof EmpresaUi) {
				setEmpresaUi((EmpresaUi) valueChangeEvent.getNewValue());

				arquivoInputUis = arquivoInputService.listByEmpresaId(getEmpresaUi());
			} else {
				arquivoInputUis = new ArrayList<>();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}

	public List<ArquivoInputUi> getArquivoInputUis() {
		return arquivoInputUis;
	}

	public void setArquivoInputUis(List<ArquivoInputUi> arquivoInputUis) {
		this.arquivoInputUis = arquivoInputUis;
	}

	public ArquivoInputUi getArquivoInputUi() {
		return arquivoInputUi;
	}

	public void setArquivoInputUi(ArquivoInputUi arquivoInputUi) {
		this.arquivoInputUi = arquivoInputUi;
	}

	public EmpresaUi getEmpresaUi() {
		return empresaUi;
	}

	public void setEmpresaUi(EmpresaUi empresaUi) {
		this.empresaUi = empresaUi;
	}
}

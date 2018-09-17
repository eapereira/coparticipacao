package br.com.spread.qualicorp.coparticipacao.bean;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.spread.qualicorp.coparticipacao.exception.BeanException;
import br.com.spread.qualicorp.coparticipacao.util.FacesUtils;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Controller
@SessionScope
public class DownloadBean extends AbstractBean {

	private static final Logger LOGGER = LogManager.getLogger(DownloadBean.class);

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	private EmpresaUi empresaUi;

	private List<ArquivoExecucaoUi> arquivoExecucaoUis;

	private ArquivoExecucaoUi selectedArquivoExecucaoUi;

	@Override
	public void initBean() throws BeanException {
		LOGGER.info("BEGIN");

		arquivoExecucaoUis = new ArrayList<>();
		setEmpresaUi(null);

		LOGGER.info("END");
	}

	public String showPage() throws BeanException {
		LOGGER.info("BEGIN");

		initBean();

		LOGGER.info("END");
		return "download";
	}

	public List<ArquivoExecucaoUi> getArquivoExecucaoUis() {
		return arquivoExecucaoUis;
	}

	public void setArquivoExecucaoUis(List<ArquivoExecucaoUi> arquivoExecucaoUis) {
		this.arquivoExecucaoUis = arquivoExecucaoUis;
	}

	public ArquivoExecucaoUi getSelectedArquivoExecucaoUi() {
		return selectedArquivoExecucaoUi;
	}

	public void setSelectedArquivoExecucaoUi(ArquivoExecucaoUi selectedArquivoExecucaoUi) {
		this.selectedArquivoExecucaoUi = selectedArquivoExecucaoUi;
	}

	public EmpresaUi getEmpresaUi() {
		return empresaUi;
	}

	public void setEmpresaUi(EmpresaUi empresaUi) {
		this.empresaUi = empresaUi;
	}

	public void valueChangeListenerUpdateArquivoExecucao(ValueChangeEvent valueChangeEvent) throws BeanException {
		try {
			LOGGER.info("BEGIN");

			if (valueChangeEvent.getNewValue() != null && valueChangeEvent.getNewValue() instanceof EmpresaUi) {
				setEmpresaUi((EmpresaUi) valueChangeEvent.getNewValue());

				updateArquivoExecucao();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FacesUtils.addError(e.getMessage());
			
			throw new BeanException(e);
		}
	}

	private void updateArquivoExecucao() throws BeanException {
		LocalDate currentDate;

		try {
			LOGGER.info("BEGIN");

			if (getEmpresaUi() != null) {
				LOGGER.info("Component has changed its value to [{}]:", getEmpresaUi().getNameEmpresa());

				currentDate = LocalDate.now();
				arquivoExecucaoUis = arquivoExecucaoService.listByEmpresaIdAndMesAndAno(
						getEmpresaUi(),
						currentDate.getMonthValue(),
						currentDate.getYear());
			} else {
				arquivoExecucaoUis = new ArrayList<>();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FacesUtils.addError(e.getMessage());
			
			throw new BeanException(e);
		}
	}

	public boolean disabledDownloadButton(ArquivoExecucaoUi arquivoExecucaoUi) throws BeanException {
		try {
			LOGGER.info("BEGIN");

			if (arquivoExecucaoService.isJobDone(arquivoExecucaoUi, getUser())) {
				return false;
			}

			LOGGER.info("END");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FacesUtils.addError(e.getMessage());
			
			throw new BeanException(e);
		}
	}

	public StreamedContent getDownloadFile() throws BeanException {
		File downloadFile = null;
		StreamedContent streamedContent = null;
		ArquivoExecucaoUi arquivoExecucaoUi;

		try {
			LOGGER.info("BEGIN");

			arquivoExecucaoUi = getSelectedArquivoExecucaoUi();

			if (arquivoExecucaoUi != null) {
				LOGGER.info("Downloading file with ContratoUi [{}]", arquivoExecucaoUi.getContrato().getCdContrato());

				if (StatusExecucaoType.SUCCESS.equals(arquivoExecucaoUi.getStatusExecucaoType())) {
					downloadFile = new File(arquivoExecucaoUi.getNameArquivoOutput());

					streamedContent = new DefaultStreamedContent(
							new FileInputStream(downloadFile),
							"application/vnd.ms-excel",
							downloadFile.getName());
				}
			}

			LOGGER.info("END");
			return streamedContent;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FacesUtils.addError(e.getMessage());
			
			throw new BeanException(e);
		}
	}

	public void pollListener() throws BeanException {
		try {
			LOGGER.info("BEGIN");

			updateArquivoExecucao();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BeanException(e);
		}
	}
}

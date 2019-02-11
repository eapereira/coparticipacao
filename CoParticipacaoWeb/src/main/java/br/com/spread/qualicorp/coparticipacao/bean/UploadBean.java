package br.com.spread.qualicorp.coparticipacao.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.spread.qualicorp.coparticipacao.exception.BeanException;
import br.com.spread.qualicorp.coparticipacao.util.FacesUtils;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ExecucaoService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Controller
@SessionScope
public class UploadBean extends AbstractBean {

	private static final Logger LOGGER = LogManager.getLogger(UploadBean.class);

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	@Autowired
	private ExecucaoService execucaoService;

	@Autowired
	private DownloadBean downloadBean;

	private List<ArquivoExecucaoUi> arquivoExecucaoUis;

	private FileUpload fileUpload;

	private List<UploadedFile> uploadedFiles;

	private boolean disabledButtonToDefinition;

	private ArquivoExecucaoUi selectedArquivoExecucaoUi;

	private EmpresaUi empresaUi;

	/**
	 * Descrição com o nome da Operadora e Empresa selecionados pelo usuário.
	 */
	private String nameUpload;

	@Override
	public void initBean() throws BeanException {
		LOGGER.info("BEGIN");

		arquivoExecucaoUis = new ArrayList<>();
		uploadedFiles = new ArrayList<>();
		disabledButtonToDefinition = true;
		empresaUi = null;

		LOGGER.info("END");
	}

	public String showPage() throws BeanException {
		LOGGER.info("BEGIN");

		initBean();

		LOGGER.info("END");
		return "uploadEmpresa";
	}

	public void updateArquivosExecucao() throws BeanException {
		LocalDate currentDate;

		try {
			LOGGER.info("BEGIN");

			currentDate = LocalDate.now();

			if (getEmpresaUi() != null) {
				arquivoExecucaoUis = arquivoExecucaoService
						.listByEmpresaIdAndMesAndAno(empresaUi, currentDate.getMonthValue(), currentDate.getYear());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FacesUtils.addError(e.getMessage());

			throw new BeanException(e);
		}
	}

	public List<ArquivoExecucaoUi> getArquivoExecucaoUis() {
		return arquivoExecucaoUis;
	}

	public void setArquivoExecucaoUis(List<ArquivoExecucaoUi> arquivoExecucaoUis) {
		this.arquivoExecucaoUis = arquivoExecucaoUis;
	}

	public void fileUploadListener(FileUploadEvent fileUploadEvent) throws BeanException {
		UploadedFile uploadedFile;
		ArquivoExecucaoUi arquivoExecucaoUi;

		try {
			LOGGER.info("BEGIN");

			uploadedFile = fileUploadEvent.getFile();

			arquivoExecucaoUi = arquivoExecucaoService
					.saveFile(getUser(), uploadedFile.getInputstream(), uploadedFile.getFileName());

			getArquivoExecucaoUis().add(arquivoExecucaoUi);

			disabledButtonToDefinition = false;

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FacesUtils.addError(e.getMessage());

			throw new BeanException(e);
		}
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public List<UploadedFile> getUploadedFiles() {
		return uploadedFiles;
	}

	public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public String showUploadDefinition() throws BeanException {
		LOGGER.info("BEGIN");
		LOGGER.info("Calling page [uploadDefinition]:");

		LOGGER.info("END");
		return "uploadDefinition";
	}

	public boolean isDisabledButtonToDefinition() throws BeanException {
		LOGGER.info("BEGIN");

		LOGGER.info("END");
		return disabledButtonToDefinition;
	}

	public ArquivoExecucaoUi getSelectedArquivoExecucaoUi() {
		return selectedArquivoExecucaoUi;
	}

	public void setSelectedArquivoExecucaoUi(ArquivoExecucaoUi selectedArquivoExecucaoUi) {
		this.selectedArquivoExecucaoUi = selectedArquivoExecucaoUi;
	}

	public boolean isDisabledConfirmUploadDefinition() throws BeanException {
		LOGGER.info("BEGIN");

		for (ArquivoExecucaoUi arquivoExecucaoUi : getArquivoExecucaoUis()) {
			/*
			 * Só podemos habilitar o botão para gravar e preparar os arquivos
			 * para execução, se o usuário preencheu todos os contratos:
			 */
			if (arquivoExecucaoUi.getContrato() == null) {
				LOGGER.info("END");
				return true;
			}
		}

		LOGGER.info("END");
		return false;
	}

	public String confirmUploadDefinition() throws BeanException {
		try {
			LOGGER.info("BEGIN");

			execucaoService.sendToProcess(getArquivoExecucaoUis(), getEmpresaUi(), getUser());

			/*
			 * Para fazer a tela já mostrar os downloads da empresa que o
			 * usuário esta utilizando:
			 */
			downloadBean.setEmpresaUi(getEmpresaUi());

			LOGGER.info("END");
			return "download";
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FacesUtils.addError(e.getMessage());
		}

		return StringUtils.EMPTY;
	}

	public String confirmEmpresa() throws BeanException {
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			if (getEmpresaUi() != null) {
				LOGGER.info("END");

				sb = new StringBuilder();
				sb.append(getEmpresaUi().getOperadora().getNameOperadora());
				sb.append("::");
				sb.append(getEmpresaUi().getNameEmpresa());

				setNameUpload(sb.toString());

				return "upload";
			}

			LOGGER.info("END");
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FacesUtils.addError(e.getMessage());

			throw new BeanException(e);
		}
	}

	public String cancelar() {
		return "home";
	}

	public EmpresaUi getEmpresaUi() {
		return empresaUi;
	}

	public void setEmpresaUi(EmpresaUi empresaUi) {
		this.empresaUi = empresaUi;
	}

	public boolean isDisabledConfirmEmpresa() {
		if (getEmpresaUi() != null) {
			return false;
		}

		return true;
	}

	public String getNameUpload() {
		return nameUpload;
	}

	public void setNameUpload(String nameUpload) {
		this.nameUpload = nameUpload;
	}

}

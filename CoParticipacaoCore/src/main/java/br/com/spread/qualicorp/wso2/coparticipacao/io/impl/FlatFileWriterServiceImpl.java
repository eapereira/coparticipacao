package br.com.spread.qualicorp.wso2.coparticipacao.io.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.StatusExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.io.FlatFileWriterService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class FlatFileWriterServiceImpl implements FlatFileWriterService {

	private static final Logger LOGGER = LogManager.getLogger(FlatFileWriterServiceImpl.class);

	@Autowired
	private ViewDestinationService viewDestinationService;

	@Autowired
	private ViewDestinationColsDefService viewDestinationColsDefService;

	@Autowired
	private ArquivoOutputSheetService arquivoOutputSheetService;

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	public void write(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoOutputUi arquivoOutputUi,
			ArquivoOutputService arquivoOutputService) throws ServiceException {
		List<ArquivoOutputSheetUi> arquivoOutputSheetUis;
		List<DynamicEntity> dynamicEntities;
		ViewDestinationUi viewDestinationUi;
		List<ViewDestinationColsDefUi> viewDestinationColsDefUis;
		EmpresaUi empresaUi;
		ArquivoExecucaoUi arquivoExecucaoUi;
		ArquivoExecucaoUi arquivoExecucaoUiTmp;

		try {
			LOGGER.info("BEGIN");

			LOGGER.info("Creating ArquivoExecucaoUi for FlatFile data:");
			arquivoExecucaoUiTmp = coParticipacaoContext.getArquivoExecucaoUi();
			arquivoExecucaoUi = arquivoExecucaoService.createArquivoExecucao(coParticipacaoContext, UseType.EXTRA_FILE);
			arquivoExecucaoUi.setContrato(arquivoOutputUi.getArquivoInput().getContrato());

			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUi);
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.STARTED);
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.RUNNING);

			empresaUi = coParticipacaoContext.getEmpresaUi();

			for (Contrato contrato : empresaUi.getContratos()) {
				if (UseType.FATUCOPA.equals(contrato.getUseType())) {
					arquivoOutputSheetUis = arquivoOutputSheetService.listByArquivoOutputId(arquivoOutputUi);

					for (ArquivoOutputSheetUi arquivoOutputSheetUi : arquivoOutputSheetUis) {
						viewDestinationUi = (ViewDestinationUi) arquivoOutputSheetUi.getViewDestination();
						viewDestinationColsDefUis = viewDestinationColsDefService
								.listByViewDestinationId(viewDestinationUi);

						LOGGER.info(
								"Creating the report for the ViewDestination [{}]:",
								viewDestinationUi.getNameView());
						dynamicEntities = viewDestinationService.listByContratoAndMesAndAno(
								viewDestinationUi,
								(ContratoUi) contrato,
								coParticipacaoContext.getMes(),
								coParticipacaoContext.getAno());

						writeToFile(
								coParticipacaoContext,
								arquivoOutputUi,
								(ContratoUi) contrato,
								viewDestinationColsDefUis,
								dynamicEntities,
								arquivoOutputService);
					}
				}
			}

			LOGGER.info("Updating ArquivoExecucaoUi with SUCCESS information:");
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.SUCCESS);
			coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUiTmp);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

			coParticipacaoContext.getArquivoExecucaoUi().setErrorMessage(e.getMessage());
			arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.ERROR);

			throw new ServiceException(e);
		}
	}

	private void writeToFile(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoOutputUi arquivoOutputUi,
			ContratoUi contratoUi,
			List<ViewDestinationColsDefUi> viewDestinationColsDefUis,
			List<DynamicEntity> dynamicEntities,
			ArquivoOutputService arquivoOutputService) throws ServiceException {
		FileWriter fileWriter = null;
		Object value;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			fileWriter = createOutputFile(coParticipacaoContext, arquivoOutputUi, contratoUi, arquivoOutputService);

			sb = new StringBuilder();

			for (DynamicEntity dynamicEntity : dynamicEntities) {

				sb.setLength(NumberUtils.INTEGER_ZERO);

				for (ViewDestinationColsDefUi viewDestinationColsDefUi : viewDestinationColsDefUis) {
					value = dynamicEntity.getColumnValue(viewDestinationColsDefUi.getNameColumn());

					LOGGER.info(
							"ViewDestination [{}] has column [{}] with value [{}]:",
							viewDestinationColsDefUi.getViewDestination().getNameView(),
							viewDestinationColsDefUi.getNameColumn(),
							value);

					sb.append(value);
				}

				fileWriter.write(sb.toString());
				fileWriter.write("\n");
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		} finally {
			closeFile(fileWriter);
		}
	}

	private void closeFile(FileWriter fileWriter) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			if (fileWriter != null) {
				fileWriter.close();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private FileWriter createOutputFile(
			CoParticipacaoContext coParticipacaoContext,
			ArquivoOutputUi arquivoOutputUi,
			ContratoUi contratoUi,
			ArquivoOutputService arquivoOutputService) throws ServiceException {
		FileWriter fileWriter;
		StringBuilder sb;
		File file;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append(contratoUi.getEmpresa().getOutputReportDir());
			sb.append(File.separator);
			sb.append(arquivoOutputService.createFileName(coParticipacaoContext, arquivoOutputUi, contratoUi));

			file = new File(sb.toString());

			if (file.exists()) {
				file.delete();
			}

			fileWriter = new FileWriter(file);

			coParticipacaoContext.getArquivoExecucaoUi().setNameArquivoOutput(file.getAbsolutePath());

			LOGGER.info("END");
			return fileWriter;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
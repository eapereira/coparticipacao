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
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputSheetService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationService;
import br.com.spread.qualicorp.wso2.coparticipacao.util.CoParticipacaoFileUtils;

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

	@Autowired
	private ContratoService contratoService;

	private static final String LINE_END = "\r\n";// System.getProperty("line.terminator");

	public void write(CoParticipacaoContext coParticipacaoContext, ArquivoOutputUi arquivoOutputUi)
			throws ServiceException {
		List<ArquivoOutputSheetUi> arquivoOutputSheetUis;
		List<DynamicEntity> dynamicEntities;
		ViewDestinationUi viewDestinationUi;
		List<ViewDestinationColsDefUi> viewDestinationColsDefUis;
		EmpresaUi empresaUi;
		ArquivoExecucaoUi arquivoExecucaoUi;
		ArquivoExecucaoUi arquivoExecucaoUiTmp;
		ContratoUi contratoUi;
		ContratoUi parent;

		try {
			LOGGER.info("BEGIN");

			contratoUi = (ContratoUi) arquivoOutputUi.getArquivoInput().getContrato();
			parent = contratoService.findParentByChildId(contratoUi);

			if (parent != null) {
				arquivoExecucaoUiTmp = coParticipacaoContext.getArquivoExecucaoUi();
				LOGGER.info("Creating ArquivoExecucaoUi for FlatFile data[{}]:", contratoUi.getCdContrato());

				arquivoExecucaoUi = arquivoExecucaoService.createArquivoExecucao(coParticipacaoContext, contratoUi);

				coParticipacaoContext.setArquivoExecucaoUi(arquivoExecucaoUi);
				arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.STARTED);
				arquivoExecucaoService.updateStatus(coParticipacaoContext, StatusExecucaoType.RUNNING);

				empresaUi = coParticipacaoContext.getEmpresaUi();

				/*
				 * Gerando os arquivos de todos os contratos FATUCOPA da
				 * empresa:
				 */
				for (Contrato contrato : empresaUi.getContratos()) {
					if (UseType.FATUCOPA.equals(contrato.getUseType())) {
						if (parent.getId().equals(contrato.getId())) {
							LOGGER.info("Creating FlatFile for ContratoUi[{}]:", contrato.getCdContrato());

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
										dynamicEntities);

							}

							break;
						}
					}
				}
			} else {
				throw new ServiceException(
						"ContratoUi[%s] must has a parent to bind a ConstratoUi's FATUCOPA",
						contratoUi.getCdContrato());
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
			List<DynamicEntity> dynamicEntities) throws ServiceException {
		FileWriter fileWriter = null;
		Object value;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			fileWriter = createOutputFile(coParticipacaoContext, arquivoOutputUi, contratoUi);

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
				fileWriter.write(LINE_END);
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
			ContratoUi contratoUi) throws ServiceException {
		FileWriter fileWriter;
		StringBuilder sb;
		File file;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();
			sb.append(contratoUi.getEmpresa().getOutputReportDir());
			sb.append(File.separator);
			sb.append(CoParticipacaoFileUtils.createFileName(coParticipacaoContext, arquivoOutputUi, contratoUi));

			file = new File(sb.toString());

			if (file.exists()) {
				file.delete();
			}

			fileWriter = new FileWriter(file);

			LOGGER.info("OutputFileName[{}] for report:", file.getAbsolutePath());
			coParticipacaoContext.getArquivoExecucaoUi().setNameArquivoOutput(file.getAbsolutePath());

			LOGGER.info("END");
			return fileWriter;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}

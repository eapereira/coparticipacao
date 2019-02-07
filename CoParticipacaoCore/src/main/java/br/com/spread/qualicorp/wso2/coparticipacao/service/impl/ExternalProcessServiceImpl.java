package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ExternalProcessDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ExternalProcess;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExternalProcessEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ExternalProcessEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ExternalProcessUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExternalProcessUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ExternalProcessService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ExternalProcessServiceImpl
		extends AbstractServiceImpl<ExternalProcessUi, ExternalProcessEntity, ExternalProcess>
		implements ExternalProcessService {

	private static final Logger LOGGER = LogManager.getLogger(ExternalProcessServiceImpl.class);

	private static final int GROUP_CONTRATO = 1;
	private static final int GROUP_ANO = 4;
	private static final int GROUP_MES = 5;

	@Autowired
	private ExternalProcessDao externalProcessDao;

	@Autowired
	private ExternalProcessUiMapper uiMapper;

	@Autowired
	private ExternalProcessEntityMapper entityMapper;

	public void createNameArquivoOutput(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException {
		Matcher matcher;
		StringBuilder sb;
		String fileName;
		EmpresaUi empresaUi;
		File outputFile;
		Pattern pattern;

		try {
			LOGGER.info("BEGIN");

			empresaUi = (EmpresaUi) arquivoExecucaoUi.getContrato().getEmpresa();

			pattern = Pattern.compile(empresaUi.getExternalProcess().getRegexpNameArquivoInput());
			matcher = pattern.matcher(arquivoExecucaoUi.getNameArquivoInput());

			if (matcher.find()) {
				sb = new StringBuilder();

				fileName = createNameArquivoOutputIntervalor(matcher);

				sb.append(empresaUi.getOutputReportDir());

				if (!empresaUi.getOutputReportDir().endsWith(File.separator)) {
					sb.append(File.separator);
				}

				sb.append(fileName);

				LOGGER.info("ExternalProcess.INTERVALOR output path[{}]:", sb.toString());
				arquivoExecucaoUi.setNameArquivoOutput(sb.toString());

				outputFile = new File(arquivoExecucaoUi.getNameArquivoOutput());

				if (outputFile.exists()) {
					outputFile.delete();
				}
			} else {
				throw new ServiceException(
						"File [%s] not recognized as an ExternalProcess:",
						arquivoExecucaoUi.getNameArquivoInput());
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private String createNameArquivoOutputIntervalor(Matcher matcher) throws ServiceException {
		String contratoId;
		String mes;
		String ano;
		String fileName;

		try {
			LOGGER.info("BEGIN");

			contratoId = matcher.group(GROUP_CONTRATO);
			mes = StringUtils.leftPad(matcher.group(GROUP_MES), 2, "0");
			ano = matcher.group(GROUP_ANO);

			fileName = String.format("Intervalor-SAS(Saude)_Coparticipacao_(%s%s)_%s.xls", ano, mes, contratoId);

			LOGGER.info("ExternalProcess.INTERVALOR has NameArquivoOutput[{}]:", fileName);

			LOGGER.info("END");
			return fileName;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public boolean isJobDone(ArquivoExecucaoUi arquivoExecucaoUi) throws ServiceException {
		File outputFile;
		Matcher matcher;
		EmpresaUi empresaUi;
		Pattern pattern;

		try {
			LOGGER.info("BEGIN");

			empresaUi = (EmpresaUi) arquivoExecucaoUi.getContrato().getEmpresa();
			pattern = Pattern.compile(empresaUi.getExternalProcess().getRegexpNameArquivoInput());

			matcher = pattern.matcher(arquivoExecucaoUi.getNameArquivoInput());

			if (matcher.find()) {
				outputFile = new File(arquivoExecucaoUi.getNameArquivoOutput());

				if (outputFile.exists()) {
					LOGGER.info("END");
					return true;
				}
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected ExternalProcessUi createUi() {
		return new ExternalProcessUi();
	}

	@Override
	protected ExternalProcessEntity createEntity() {
		return new ExternalProcessEntity();
	}

	@Override
	protected AbstractDao<ExternalProcessEntity> getDao() {
		return externalProcessDao;
	}

	@Override
	protected AbstractMapper<ExternalProcess, ExternalProcessUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ExternalProcess, ExternalProcessEntity> getEntityMapper() {
		return entityMapper;
	}
}

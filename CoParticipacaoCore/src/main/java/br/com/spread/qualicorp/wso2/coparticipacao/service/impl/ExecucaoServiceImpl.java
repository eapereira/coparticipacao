package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.service.ExecucaoBatchService;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ExecucaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Execucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ExecucaoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ExecucaoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ExecucaoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ExecucaoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.util.DateUtils;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.XmlUtils;
import br.com.spread.qualicorp.wso2.coparticipacao.xml.execucao.JobXml;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ExecucaoServiceImpl extends AbstractServiceImpl<ExecucaoUi, ExecucaoEntity, Execucao>
		implements ExecucaoService {

	private static final Logger LOGGER = LogManager.getLogger(ExecucaoServiceImpl.class);

	@Autowired
	private ExecucaoUiMapper uiMapper;

	@Autowired
	private ExecucaoEntityMapper entityMapper;

	@Autowired
	private ExecucaoDao execucaoDao;

	@Autowired
	private ArquivoExecucaoService arquivoExecucaoService;

	@Autowired
	private ExecucaoBatchService execucaoBatchService;

	@Override
	protected ExecucaoUi createUi() {
		return new ExecucaoUi();
	}

	@Override
	protected ExecucaoEntity createEntity() {
		return new ExecucaoEntity();
	}

	@Override
	protected AbstractDao<ExecucaoEntity> getDao() {
		return execucaoDao;
	}

	@Override
	protected AbstractMapper<Execucao, ExecucaoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Execucao, ExecucaoEntity> getEntityMapper() {
		return entityMapper;
	}

	public void sendToProcess(List<ArquivoExecucaoUi> arquivoExecucaoUis, EmpresaUi empresaUi, UserUi userUi)
			throws ServiceException {
		ExecucaoUi execucaoUi;

		try {
			LOGGER.info("BEGIN");

			execucaoUi = new ExecucaoUi();
			execucaoUi.setUserCreated(userUi);
			execucaoUi.setEmpresa(empresaUi);
			execucaoUi.setExecucaoType(ExecucaoType.OPEN);

			for (ArquivoExecucaoUi arquivoExecucaoUi : arquivoExecucaoUis) {
				execucaoUi.addArquivoExecucao(arquivoExecucaoUi);
			}

			// save(execucaoUi);
			execucaoBatchService.saveBatch(execucaoUi);
			arquivoExecucaoService.sendToProcess(arquivoExecucaoUis);

			/*
			 * Agora devemos criar o arquivo XML com o número do processo
			 * ExecucaoUi para ser enviado ao WebService e processar os
			 * arquivos:
			 */
			createJob(empresaUi, execucaoUi);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private void createJob(EmpresaUi empresaUi, ExecucaoUi execucaoUi) throws ServiceException {
		JobXml jobXml;
		StringBuilder sb;
		LocalDate currentDate;

		try {
			LOGGER.info("BEGIN");

			jobXml = new JobXml();
			jobXml.addExecucao(execucaoUi);

			// Formato do arquivo, /CARGILL-20180925.copa.xml
			sb = new StringBuilder();
			sb.append(empresaUi.getInputDir());
			sb.append(File.separator);
			sb.append(empresaUi.getCdEmpresa());
			sb.append("-");

			currentDate = LocalDate.now();
			sb.append(DateUtils.dateToString(currentDate, "yyyyMMdd"));
			sb.append(".copa.xml");

			LOGGER.info("Creating Job file[{}] to be processed:", sb.toString());

			XmlUtils.writeFile(sb.toString(), jobXml, JobXml.class);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	protected void logBatchInfo(ExecucaoUi execucaoUi) throws ServiceException {
		LOGGER.debug("EXECUCAO.CD_EMPRESA: ............ [{}]", execucaoUi.getEmpresa().getCdEmpresa());
		LOGGER.debug("EXECUCAO.TP_STATUS: ............. [{}]", execucaoUi.getExecucaoType().getDescription());
		LOGGER.debug("EXECUCAO.USER_CREATED: .......... [{}]", execucaoUi.getUserCreated().getNameLogin());
	}

	public ExecucaoUi findById(Long id) throws ServiceException {
		ExecucaoUi execucaoUi;
		try {
			LOGGER.info("BEGIN");

			execucaoUi = entityToUi(execucaoDao.findById(id));

			LOGGER.info("END");
			return execucaoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public ExecucaoUi save(ExecucaoUi execucaoUi) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			for (ArquivoExecucao arquivoExecucao : execucaoUi.getArquivoExecucaos()) {
				if (arquivoExecucao.getId() == null) {
					arquivoExecucao.setCreated(LocalDateTime.now());
				}

				arquivoExecucao.setAltered(LocalDateTime.now());
			}

			execucaoUi = super.save(execucaoUi);

			LOGGER.info("END");
			return execucaoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}

package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ReportDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Report;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ReportEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ReportEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ReportUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ReportUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ReportService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class ReportServiceImpl extends AbstractServiceImpl<ReportUi, ReportEntity, Report> implements ReportService {

	private static final Logger LOGGER = LogManager.getLogger(ReportServiceImpl.class);

	@Autowired
	private ReportUiMapper uiMapper;

	@Autowired
	private ReportEntityMapper entityMapper;

	@Autowired
	private ReportDao reportDao;

	public List<ReportUi> listByEmpresa(EmpresaUi empresaUi) throws ServiceException {
		List<ReportUi> reportUis;

		try {
			LOGGER.info("BEGIN");

			reportUis = entityToUi(reportDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return reportUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected ReportUi createUi() {
		return new ReportUi();
	}

	@Override
	protected ReportEntity createEntity() {
		return new ReportEntity();
	}

	@Override
	protected AbstractDao<ReportEntity> getDao() {
		return reportDao;
	}

	@Override
	protected AbstractMapper<Report, ReportUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Report, ReportEntity> getEntityMapper() {
		return entityMapper;
	}

	public boolean hasReports(EmpresaUi empresaUi) throws ServiceException {
		List<ReportUi> reportUis;

		try {
			LOGGER.info("BEGIN");

			reportUis = listByEmpresa(empresaUi);

			if (!reportUis.isEmpty()) {
				return true;
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void writeReport(CoParticipacaoContext coParticipacaoContext) throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}

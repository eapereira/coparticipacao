package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoOutputEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoOutputUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoOutputServiceImpl extends
		AbstractServiceImpl<ArquivoOutputUi, ArquivoOutputEntity, ArquivoOutput>
		implements ArquivoOutputService {

	private static final Logger LOGGER = LogManager
			.getLogger(ArquivoOutputServiceImpl.class);

	@Autowired
	private ArquivoOutputDao arquivoOutputDao;

	@Autowired
	private ArquivoOutputUiMapper uiMapper;

	@Autowired
	private ArquivoOutputEntityMapper entityMapper;

	@Autowired
	private ViewDestinationService viewDestinationService;

	@Override
	protected ArquivoOutputUi createUi() {
		return new ArquivoOutputUi();
	}

	@Override
	protected ArquivoOutputEntity createEntity() {
		return new ArquivoOutputEntity();
	}

	@Override
	protected AbstractDao<ArquivoOutputEntity> getDao() {
		return arquivoOutputDao;
	}

	@Override
	protected AbstractMapper<ArquivoOutput, ArquivoOutputUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoOutput, ArquivoOutputEntity> getEntityMapper() {
		return entityMapper;
	}

	public void writeOutputFile(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private String createSqlToViewDestination(
			ViewDestinationUi viewDestinationUi) throws ServiceException {
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();

			sb.append("select ");

			for (ViewDestinationColsDef viewDestinationColsDef : viewDestinationUi
					.getViewDestinationColsDefs()) {
				sb.append(viewDestinationColsDef.getNameColumn());
				sb.append(", ");
			}

			sb.append(" 1 ID_BLAH ");
			sb.append(" from ");
			sb.append(viewDestinationUi.getNameView());
			sb.append(" viewDestination ");
			sb.append("where	viewDestination.ID_EMPRESA = ? ");
			sb.append("and		viewDestination.CD_MES = ? ");
			sb.append("and 		viewDestination.CD_ANO = ? ");

			LOGGER.info(
					"Query to use with dynamic created view [{}]:",
					sb.toString());

			LOGGER.info("END");
			return sb.toString();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}
}

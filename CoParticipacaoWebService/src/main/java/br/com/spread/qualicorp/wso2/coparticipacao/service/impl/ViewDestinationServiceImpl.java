package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ViewDestinationDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestination;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestinationColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ViewDestinationEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ViewDestinationUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DynamicService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ViewDestinationServiceImpl extends
		AbstractServiceImpl<ViewDestinationUi, ViewDestinationEntity, ViewDestination>
		implements ViewDestinationService {

	private static final Logger LOGGER = LogManager
			.getLogger(ViewDestinationServiceImpl.class);

	@Autowired
	private ViewDestinationDao viewDestinationDao;

	@Autowired
	private ViewDestinationUiMapper uiMapper;

	@Autowired
	private ViewDestinationEntityMapper entityMapper;

	@Autowired
	private DynamicService dynamicService;

	@Override
	protected ViewDestinationUi createUi() {
		return new ViewDestinationUi();
	}

	@Override
	protected ViewDestinationEntity createEntity() {
		return new ViewDestinationEntity();
	}

	@Override
	protected AbstractDao<ViewDestinationEntity> getDao() {
		return viewDestinationDao;
	}

	@Override
	protected AbstractMapper<ViewDestination, ViewDestinationUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ViewDestination, ViewDestinationEntity> getEntityMapper() {
		return entityMapper;
	}

	public String createSqlToViewDestination(
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

	public List<DynamicEntity> listByEmpresaAndMesAndAno(
			ViewDestinationUi ViewDestinationUi,
			EmpresaUi empresaUi,
			int mes,
			int ano) throws ServiceException {
		List<DynamicEntity> dynamicEntities;
		String sql;
		try {
			LOGGER.info("BEGIN");

			sql = createSqlToViewDestination(ViewDestinationUi);

			dynamicEntities = dynamicService
					.listByEmpresaAndMesAndAno(sql, empresaUi, mes, ano);

			LOGGER.info("END");
			return dynamicEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

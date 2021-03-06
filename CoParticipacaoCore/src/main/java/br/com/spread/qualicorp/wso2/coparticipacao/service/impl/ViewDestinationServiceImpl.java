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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ViewDestinationEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ViewDestinationUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DynamicService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ViewDestinationService;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ViewDestinationServiceImpl
		extends AbstractServiceImpl<ViewDestinationUi, ViewDestinationEntity, ViewDestination>
		implements ViewDestinationService {

	private static final Logger LOGGER = LogManager.getLogger(ViewDestinationServiceImpl.class);

	@Autowired
	private ViewDestinationDao viewDestinationDao;

	@Autowired
	private ViewDestinationUiMapper uiMapper;

	@Autowired
	private ViewDestinationEntityMapper entityMapper;

	@Autowired
	private DynamicService dynamicService;

	@Autowired
	private ViewDestinationColsDefService viewDestinationColsDefService;

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

	public String createSqlToViewDestination(ViewDestinationUi viewDestinationUi, boolean useContrato)
			throws ServiceException {
		List<ViewDestinationColsDefUi> viewDestinationColsDefUis;
		StringBuilder sb;

		try {
			LOGGER.info("BEGIN");

			sb = new StringBuilder();

			viewDestinationColsDefUis = viewDestinationColsDefService.listByViewDestinationId(viewDestinationUi);

			sb.append("select ");

			for (ViewDestinationColsDefUi viewDestinationColsDefUi : viewDestinationColsDefUis) {
				sb.append("viewDestination.");
				sb.append(viewDestinationColsDefUi.getNameColumn());
				sb.append(", ");
			}

			sb.append(" 1 ID_BLAH ");
			sb.append(" from ");
			sb.append(viewDestinationUi.getNameView());
			sb.append(" viewDestination ");
			sb.append("where	1 = 1 ");

			sb.append("and		viewDestination.CD_MES 		= ? ");
			sb.append("and 		viewDestination.CD_ANO 		= ? ");

			if (useContrato) {
				sb.append("and		viewDestination.ID_CONTRATO	= ? ");
			}

			LOGGER.info("Query to use with dynamic created view [{}]:", sb.toString());

			LOGGER.info("END");
			return sb.toString();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DynamicEntity> listByContratoAndMesAndAno(
			ViewDestinationUi viewDestinationUii,
			ContratoUi contratoUi,
			int mes,
			int ano) throws ServiceException {
		List<DynamicEntity> dynamicEntities;
		String sql;
		try {
			LOGGER.info("BEGIN");

			sql = createSqlToViewDestination(viewDestinationUii, true);

			dynamicEntities = dynamicService.listByEmpresaAndMesAndAno(sql, contratoUi, mes, ano);

			LOGGER.info("END");
			return dynamicEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<DynamicEntity> listBydMesAndAno(ViewDestinationUi viewDestinationUi, int mes, int ano)
			throws ServiceException {
		List<DynamicEntity> dynamicEntities;
		String sql;
		try {
			LOGGER.info("BEGIN");

			sql = createSqlToViewDestination(viewDestinationUi, false);

			dynamicEntities = dynamicService.listByMesAndAno(sql, mes, ano);

			LOGGER.info("END");
			return dynamicEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

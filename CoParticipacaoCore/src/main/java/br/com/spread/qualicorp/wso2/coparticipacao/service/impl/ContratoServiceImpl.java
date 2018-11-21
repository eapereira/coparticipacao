package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ContratoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ContratoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ContratoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class ContratoServiceImpl extends AbstractServiceImpl<ContratoUi, ContratoEntity, Contrato>
		implements ContratoService {

	private static final Logger LOGGER = LogManager.getLogger(ContratoServiceImpl.class);

	@Autowired
	private ContratoDao contratoDao;

	@Autowired
	private ContratoUiMapper uiMapper;

	@Autowired
	private ContratoEntityMapper entityMapper;

	public ContratoUi findByCdContrato(String contratoName) throws ServiceException {
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");
			contratoUi = entityToUi(contratoDao.findByCdContrato(contratoName));

			LOGGER.info("END");
			return contratoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public ContratoUi findByCdEmpresaAndCdContrato(String cdEmpresa, String cdContrato) throws ServiceException {
		ContratoUi contratoUi;

		try {
			LOGGER.info("BEGIN");

			contratoUi = entityToUi(contratoDao.findByCdEmpresaAndCdContrato(cdEmpresa, cdContrato));

			LOGGER.info("END");
			return contratoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected AbstractDao<ContratoEntity> getDao() {
		return contratoDao;
	}

	@Override
	protected ContratoUi createUi() {
		return new ContratoUi();
	}

	@Override
	protected ContratoEntity createEntity() {
		return new ContratoEntity();
	}

	@Override
	protected AbstractMapper<Contrato, ContratoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Contrato, ContratoEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<ContratoUi> listByEmpresaId(EmpresaUi empresaUi) throws ServiceException {
		List<ContratoUi> contratoUis;

		try {
			LOGGER.info("BEGIN");

			contratoUis = entityToUi(contratoDao.listByEmpresaId(empresaUi.getId()));

			LOGGER.info("END");
			return contratoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public ContratoUi findParentByChildId(ContratoUi child) throws ServiceException {
		ContratoUi parent;

		try {
			LOGGER.info("BEGIN");

			parent = entityToUi(contratoDao.findParentByChildId(child.getId()));

			LOGGER.info("END");
			return parent;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<ContratoUi> listByParent(ContratoUi contratoUi) throws ServiceException {
		List<ContratoUi> contratoUis;

		try {
			LOGGER.info("BEGIN");

			contratoUis = entityToUi(contratoDao.listByParent(contratoUi.getId()));

			LOGGER.info("END");
			return contratoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

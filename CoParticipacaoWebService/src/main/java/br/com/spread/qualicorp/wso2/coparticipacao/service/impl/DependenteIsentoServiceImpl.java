package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DependenteIsentoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.DependenteIsentoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.DependenteIsentoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DependenteIsentoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DependenteIsentoServiceImpl extends
		AbstractServiceImpl<DependenteIsentoUi, DependenteIsentoEntity, DependenteIsento>
		implements DependenteIsentoService {

	private static final Logger LOGGER = LogManager
			.getLogger(DependenteIsentoServiceImpl.class);

	@Autowired
	private DependenteIsentoDao dependenteIsentoDao;

	@Autowired
	private DependenteIsentoUiMapper uiMapper;

	@Autowired
	private DependenteIsentoEntityMapper entityMapper;

	@Override
	protected DependenteIsentoUi createUi() {
		return new DependenteIsentoUi();
	}

	@Override
	protected DependenteIsentoEntity createEntity() {
		return new DependenteIsentoEntity();
	}

	@Override
	protected AbstractDao<DependenteIsentoEntity> getDao() {
		return dependenteIsentoDao;
	}

	@Override
	protected AbstractMapper<DependenteIsento, DependenteIsentoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<DependenteIsento, DependenteIsentoEntity> getEntityMapper() {
		return entityMapper;
	}

	public void deleteByMesAndAno(EmpresaUi empresaUi, Integer mes, Integer ano)
			throws ServiceException {
		try {
			LOGGER.info("BEGIN");

			dependenteIsentoDao.deleteByMesAndAno(empresaUi.getId(), mes, ano);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoInputColsDefEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoInputColsDefUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputColsDefService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoInputColsDefServiceImpl extends
		AbstractServiceImpl<ArquivoInputColsDefUi, ArquivoInputColsDefEntity, ArquivoInputColsDef>
		implements ArquivoInputColsDefService {

	private static final Logger LOGGER = LogManager
			.getLogger(ArquivoInputColsDefServiceImpl.class);

	@Autowired
	private ArquivoInputColsDefDao arquivoInputColsDefDao;

	@Autowired
	private ArquivoInputColsDefUiMapper uiMapper;

	@Autowired
	private ArquivoInputColsDefEntityMapper entityMapper;

	@Override
	protected AbstractDao<ArquivoInputColsDefEntity> getDao() {
		return arquivoInputColsDefDao;
	}

	public List<ArquivoInputColsDefUi> listByArquivoInputId(Long id)
			throws ServiceException {
		List<ArquivoInputColsDefUi> arquivoInputColsDefUis;

		try {
			LOGGER.info("BEGIN");
			arquivoInputColsDefUis = entityToUi(
					arquivoInputColsDefDao.listByArquivoInput(id));

			LOGGER.info("END");
			return arquivoInputColsDefUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected ArquivoInputColsDefUi createUi() {
		return new ArquivoInputColsDefUi();
	}

	@Override
	protected ArquivoInputColsDefEntity createEntity() {
		return new ArquivoInputColsDefEntity();
	}

	@Override
	protected AbstractMapper<ArquivoInputColsDef, ArquivoInputColsDefUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoInputColsDef, ArquivoInputColsDefEntity> getEntityMapper() {
		return entityMapper;
	}

}

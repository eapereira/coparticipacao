package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputOutputDesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputOutputDesconhecidoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoInputOutputDesconhecidoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoInputOutputDesconhecidoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputOutputDesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoInputOutputDesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoInputOutputDesconhecidoServiceImpl extends
		AbstractServiceImpl<ArquivoInputOutputDesconhecidoUi, ArquivoInputOutputDesconhecidoEntity, ArquivoInputOutputDesconhecido>
		implements ArquivoInputOutputDesconhecidoService {

	private static final Logger LOGGER = LogManager
			.getLogger(ArquivoInputOutputDesconhecidoServiceImpl.class);

	@Autowired
	private ArquivoInputOutputDesconhecidoDao arquivoInputOutputDesconhecidoDao;

	@Autowired
	private ArquivoInputOutputDesconhecidoUiMapper uiMapper;

	@Autowired
	private ArquivoInputOutputDesconhecidoEntityMapper entityMapper;

	@Override
	protected ArquivoInputOutputDesconhecidoUi createUi() {
		return new ArquivoInputOutputDesconhecidoUi();
	}

	@Override
	protected ArquivoInputOutputDesconhecidoEntity createEntity() {
		return new ArquivoInputOutputDesconhecidoEntity();
	}

	@Override
	protected AbstractDao<ArquivoInputOutputDesconhecidoEntity> getDao() {
		return arquivoInputOutputDesconhecidoDao;
	}

	@Override
	protected AbstractMapper<ArquivoInputOutputDesconhecido, ArquivoInputOutputDesconhecidoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoInputOutputDesconhecido, ArquivoInputOutputDesconhecidoEntity> getEntityMapper() {
		return entityMapper;
	}

	public List<ArquivoInputOutputDesconhecidoUi> listByArquivoInputId(Long id)
			throws ServiceException {
		List<ArquivoInputOutputDesconhecidoUi> arquivoInputOutputDesconhecidoUis;

		try {
			LOGGER.info("BEGIN");

			arquivoInputOutputDesconhecidoUis = entityToUi(
					arquivoInputOutputDesconhecidoDao.listByArquivoInputId(id));

			LOGGER.info("END");
			return arquivoInputOutputDesconhecidoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public List<ArquivoInputOutputDesconhecidoUi> listByContratoId(
			ContratoUi contratoUi) throws ServiceException {
		List<ArquivoInputOutputDesconhecidoUi> arquivoInputOutputDesconhecidoUis;

		try {
			LOGGER.info("BEGIN");

			arquivoInputOutputDesconhecidoUis = entityToUi(
					arquivoInputOutputDesconhecidoDao
							.listByContratoId(contratoUi.getId()));

			LOGGER.info("END");
			return arquivoInputOutputDesconhecidoUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

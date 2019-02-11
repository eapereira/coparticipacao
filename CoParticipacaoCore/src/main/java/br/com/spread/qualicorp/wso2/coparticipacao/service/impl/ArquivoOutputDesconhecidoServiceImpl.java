package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoOutputDesconhecidoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputDesconhecidoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.ArquivoOutputDesconhecidoEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.ArquivoOutputDesconhecidoUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputDesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ArquivoOutputDesconhecidoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class ArquivoOutputDesconhecidoServiceImpl extends
		AbstractServiceImpl<ArquivoOutputDesconhecidoUi, ArquivoOutputDesconhecidoEntity, ArquivoOutputDesconhecido>
		implements ArquivoOutputDesconhecidoService {

	private static final Logger LOGGER = LogManager
			.getLogger(ArquivoOutputDesconhecidoServiceImpl.class);

	@Autowired
	private ArquivoOutputDesconhecidoDao arquivoOutputDesconhecidoDao;

	@Autowired
	private ArquivoOutputDesconhecidoUiMapper uiMapper;

	@Autowired
	private ArquivoOutputDesconhecidoEntityMapper entityMapper;

	@Override
	protected ArquivoOutputDesconhecidoUi createUi() {
		return new ArquivoOutputDesconhecidoUi();
	}

	@Override
	protected ArquivoOutputDesconhecidoEntity createEntity() {
		return new ArquivoOutputDesconhecidoEntity();
	}

	@Override
	protected AbstractDao<ArquivoOutputDesconhecidoEntity> getDao() {
		return arquivoOutputDesconhecidoDao;
	}

	@Override
	protected AbstractMapper<ArquivoOutputDesconhecido, ArquivoOutputDesconhecidoUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<ArquivoOutputDesconhecido, ArquivoOutputDesconhecidoEntity> getEntityMapper() {
		return entityMapper;
	}

	public ArquivoOutputDesconhecidoUi findByArquivoInputId(Long id)
			throws ServiceException {
		ArquivoOutputDesconhecidoUi arquivoOutputDesconhecidoUi;

		try {
			LOGGER.info("BEGIN");

			arquivoOutputDesconhecidoUi = entityToUi(
					arquivoOutputDesconhecidoDao.findByArquivoInputId(id));

			LOGGER.info("END");
			return arquivoOutputDesconhecidoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public ArquivoOutputDesconhecidoUi findByContratoId(ContratoUi contratoUi)
			throws ServiceException {
		ArquivoOutputDesconhecidoUi arquivoOutputDesconhecidoUi;

		try {
			LOGGER.info("BEGIN");

			arquivoOutputDesconhecidoUi = entityToUi(
					arquivoOutputDesconhecidoDao
							.findByContratoId(contratoUi.getId()));

			LOGGER.info("END");
			return arquivoOutputDesconhecidoUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

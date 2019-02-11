package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegisterDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Register;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegisterEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegisterEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegisterUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegisterUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegisterService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegisterServiceImpl extends AbstractServiceImpl<RegisterUi, RegisterEntity, Register>
		implements RegisterService {
	private static final Logger LOGGER = LogManager.getLogger(RegisterServiceImpl.class);

	@Autowired
	private RegisterDao registerDao;

	@Autowired
	private RegisterUiMapper uiMapper;

	@Autowired
	private RegisterEntityMapper entityMapper;

	public List<RegisterUi> listByArquivoInputSheet(ArquivoInputSheetUi arquivoInputSheetUi) throws ServiceException {
		List<RegisterUi> registerUis;

		try {
			LOGGER.info("BEGIN");

			registerUis = entityToUi(registerDao.listByArquivoInputSheetId(arquivoInputSheetUi.getId()));

			LOGGER.info("END");
			return registerUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	protected RegisterUi createUi() {
		return new RegisterUi();
	}

	@Override
	protected RegisterEntity createEntity() {
		return new RegisterEntity();
	}

	@Override
	protected AbstractDao<RegisterEntity> getDao() {
		return registerDao;
	}

	@Override
	protected AbstractMapper<Register, RegisterUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Register, RegisterEntity> getEntityMapper() {
		return entityMapper;
	}

}

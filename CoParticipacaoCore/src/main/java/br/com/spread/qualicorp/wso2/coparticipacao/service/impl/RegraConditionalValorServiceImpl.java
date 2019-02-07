package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegraConditionalValorDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalValor;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalValorEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.RegraConditionalValorEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.RegraConditionalValorUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalOperationUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalValorUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.RegraConditionalValorService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class RegraConditionalValorServiceImpl
		extends AbstractServiceImpl<RegraConditionalValorUi, RegraConditionalValorEntity, RegraConditionalValor>
		implements RegraConditionalValorService {

	private static final Logger LOGGER = LogManager.getLogger(RegraConditionalValorServiceImpl.class);

	@Autowired
	private RegraConditionalValorDao regraConditionalValorDao;

	@Autowired
	private RegraConditionalValorUiMapper uiMapper;

	@Autowired
	private RegraConditionalValorEntityMapper entityMapper;

	public RegraConditionalValorServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RegraConditionalValorUi createUi() {
		return new RegraConditionalValorUi();
	}

	@Override
	protected RegraConditionalValorEntity createEntity() {
		return new RegraConditionalValorEntity();
	}

	@Override
	protected AbstractDao<RegraConditionalValorEntity> getDao() {
		return regraConditionalValorDao;
	}

	@Override
	protected AbstractMapper<RegraConditionalValor, RegraConditionalValorUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<RegraConditionalValor, RegraConditionalValorEntity> getEntityMapper() {
		return entityMapper;
	}

	public Object getValor(RegraConditionalValorUi regraConditionalValorUi, ColDefType colDefType)
			throws ServiceException {
		Object value = null;

		try {
			LOGGER.info("BEGIN");

			if (ColDefType.INT.equals(colDefType)) {
				value = BigDecimal.valueOf(regraConditionalValorUi.getIntValue());
			} else if (ColDefType.LONG.equals(colDefType)) {
				value = BigDecimal.valueOf(regraConditionalValorUi.getLongValue());
			} else if (ColDefType.DOUBLE.equals(colDefType)) {
				value = regraConditionalValorUi.getBigDecimalValue();
			} else if (ColDefType.STRING.equals(colDefType)) {
				value = regraConditionalValorUi.getBigDecimalValue();				
			} else {
				LOGGER.info("Unkown type for column RegraConditionalValor:");
				throw new ServiceException(
						"Unkown type for column RegraConditionalValor it only accepts [INT, LONG, DOUBLE, DATE, STRING]:");
			}

			LOGGER.info("END");
			return value;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<RegraConditionalValorUi> listByRegraConditionalOperation(
			RegraConditionalOperationUi regraConditionalOperationUi) throws ServiceException {
		List<RegraConditionalValorUi> regraConditionalValorUis;

		try {
			LOGGER.info("BEGIN");

			regraConditionalValorUis = entityToUi(
					regraConditionalValorDao.listByRegraConditionalOperationId(regraConditionalOperationUi.getId()));

			LOGGER.info("END");
			return regraConditionalValorUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

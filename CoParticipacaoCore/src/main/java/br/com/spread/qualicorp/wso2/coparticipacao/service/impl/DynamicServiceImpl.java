package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.batch.dao.DynamicDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DynamicService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DynamicServiceImpl implements DynamicService {

	private static final Logger LOGGER = LogManager.getLogger(DynamicServiceImpl.class);

	@Autowired
	private DynamicDao dynamicDao;

	public List<DynamicEntity> listByEmpresaAndMesAndAno(String sql, ContratoUi contratoUi, int mes, int ano)
			throws ServiceException {
		List<DynamicEntity> dynamicEntities;

		try {
			LOGGER.info("BEGIN");

			dynamicEntities = dynamicDao.listByEmpresaAndMesAndAno(sql, contratoUi.getId(), mes, ano);

			LOGGER.info("END");
			return dynamicEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public List<DynamicEntity> listByMesAndAno(String sql, int mes, int ano) throws ServiceException {
		List<DynamicEntity> dynamicEntities;

		try {
			LOGGER.info("BEGIN");

			dynamicEntities = dynamicDao.listByMesAndAno(sql, mes, ano);

			LOGGER.info("END");
			return dynamicEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}

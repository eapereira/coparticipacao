package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.jdbc.DynamicDao;
import br.com.spread.qualicorp.wso2.coparticipacao.service.DynamicService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class DynamicServiceImpl implements DynamicService {

	private static final Logger LOGGER = LogManager
			.getLogger(DynamicServiceImpl.class);

	@Autowired
	private DynamicDao dynamicDao;

	public List<DynamicEntity> listByEmpresaAndMesAndAno(
			String sql,
			EmpresaUi empresaUi,
			int mes,
			int ano) throws ServiceException {
		List<DynamicEntity> dynamicEntities;

		try {
			LOGGER.info("BEGIN");

			dynamicEntities = dynamicDao.listByEmpresaAndMesAndAno(
					sql,
					empresaUi.getId(),
					mes,
					ano);

			LOGGER.info("END");
			return dynamicEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}

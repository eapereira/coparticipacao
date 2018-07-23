package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.EmpresaDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.EmpresaEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.EmpresaUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class EmpresaServiceImpl extends AbstractServiceImpl<EmpresaUi, EmpresaEntity, Empresa> implements EmpresaService {

	private static final Logger LOGGER = LogManager.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private EmpresaDao empresaDao;

	@Autowired
	private EmpresaUiMapper uiMapper;

	@Autowired
	private EmpresaEntityMapper entityMapper;

	@Autowired
	private ContratoService contratoService;

	@Override
	protected EmpresaUi createUi() {
		return new EmpresaUi();
	}

	@Override
	protected EmpresaEntity createEntity() {
		return new EmpresaEntity();
	}

	@Override
	protected AbstractDao<EmpresaEntity> getDao() {
		return empresaDao;
	}

	@Override
	protected AbstractMapper<Empresa, EmpresaUi> getUiMapper() {
		return uiMapper;
	}

	@Override
	protected AbstractMapper<Empresa, EmpresaEntity> getEntityMapper() {
		return entityMapper;
	}

	@Override
	public EmpresaUi findById(Long id) throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = entityToUi(empresaDao.findById(id));

			// Forçando a carga dos contrato:
			empresaUi.getContratos().clear();
			empresaUi.getContratos().addAll(contratoService.listByEmpresaId(empresaUi));

			LOGGER.info("END");
			return empresaUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

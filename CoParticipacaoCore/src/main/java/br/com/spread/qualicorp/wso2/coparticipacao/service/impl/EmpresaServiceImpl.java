package br.com.spread.qualicorp.wso2.coparticipacao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.AbstractDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.EmpresaDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.CoParticipacaoContext;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.EmpresaEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.EmpresaUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.OperadoraUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ContratoService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.EmpresaService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Service
public class EmpresaServiceImpl extends AbstractServiceImpl<EmpresaUi, EmpresaEntity, Empresa>
		implements EmpresaService {

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

	public boolean canAutomaticallyCreateBeneficiario(CoParticipacaoContext coParticipacaoContext)
			throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = coParticipacaoContext.getEmpresaUi();

			if (UseType.MECSAS.equals(coParticipacaoContext.getContratoUi().getUseType())
					|| UseType.NAO_LOCALIZADO.equals(coParticipacaoContext.getContratoUi().getUseType())) {
				return true;
			} else {
				if (empresaUi.isAutomaticCreateBeneficiario()) {
					return true;
				}
			}

			LOGGER.info("END");
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public EmpresaUi findByName(String nameEmpresa) throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = entityToUi(empresaDao.findByName(nameEmpresa));

			LOGGER.info("END");
			return empresaUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public EmpresaUi findByCdEmpresa(String cdEmpresa) throws ServiceException {
		EmpresaUi empresaUi;

		try {
			LOGGER.info("BEGIN");

			empresaUi = entityToUi(empresaDao.findByCdEmpresa(cdEmpresa));

			LOGGER.info("END");
			return empresaUi;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<EmpresaUi> listByOperadoraId(OperadoraUi operadoraUi) throws ServiceException {
		List<EmpresaUi> empresaUis;

		try {
			LOGGER.info("BEGIN");

			empresaUis = entityToUi(empresaDao.listByOperadoraId(operadoraUi.getId()));

			LOGGER.info("END");
			return empresaUis;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}

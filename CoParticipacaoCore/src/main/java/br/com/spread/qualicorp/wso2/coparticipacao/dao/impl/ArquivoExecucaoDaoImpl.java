package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoExecucaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ContratoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ExecucaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.UserDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class ArquivoExecucaoDaoImpl extends AbstractDaoImpl<ArquivoExecucaoEntity> implements ArquivoExecucaoDao {

	private static final Logger LOGGER = LogManager.getLogger(ArquivoExecucaoDaoImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private ContratoDao contratoDao;

	@Autowired
	private ExecucaoDao execucaoDao;

	public ArquivoExecucaoDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ArquivoExecucaoEntity> listByEmpresaIdAndMesAndAno(Long empresaId, Integer mes, Integer ano)
			throws DaoException {
		List<ArquivoExecucaoEntity> arquivoExecucaoEntities;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("listByEmpresaIdAndMesAndAno");
			query.setParameter("empresaId", empresaId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			arquivoExecucaoEntities = query.getResultList();

			LOGGER.info("END");
			return arquivoExecucaoEntities;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public ArquivoExecucaoEntity findByArquivoInputIdAndMesAndAno(Long arquivoInputId, Integer mes, Integer ano)
			throws DaoException {
		ArquivoExecucaoEntity arquivoExecucaoEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByArquivoInputIdAndMesAndAno");
			query.setParameter("arquivoInputId", arquivoInputId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			arquivoExecucaoEntity = (ArquivoExecucaoEntity) query.getSingleResult();

			LOGGER.info("END");
			return arquivoExecucaoEntity;
		} catch (NoResultException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public ArquivoExecucaoEntity findByContratoIdAndMesAndAno(Long contratoId, Integer mes, Integer ano)
			throws DaoException {
		ArquivoExecucaoEntity arquivoExecucaoEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findByContratoIdAndMesAndAno");
			query.setParameter("contratoId", contratoId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			arquivoExecucaoEntity = (ArquivoExecucaoEntity) query.getSingleResult();

			LOGGER.info("END");
			return arquivoExecucaoEntity;
		} catch (NoResultException e) {
			LOGGER.error(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public void deleteByContratoIdAndMesAndAno(Long contratoId, Integer mes, Integer ano) throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByContratoIdAndMesAndAno");
			query.setParameter("contratoId", contratoId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public void deleteByEmpresaIdAndMesAndAno(Long empresaId, Integer mes, Integer ano) throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByEmpresaIdAndMesAndAno");
			query.setParameter("empresaId", empresaId);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public void deleteByEmpresaAndUseTypeAndMesAndAno(Long empresaId, UseType useType, Integer mes, Integer ano)
			throws DaoException {
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("deleteByEmpresaIdAndUseTypeAndMesAndAno");
			query.setParameter("empresaId", empresaId);
			query.setParameter("useType", useType);
			query.setParameter("mes", mes);
			query.setParameter("ano", ano);

			query.executeUpdate();

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	public ArquivoExecucaoEntity save(ArquivoExecucaoEntity arquivoExecucaoEntity) throws DaoException {
		UserEntity userEntity;
		ContratoEntity contratoEntity;
		ArquivoExecucaoEntity arquivoExecucaoEntityDb;
		ExecucaoEntity execucaoEntity;

		try {
			LOGGER.info("BEGIN");

			execucaoEntity = execucaoDao.findById(arquivoExecucaoEntity.getExecucao().getId());

			if (arquivoExecucaoEntity.getId() != null) {
				arquivoExecucaoEntityDb = findById(arquivoExecucaoEntity.getId());
			} else {
				arquivoExecucaoEntityDb = arquivoExecucaoEntity;

				contratoEntity = contratoDao.findById(arquivoExecucaoEntity.getContrato().getId());
				arquivoExecucaoEntityDb.setContrato(contratoEntity);

				userEntity = userDao.findById(arquivoExecucaoEntity.getUserCreated().getId());
				arquivoExecucaoEntityDb.setUserCreated(userEntity);
			}

			if (arquivoExecucaoEntity.getUserAltered() != null) {
				userEntity = userDao.findById(arquivoExecucaoEntity.getUserCreated().getId());
				arquivoExecucaoEntityDb.setUserAltered(userEntity);
			}

			arquivoExecucaoEntityDb.setMes(arquivoExecucaoEntity.getMes());
			arquivoExecucaoEntityDb.setAno(arquivoExecucaoEntity.getAno());
			arquivoExecucaoEntityDb.setErrorMessage(arquivoExecucaoEntity.getErrorMessage());
			arquivoExecucaoEntityDb.setStarted(arquivoExecucaoEntity.getStarted());
			arquivoExecucaoEntityDb.setFinnished(arquivoExecucaoEntity.getFinnished());
			arquivoExecucaoEntityDb.setNameArquivoInput(arquivoExecucaoEntity.getNameArquivoInput());
			arquivoExecucaoEntityDb.setNameArquivoOutput(arquivoExecucaoEntity.getNameArquivoOutput());
			arquivoExecucaoEntityDb.setOrdem(arquivoExecucaoEntity.getOrdem());
			arquivoExecucaoEntityDb.setStatusExecucaoType(arquivoExecucaoEntity.getStatusExecucaoType());

			arquivoExecucaoEntityDb.setExecucao(execucaoEntity);

			arquivoExecucaoEntityDb = super.save(arquivoExecucaoEntityDb);

			LOGGER.info("END");
			return arquivoExecucaoEntityDb;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}

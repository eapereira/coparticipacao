package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ContratoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.EmpresaDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.ExecucaoDao;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.UserDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class ExecucaoDaoImpl extends AbstractDaoImpl<ExecucaoEntity> implements ExecucaoDao {

	private static final Logger LOGGER = LogManager.getLogger(ExecucaoDaoImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmpresaDao empresaDao;

	@Autowired
	private ContratoDao contratoDao;

	public ExecucaoDaoImpl() throws DaoException {
		super();
	}

	@Override
	public ExecucaoEntity findById(Long id) throws DaoException {
		ExecucaoEntity execucaoEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery("findById");
			query.setParameter("id", id);

			execucaoEntity = (ExecucaoEntity) query.getSingleResult();

			LOGGER.info("END");
			return execucaoEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}

	}

	@Override
	public ExecucaoEntity save(ExecucaoEntity execucaoEntity) throws DaoException {
		EmpresaEntity empresaEntity;
		UserEntity userEntity;
		ArquivoExecucaoEntity arquivoExecucaoEntity;
		ContratoEntity contratoEntity;
		ExecucaoEntity execucaoEntityDb;

		try {
			LOGGER.info("BEGIN");

			userEntity = userDao.findById(execucaoEntity.getUserCreated().getId());

			if (execucaoEntity.getId() == null) {
				empresaEntity = empresaDao.findById(execucaoEntity.getEmpresa().getId());

				execucaoEntity.setEmpresa(empresaEntity);
				execucaoEntity.setUserCreated(userEntity);

				if (execucaoEntity.getUserAltered() != null) {
					userEntity = userDao.findById(execucaoEntity.getUserAltered().getId());
					execucaoEntity.setUserAltered(userEntity);
				}

				for (ArquivoExecucao arquivoExecucao : execucaoEntity.getArquivoExecucaos()) {
					arquivoExecucaoEntity = (ArquivoExecucaoEntity) arquivoExecucao;
					contratoEntity = contratoDao.findById(arquivoExecucaoEntity.getContrato().getId());

					arquivoExecucaoEntity.setContrato(contratoEntity);
					arquivoExecucaoEntity.setUserCreated(userEntity);

					if (arquivoExecucaoEntity.getUserAltered() != null) {
						userEntity = userDao.findById(arquivoExecucaoEntity.getUserAltered().getId());
						arquivoExecucaoEntity.setUserAltered(userEntity);
					}
				}

				execucaoEntityDb = execucaoEntity;
			} else {
				execucaoEntityDb = findById(execucaoEntity.getId());
				execucaoEntityDb.setExecucaoType(execucaoEntity.getExecucaoType());
				execucaoEntityDb.setUserAltered(userEntity);
			}

			execucaoEntity = super.save(execucaoEntityDb);

			LOGGER.info("END");
			return execucaoEntity;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}
}

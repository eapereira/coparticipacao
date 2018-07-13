package br.com.spread.qualicorp.wso2.coparticipacao.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.dao.InputBeneficiarioDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputBeneficiarioEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public class InputBeneficiarioDaoImpl
		extends AbstractDaoImpl<InputBeneficiarioEntity>
		implements InputBeneficiarioDao {

	private static final Logger LOGGER = LogManager
			.getLogger(InputBeneficiarioDaoImpl.class);

	public InputBeneficiarioDaoImpl() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	public InputBeneficiarioEntity findByArquivoInputId(Long id)
			throws DaoException {
		InputBeneficiarioEntity inputBeneficiarioEntity;
		Query query;

		try {
			LOGGER.info("BEGIN");

			query = createQuery2("findByArquivoInputId");
			query.setParameter("arquivoInputId", id);

			inputBeneficiarioEntity = (InputBeneficiarioEntity) query.getSingleResult();

			LOGGER.info("END");
			return inputBeneficiarioEntity;
		} catch (NoResultException e) {
			LOGGER.info(e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

}

package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegisterColumnEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegisterColumnDao extends AbstractDao<RegisterColumnEntity> {

	List<RegisterColumnEntity> listByRegisterId(Long registerId) throws DaoException;
}

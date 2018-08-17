package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DesconhecidoJdbcDao extends AbstractJdbcDao<DesconhecidoEntity> {

	void deleteByMesAndAno(Long contratoId, int mes, int ano) throws DaoException;

}

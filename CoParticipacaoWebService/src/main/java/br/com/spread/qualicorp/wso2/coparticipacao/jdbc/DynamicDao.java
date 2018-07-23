package br.com.spread.qualicorp.wso2.coparticipacao.jdbc;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DynamicEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DynamicDao extends AbstractJdbcDao<DynamicEntity> {

	List<DynamicEntity> listByEmpresaAndMesAndAno(String sql, Long empresaId, int mes, int ano)
			throws DaoException;

}

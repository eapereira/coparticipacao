package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoDetailEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LancamentoDetailDao extends AbstractDao<LancamentoDetailEntity> {

	List<LancamentoDetailEntity> listByEmpresaId(Long empresaId) throws DaoException;

}

package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DesconhecidoDao extends AbstractDao<DesconhecidoEntity> {

	void deleteByMesAndAno(int mes, int ano) throws DaoException;

}

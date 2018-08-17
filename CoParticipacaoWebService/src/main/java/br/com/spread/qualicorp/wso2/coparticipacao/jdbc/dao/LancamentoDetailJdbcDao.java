package br.com.spread.qualicorp.wso2.coparticipacao.jdbc.dao;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoDetailEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Repository
public interface LancamentoDetailJdbcDao extends AbstractJdbcDao<LancamentoDetailEntity> {

	void deleteByContratoAndMesAndAno(Long contratoId, int mes, int ano) throws DaoException;

}

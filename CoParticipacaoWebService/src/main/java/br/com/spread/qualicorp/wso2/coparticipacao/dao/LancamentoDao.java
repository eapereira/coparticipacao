package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface LancamentoDao extends AbstractDao<LancamentoEntity> {

	void deleteByMesAndAno(Long contratoId, int mes, int ano) throws DaoException;

	List<LancamentoEntity> listByMesAndAno(Long id, int mes, int ano) throws DaoException;

	List<LancamentoEntity> listByEmpresaId(Long empresaId) throws DaoException;

}

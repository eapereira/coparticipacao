package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface DependenteIsentoDao extends AbstractDao<DependenteIsentoEntity> {

	void deleteByMesAndAno(Long empresaId, Integer mes, Integer ano) throws DaoException;

	List<DependenteIsentoEntity> listByEmpresaId(Long empresaId) throws DaoException;

	void deleteByContratoAndMesAndAno(Long contratoId, Integer mes, Integer ano)throws DaoException;

}

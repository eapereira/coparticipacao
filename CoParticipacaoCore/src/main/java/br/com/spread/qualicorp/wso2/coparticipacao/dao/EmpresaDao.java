package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface EmpresaDao extends AbstractDao<EmpresaEntity> {

	EmpresaEntity findByName(String nameEmpresa) throws DaoException;

	EmpresaEntity findByCdEmpresa(String cdEmpresa) throws DaoException;

	List<EmpresaEntity> listByOperadoraId(Long operadoraId) throws DaoException;

}

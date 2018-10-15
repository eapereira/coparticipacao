package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ContratoDao extends AbstractDao<ContratoEntity> {

	ContratoEntity findByCdContrato(String contratoName) throws DaoException;

	List<ContratoEntity> listByEmpresaId(Long id) throws DaoException;

	ContratoEntity findByCdEmpresaAndCdContrato(String cdEmpresa, String cdContrato) throws DaoException;

	ContratoEntity findParentByChildId(Long childId) throws DaoException;

}

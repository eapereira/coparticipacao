package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public interface ArquivoInputDao extends AbstractDao<ArquivoInputEntity> {

	ArquivoInputEntity findByContratoId(Long contratoId) throws DaoException;

	List<ArquivoInputEntity> listByEmpresaId(Long empresaId) throws DaoException;

	ArquivoInputEntity findByCdContrato(String cdContrato) throws DaoException;

	ArquivoInputEntity findByEmpresaCdContrato(Long empresaId, String cdContrato) throws DaoException;

}

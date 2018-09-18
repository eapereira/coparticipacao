package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UseType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DesconhecidoDao extends AbstractDao<DesconhecidoEntity> {

	void deleteByMesAndAno(Long contratoId, int mes, int ano) throws DaoException;

	List<DesconhecidoEntity> listByMesAndAno(Long contratoId, int mes, int ano) throws DaoException;

	List<DesconhecidoEntity> listByContratoId(Long contratoId) throws DaoException;

	List<DesconhecidoEntity> listByEmpresaId(Long id) throws DaoException;

	List<DesconhecidoEntity> listByEmpresaIdAndUseType(Long empresaId, UseType useType) throws DaoException;

}

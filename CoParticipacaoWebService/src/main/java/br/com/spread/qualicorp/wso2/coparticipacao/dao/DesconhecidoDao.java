package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DesconhecidoEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface DesconhecidoDao extends AbstractDao<DesconhecidoEntity> {

	void deleteByMesAndAno(Long empresaId, int mes, int ano) throws DaoException;

	List<DesconhecidoEntity> listByMesAndAno(Long arquivoInputId, int mes, int ano)
			throws DaoException;

}

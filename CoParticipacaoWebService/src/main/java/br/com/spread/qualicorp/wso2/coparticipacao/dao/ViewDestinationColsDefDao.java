package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ViewDestinationColsDefEntity;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Repository
public interface ViewDestinationColsDefDao extends AbstractDao<ViewDestinationColsDefEntity> {

	List<ViewDestinationColsDefEntity> listByViewDestinationId(Long id) throws DaoException;

}

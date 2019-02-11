package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegisterEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface RegisterDao extends AbstractDao<RegisterEntity> {

	List<RegisterEntity> listByArquivoInputSheetId(Long arquivoInputSheetId) throws DaoException;
}

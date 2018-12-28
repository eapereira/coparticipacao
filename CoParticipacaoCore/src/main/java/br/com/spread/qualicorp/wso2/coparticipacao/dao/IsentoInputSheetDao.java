package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.IsentoInputSheetEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface IsentoInputSheetDao extends AbstractDao<IsentoInputSheetEntity> {

	List<IsentoInputSheetEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException;

	IsentoInputSheetEntity findByArquivoInputSheet(Long arquivoInputSheetId) throws DaoException;
}

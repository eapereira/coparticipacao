package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputSheetEntity;

/**
 * 
 * @author edson.apereira
 *
 */
public interface ArquivoInputSheetDao extends AbstractDao<ArquivoInputSheetEntity> {

	List<ArquivoInputSheetEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException;

}

package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioColsEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface BeneficiarioColsDao extends AbstractDao<BeneficiarioColsEntity> {
	List<BeneficiarioColsEntity> listByArquivoInputId(Long arquivoInputId) throws DaoException;

	List<BeneficiarioColsEntity> listByArquivoInputSheetId(Long arquivoInputSheetId) throws DaoException;
}

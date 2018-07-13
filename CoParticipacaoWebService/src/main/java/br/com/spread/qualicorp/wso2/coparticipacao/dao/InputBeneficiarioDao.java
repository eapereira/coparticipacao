package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputBeneficiarioEntity;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface InputBeneficiarioDao
		extends AbstractDao<InputBeneficiarioEntity> {

	InputBeneficiarioEntity findByArquivoInputId(Long id) throws DaoException;

}

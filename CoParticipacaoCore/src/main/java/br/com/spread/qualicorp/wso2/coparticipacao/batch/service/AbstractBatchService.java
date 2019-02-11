package br.com.spread.qualicorp.wso2.coparticipacao.batch.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface AbstractBatchService<UI extends AbstractDomain> {
	void saveBatch(UI ui) throws ServiceException;

	void saveBatch(List<UI> ui) throws ServiceException;
}

package br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface AutomindResumoService extends AbstractService<AutomindResumoViewUi> {

	List<AutomindResumoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException;

}

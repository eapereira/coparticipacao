package br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.AutomindCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface AutomindCoparticipacaoService extends AbstractService<AutomindCoparticipacaoViewUi> {

	List<AutomindCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException;
}

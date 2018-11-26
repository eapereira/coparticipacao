package br.com.spread.qualicorp.wso2.coparticipacao.report.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface CelpeSaudeCoparticipacaoService extends AbstractService<CelpeSaudeCoparticipacaoViewUi> {

	List<CelpeSaudeCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException;
}

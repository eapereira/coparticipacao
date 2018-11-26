package br.com.spread.qualicorp.wso2.coparticipacao.report.service;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.CelpeSaudeResumoDetailViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface CelpeSaudeResumoDetailService extends AbstractService<CelpeSaudeResumoDetailViewUi> {

	List<CelpeSaudeResumoDetailViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException;
}

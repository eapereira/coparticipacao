package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface SpreadSaudeResumoService extends AbstractService<SpreadSaudeResumoViewUi> {

	List<SpreadSaudeResumoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException;
}

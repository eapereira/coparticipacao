package br.com.spread.qualicorp.wso2.coparticipacao.report.service.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface LMTransportesCoparticipacaoService extends AbstractService<LMTransportesCoparticipacaoViewUi> {

	List<LMTransportesCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException;
}

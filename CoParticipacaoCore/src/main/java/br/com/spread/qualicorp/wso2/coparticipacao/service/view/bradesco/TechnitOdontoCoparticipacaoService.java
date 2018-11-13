package br.com.spread.qualicorp.wso2.coparticipacao.service.view.bradesco;

import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.TechnitOdontoCoparticipacaoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.service.AbstractService;
import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public interface TechnitOdontoCoparticipacaoService extends AbstractService<TechnitOdontoCoparticipacaoViewUi> {

	List<TechnitOdontoCoparticipacaoViewUi> listByMesAndAno(Integer mes, Integer ano) throws ServiceException;
}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.UserUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.LMTransportesResumoViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.LMTransportesResumoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserUiMapper.class })
public abstract class LMTransportesResumoViewUiMapper extends AbstractMapper<LMTransportesResumoView, LMTransportesResumoViewUi> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.view.bradesco;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui.UserUiMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.view.bradesco.SpreadSaudeCoparticipacaoResumidaViewUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.SpreadSaudeCoparticipacaoResumidaView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserUiMapper.class })
public abstract class SpreadSaudeCoparticipacaoResumidaViewUiMapper
		extends AbstractMapper<SpreadSaudeCoparticipacaoResumidaView, SpreadSaudeCoparticipacaoResumidaViewUi> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.SpreadSaudeCoparticipacaoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.UserEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.SpreadSaudeCoparticipacaoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserEntityMapper.class })
public abstract class SpreadSaudeCoparticipacaoViewEntityMapper
		extends AbstractMapper<SpreadSaudeCoparticipacaoView, SpreadSaudeCoparticipacaoViewEntity> {

}

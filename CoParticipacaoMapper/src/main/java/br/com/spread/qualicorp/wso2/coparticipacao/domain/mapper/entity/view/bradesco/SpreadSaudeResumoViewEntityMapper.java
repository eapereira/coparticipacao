package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.SpreadSaudeResumoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.UserEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.SpreadSaudeResumoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserEntityMapper.class })
public abstract class SpreadSaudeResumoViewEntityMapper
		extends AbstractMapper<SpreadSaudeResumoView, SpreadSaudeResumoViewEntity> {

}

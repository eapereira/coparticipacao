package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.view.bradesco;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.view.bradesco.CelpeSaudeResumoViewEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity.UserEntityMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco.CelpeSaudeResumoView;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserEntityMapper.class })
public abstract class CelpeSaudeResumoViewEntityMapper
		extends AbstractMapper<CelpeSaudeResumoView, CelpeSaudeResumoViewEntity> {

}

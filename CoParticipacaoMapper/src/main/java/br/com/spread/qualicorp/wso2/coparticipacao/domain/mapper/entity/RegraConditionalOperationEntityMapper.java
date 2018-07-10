package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalOperation;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalOperationEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserEntityMapper.class,
				RegraConditionalEntityMapper.class,
				RegraConditionalFieldEntityMapper.class,
				RegraConditionalValorEntityMapper.class })
public abstract class RegraConditionalOperationEntityMapper extends
		AbstractMapper<RegraConditionalOperation, RegraConditionalOperationEntity> {

}

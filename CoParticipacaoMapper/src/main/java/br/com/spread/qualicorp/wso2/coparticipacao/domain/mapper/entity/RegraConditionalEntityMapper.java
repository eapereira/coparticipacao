package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditional;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraConditionalEntity;
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
				RegraConditionalResultEntityMapper.class,
				RegraConditionalOperationEntityMapper.class,
				ArquivoInputEntityMapper.class })
public abstract class RegraConditionalEntityMapper
		extends AbstractMapper<RegraConditional, RegraConditionalEntity> {
	public static final RegraConditionalEntityMapper INSTANCE = Mappers
			.getMapper(RegraConditionalEntityMapper.class);
}

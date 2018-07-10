package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoEntity;
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
				DependenteEntityMapper.class,
				TitularIsentoEntityMapper.class,
				ContratoEntityMapper.class })
public abstract class LancamentoEntityMapper
		extends AbstractMapper<Lancamento, LancamentoEntity> {
	public static final LancamentoEntityMapper INSTANCE = Mappers
			.getMapper(LancamentoEntityMapper.class);
}

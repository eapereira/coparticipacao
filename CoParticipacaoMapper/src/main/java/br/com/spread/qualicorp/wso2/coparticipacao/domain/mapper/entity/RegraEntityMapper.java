package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraEntity;
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
				ArquivoInputSheetEntityMapper.class,
				RegraResultEntityMapper.class,
				RegraOperationEntityMapper.class,
				ArquivoInputEntityMapper.class })
public abstract class RegraEntityMapper extends AbstractMapper<Regra, RegraEntity> {
	public static final RegraEntityMapper INSTANCE = Mappers.getMapper(RegraEntityMapper.class);
}

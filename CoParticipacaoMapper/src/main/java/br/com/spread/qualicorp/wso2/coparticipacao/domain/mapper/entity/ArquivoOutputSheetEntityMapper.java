package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserEntityMapper.class })
public abstract class ArquivoOutputSheetEntityMapper
		extends AbstractMapper<ArquivoOutputSheet, ArquivoOutputSheetEntity> {
	public static final ArquivoOutputSheetEntityMapper INSTANCE = Mappers
			.getMapper(ArquivoOutputSheetEntityMapper.class);
}

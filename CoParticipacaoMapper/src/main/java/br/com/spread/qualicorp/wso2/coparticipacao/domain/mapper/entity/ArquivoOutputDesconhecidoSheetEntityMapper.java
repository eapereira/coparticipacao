package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputDesconhecidoSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserEntityMapper.class,
				ArquivoOutputDesconhecidoEntityMapper.class,
				ViewDestinationEntityMapper.class })
public abstract class ArquivoOutputDesconhecidoSheetEntityMapper
		extends AbstractMapper<ArquivoOutputDesconhecidoSheet, ArquivoOutputDesconhecidoSheetEntity> {

}

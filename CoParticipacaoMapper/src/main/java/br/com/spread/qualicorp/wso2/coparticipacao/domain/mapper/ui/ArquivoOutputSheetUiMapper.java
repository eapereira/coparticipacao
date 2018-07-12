package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputSheetUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				ArquivoOutputUiMapper.class,
				ArquivoOutputSheetColsDefUiMapper.class,
				ViewDestinationUiMapper.class })
public abstract class ArquivoOutputSheetUiMapper
		extends AbstractMapper<ArquivoOutputSheet, ArquivoOutputSheetUi> {
	public static final ArquivoOutputSheetUiMapper INSTANCE = Mappers
			.getMapper(ArquivoOutputSheetUiMapper.class);
}

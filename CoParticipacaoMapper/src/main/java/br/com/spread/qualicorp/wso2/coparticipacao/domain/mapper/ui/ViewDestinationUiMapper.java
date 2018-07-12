package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ViewDestination;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ViewDestinationUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				ArquivoOutputSheetUiMapper.class,
				ViewDestinationColsDefUiMapper.class })
public abstract class ViewDestinationUiMapper
		extends AbstractMapper<ViewDestination, ViewDestinationUi> {
	public static final ViewDestinationUiMapper INSTANCE = Mappers
			.getMapper(ViewDestinationUiMapper.class);
}

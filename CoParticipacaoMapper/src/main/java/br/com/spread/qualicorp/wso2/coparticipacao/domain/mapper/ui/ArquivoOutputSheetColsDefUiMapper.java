package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputSheetColsDefUi;

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
public abstract class ArquivoOutputSheetColsDefUiMapper extends
		AbstractMapper<ArquivoOutputSheetColsDef, ArquivoOutputSheetColsDefUi> {
}

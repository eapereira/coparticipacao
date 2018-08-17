package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputDesconhecidoSheetUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserUiMapper.class, ArquivoOutputDesconhecidoUiMapper.class, ViewDestinationUiMapper.class })
public abstract class ArquivoOutputDesconhecidoSheetUiMapper
		extends AbstractMapper<ArquivoOutputDesconhecidoSheet, ArquivoOutputDesconhecidoSheetUi> {

}

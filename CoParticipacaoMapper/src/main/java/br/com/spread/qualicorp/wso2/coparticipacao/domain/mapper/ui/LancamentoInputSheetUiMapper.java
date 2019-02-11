package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserUiMapper.class, ArquivoInputSheetUiMapper.class, LancamentoInputSheetColsUiMapper.class })
public abstract class LancamentoInputSheetUiMapper
		extends AbstractMapper<LancamentoInputSheet, LancamentoInputSheetUi> {

}

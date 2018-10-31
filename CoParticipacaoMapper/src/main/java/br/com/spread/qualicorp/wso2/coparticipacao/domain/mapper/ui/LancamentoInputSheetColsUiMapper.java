package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoInputSheetColsUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				ArquivoInputSheetUiMapper.class,
				LancamentoInputSheetUiMapper.class,
				ArquivoInputSheetColsDefUiMapper.class })
public abstract class LancamentoInputSheetColsUiMapper
		extends AbstractMapper<LancamentoInputSheetCols, LancamentoInputSheetColsUi> {

}

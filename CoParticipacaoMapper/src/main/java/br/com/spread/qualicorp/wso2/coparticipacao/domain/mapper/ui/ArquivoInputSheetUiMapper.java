package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				ArquivoInputUiMapper.class,
				RegraUiMapper.class,
				RegraConditionalUiMapper.class,
				IsentoInputSheetUiMapper.class,
				ContratoUiMapper.class,
				LancamentoInputSheetUiMapper.class,
				BeneficiarioColsUiMapper.class,
				RegisterUiMapper.class,
				RegisterColumnUiMapper.class })
public abstract class ArquivoInputSheetUiMapper extends AbstractMapper<ArquivoInputSheet, ArquivoInputSheetUi> {

}

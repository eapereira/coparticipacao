package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				ContratoUiMapper.class,
				ArquivoInputColsDefUiMapper.class,
				RegraUiMapper.class,
				InputTitularIsentoUiMapper.class,
				InputDependenteIsentoUiMapper.class,
				ArquivoOutputUiMapper.class,
				ArquivoOutputDesconhecidoUiMapper.class,
				ArquivoInputSheetUiMapper.class,
				IsentoInputSheetUiMapper.class})
public abstract class ArquivoInputUiMapper
		extends AbstractMapper<ArquivoInput, ArquivoInputUi> {

}

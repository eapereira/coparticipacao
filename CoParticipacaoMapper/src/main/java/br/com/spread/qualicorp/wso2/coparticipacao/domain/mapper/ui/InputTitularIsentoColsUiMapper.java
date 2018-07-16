package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoColsUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserUiMapper.class, InputTitularIsentoColsDefUiMapper.class, ArquivoInputColsDefUiMapper.class,
				InputTitularIsentoUiMapper.class })
public abstract class InputTitularIsentoColsUiMapper
		extends
		AbstractMapper<InputTitularIsentoCols, InputTitularIsentoColsUi> {

}

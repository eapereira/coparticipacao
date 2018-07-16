package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputTitularIsentoUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserUiMapper.class, ArquivoInputUiMapper.class, InputTitularIsentoColsUiMapper.class })
public abstract class InputTitularIsentoUiMapper extends AbstractMapper<InputTitularIsento, InputTitularIsentoUi> {

}

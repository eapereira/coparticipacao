package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserUiMapper.class, DependenteIsentoColsDefUiMapper.class, ArquivoInputColsDefUiMapper.class,
				InputDependenteIsentoUiMapper.class })
public abstract class InputDependenteIsentoColsUiMapper
		extends
		AbstractMapper<InputDependenteIsentoCols, InputDependenteIsentoColsUi> {

}

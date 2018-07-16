package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputDependenteIsentoColsDefUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserUiMapper.class, InputDependenteIsentoColsUiMapper.class })
public abstract class InputDependenteIsentoColsDefUiMapper
		extends
		AbstractMapper<InputDependenteIsentoColsDef, InputDependenteIsentoColsDefUi> {

}

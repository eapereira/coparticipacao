package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoColsDefUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserUiMapper.class, InputDependenteIsentoColsUiMapper.class })
public abstract class DependenteIsentoColsDefUiMapper
		extends
		AbstractMapper<DependenteIsentoColsDef, DependenteIsentoColsDefUi> {

}

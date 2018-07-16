package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, InputDependenteIsentoColsEntityMapper.class })
public abstract class InputDependenteIsentoColsDefEntityMapper
		extends
		AbstractMapper<InputDependenteIsentoColsDef, InputDependenteIsentoColsDefEntity> {

}

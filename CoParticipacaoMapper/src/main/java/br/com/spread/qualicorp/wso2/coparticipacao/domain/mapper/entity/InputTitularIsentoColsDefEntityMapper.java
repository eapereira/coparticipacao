package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, InputTitularIsentoColsEntityMapper.class })
public abstract class InputTitularIsentoColsDefEntityMapper
		extends
		AbstractMapper<InputTitularIsentoColsDef, InputTitularIsentoColsDefEntity> {

}

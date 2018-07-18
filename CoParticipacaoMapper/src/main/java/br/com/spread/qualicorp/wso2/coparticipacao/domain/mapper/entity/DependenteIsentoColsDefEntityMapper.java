package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, InputDependenteIsentoColsEntityMapper.class })
public abstract class DependenteIsentoColsDefEntityMapper
		extends
		AbstractMapper<DependenteIsentoColsDef, DependenteIsentoColsDefEntity> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularIsentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, InputTitularIsentoColsEntityMapper.class })
public abstract class TitularIsentoColsDefEntityMapper
		extends
		AbstractMapper<TitularIsentoColsDef, TitularIsentoColsDefEntity> {

}

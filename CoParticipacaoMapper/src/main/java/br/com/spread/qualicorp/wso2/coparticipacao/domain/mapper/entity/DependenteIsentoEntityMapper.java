package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteIsentoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserEntityMapper.class })
public abstract class DependenteIsentoEntityMapper
		extends AbstractMapper<DependenteIsento, DependenteIsentoEntity> {

}

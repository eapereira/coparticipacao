package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Parameter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ParameterEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserEntityMapper.class, EmpresaEntityMapper.class })
public abstract class ParameterEntityMapper
		extends AbstractMapper<Parameter, ParameterEntity> {

}

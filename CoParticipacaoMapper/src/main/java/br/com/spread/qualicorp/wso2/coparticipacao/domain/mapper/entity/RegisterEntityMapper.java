package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Register;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegisterEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, RegisterColumnEntityMapper.class, RegisterColumnEntityMapper.class })
public abstract class RegisterEntityMapper extends AbstractMapper<Register, RegisterEntity> {

}

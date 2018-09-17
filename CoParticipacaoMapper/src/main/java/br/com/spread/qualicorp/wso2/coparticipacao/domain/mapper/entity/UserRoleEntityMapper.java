package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.UserRole;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserRoleEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserEntityMapper.class, RoleEntityMapper.class })
public abstract class UserRoleEntityMapper extends AbstractMapper<UserRole, UserRoleEntity> {

}

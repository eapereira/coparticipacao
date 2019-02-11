package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ExternalProcess;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExternalProcessEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring", uses = { UserEntityMapper.class, EmpresaEntityMapper.class })
public abstract class ExternalProcessEntityMapper extends AbstractMapper<ExternalProcess, ExternalProcessEntity> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Execucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ExecucaoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, ArquivoExecucaoEntityMapper.class, EmpresaEntityMapper.class })
public abstract class ExecucaoEntityMapper extends AbstractMapper<Execucao, ExecucaoEntity> {

}

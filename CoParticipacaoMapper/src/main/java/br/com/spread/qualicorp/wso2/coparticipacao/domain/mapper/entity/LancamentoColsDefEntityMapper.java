package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, InputLancamentoEntityMapper.class })
public abstract class LancamentoColsDefEntityMapper
		extends AbstractMapper<LancamentoColsDef, LancamentoColsDefEntity> {

}

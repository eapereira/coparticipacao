package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, ArquivoInputEntityMapper.class, LancamentoInputColsEntityMapper.class })
public abstract class LancamentoInputEntityMapper extends AbstractMapper<LancamentoInput, LancamentoInputEntity> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputDesconhecidoColsDefEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserEntityMapper.class,
				ArquivoOutputDesconhecidoEntityMapper.class,
				ArquivoInputOutputDesconhecidoEntityMapper.class })
public abstract class ArquivoOutputDesconhecidoColsDefEntityMapper extends
		AbstractMapper<ArquivoOutputDesconhecidoColsDef, ArquivoOutputDesconhecidoColsDefEntity> {

}

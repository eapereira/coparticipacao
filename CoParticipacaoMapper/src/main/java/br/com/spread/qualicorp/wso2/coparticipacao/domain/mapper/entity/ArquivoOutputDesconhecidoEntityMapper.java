package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoOutputDesconhecidoEntity;
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
				ArquivoInputEntityMapper.class,
				ArquivoOutputDesconhecidoColsDefEntityMapper.class })
public abstract class ArquivoOutputDesconhecidoEntityMapper extends
		AbstractMapper<ArquivoOutputDesconhecido, ArquivoOutputDesconhecidoEntity> {

}

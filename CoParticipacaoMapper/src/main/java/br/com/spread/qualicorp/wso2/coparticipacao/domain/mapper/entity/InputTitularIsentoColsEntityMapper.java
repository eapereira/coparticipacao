package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputTitularIsentoColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, TitularIsentoColsDefEntityMapper.class,
				ArquivoInputColsDefEntityMapper.class, InputTitularIsentoEntityMapper.class })
public abstract class InputTitularIsentoColsEntityMapper
		extends
		AbstractMapper<InputTitularIsentoCols, InputTitularIsentoColsEntity> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputDependenteIsentoColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, InputDependenteIsentoColsDefEntityMapper.class,
				ArquivoInputColsDefEntityMapper.class, InputDependenteIsentoEntityMapper.class })
public abstract class InputDependenteIsentoColsEntityMapper
		extends
		AbstractMapper<InputDependenteIsentoCols, InputDependenteIsentoColsEntity> {

}

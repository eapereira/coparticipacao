package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserEntityMapper.class,
				ContratoEntityMapper.class,
				ArquivoInputColsDefEntityMapper.class,
				DesconhecidoEntityMapper.class,
				RegraEntityMapper.class,
				InputTitularIsentoEntityMapper.class,
				InputDependenteIsentoEntityMapper.class,
				IsentoInputSheetEntityMapper.class })
public abstract class ArquivoInputEntityMapper extends AbstractMapper<ArquivoInput, ArquivoInputEntity> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputColsDefEntity;
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
				ArquivoInputEntityMapper.class,
				LancamentoInputEntityMapper.class,
				RegraResultEntityMapper.class,
				DesconhecidoEntityMapper.class})
public abstract class ArquivoInputColsDefEntityMapper
		extends AbstractMapper<ArquivoInputColsDef, ArquivoInputColsDefEntity> {

}

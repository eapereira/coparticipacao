package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ContratoEntity;
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
				EmpresaEntityMapper.class,
				TitularEntityMapper.class,
				ArquivoInputEntityMapper.class,
				DependenteEntityMapper.class,
				LancamentoEntityMapper.class,
				ArquivoExecucaoEntityMapper.class,
				DesconhecidoEntityMapper.class })
public abstract class ContratoEntityMapper extends AbstractMapper<Contrato, ContratoEntity> {

}

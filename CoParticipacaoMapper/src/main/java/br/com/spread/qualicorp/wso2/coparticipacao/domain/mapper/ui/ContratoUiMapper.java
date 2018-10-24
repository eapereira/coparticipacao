package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ContratoUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				TitularUiMapper.class,
				DependenteUiMapper.class,
				EmpresaUiMapper.class,
				ArquivoInputUiMapper.class,
				LancamentoUiMapper.class,
				ArquivoExecucaoUiMapper.class,
				TitularUiMapper.class,
				DesconhecidoUiMapper.class })
public abstract class ContratoUiMapper extends AbstractMapper<Contrato, ContratoUi> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				ArquivoInputUiMapper.class,
				ArquivoOutputUiMapper.class,
				OperadoraUiMapper.class,
				ContratoUiMapper.class,
				ParameterUiMapper.class,
				ExternalProcessUiMapper.class,
				TitularUiMapper.class,
				ExecucaoUiMapper.class })
public abstract class EmpresaUiMapper extends AbstractMapper<Empresa, EmpresaUi> {

}

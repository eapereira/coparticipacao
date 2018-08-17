package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.User;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.UserUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				ArquivoInputColsDefUiMapper.class,
				ArquivoInputUiMapper.class,
				ArquivoOutputUiMapper.class,
				ArquivoOutputSheetUiMapper.class,
				ContratoUiMapper.class,
				DependenteIsentoUiMapper.class,
				DependenteUiMapper.class,
				EmpresaUiMapper.class,
				LancamentoInputUiMapper.class,
				LancamentoInputColsUiMapper.class,
				LancamentoDetailUiMapper.class,
				LancamentoUiMapper.class,
				OperadoraUiMapper.class,
				ParameterUiMapper.class,
				RegraFieldUiMapper.class,
				RegraUiMapper.class,
				RegraOperationUiMapper.class,
				RegraValorUiMapper.class,
				TitularIsentoUiMapper.class,
				TitularUiMapper.class,
				ViewDestinationColsDefUiMapper.class,
				ArquivoOutputDesconhecidoSheetUiMapper.class,
				ViewDestinationUiMapper.class })
public abstract class UserUiMapper extends AbstractMapper<User, UserUi> {

}

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
				ArquivoExecucaoUiMapper.class,
				ArquivoInputColsDefUiMapper.class,
				ArquivoInputUiMapper.class,
				ArquivoOutputDesconhecidoSheetUiMapper.class,
				ArquivoOutputDesconhecidoUiMapper.class,
				ArquivoOutputSheetUiMapper.class,
				ArquivoOutputUiMapper.class,
				BeneficiarioColsUiMapper.class,
				ContratoUiMapper.class,
				DependenteIsentoColsDefUiMapper.class,
				DependenteIsentoUiMapper.class,
				DependenteUiMapper.class,
				DesconhecidoUiMapper.class,
				EmpresaUiMapper.class,
				ExternalProcessUiMapper.class,
				InputDependenteIsentoColsUiMapper.class,
				InputDependenteIsentoUiMapper.class,
				InputTitularIsentoColsUiMapper.class,
				InputTitularIsentoUiMapper.class,
				IsentoInputSheetColsUiMapper.class,
				IsentoInputSheetUiMapper.class,
				LancamentoInputColsUiMapper.class,
				LancamentoInputUiMapper.class,
				LancamentoUiMapper.class,
				OperadoraUiMapper.class,
				ParameterUiMapper.class,
				RegraConditionalFieldUiMapper.class,
				RegraConditionalOperationUiMapper.class,
				RegraConditionalResultUiMapper.class,
				RegraConditionalUiMapper.class,
				RegraConditionalValorUiMapper.class,
				RegraFieldUiMapper.class,
				RegraOperationUiMapper.class,
				RegraResultUiMapper.class,
				RegraUiMapper.class,
				RegraValorUiMapper.class,
				RoleUiMapper.class,
				TitularIsentoColsDefUiMapper.class,
				TitularIsentoUiMapper.class,
				TitularUiMapper.class,
				UserRoleUiMapper.class,
				ViewDestinationColsDefUiMapper.class,
				ViewDestinationUiMapper.class,
				TitularResumoUiMapper.class,
				DependenteResumoUiMapper.class,
				ReportUiMapper.class,
				ArquivoInputSheetUiMapper.class,
				ArquivoInputSheetColsDefUiMapper.class,
				ExecucaoUiMapper.class })
public abstract class UserUiMapper extends AbstractMapper<User, UserUi> {

}

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
				ArquivoInputUiMapper.class,
				ArquivoOutputDesconhecidoSheetUiMapper.class,
				ArquivoOutputDesconhecidoUiMapper.class,
				ArquivoOutputSheetUiMapper.class,
				ArquivoOutputUiMapper.class,
				BeneficiarioColsUiMapper.class,
				ContratoUiMapper.class,
				DependenteIsentoUiMapper.class,
				DependenteUiMapper.class,
				DesconhecidoUiMapper.class,
				EmpresaUiMapper.class,
				ExternalProcessUiMapper.class,
				IsentoInputSheetColsUiMapper.class,
				IsentoInputSheetUiMapper.class,
				LancamentoUiMapper.class,
				OperadoraUiMapper.class,
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
				TitularIsentoUiMapper.class,
				TitularUiMapper.class,
				UserRoleUiMapper.class,
				ViewDestinationColsDefUiMapper.class,
				ViewDestinationUiMapper.class,
				TitularResumoUiMapper.class,
				DependenteResumoUiMapper.class,
				ArquivoInputSheetUiMapper.class,
				ArquivoInputSheetColsDefUiMapper.class,
				LancamentoInputSheetUiMapper.class,
				LancamentoInputSheetColsUiMapper.class,
				ExecucaoUiMapper.class })
public abstract class UserUiMapper extends AbstractMapper<User, UserUi> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.User;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				ArquivoExecucaoEntityMapper.class,
				ArquivoInputColsDefEntityMapper.class,
				ArquivoInputEntityMapper.class,
				ArquivoOutputDesconhecidoEntityMapper.class,
				ArquivoOutputDesconhecidoSheetEntityMapper.class,
				ArquivoOutputEntityMapper.class,
				ArquivoOutputSheetEntityMapper.class,
				BeneficiarioColsEntityMapper.class,
				ContratoEntityMapper.class,
				DependenteEntityMapper.class,
				DependenteIsentoColsDefEntityMapper.class,
				DependenteIsentoEntityMapper.class,
				DesconhecidoEntityMapper.class,
				EmpresaEntityMapper.class,
				ExternalProcessEntityMapper.class,
				InputDependenteIsentoColsEntityMapper.class,
				InputDependenteIsentoEntityMapper.class,
				InputTitularIsentoColsEntityMapper.class,
				InputTitularIsentoEntityMapper.class,
				IsentoInputSheetColsEntityMapper.class,
				IsentoInputSheetEntityMapper.class,
				LancamentoEntityMapper.class,
				LancamentoInputColsEntityMapper.class,
				LancamentoInputEntityMapper.class,
				OperadoraEntityMapper.class,
				ParameterEntityMapper.class,
				RegraConditionalEntityMapper.class,
				RegraConditionalFieldEntityMapper.class,
				RegraConditionalOperationEntityMapper.class,
				RegraConditionalResultEntityMapper.class,
				RegraConditionalValorEntityMapper.class,
				RegraEntityMapper.class,
				RegraFieldEntityMapper.class,
				RegraOperationEntityMapper.class,
				RegraResultEntityMapper.class,
				RegraValorEntityMapper.class,
				RoleEntityMapper.class,
				TitularEntityMapper.class,
				TitularIsentoColsDefEntityMapper.class,
				TitularIsentoEntityMapper.class,
				UserRoleEntityMapper.class,
				ViewDestinationColsDefEntityMapper.class,
				ViewDestinationEntityMapper.class,
				TitularResumoEntityMapper.class,
				DependenteResumoEntityMapper.class,
				ReportEntityMapper.class,
				ArquivoInputSheetEntityMapper.class,
				ArquivoInputSheetColsDefEntityMapper.class,
				ExecucaoEntityMapper.class })
public abstract class UserEntityMapper extends AbstractMapper<User, UserEntity> {
	public static final UserEntityMapper MAPPER = Mappers.getMapper(UserEntityMapper.class);

}

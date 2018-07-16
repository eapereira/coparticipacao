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
				ArquivoInputColsDefEntityMapper.class,
				ArquivoInputEntityMapper.class,
				ArquivoOutputEntityMapper.class,
				ArquivoOutputSheetColsDefEntityMapper.class,
				ArquivoOutputSheetEntityMapper.class,
				ContratoEntityMapper.class,
				DependenteColsDefEntityMapper.class,
				DependenteIsentoEntityMapper.class,
				DependenteEntityMapper.class,
				EmpresaEntityMapper.class,
				InputDependenteEntityMapper.class,
				InputLancamentoEntityMapper.class,
				InputTitularEntityMapper.class,
				LancamentoColsDefEntityMapper.class,
				LancamentoDetailEntityMapper.class,
				LancamentoEntityMapper.class,
				OperadoraEntityMapper.class,
				ParameterEntityMapper.class,
				RegraFieldEntityMapper.class,
				RegraEntityMapper.class,
				RegraOperationEntityMapper.class,
				RegraValorEntityMapper.class,
				TitularColsDefEntityMapper.class,
				TitularIsentoEntityMapper.class,
				TitularEntityMapper.class,
				ViewDestinationColsDefEntityMapper.class,
				ViewDestinationEntityMapper.class })
public abstract class UserEntityMapper
		extends AbstractMapper<User, UserEntity> {
	public static final UserEntityMapper MAPPER = Mappers
			.getMapper(UserEntityMapper.class);

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditional;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				RegraConditionalResultUiMapper.class,
				RegraConditionalOperationUiMapper.class,
				ArquivoInputUiMapper.class })
public abstract class RegraConditionalUiMapper
		extends AbstractMapper<RegraConditional, RegraConditionalUi> {
	public static final RegraConditionalUiMapper INSTANCE = Mappers
			.getMapper(RegraConditionalUiMapper.class);
}

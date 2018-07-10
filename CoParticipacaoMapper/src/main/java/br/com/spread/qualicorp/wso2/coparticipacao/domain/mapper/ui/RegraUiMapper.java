package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				RegraResultUiMapper.class,
				RegraOperationUiMapper.class,
				ArquivoInputUiMapper.class })
public abstract class RegraUiMapper extends AbstractMapper<Regra, RegraUi> {
	public static final RegraUiMapper INSTANCE = Mappers
			.getMapper(RegraUiMapper.class);
}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Lancamento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				DependenteUiMapper.class,
				TitularUiMapper.class,
				TitularIsentoUiMapper.class,
				ContratoUiMapper.class })
public abstract class LancamentoUiMapper
		extends AbstractMapper<Lancamento, LancamentoUi> {
	public static final LancamentoUiMapper INSTANCE = Mappers
			.getMapper(LancamentoUiMapper.class);
}

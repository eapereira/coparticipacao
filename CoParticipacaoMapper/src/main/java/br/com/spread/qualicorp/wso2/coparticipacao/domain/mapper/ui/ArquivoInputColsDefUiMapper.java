package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputColsDefUi;

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
				InputDependenteUiMapper.class,
				InputLancamentoUiMapper.class,
				InputTitularUiMapper.class })
public abstract class ArquivoInputColsDefUiMapper
		extends AbstractMapper<ArquivoInputColsDef, ArquivoInputColsDefUi> {

}

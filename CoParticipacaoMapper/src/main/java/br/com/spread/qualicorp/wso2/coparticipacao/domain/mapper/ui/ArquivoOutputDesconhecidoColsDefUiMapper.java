package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoOutputDesconhecidoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoOutputDesconhecidoColsDefUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				ArquivoOutputDesconhecidoUiMapper.class,
				ArquivoInputOutputDesconhecidoUiMapper.class })
public abstract class ArquivoOutputDesconhecidoColsDefUiMapper extends
		AbstractMapper<ArquivoOutputDesconhecidoColsDef, ArquivoOutputDesconhecidoColsDefUi> {

}

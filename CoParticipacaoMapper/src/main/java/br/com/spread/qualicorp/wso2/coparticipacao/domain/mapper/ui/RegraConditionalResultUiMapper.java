package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditionalResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.RegraConditionalResultUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				RegraUiMapper.class,
				ArquivoInputColsDefUiMapper.class,
				RegraConditionalUiMapper.class })
public abstract class RegraConditionalResultUiMapper extends
		AbstractMapper<RegraConditionalResult, RegraConditionalResultUi> {

}

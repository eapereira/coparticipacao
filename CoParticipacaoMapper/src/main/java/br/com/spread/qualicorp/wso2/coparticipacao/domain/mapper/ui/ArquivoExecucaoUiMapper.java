package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoExecucaoUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserUiMapper.class, ArquivoInputUiMapper.class, ContratoUiMapper.class, ExecucaoUiMapper.class })
public abstract class ArquivoExecucaoUiMapper extends AbstractMapper<ArquivoExecucao, ArquivoExecucaoUi> {

}

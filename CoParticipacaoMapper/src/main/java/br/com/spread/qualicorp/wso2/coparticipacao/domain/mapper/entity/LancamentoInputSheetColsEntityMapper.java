package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.LancamentoInputSheetColsEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserEntityMapper.class,
				ArquivoInputSheetEntityMapper.class,
				LancamentoInputSheetEntityMapper.class,
				ArquivoInputSheetColsDefEntityMapper.class })
public abstract class LancamentoInputSheetColsEntityMapper
		extends AbstractMapper<LancamentoInputSheetCols, LancamentoInputSheetColsEntity> {

}

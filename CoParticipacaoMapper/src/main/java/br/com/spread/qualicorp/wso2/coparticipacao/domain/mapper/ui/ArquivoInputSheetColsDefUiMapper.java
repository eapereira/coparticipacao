package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputSheetColsDefUi;

@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				ArquivoInputSheetUiMapper.class,
				BeneficiarioColsUiMapper.class,
				LancamentoInputSheetColsUiMapper.class })
public abstract class ArquivoInputSheetColsDefUiMapper
		extends AbstractMapper<ArquivoInputSheetColsDef, ArquivoInputSheetColsDefUi> {

}

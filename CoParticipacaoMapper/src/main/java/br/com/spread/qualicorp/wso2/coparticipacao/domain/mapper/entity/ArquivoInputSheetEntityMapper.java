package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.ArquivoInputSheetEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserEntityMapper.class,
				ArquivoInputEntityMapper.class,
				RegraEntityMapper.class,
				RegraConditionalEntityMapper.class,
				IsentoInputSheetEntityMapper.class,
				ContratoEntityMapper.class,
				LancamentoInputSheetEntityMapper.class,
				BeneficiarioColsEntityMapper.class,
				ArquivoInputSheetColsDefEntityMapper.class })
public abstract class ArquivoInputSheetEntityMapper extends AbstractMapper<ArquivoInputSheet, ArquivoInputSheetEntity> {

}

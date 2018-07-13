package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiarioBind;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.InputBeneficiarioBindUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				TitularColsDefUiMapper.class,
				ArquivoInputUiMapper.class,
				BeneficiarioColsDefUiMapper.class,
				InputBeneficiarioUiMapper.class })
public abstract class InputBeneficiarioBindUiMapper
		extends AbstractMapper<InputBeneficiarioBind, InputBeneficiarioBindUi> {

}

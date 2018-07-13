package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioColsDefUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = {
				UserUiMapper.class,
				BeneficiarioUiMapper.class,
				InputBeneficiarioUiMapper.class })
public abstract class BeneficiarioColsDefUiMapper
		extends AbstractMapper<BeneficiarioColsDef, BeneficiarioColsDefUi> {

}

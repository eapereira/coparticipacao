package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiario;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputBeneficiarioEntity;
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
				BeneficiarioColsDefEntityMapper.class,
				BeneficiarioEntityMapper.class })
public abstract class InputBeneficiarioEntityMapper
		extends AbstractMapper<InputBeneficiario, InputBeneficiarioEntity> {

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Beneficiario;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioEntity;
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
				InputBeneficiarioEntityMapper.class })
public abstract class BeneficiarioEntityMapper
		extends AbstractMapper<Beneficiario, BeneficiarioEntity> {

}

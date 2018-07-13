package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioColsDefEntity;
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
				BeneficiarioEntityMapper.class,
				InputBeneficiarioEntityMapper.class })
public abstract class BeneficiarioColsDefEntityMapper
		extends AbstractMapper<BeneficiarioColsDef, BeneficiarioColsDefEntity> {

}

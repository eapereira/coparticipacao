package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.BeneficiarioColsEntity;
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
				ArquivoInputEntityMapper.class,
				ArquivoInputColsDefEntityMapper.class })
public abstract class BeneficiarioColsEntityMapper
		extends AbstractMapper<BeneficiarioCols, BeneficiarioColsEntity> {

}

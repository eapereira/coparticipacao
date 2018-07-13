package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiarioBind;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.InputBeneficiarioBindEntity;
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
				TitularColsDefEntityMapper.class,
				ArquivoInputEntityMapper.class,
				BeneficiarioColsDefEntityMapper.class,
				InputBeneficiarioEntityMapper.class })
public abstract class InputBeneficiarioBindEntityMapper extends
		AbstractMapper<InputBeneficiarioBind, InputBeneficiarioBindEntity> {

}

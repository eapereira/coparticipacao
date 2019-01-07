package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegisterColumnEntity;
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
				RegisterEntityMapper.class,
				BeneficiarioColsEntityMapper.class,
				LancamentoInputSheetColsEntityMapper.class })
public abstract class RegisterColumnEntityMapper extends AbstractMapper<RegisterColumn, RegisterColumnEntity> {

}

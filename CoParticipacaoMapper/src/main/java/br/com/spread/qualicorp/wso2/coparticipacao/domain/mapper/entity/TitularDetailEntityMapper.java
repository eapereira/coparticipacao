package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularDetailEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserEntityMapper.class, TitularEntityMapper.class, ArquivoInputColsDefEntityMapper.class })
public abstract class TitularDetailEntityMapper extends AbstractMapper<TitularDetail, TitularDetailEntity> {

}

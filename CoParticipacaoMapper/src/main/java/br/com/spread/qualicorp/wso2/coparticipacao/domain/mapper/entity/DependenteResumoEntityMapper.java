package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteResumo;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteResumoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring")
public abstract class DependenteResumoEntityMapper extends AbstractMapper<DependenteResumo, DependenteResumoEntity> {

}

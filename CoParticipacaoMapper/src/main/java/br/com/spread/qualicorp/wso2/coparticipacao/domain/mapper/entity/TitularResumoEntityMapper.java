package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularResumo;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularResumoEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Mapper(componentModel = "spring")
public abstract class TitularResumoEntityMapper extends AbstractMapper<TitularResumo, TitularResumoEntity> {

}

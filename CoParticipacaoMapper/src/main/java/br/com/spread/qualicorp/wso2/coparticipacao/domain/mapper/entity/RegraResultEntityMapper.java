package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.entity;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraResult;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.RegraResultEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

@Mapper(
		componentModel = "spring",
		uses = {
				UserEntityMapper.class,
				ArquivoInputColsDefEntityMapper.class,
				RegraEntityMapper.class })
public abstract class RegraResultEntityMapper
		extends AbstractMapper<RegraResult, RegraResultEntity> {

}

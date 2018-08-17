package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.MappingTarget;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public abstract class AbstractMapper<SOURCE extends AbstractDomain, TARGET extends AbstractDomain> {

	public AbstractMapper() {
	}
	
	public void copyProperties(SOURCE source, @MappingTarget TARGET target) throws Exception {
		mapProperties(source, target, new CycleAvoidingMappingContext());
	}

	public abstract List<SOURCE> mapProperties(List<SOURCE> sources, @Context CycleAvoidingMappingContext context)
			throws Exception;

	public abstract void mapProperties(
			SOURCE sources,
			@MappingTarget TARGET targets,
			@Context CycleAvoidingMappingContext context) throws Exception;

	public abstract TARGET mapProperties(SOURCE source, @Context CycleAvoidingMappingContext context) throws Exception;

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.internal.PersistentList;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DomainEntity;

/**
 * A type to be used as {@link Context} parameter to track cycles in graphs.
 * <p>
 * Depending on the actual use case, the two methods below could also be changed
 * to only accept certain argument types, e.g. base classes of graph nodes,
 * avoiding the need to capture any other objects that wouldn't necessarily
 * result in cycles.
 *
 * @author Andreas Gudian
 */
@Component
public class CycleAvoidingMappingContext {
	private Map<Object, Object> knownInstances = new IdentityHashMap<Object, Object>();

	@BeforeMapping
	public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) throws Exception {
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();

		if (source instanceof PersistentBag || source instanceof PersistentList || source instanceof DomainEntity) {
			if (!persistenceUtil.isLoaded(source)) {
				if (source instanceof DomainEntity) {
					return targetType.newInstance();
				} else {
					return (T) new ArrayList<T>();
				}
			}
		}

		return (T) knownInstances.get(source);
	}

	@BeforeMapping
	public void storeMappedInstance(Object source, @MappingTarget Object target) {
		knownInstances.put(source, target);
	}

}

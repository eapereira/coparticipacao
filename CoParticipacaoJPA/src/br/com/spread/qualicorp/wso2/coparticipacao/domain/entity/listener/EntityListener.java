package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class EntityListener<T extends AbstractDomain> {

	private static final Logger LOGGER = LogManager.getLogger(EntityListener.class);

	public EntityListener() {

	}

	@PreUpdate
	public void preUpdate(T entity) {
		LOGGER.info("Pre-Update:");
		logProperties(entity);
	}
	
	@PrePersist
	public void prePersist(T entity) {
		LOGGER.info("Pre-Persist:");
		logProperties(entity);
	}

	protected abstract void logProperties(T entity);
}

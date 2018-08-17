package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.LocalDateTimeConverter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@MappedSuperclass
public abstract class AbstractDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7751554221237230488L;

	private Long id;
	
	private Long version;

	private LocalDateTime altered;

	private LocalDateTime created;

	private User userCreated;

	private User userAltered;

	public AbstractDomain() {

	}

	public AbstractDomain(AbstractDomain abstractDomain) {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "DT_ALTERED")
	public LocalDateTime getAltered() {
		return altered;
	}

	public void setAltered(LocalDateTime altered) {
		this.altered = altered;
	}

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "DT_CREATED")
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "USER_CREATED")
	public User getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}

	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "USER_ALTERED")
	public User getUserAltered() {
		return userAltered;
	}

	public void setUserAltered(User userAltered) {
		this.userAltered = userAltered;
	}

	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altered == null) ? 0 : altered.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userAltered == null) ? 0 : userAltered.hashCode());
		result = prime * result + ((userCreated == null) ? 0 : userCreated.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractDomain other = (AbstractDomain) obj;
		if (altered == null) {
			if (other.altered != null)
				return false;
		} else if (!altered.equals(other.altered))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userAltered == null) {
			if (other.userAltered != null)
				return false;
		} else if (!userAltered.equals(other.userAltered))
			return false;
		if (userCreated == null) {
			if (other.userCreated != null)
				return false;
		} else if (!userCreated.equals(other.userCreated))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}

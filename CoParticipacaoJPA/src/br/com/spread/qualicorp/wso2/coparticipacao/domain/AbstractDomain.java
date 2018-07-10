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

import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.UserEntity;

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
}

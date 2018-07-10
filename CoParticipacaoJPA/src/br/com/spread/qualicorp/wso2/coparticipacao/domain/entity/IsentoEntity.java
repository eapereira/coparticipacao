package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Isento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.IsentoUi;

/**
 * The persistent class for the tb_isento database table.
 * 
 */
@Entity
@Table(name = "TB_ISENTO")
@NamedQuery(
		name = "IsentoEntity.findAll",
		query = "SELECT i FROM IsentoEntity i")
public class IsentoEntity extends Isento implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3060772896734680233L;

	public IsentoEntity() {
	}

	public IsentoEntity(IsentoUi ui) {
		super(ui);
	}

	@Column(name = "NM_ISENTO")
	public String getNameIsento() {
		return super.getNameIsento();
	}

	// bi-directional many-to-one association to DependenteIsento
	@OneToMany(mappedBy = "isento", targetEntity = DependenteIsentoEntity.class)
	public List<DependenteIsento> getDependenteIsentos() {
		return super.getDependenteIsentos();
	}

	// bi-directional many-to-one association to TitularIsento
	@OneToMany(mappedBy = "isento", targetEntity = TitularIsentoEntity.class)
	public List<TitularIsento> getTitularIsentos() {
		return super.getTitularIsentos();
	}

}
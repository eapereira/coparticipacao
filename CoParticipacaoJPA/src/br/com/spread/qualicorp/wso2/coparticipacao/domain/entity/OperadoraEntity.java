package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Operadora;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.OperadoraUi;

/**
 * The persistent class for the tb_operadora database table.
 * 
 */
@Entity
@Table(name = "TB_OPERADORA")
@NamedQuery(name = "OperadoraEntity.findAll", query = "SELECT o FROM OperadoraEntity o")
public class OperadoraEntity extends Operadora implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6009559588376713065L;

	public OperadoraEntity() {
	}

	public OperadoraEntity(OperadoraUi ui) {
		super(ui);
	}

	@Column(name = "NM_OPERADORA")
	public String getNameOperadora() {
		return super.getNameOperadora();
	}

	// bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy = "operadora", targetEntity = EmpresaEntity.class)
	@Override
	public List<Empresa> getEmpresas() {
		// TODO Auto-generated method stub
		return super.getEmpresas();
	}

}
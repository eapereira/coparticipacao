package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Beneficiario;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_BENEFICIARIO")
@NamedQuery(
		name = "BeneficiarioEntity.findAll",
		query = "SELECT a FROM BeneficiarioEntity a")
public class BeneficiarioEntity extends Beneficiario {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6254614830341990387L;

	public BeneficiarioEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "NM_TITULAR")
	@Override
	public String getNameTitular() {
		// TODO Auto-generated method stub
		return super.getNameTitular();
	}

	@Column(name = "NM_DEPENDENTE")
	@Override
	public String getNameDependente() {
		// TODO Auto-generated method stub
		return super.getNameDependente();
	}

	@Column(name = "NM_NR_CPF")
	@Override
	public Long getCpf() {
		// TODO Auto-generated method stub
		return super.getCpf();
	}

	@Column(name = "NR_MATRICULA")
	@Override
	public Long getMatricula() {
		// TODO Auto-generated method stub
		return super.getMatricula();
	}

}

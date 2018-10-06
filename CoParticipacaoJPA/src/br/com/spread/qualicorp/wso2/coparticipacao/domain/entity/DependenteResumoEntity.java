package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteResumo;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_DEPENDENTE_RESUMO")
@NamedQuery(name = "DependenteResumoEntity.findAll", query = "SELECT d FROM DependenteResumoEntity d")
public class DependenteResumoEntity extends DependenteResumo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1635690591289984546L;

	public DependenteResumoEntity() {
		super();
	}

	@Column(name = "NR_MATRICULA")
	@Override
	public Long getMatricula() {
		// TODO Auto-generated method stub
		return super.getMatricula();
	}

	@Column(name = "NR_CPF")
	@Override
	public Long getCpf() {
		// TODO Auto-generated method stub
		return super.getCpf();
	}

	@Column(name = "ID_EMPRESA")
	@Override
	public Long getIdEmpresa() {
		// TODO Auto-generated method stub
		return super.getIdEmpresa();
	}

	@Column(name = "NM_DEPENDENTE")
	@Override
	public String getNameBeneficiario() {
		// TODO Auto-generated method stub
		return super.getNameBeneficiario();
	}

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularResumo;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "VW_TITULAR_RESUMO")
@NamedQuery(name = "TitularResumoEntity.findAll", query = "SELECT d FROM TitularResumoEntity d")
public class TitularResumoEntity extends TitularResumo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7765832052015953589L;

	public TitularResumoEntity() {
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
	
	@Column(name = "NM_TITULAR")
	@Override
	public String getNameBeneficiario() {
		// TODO Auto-generated method stub
		return super.getNameBeneficiario();
	}

	@Column(name = "ID_EMPRESA")
	@Override
	public Long getIdEmpresa() {
		// TODO Auto-generated method stub
		return super.getIdEmpresa();
	}
	
}

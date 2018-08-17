package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.time.LocalDate;

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
@NamedQuery(name = "BeneficiarioEntity.findAll", query = "SELECT a FROM BeneficiarioEntity a")
public class BeneficiarioEntity extends Beneficiario implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6254614830341990387L;

	public BeneficiarioEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "NM_BENEFICIARIO")
	@Override
	public String getNameBeneficiario() {
		// TODO Auto-generated method stub
		return super.getNameBeneficiario();
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

	@Column(name = "NM_LABEL")
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return super.getLabel();
	}

	@Column(name = "NR_REF_CODE")
	@Override
	public Long getReferenceCode() {
		// TODO Auto-generated method stub
		return super.getReferenceCode();
	}

	@Column(name = "DT_NASCIMENTO")
	@Override
	public LocalDate getDtNascimento() {
		// TODO Auto-generated method stub
		return super.getDtNascimento();
	}

	@Column(name = "NR_DIGITO_CPF")
	@Override
	public Integer getDigitoCpf() {
		// TODO Auto-generated method stub
		return super.getDigitoCpf();
	}

	@Column(name = "NR_MATRICULA_EMPRESA")
	@Override
	public Long getMatriculaEmpresa() {
		// TODO Auto-generated method stub
		return super.getMatriculaEmpresa();
	}

}

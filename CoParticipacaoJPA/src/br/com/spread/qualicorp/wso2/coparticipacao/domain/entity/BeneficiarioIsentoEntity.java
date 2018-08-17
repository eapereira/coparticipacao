package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioIsento;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.IsentoTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_BENEFICIARIO_ISENTO")
@NamedQuery(name = "BeneficiarioIsentoEntity.findAll", query = "SELECT a FROM BeneficiarioIsentoEntity a")
public class BeneficiarioIsentoEntity extends BeneficiarioIsento implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2734885290480887040L;

	public BeneficiarioIsentoEntity() {
		super();
	}

	@Column(name = "CD_MATRICULA")
	@Override
	public Long getMatricula() {
		// TODO Auto-generated method stub
		return super.getMatricula();
	}

	@Column(name = "NM_NAME")
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Column(name = "DT_NASCIMENTO")
	@Override
	public LocalDate getDtNascimento() {
		// TODO Auto-generated method stub
		return super.getDtNascimento();
	}

	@Column(name = "NR_CPF")
	@Override
	public Long getCpf() {
		// TODO Auto-generated method stub
		return super.getCpf();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = TitularEntity.class)
	@JoinColumn(name = "ID_TITULAR")
	@Override
	public Titular getTitular() {
		// TODO Auto-generated method stub
		return super.getTitular();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DependenteEntity.class)
	@JoinColumn(name = "ID_DEPENDENTE")
	@Override
	public Dependente getDependente() {
		// TODO Auto-generated method stub
		return super.getDependente();
	}

	@Convert(converter = IsentoTypeConverter.class)
	@Column(name = "TP_ISENTO")
	@Override
	public IsentoType getIsentoType() {
		// TODO Auto-generated method stub
		return super.getIsentoType();
	}
}

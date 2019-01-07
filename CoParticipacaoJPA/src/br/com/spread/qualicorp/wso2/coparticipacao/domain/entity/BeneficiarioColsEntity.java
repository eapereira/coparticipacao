package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.BeneficiarioColTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_BENEFICIARIO_COLS")
@NamedQuery(name = "BeneficiarioColsEntity.findAll", query = "SELECT a FROM BeneficiarioColsEntity a")
public class BeneficiarioColsEntity extends BeneficiarioCols implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2987965007744037960L;

	public BeneficiarioColsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Convert(converter = BeneficiarioColTypeConverter.class)
	@Column(name = "CD_BENEFICIARIO_COLS_DEF")
	@Override
	public BeneficiarioColType getBeneficiarioColType() {
		// TODO Auto-generated method stub
		return super.getBeneficiarioColType();
	}

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = RegisterColumnEntity.class)
	@JoinColumn(name = "ID_REGISTER_COLUMN")
	@Override
	public RegisterColumn getRegisterColumn() {
		// TODO Auto-generated method stub
		return super.getRegisterColumn();
	}
}

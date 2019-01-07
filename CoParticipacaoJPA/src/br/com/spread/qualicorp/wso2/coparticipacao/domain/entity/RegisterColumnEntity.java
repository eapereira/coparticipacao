package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Register;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ColDefTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_REGISTER_COLUMMN")
@NamedQuery(name = "RegisterColumnEntity.findAll", query = "SELECT o FROM RegisterColumnEntity o")
public class RegisterColumnEntity extends RegisterColumn implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7931394552751743248L;

	public RegisterColumnEntity() {
		super();
	}

	@Column(name = "CD_ORDEM")
	public Integer getOrdem() {
		return super.getOrdem();
	}

	@Convert(converter = ColDefTypeConverter.class)
	@Column(name = "CD_TYPE")
	public ColDefType getType() {
		return super.getType();
	}

	@Column(name = "NM_COLUMN")
	public String getNameColumn() {
		return super.getNameColumn();
	}

	@Column(name = "VL_LENGTH")
	public Integer getLength() {
		return super.getLength();
	}

	@Column(name = "CD_FORMAT")
	@Override
	public String getFormat() {
		// TODO Auto-generated method stub
		return super.getFormat();
	}

	@Column(name = "CD_LOCALE_PATTERN")
	@Override
	public String getLocalePattern() {
		// TODO Auto-generated method stub
		return super.getLocalePattern();
	}

	// bi-directional many-to-one association to ArquivoInput
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RegisterEntity.class)
	@JoinColumn(name = "ID_REGISTER")
	@Override
	public Register getRegister() {
		// TODO Auto-generated method stub
		return super.getRegister();
	}

	@Column(name = "CD_RESTRICTED_VALUE")
	@Override
	public String getRestrictedValue() {
		// TODO Auto-generated method stub
		return super.getRestrictedValue();
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = BeneficiarioColsEntity.class)
	@Override
	public List<BeneficiarioCols> getBeneficiarioCols() {
		// TODO Auto-generated method stub
		return super.getBeneficiarioCols();
	}
}

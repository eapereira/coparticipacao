package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputBeneficiarioBind;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_BENEFICIARIO_COLS_DEF")
@NamedQuery(
		name = "BeneficiarioColsDefEntity.findAll",
		query = "SELECT a FROM BeneficiarioColsDefEntity a")
public class BeneficiarioColsDefEntity extends BeneficiarioColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6079398426177719717L;

	public BeneficiarioColsDefEntity() {
		super();
		// TODO Auto-generated constructor stub
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

	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "beneficiarioColsDef",
			targetEntity = InputBeneficiarioBindEntity.class)
	@Override
	public List<InputBeneficiarioBind> getInputBeneficiarioBinds() {
		// TODO Auto-generated method stub
		return super.getInputBeneficiarioBinds();
	}

}

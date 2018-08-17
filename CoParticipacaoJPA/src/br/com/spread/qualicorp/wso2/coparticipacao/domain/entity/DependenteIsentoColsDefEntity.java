package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ColDefTypeConverter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_DEPENDENTE_ISENTO_COLS_DEF")
@NamedQuery(name = "DependenteIsentoColsDefEntity.findAll", query = "SELECT a FROM DependenteIsentoColsDefEntity a")
public class DependenteIsentoColsDefEntity extends DependenteIsentoColsDef implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3111511501531525810L;

	public DependenteIsentoColsDefEntity() {

	}

	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "dependenteIsentoColsDef",
			fetch = FetchType.LAZY,
			targetEntity = InputDependenteIsentoColsEntity.class)
	@Override
	public List<InputDependenteIsentoCols> getInputDependenteIsentoCols() {
		// TODO Auto-generated method stub
		return super.getInputDependenteIsentoCols();
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
}

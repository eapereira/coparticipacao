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
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.TitularIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ColDefTypeConverter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_TITULAR_ISENTO_COLS_DEF")
@NamedQuery(name = "TitularIsentoColsDefEntity.findAll", query = "SELECT a FROM TitularIsentoColsDefEntity a")
public class TitularIsentoColsDefEntity extends TitularIsentoColsDef implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4953063670754292051L;

	public TitularIsentoColsDefEntity() {
		super();
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
			mappedBy = "titularIsentoColsDef",
			fetch = FetchType.LAZY,
			targetEntity = InputTitularIsentoColsEntity.class)
	@Override
	public List<InputTitularIsentoCols> getInputTitularIsentoCols() {
		// TODO Auto-generated method stub
		return super.getInputTitularIsentoCols();
	}
}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputTitularIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ColDefTypeConverter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_INPUT_TITULAR_ISENTO_COLS_DEF")
@NamedQuery(
		name = "InputTitularIsentoColsDefEntity.findAll",
		query = "SELECT a FROM InputTitularIsentoColsDefEntity a")
public class InputTitularIsentoColsDefEntity extends InputTitularIsentoColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4953063670754292051L;

	public InputTitularIsentoColsDefEntity() {
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

	@Column(name = "TP_ISENTO")
	@Override
	public Integer getTpIsento() {
		// TODO Auto-generated method stub
		return super.getTpIsento();
	}

	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = InputTitularIsentoColsEntity.class)
	@JoinColumn(name = "ID_TITULAR_ISENTO_COLS")
	@Override
	public InputTitularIsentoCols getInputTitularIsentoCols() {
		// TODO Auto-generated method stub
		return super.getInputTitularIsentoCols();
	}
}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.InputDependenteIsentoColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ColDefTypeConverter;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_INPUT_DEPENDENTE_ISENTO_COLS_DEF")
@NamedQuery(
		name = "InputDependenteIsentoColsDefEntity.findAll",
		query = "SELECT a FROM InputDependenteIsentoColsDefEntity a")
public class InputDependenteIsentoColsDefEntity extends InputDependenteIsentoColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3111511501531525810L;

	public InputDependenteIsentoColsDefEntity() {

	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InputDependenteIsentoColsEntity.class)
	@Override
	public InputDependenteIsentoCols getInputDependenteIsentoCols() {
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

	@Column(name = "TP_ISENTO")
	@Override
	public Integer getTpIsento() {
		// TODO Auto-generated method stub
		return super.getTpIsento();
	}
}

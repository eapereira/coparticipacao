package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ColDefType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ColDefTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ARQUIVO_INPUT_SHEET_COLS_DEF")
@NamedQuery(name = "ArquivoInputSheetColsDefEntity.findAll", query = "SELECT a FROM ArquivoInputSheetColsDefEntity a")
public class ArquivoInputSheetColsDefEntity extends ArquivoInputSheetColsDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6258336005890943176L;

	public ArquivoInputSheetColsDefEntity() {
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
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputSheetEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_SHEET")
	@Override
	public ArquivoInputSheet getArquivoInputSheet() {
		// TODO Auto-generated method stub
		return super.getArquivoInputSheet();
	}

	@Column(name = "CD_RESTRICTED_VALUE")
	@Override
	public String getRestrictedValue() {
		// TODO Auto-generated method stub
		return super.getRestrictedValue();
	}
}

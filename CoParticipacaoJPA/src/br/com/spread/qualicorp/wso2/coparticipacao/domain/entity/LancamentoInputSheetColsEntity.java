package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoColType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheetCols;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.LancamentoColTypeConverter;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_LANCAMENTO_INPUT_SHEET_COLS")
@NamedQuery(name = "LancamentoInputSheetColsEntity.findAll", query = "SELECT i FROM LancamentoInputSheetColsEntity i")
public class LancamentoInputSheetColsEntity extends LancamentoInputSheetCols {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4038175479606370930L;

	public LancamentoInputSheetColsEntity() {
		super();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = LancamentoInputSheetEntity.class)
	@JoinColumn(name = "ID_LANCAMENTO_INPUT_SHEET")
	@Override
	public LancamentoInputSheet getLancamentoInputSheet() {
		// TODO Auto-generated method stub
		return super.getLancamentoInputSheet();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputSheetColsDefEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_SHEET_COLS_DEF")
	@Override
	public ArquivoInputSheetColsDef getArquivoInputSheetColsDef() {
		// TODO Auto-generated method stub
		return super.getArquivoInputSheetColsDef();
	}

	@Column(name = "CD_LANCAMENTO_COL_DEF")
	@Convert(converter = LancamentoColTypeConverter.class)
	@Override
	public LancamentoColType getLancamentoColType() {
		// TODO Auto-generated method stub
		return super.getLancamentoColType();
	}
}

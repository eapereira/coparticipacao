package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheetCols;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_LANCAMENTO_INPUT_SHEET")
@NamedQuery(name = "LancamentoInputSheetEntity.findAll", query = "SELECT i FROM LancamentoInputSheetEntity i")
public class LancamentoInputSheetEntity extends LancamentoInputSheet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7829355473355275626L;

	public LancamentoInputSheetEntity() {
		super();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputSheetEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_SHEET")
	@Override
	public ArquivoInputSheet getArquivoInputSheet() {
		// TODO Auto-generated method stub
		return super.getArquivoInputSheet();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "lancamentoInputSheet",
			targetEntity = LancamentoInputSheetColsEntity.class)
	@Override
	public List<LancamentoInputSheetCols> getLancamentoInputSheetCols() {
		// TODO Auto-generated method stub
		return super.getLancamentoInputSheetCols();
	}
}

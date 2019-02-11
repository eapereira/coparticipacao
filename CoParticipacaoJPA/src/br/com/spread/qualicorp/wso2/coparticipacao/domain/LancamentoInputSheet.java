package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class LancamentoInputSheet extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5886925919287532045L;

	private ArquivoInputSheet arquivoInputSheet;

	private List<LancamentoInputSheetCols> lancamentoInputSheetCols;

	public LancamentoInputSheet() {
		super();
	}

	public ArquivoInputSheet getArquivoInputSheet() {
		return arquivoInputSheet;
	}

	public void setArquivoInputSheet(ArquivoInputSheet arquivoInputSheet) {
		this.arquivoInputSheet = arquivoInputSheet;
	}

	public List<LancamentoInputSheetCols> getLancamentoInputSheetCols() {
		return lancamentoInputSheetCols;
	}

	public void setLancamentoInputSheetCols(List<LancamentoInputSheetCols> lancamentoInputSheetCols) {
		this.lancamentoInputSheetCols = lancamentoInputSheetCols;
	}

	public void addLancamentoInputSheetCols(LancamentoInputSheetCols lancamentoInputSheetCols) {
		getLancamentoInputSheetCols().add(lancamentoInputSheetCols);
		lancamentoInputSheetCols.setLancamentoInputSheet(this);
	}

	public void removeLancamentoInputSheetCols(LancamentoInputSheetCols lancamentoInputSheetCols) {
		getLancamentoInputSheetCols().remove(lancamentoInputSheetCols);
		lancamentoInputSheetCols.setLancamentoInputSheet(null);
	}

}

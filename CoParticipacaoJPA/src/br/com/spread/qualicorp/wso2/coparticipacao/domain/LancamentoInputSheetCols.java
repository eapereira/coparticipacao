package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class LancamentoInputSheetCols extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3009354352514778704L;

	private LancamentoInputSheet lancamentoInputSheet;

	private ArquivoInputSheetColsDef arquivoInputSheetColsDef;

	private LancamentoColType lancamentoColType;

	public LancamentoInputSheetCols() {
		super();
	}

	public LancamentoInputSheet getLancamentoInputSheet() {
		return lancamentoInputSheet;
	}

	public void setLancamentoInputSheet(LancamentoInputSheet lancamentoInputSheet) {
		this.lancamentoInputSheet = lancamentoInputSheet;
	}

	public ArquivoInputSheetColsDef getArquivoInputSheetColsDef() {
		return arquivoInputSheetColsDef;
	}

	public void setArquivoInputSheetColsDef(ArquivoInputSheetColsDef arquivoInputSheetColsDef) {
		this.arquivoInputSheetColsDef = arquivoInputSheetColsDef;
	}

	public LancamentoColType getLancamentoColType() {
		return lancamentoColType;
	}

	public void setLancamentoColType(LancamentoColType lancamentoColType) {
		this.lancamentoColType = lancamentoColType;
	}
}

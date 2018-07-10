package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_input_lancamento database table.
 * 
 */
public abstract class InputLancamento extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private ArquivoInputColsDef arquivoInputColsDef;

	private LancamentoColsDef lancamentoColsDef;

	public InputLancamento() {
	}

	public InputLancamento(InputLancamento entity) {
		super(entity);
	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return this.arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

	public LancamentoColsDef getLancamentoColsDef() {
		return this.lancamentoColsDef;
	}

	public void setLancamentoColsDef(LancamentoColsDef lancamentoColsDef) {
		this.lancamentoColsDef = lancamentoColsDef;
	}

}
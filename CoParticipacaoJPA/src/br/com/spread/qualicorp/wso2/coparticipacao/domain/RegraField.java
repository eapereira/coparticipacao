package br.com.spread.qualicorp.wso2.coparticipacao.domain;

/**
 * The persistent class for the tb_regra_field database table.
 * 
 */
public abstract class RegraField extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	private ArquivoInputColsDef arquivoInputColsDef;

	private RegraOperation regraOperation;

	public RegraField() {
	}

	public RegraField(RegraField entity) {
		super(entity);
	}

	public RegraOperation getRegraOperation() {
		return this.regraOperation;
	}

	public void setRegraOperation(RegraOperation regraOperation) {
		this.regraOperation = regraOperation;
	}

	public ArquivoInputColsDef getArquivoInputColsDef() {
		return arquivoInputColsDef;
	}

	public void setArquivoInputColsDef(ArquivoInputColsDef arquivoInputColsDef) {
		this.arquivoInputColsDef = arquivoInputColsDef;
	}

}
package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tb_arquivo_output database table.
 * 
 */
public abstract class ArquivoOutput extends AbstractDomain {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7623160858553621671L;

	private String descrArquivo;

	private String nameArquivoFormat;
	private Empresa empresa;

	private List<ArquivoOutputSheet> arquivoOutputSheets;

	public ArquivoOutput() {
		arquivoOutputSheets=new ArrayList<>();
	}

	public ArquivoOutput(ArquivoOutput entity) {
		super(entity);
	}

	public String getDescrArquivo() {
		return this.descrArquivo;
	}

	public void setDescrArquivo(String descrArquivo) {
		this.descrArquivo = descrArquivo;
	}

	public String getNameArquivoFormat() {
		return this.nameArquivoFormat;
	}

	public void setNameArquivoFormat(String nmArquivoFormat) {
		this.nameArquivoFormat = nmArquivoFormat;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<ArquivoOutputSheet> getArquivoOutputSheets() {
		return this.arquivoOutputSheets;
	}

	public void setArquivoOutputSheets(List<ArquivoOutputSheet> arquivoOutputSheets) {
		this.arquivoOutputSheets = arquivoOutputSheets;
	}

	public ArquivoOutputSheet addArquivoOutputSheet(ArquivoOutputSheet arquivoOutputSheet) {
		getArquivoOutputSheets().add(arquivoOutputSheet);
		arquivoOutputSheet.setArquivoOutput(this);

		return arquivoOutputSheet;
	}

	public ArquivoOutputSheet removeArquivoOutputSheet(ArquivoOutputSheet arquivoOutputSheet) {
		getArquivoOutputSheets().remove(arquivoOutputSheet);
		arquivoOutputSheet.setArquivoOutput(null);

		return arquivoOutputSheet;
	}

}
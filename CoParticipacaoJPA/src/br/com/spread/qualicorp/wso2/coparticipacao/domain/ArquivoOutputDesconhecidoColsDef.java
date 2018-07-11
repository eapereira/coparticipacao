package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class ArquivoOutputDesconhecidoColsDef extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5744067170574192256L;

	private Integer ordem;
	private ColDefType type;

	private String nameColumn;
	private Integer length;
	private String format;

	private ArquivoOutputDesconhecido arquivoOutputDesconhecido;

	private List<ArquivoInputOutputDesconhecido> arquivoInputOutputDesconhecidos;

	public ArquivoOutputDesconhecidoColsDef() {
		arquivoInputOutputDesconhecidos = new ArrayList<>();
	}

	public ArquivoOutputDesconhecido getArquivoOutputDesconhecido() {
		return arquivoOutputDesconhecido;
	}

	public void setArquivoOutputDesconhecido(
			ArquivoOutputDesconhecido arquivoOutputDesconhecido) {
		this.arquivoOutputDesconhecido = arquivoOutputDesconhecido;
	}

	public List<ArquivoInputOutputDesconhecido> getArquivoInputOutputDesconhecidos() {
		return arquivoInputOutputDesconhecidos;
	}

	public void setArquivoInputOutputDesconhecidos(
			List<ArquivoInputOutputDesconhecido> arquivoInputOutputDesconhecidos) {
		this.arquivoInputOutputDesconhecidos = arquivoInputOutputDesconhecidos;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public ColDefType getType() {
		return type;
	}

	public void setType(ColDefType type) {
		this.type = type;
	}

	public String getNameColumn() {
		return nameColumn;
	}

	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}

package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class Execucao extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5223749130215131303L;

	private Empresa empresa;

	private ExecucaoType execucaoType;

	private List<ArquivoExecucao> arquivoExecucaos;

	public Execucao() {
		arquivoExecucaos = new ArrayList<>();
	}

	public List<ArquivoExecucao> getArquivoExecucaos() {
		return arquivoExecucaos;
	}

	public void setArquivoExecucaos(List<ArquivoExecucao> arquivoExecucaos) {
		this.arquivoExecucaos = arquivoExecucaos;
	}

	public void addArquivoExecucao(ArquivoExecucao arquivoExecucao) {
		getArquivoExecucaos().add(arquivoExecucao);
		arquivoExecucao.setExecucao(this);
	}

	public void removeArquivoExecucao(ArquivoExecucao arquivoExecucao) {
		getArquivoExecucaos().remove(arquivoExecucao);
		arquivoExecucao.setExecucao(null);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ExecucaoType getExecucaoType() {
		return execucaoType;
	}

	public void setExecucaoType(ExecucaoType execucaoType) {
		this.execucaoType = execucaoType;
	}
}

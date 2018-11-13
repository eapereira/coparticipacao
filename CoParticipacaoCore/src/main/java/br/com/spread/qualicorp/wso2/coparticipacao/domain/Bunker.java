package br.com.spread.qualicorp.wso2.coparticipacao.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.ArquivoInputUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DesconhecidoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.EmpresaUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.LancamentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularIsentoUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;

/**
 * Classe para armazenar os registros carregados pelo sistema, ao atingir o
 * número máximo de registros definido na variável <code>MAX_BUNKER_SIZE</code>
 * DEFINIDA EM {@link ParameterUi} para a {@link EmpresaUi} que possui o
 * {@link ArquivoInputUi}, que esta sendo processado.</br>
 * Os registros serão enviados para serem gravados no banco ao final do
 * processamento.
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class Bunker {
	private List<TitularUi> titularUis;

	private List<DependenteUi> dependenteUis;

	private List<LancamentoUi> lancamentoUis;

	private List<DesconhecidoUi> desconhecidoUis;

	private List<TitularIsentoUi> titularIsentoUis;

	private List<DependenteIsentoUi> dependenteIsentoUis;

	public Bunker() {
		titularUis = new ArrayList<TitularUi>();
		dependenteUis = new ArrayList<DependenteUi>();
		lancamentoUis = new ArrayList<LancamentoUi>();
		desconhecidoUis = new ArrayList<DesconhecidoUi>();

		titularIsentoUis = new ArrayList<TitularIsentoUi>();
		dependenteIsentoUis = new ArrayList<DependenteIsentoUi>();
	}

	public List<TitularUi> getTitularUis() {
		return titularUis;
	}

	public void setTitularUis(List<TitularUi> titularUis) {
		this.titularUis = titularUis;
	}

	public List<DependenteUi> getDependenteUis() {
		return dependenteUis;
	}

	public void setDependenteUis(List<DependenteUi> dependenteUis) {
		this.dependenteUis = dependenteUis;
	}

	public List<LancamentoUi> getLancamentoUis() {
		return lancamentoUis;
	}

	public void setLancamentoUis(List<LancamentoUi> lancamentoUis) {
		this.lancamentoUis = lancamentoUis;
	}

	public List<DesconhecidoUi> getDesconhecidoUis() {
		return desconhecidoUis;
	}

	public void setDesconhecidoUis(List<DesconhecidoUi> desconhecidoUis) {
		this.desconhecidoUis = desconhecidoUis;
	}

	public List<TitularIsentoUi> getTitularIsentoUis() {
		return titularIsentoUis;
	}

	public void setTitularIsentoUis(List<TitularIsentoUi> titularIsentoUis) {
		this.titularIsentoUis = titularIsentoUis;
	}

	public List<DependenteIsentoUi> getDependenteIsentoUis() {
		return dependenteIsentoUis;
	}

	public void setDependenteIsentoUis(
			List<DependenteIsentoUi> dependenteIsentoUis) {
		this.dependenteIsentoUis = dependenteIsentoUis;
	}
}

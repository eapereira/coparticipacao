package br.com.spread.qualicorp.wso2.coparticipacao.domain.view.bradesco;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class AutomindCoparticipacaoView extends BradescoCoparticipacaoView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3078942775061985813L;

	private String subFatura;
	
	public AutomindCoparticipacaoView() {
		super();
	}

	public String getSubFatura() {
		return subFatura;
	}

	public void setSubFatura(String subFatura) {
		this.subFatura = subFatura;
	}

}

package br.com.spread.qualicorp.wso2.coparticipacao.exception;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TitularDuplicated extends CoParticipacaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8919320684755724486L;

	public TitularDuplicated() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TitularDuplicated(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public TitularDuplicated(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TitularDuplicated(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TitularDuplicated(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TitularDuplicated(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TitularDuplicated(BeneficiarioUi beneficiarioUi) {
		super(String.format("There's already a Titular in database with NR_CPF[%s]:", beneficiarioUi.getCpf()));
	}
}

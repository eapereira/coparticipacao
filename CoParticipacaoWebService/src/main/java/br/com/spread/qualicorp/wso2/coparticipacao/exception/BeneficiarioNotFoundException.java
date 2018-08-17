package br.com.spread.qualicorp.wso2.coparticipacao.exception;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Beneficiario;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class BeneficiarioNotFoundException extends CoParticipacaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2732017378195096978L;

	public BeneficiarioNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BeneficiarioNotFoundException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public BeneficiarioNotFoundException(
			String message,
			Throwable cause,
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BeneficiarioNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BeneficiarioNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BeneficiarioNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BeneficiarioNotFoundException(BeneficiarioUi beneficiarioUi) {
		super(
				"The Beneficiário [%s] with Matricula [%s] and CPF [%s] wasn't found in database:",
				beneficiarioUi.getNameBeneficiario(),
				beneficiarioUi.getMatricula(),
				beneficiarioUi.getCpf());
	}
}

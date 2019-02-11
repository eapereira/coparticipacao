package br.com.spread.qualicorp.wso2.coparticipacao.exception;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.BeneficiarioUi;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DependenteDuplicated extends CoParticipacaoException {

	public DependenteDuplicated() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DependenteDuplicated(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public DependenteDuplicated(
			String message,
			Throwable cause,
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DependenteDuplicated(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DependenteDuplicated(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DependenteDuplicated(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DependenteDuplicated(BeneficiarioUi beneficiarioUi) {
		super(
				String.format(
						"There's already a Dependente[{}] in database with NR_CPF[{}]:",
						beneficiarioUi.getNameBeneficiario(),
						beneficiarioUi.getCpf()));
	}

}

package br.com.spread.qualicorp.wso2.coparticipacao.exception;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class DependenteNotFoundException extends CoParticipacaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3909351770398999310L;

	public DependenteNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public DependenteNotFoundException(
			String message,
			Throwable cause,
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DependenteNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DependenteNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DependenteNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DependenteNotFoundException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

}

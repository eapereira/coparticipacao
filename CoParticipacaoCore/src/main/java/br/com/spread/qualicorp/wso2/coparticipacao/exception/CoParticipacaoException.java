package br.com.spread.qualicorp.wso2.coparticipacao.exception;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class CoParticipacaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4203756305842810672L;

	public CoParticipacaoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoParticipacaoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CoParticipacaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CoParticipacaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CoParticipacaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CoParticipacaoException(String message, Object ... args) {
		super(String.format(message, args));
	}

}

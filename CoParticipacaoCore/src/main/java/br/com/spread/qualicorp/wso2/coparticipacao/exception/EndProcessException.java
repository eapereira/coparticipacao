package br.com.spread.qualicorp.wso2.coparticipacao.exception;

import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class EndProcessException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1496611811013160110L;

	public EndProcessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EndProcessException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public EndProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EndProcessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EndProcessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EndProcessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

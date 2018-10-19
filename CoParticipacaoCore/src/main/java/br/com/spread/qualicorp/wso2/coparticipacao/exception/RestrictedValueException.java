package br.com.spread.qualicorp.wso2.coparticipacao.exception;

import br.com.spread.qualicorp.wso2.coparticipacao.service.ServiceException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class RestrictedValueException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 526583573403658303L;

	public RestrictedValueException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestrictedValueException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public RestrictedValueException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RestrictedValueException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RestrictedValueException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RestrictedValueException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

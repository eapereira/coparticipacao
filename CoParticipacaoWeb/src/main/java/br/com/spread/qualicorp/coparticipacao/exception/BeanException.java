package br.com.spread.qualicorp.coparticipacao.exception;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class BeanException extends CoParticipacaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4340695921092732871L;

	public BeanException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BeanException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public BeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BeanException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BeanException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BeanException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

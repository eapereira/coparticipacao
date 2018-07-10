package br.com.spread.qualicorp.wso2.coparticipacao.dao;

import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class DaoException extends CoParticipacaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1800880365866840007L;

	public DaoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

}

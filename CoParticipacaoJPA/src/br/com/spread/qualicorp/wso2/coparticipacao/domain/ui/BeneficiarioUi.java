package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Beneficiario;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class BeneficiarioUi extends Beneficiario {
	/**
	 * 
	 */
	private static final long serialVersionUID = 294003587169638383L;

	public BeneficiarioUi() {
		super();
	}

	public BeneficiarioUi(BeneficiarioUi beneficiarioUi) throws IllegalAccessException, InvocationTargetException {
		super();

		BeanUtils.copyProperties(this, beneficiarioUi);
	}
}

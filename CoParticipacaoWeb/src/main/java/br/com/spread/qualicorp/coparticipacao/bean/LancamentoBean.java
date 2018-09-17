package br.com.spread.qualicorp.coparticipacao.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.spread.qualicorp.coparticipacao.exception.BeanException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Controller
@SessionScope
public class LancamentoBean extends AbstractBean {

	private static final Logger LOGGER = LogManager.getLogger(LancamentoBean.class);

	public String showPage() throws BeanException {
		LOGGER.info("BEGIN");

		LOGGER.info("END");
		return "lancamento";
	}
}

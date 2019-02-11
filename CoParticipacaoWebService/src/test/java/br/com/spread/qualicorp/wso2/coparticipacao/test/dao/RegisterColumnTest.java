package br.com.spread.qualicorp.wso2.coparticipacao.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.RegisterColumnDao;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class RegisterColumnTest extends CoParticipacaoTest {

	@Autowired
	private RegisterColumnDao registerColumnDao;

	@Test
	public void testFindById() throws Exception {
		Assert.assertNotNull(registerColumnDao);
	}
}

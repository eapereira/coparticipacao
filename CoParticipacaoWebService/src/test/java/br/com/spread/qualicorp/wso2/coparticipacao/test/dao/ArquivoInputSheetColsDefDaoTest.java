package br.com.spread.qualicorp.wso2.coparticipacao.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.ArquivoInputSheetColsDefDao;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
@Transactional
public class ArquivoInputSheetColsDefDaoTest {
	
	@Autowired
	private ArquivoInputSheetColsDefDao arquivoInputColsDefDao;

	@Test
	public void testFindById() throws Exception {
		Assert.assertNotNull(arquivoInputColsDefDao);
	}
}

package br.com.spread.qualicorp.wso2.coparticipacao.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.EmpresaDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;
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
public class EmpresaDaoTest {

	@Autowired
	private EmpresaDao empresaDao;

	private static final Long ID_MUITO_FACIL = 1l;

	private static final String NM_MUITO_FACIL = "Muito Fácil Arrecadação e Recebimento Ltda.";

	@Test
	public void testFindById() throws Exception {
		EmpresaEntity empresaEntity = empresaDao.findById(ID_MUITO_FACIL);

		Assert.assertNotNull(empresaEntity);

		Assert.assertEquals(empresaEntity.getId(), ID_MUITO_FACIL);
		Assert.assertEquals(empresaEntity.getNameEmpresa(), NM_MUITO_FACIL);
	}
}

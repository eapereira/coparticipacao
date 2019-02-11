package br.com.spread.qualicorp.wso2.coparticipacao.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.EmpresaDao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.EmpresaEntity;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;

/**
 * 
 * @author <a href="edson.apereira@spread.com.br">Edson Alves Pereira</a>
 *
 */
public class EmpresaDaoTest extends CoParticipacaoTest {

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

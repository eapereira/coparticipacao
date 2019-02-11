package br.com.spread.qualicorp.wso2.coparticipacao.test.report.dataSource.bradesco;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import br.com.spread.qualicorp.wso2.coparticipacao.report.dataSource.bradesco.technit.TechnitOdontoCoparticipacaoJRDataSource;
import br.com.spread.qualicorp.wso2.coparticipacao.test.report.dataSource.CoParticipacaoJRField;
import br.com.spread.qualicorp.wso2.coparticipacao.test.service.CoParticipacaoTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRField;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class TechnitOdontoCoparticipacaoJRDataSourceTest extends CoParticipacaoTest {

	private static final Logger LOGGER = LogManager.getLogger(TechnitOdontoCoparticipacaoJRDataSourceTest.class);
	private static final int NUM_REGISTERS_EXPECTED = 3;

	@Test
	public void testDataSource() throws Exception {
		LOGGER.info("BEGIN");
		int numRegisters = NumberUtils.INTEGER_ZERO;
		JRDataSource dataSource = TechnitOdontoCoparticipacaoJRDataSource.getInstance();
		JRField jrField = createField("nomeTitular");

		while (dataSource.next()) {
			LOGGER.info("Field's name[{}]:", dataSource.getFieldValue(jrField));
			numRegisters++;
		}

		Assert.assertEquals(NUM_REGISTERS_EXPECTED, numRegisters);

		LOGGER.info("END");
	}

	private JRField createField(String fieldName) throws Exception {
		return new CoParticipacaoJRField(fieldName);
	}

}

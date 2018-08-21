package br.com.spread.qualicorp.wso2.coparticipacao.test.search;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.TitularUi;
import br.com.spread.qualicorp.wso2.coparticipacao.search.MapKey;
import br.com.spread.qualicorp.wso2.coparticipacao.search.PartitionMap;
import br.com.spread.qualicorp.wso2.coparticipacao.search.TitularByCpfAndNameMapKeyBuilder;
import br.com.spread.qualicorp.wso2.coparticipacao.test.config.CoParticipacaoWebServiceConfigurationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CoParticipacaoWebServiceConfigurationTest.class })
@Transactional
@ActiveProfiles("test")
public class PartitionMapTest {

	private static final int DATA_SIZE = 10000;

	private static final long BASE_CPF = 100000000000l;

	private static final Long NR_CPF = BASE_CPF + 9999;

	private static final String NM_TITULAR = "Quake 9999";

	@Test
	public void testSearcPartitionhMap() throws Exception {
		PartitionMap<TitularUi> partitionMap = new PartitionMap<TitularUi>(
				buildTitularData(),
				new TitularByCpfAndNameMapKeyBuilder());
		TitularUi titularUi = partitionMap.get(new MapKey(NR_CPF, NM_TITULAR));

		Assert.assertNotNull(titularUi);
		Assert.assertEquals(NR_CPF, titularUi.getCpf());
		Assert.assertEquals(NM_TITULAR, titularUi.getNameTitular());
	}

	@Test
	public void testSearchList() throws Exception {
		List<TitularUi> titularUis = buildTitularData();
		TitularUi titularUiResult = null;

		for (TitularUi titularUi : titularUis) {
			if (titularUi.getCpf().equals(NR_CPF)) {
				if (titularUi.getNameTitular().equals(NM_TITULAR)) {
					titularUiResult = titularUi;
					break;
				}
			}
		}

		Assert.assertNotNull(titularUiResult);
		Assert.assertEquals(NR_CPF, titularUiResult.getCpf());
		Assert.assertEquals(NM_TITULAR, titularUiResult.getNameTitular());
	}

	@Test
	public void testSearchTreeMap() throws Exception {
		TreeMap<MapKey, TitularUi> map = new TreeMap<MapKey, TitularUi>();
		List<TitularUi> titularUis = buildTitularData();
		TitularUi titularUiResult = null;

		for (TitularUi titularUi : titularUis) {
			map.put(new MapKey(titularUi.getCpf(), titularUi.getNameTitular()), titularUi);
		}

		titularUiResult = map.get(new MapKey(NR_CPF, NM_TITULAR));

		Assert.assertNotNull(titularUiResult);
		Assert.assertEquals(NR_CPF, titularUiResult.getCpf());
		Assert.assertEquals(NM_TITULAR, titularUiResult.getNameTitular());
	}

	private List<TitularUi> buildTitularData() {
		List<TitularUi> titularUis = new ArrayList<TitularUi>();
		TitularUi titularUi;

		for (int i = 0; i < DATA_SIZE; i++) {
			titularUi = new TitularUi();
			titularUi.setCpf(BASE_CPF + i);
			titularUi.setNameTitular(String.format("Quake %d", i));

			titularUis.add(titularUi);
		}

		return titularUis;
	}
}

package br.com.spread.qualicorp.wso2.coparticipacao.search;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.AbstractDomain;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class PartitionMap<T extends AbstractDomain> {

	private static final Logger LOGGER = LogManager.getLogger(PartitionMap.class);

	private static final int BRANCH_SIZE = 10;

	private Long firstIndex;

	private Long lastIndex;

	private TreeMap<MapKey, T> map;

	private List<PartitionMap<T>> children;

	private MapKeyBuilder<T> mapKeyBuilder;

	private List<T> listData;

	public PartitionMap(List<T> data, MapKeyBuilder<T> mapKeyBuilder) throws CoParticipacaoException {
		this.listData = data;
		this.mapKeyBuilder = mapKeyBuilder;
		this.firstIndex = NumberUtils.LONG_ZERO;
		this.lastIndex = NumberUtils.LONG_ZERO;

		if (!data.isEmpty()) {
			createTree(data);
		}
	}

	private void createTree(List<T> data) throws CoParticipacaoException {
		int middle = data.size() / 2;
		int index = 0;
		List<T> list;
		PartitionMap<T> partitionMap;
		MapKey mapKey = null;

		try {
			LOGGER.debug("BEGIN");
			LOGGER.debug("Received List size[{}]:", data.size());
			LOGGER.debug("Middle pivot to use [{}]:", middle);

			children = new ArrayList<PartitionMap<T>>();
			map = new TreeMap<MapKey, T>();

			list = new ArrayList<T>();

			for (T item : data) {
				mapKey = getMapKeyBuilder().getMapKey(item);

				if (getFirstIndex() == null) {
					setFirstIndex(mapKey.getKey1());
				}

				if (data.size() <= BRANCH_SIZE) {
					map.put(mapKey, item);
				} else {
					list.add(item);

					if (index >= middle) {
						partitionMap = new PartitionMap<T>(list, mapKeyBuilder);
						getChildren().add(partitionMap);

						list = new ArrayList<T>();
						index = 0;
					}

					index++;
				}

			}

			if (!list.isEmpty()) {
				partitionMap = new PartitionMap<T>(list, mapKeyBuilder);
				getChildren().add(partitionMap);
			}

			if (mapKey != null) {
				setLastIndex(mapKey.getKey1());
			}

			LOGGER.debug("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CoParticipacaoException(e);
		}
	}

	public boolean contains(Long index) {
		LOGGER.debug("Searching index: ... [{}]", index);
		LOGGER.debug("FirstIndex: ........ [{}]", getFirstIndex());
		LOGGER.debug("LastIndex: ......... [{}]", getLastIndex());

		if (index != null) {
			if (getFirstIndex() <= index && getLastIndex() >= index) {
				return true;
			}
		}

		return false;
	}

	public T get(MapKey mapKey) {
		if (contains(mapKey.getKey1())) {
			if (!getChildren().isEmpty()) {
				for (PartitionMap<T> partitionMap : getChildren()) {
					if (partitionMap.contains(mapKey.getKey1())) {
						return partitionMap.get(mapKey);
					}
				}
			} else {
				return map.get(mapKey);
			}
		}

		return null;
	}

	public Long getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(Long firstIndex) {
		this.firstIndex = firstIndex;
	}

	public Long getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(Long lastIndex) {
		this.lastIndex = lastIndex;
	}

	public TreeMap<MapKey, T> getMap() {
		return map;
	}

	public void setMap(TreeMap<MapKey, T> map) {
		this.map = map;
	}

	public List<PartitionMap<T>> getChildren() {
		return children;
	}

	public void setChildren(List<PartitionMap<T>> children) {
		this.children = children;
	}

	public MapKeyBuilder<T> getMapKeyBuilder() {
		return mapKeyBuilder;
	}

	public void setMapKeyBuilder(MapKeyBuilder<T> mapKeyBuilder) {
		this.mapKeyBuilder = mapKeyBuilder;
	}

	public void add(T value) throws CoParticipacaoException {
		listData.add(value);

		createTree(listData);
	}
}

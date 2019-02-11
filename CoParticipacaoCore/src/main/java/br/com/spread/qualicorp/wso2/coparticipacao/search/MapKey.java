package br.com.spread.qualicorp.wso2.coparticipacao.search;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class MapKey implements Comparable<MapKey> {

	private Long key1;

	private String key2;

	public MapKey() {

	}

	public MapKey(Long key1, String key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	public Long getKey1() {
		return key1;
	}

	public void setKey1(Long key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key1 == null) ? 0 : key1.hashCode());
		result = prime * result + ((key2 == null) ? 0 : key2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapKey other = (MapKey) obj;
		if (key1 == null) {
			if (other.key1 != null)
				return false;
		} else if (!key1.equals(other.key1))
			return false;
		if (key2 == null) {
			if (other.key2 != null)
				return false;
		} else if (!key2.equals(other.key2))
			return false;
		return true;
	}

	public int compareTo(MapKey mapKey) {
		if (mapKey.getKey1().equals(getKey1()) && mapKey.getKey2().equals(getKey2())) {
			return 0;
		}

		return 1;
	}
}

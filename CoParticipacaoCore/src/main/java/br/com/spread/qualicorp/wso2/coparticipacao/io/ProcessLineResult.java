package br.com.spread.qualicorp.wso2.coparticipacao.io;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public enum ProcessLineResult {

								END_OF_SHEET(0, "END-OF-SHEET"),
								READ_NEXT(1, "READ-NEXT");

	private Integer id;
	private String description;

	private ProcessLineResult(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}

package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ExternalProcess;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_EXTERNAL_PROCESS")
@NamedQuery(name = "ExternalProcessEntity.findAll", query = "SELECT e FROM ExternalProcessEntity e")
public class ExternalProcessEntity extends ExternalProcess implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3968528469465957457L;

	public ExternalProcessEntity() {
		super();
	}

	@Column(name = "CD_REGEXP_ARQUIVO_INPUT")
	@Override
	public String getRegexpNameArquivoInput() {
		// TODO Auto-generated method stub
		return super.getRegexpNameArquivoInput();
	}

	@Column(name = "CD_REGEXP_ARQUIVO_OUTPUT")
	@Override
	public String getRegexpNameArquivoOutput() {
		// TODO Auto-generated method stub
		return super.getRegexpNameArquivoOutput();
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = EmpresaEntity.class)
	@JoinColumn(name = "ID_EMPRESA")
	@Override
	public Empresa getEmpresa() {
		// TODO Auto-generated method stub
		return super.getEmpresa();
	}
}

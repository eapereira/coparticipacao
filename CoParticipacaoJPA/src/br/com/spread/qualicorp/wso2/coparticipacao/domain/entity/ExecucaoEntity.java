package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoExecucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Empresa;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Execucao;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ExecucaoType;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.converter.ExecucaoTypeConverter;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.listener.ExecucaoEntityListener;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_EXECUCAO")
@NamedQuery(name = "ExecucaoEntity.findAll", query = "SELECT e FROM ExecucaoEntity e")
@EntityListeners(ExecucaoEntityListener.class)
public class ExecucaoEntity extends Execucao implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5664966549702697723L;

	public ExecucaoEntity() {
		super();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "execucao",
			targetEntity = ArquivoExecucaoEntity.class)
	@Override
	public List<ArquivoExecucao> getArquivoExecucaos() {
		return super.getArquivoExecucaos();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = EmpresaEntity.class)
	@JoinColumn(name = "ID_EMPRESA")
	@Override
	public Empresa getEmpresa() {
		return super.getEmpresa();
	}

	@Convert(converter = ExecucaoTypeConverter.class)
	@Column(name = "TP_STATUS")
	@Override
	public ExecucaoType getExecucaoType() {
		// TODO Auto-generated method stub
		return super.getExecucaoType();
	}
}

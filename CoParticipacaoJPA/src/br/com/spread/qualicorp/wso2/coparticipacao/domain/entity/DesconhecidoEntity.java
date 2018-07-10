package br.com.spread.qualicorp.wso2.coparticipacao.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Desconhecido;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.DesconhecidoDetail;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_DESCONHECIDO")
@NamedQuery(
		name = "DesconhecidoEntity.findAll",
		query = "SELECT a FROM DesconhecidoEntity a")
public class DesconhecidoEntity extends Desconhecido {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2860052398629057173L;

	public DesconhecidoEntity() {
		super();
	}

	@Column(name = "CD_MES")
	@Override
	public Integer getMes() {
		// TODO Auto-generated method stub
		return super.getMes();
	}

	@Column(name = "CD_ANO")
	@Override
	public Integer getAno() {
		// TODO Auto-generated method stub
		return super.getAno();
	}

	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT")
	@Override
	public ArquivoInput getArquivoInput() {
		// TODO Auto-generated method stub
		return super.getArquivoInput();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "desconhecido",
			targetEntity = DesconhecidoDetailEntity.class)
	@Override
	public List<DesconhecidoDetail> getDesconhecidoDetails() {
		// TODO Auto-generated method stub
		return super.getDesconhecidoDetails();
	}

}

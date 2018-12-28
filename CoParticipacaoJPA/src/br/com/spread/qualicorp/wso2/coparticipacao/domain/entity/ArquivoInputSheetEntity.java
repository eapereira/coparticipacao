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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInput;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheetColsDef;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Contrato;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.IsentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.LancamentoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Regra;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegraConditional;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_ARQUIVO_INPUT_SHEET")
@NamedQuery(name = "ArquivoInputSheetEntity.findAll", query = "SELECT a FROM ArquivoInputSheetEntity a")
public class ArquivoInputSheetEntity extends ArquivoInputSheet implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2778836792925265748L;

	public ArquivoInputSheetEntity() {
		super();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT")
	@Override
	public ArquivoInput getArquivoInput() {
		// TODO Auto-generated method stub
		return super.getArquivoInput();
	}

	@Column(name = "CD_SHEET")
	@Override
	public Integer getSheetId() {
		// TODO Auto-generated method stub
		return super.getSheetId();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "arquivoInputSheet",
			targetEntity = ArquivoInputSheetColsDefEntity.class)
	@Override
	public List<ArquivoInputSheetColsDef> getArquivoInputSheetColsDefs() {
		// TODO Auto-generated method stub
		return super.getArquivoInputSheetColsDefs();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ContratoEntity.class)
	@JoinColumn(name = "ID_CONTRATO")
	@Override
	public Contrato getContrato() {
		// TODO Auto-generated method stub
		return super.getContrato();
	}

	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "arquivoInputSheet",
			targetEntity = LancamentoInputSheetEntity.class)
	@Override
	public LancamentoInputSheet getLancamentoInputSheet() {
		// TODO Auto-generated method stub
		return super.getLancamentoInputSheet();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "arquivoInputSheet",
			targetEntity = RegraEntity.class)
	@Override
	public List<Regra> getRegras() {
		// TODO Auto-generated method stub
		return super.getRegras();
	}

	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "arquivoInputSheet",
			targetEntity = IsentoInputSheetEntity.class)
	@Override
	public IsentoInputSheet getIsentoInputSheet() {
		// TODO Auto-generated method stub
		return super.getIsentoInputSheet();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "arquivoInputSheet",
			targetEntity = RegraConditionalEntity.class)
	@Override
	public List<RegraConditional> getRegraConditionals() {
		// TODO Auto-generated method stub
		return super.getRegraConditionals();
	}
}

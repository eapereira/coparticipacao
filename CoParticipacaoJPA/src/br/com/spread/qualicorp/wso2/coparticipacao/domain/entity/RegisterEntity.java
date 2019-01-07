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

import br.com.spread.qualicorp.wso2.coparticipacao.domain.ArquivoInputSheet;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Register;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.RegisterColumn;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Entity
@Table(name = "TB_REGISTER")
@NamedQuery(name = "RegisterEntity.findAll", query = "SELECT o FROM RegisterEntity o")
public class RegisterEntity extends Register implements DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5886976958437172377L;

	public RegisterEntity() {
		super();
	}

	@Column(name = "CD_REGISTER")
	@Override
	public Integer getCdRegister() {
		// TODO Auto-generated method stub
		return super.getCdRegister();
	}

	@Column(name = "NM_REGISTER")
	@Override
	public String getRegisterName() {
		// TODO Auto-generated method stub
		return super.getRegisterName();
	}

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "register",
			targetEntity = RegisterColumnEntity.class)
	@Override
	public List<RegisterColumn> getRegisterColumns() {
		// TODO Auto-generated method stub
		return super.getRegisterColumns();
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ArquivoInputSheetEntity.class)
	@JoinColumn(name = "ID_ARQUIVO_INPUT_SHEET")
	@Override
	public ArquivoInputSheet getArquivoInputSheet() {
		// TODO Auto-generated method stub
		return super.getArquivoInputSheet();
	}

	@Column(name = "CD_ORDEM")
	@Override
	public Integer getOrdem() {
		// TODO Auto-generated method stub
		return super.getOrdem();
	}
}

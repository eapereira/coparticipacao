package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Dependente;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.DependenteEntity;

/**
 * The persistent class for the tb_dependente database table.
 * 
 */
public class DependenteUi extends Dependente {
	private static final long serialVersionUID = 1L;

	private boolean markedForUpdated;

	public DependenteUi() {
		super();

		this.markedForUpdated = false;
	}

	public DependenteUi(DependenteEntity entity) {
		super(entity);
	}

	public boolean isMarkedForUpdated() {
		return markedForUpdated;
	}

	public void setMarkedForUpdated(boolean markedForUpdated) {
		this.markedForUpdated = markedForUpdated;
	}

	@Override
	public BeneficiarioDetail getBeneficiarioDetail() {
		if (super.getBeneficiarioDetail() == null) {
			setBeneficiarioDetail(new BeneficiarioDetail());
		}

		return super.getBeneficiarioDetail();
	}

}
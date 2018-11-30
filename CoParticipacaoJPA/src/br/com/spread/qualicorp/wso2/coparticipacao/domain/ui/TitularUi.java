package br.com.spread.qualicorp.wso2.coparticipacao.domain.ui;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.BeneficiarioDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.Titular;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.entity.TitularEntity;

/**
 * The persistent class for the tb_titular database table.
 * 
 */
public class TitularUi extends Titular {
	private static final long serialVersionUID = 1L;

	private boolean markedForUpdated;

	public TitularUi() {
		super();

		this.markedForUpdated = false;
	}

	public TitularUi(TitularEntity entity) {
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
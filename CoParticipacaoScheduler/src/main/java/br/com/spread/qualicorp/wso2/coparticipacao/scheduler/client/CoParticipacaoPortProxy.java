package br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client;

public class CoParticipacaoPortProxy implements br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPort {
	private String _endpoint = null;
	private br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPort coParticipacaoPort = null;

	public CoParticipacaoPortProxy() {
		_initCoParticipacaoPortProxy();
	}

	public CoParticipacaoPortProxy(String endpoint) {
		_endpoint = endpoint;
		_initCoParticipacaoPortProxy();
	}

	private void _initCoParticipacaoPortProxy() {
		try {
			coParticipacaoPort = (new br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPortServiceLocator())
					.getCoParticipacaoPortSoap11();
			if (coParticipacaoPort != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) coParticipacaoPort)._setProperty("javax.xml.rpc.service.endpoint.address",
							_endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) coParticipacaoPort)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (coParticipacaoPort != null)
			((javax.xml.rpc.Stub) coParticipacaoPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPort getCoParticipacaoPort() {
		if (coParticipacaoPort == null)
			_initCoParticipacaoPortProxy();
		return coParticipacaoPort;
	}

	public br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoResponse coParticipacao(
			br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoRequest coParticipacaoRequest)
			throws java.rmi.RemoteException {
		if (coParticipacaoPort == null)
			_initCoParticipacaoPortProxy();
		return coParticipacaoPort.coParticipacao(coParticipacaoRequest);
	}

}
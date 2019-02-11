/**
 * CoParticipacaoPortServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client;

public class CoParticipacaoPortServiceLocator extends org.apache.axis.client.Service implements br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPortService {

    public CoParticipacaoPortServiceLocator() {
    }


    public CoParticipacaoPortServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CoParticipacaoPortServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CoParticipacaoPortSoap11
    private java.lang.String CoParticipacaoPortSoap11_address = "http://localhost:8080/CoParticipacaoWebService/ws";
    //private java.lang.String CoParticipacaoPortSoap11_address = "http://localhost/CoParticipacaoWebService/ws";

    public java.lang.String getCoParticipacaoPortSoap11Address() {
        return CoParticipacaoPortSoap11_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CoParticipacaoPortSoap11WSDDServiceName = "CoParticipacaoPortSoap11";

    public java.lang.String getCoParticipacaoPortSoap11WSDDServiceName() {
        return CoParticipacaoPortSoap11WSDDServiceName;
    }

    public void setCoParticipacaoPortSoap11WSDDServiceName(java.lang.String name) {
        CoParticipacaoPortSoap11WSDDServiceName = name;
    }

    public br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPort getCoParticipacaoPortSoap11() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CoParticipacaoPortSoap11_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCoParticipacaoPortSoap11(endpoint);
    }

    public br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPort getCoParticipacaoPortSoap11(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPortSoap11Stub _stub = new br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPortSoap11Stub(portAddress, this);
            _stub.setPortName(getCoParticipacaoPortSoap11WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCoParticipacaoPortSoap11EndpointAddress(java.lang.String address) {
        CoParticipacaoPortSoap11_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPort.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPortSoap11Stub _stub = new br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoPortSoap11Stub(new java.net.URL(CoParticipacaoPortSoap11_address), this);
                _stub.setPortName(getCoParticipacaoPortSoap11WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CoParticipacaoPortSoap11".equals(inputPortName)) {
            return getCoParticipacaoPortSoap11();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://qualicorp.spread.com.br/WebService/CoParticipacao", "CoParticipacaoPortService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://qualicorp.spread.com.br/WebService/CoParticipacao", "CoParticipacaoPortSoap11"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CoParticipacaoPortSoap11".equals(portName)) {
            setCoParticipacaoPortSoap11EndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

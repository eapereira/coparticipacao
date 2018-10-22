/**
 * CoParticipacaoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client;

public class CoParticipacaoResponse  implements java.io.Serializable {
    private br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoInfo coParticipacaoInfo;

    public CoParticipacaoResponse() {
    }

    public CoParticipacaoResponse(
           br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoInfo coParticipacaoInfo) {
           this.coParticipacaoInfo = coParticipacaoInfo;
    }


    /**
     * Gets the coParticipacaoInfo value for this CoParticipacaoResponse.
     * 
     * @return coParticipacaoInfo
     */
    public br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoInfo getCoParticipacaoInfo() {
        return coParticipacaoInfo;
    }


    /**
     * Sets the coParticipacaoInfo value for this CoParticipacaoResponse.
     * 
     * @param coParticipacaoInfo
     */
    public void setCoParticipacaoInfo(br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client.CoParticipacaoInfo coParticipacaoInfo) {
        this.coParticipacaoInfo = coParticipacaoInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CoParticipacaoResponse)) return false;
        CoParticipacaoResponse other = (CoParticipacaoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.coParticipacaoInfo==null && other.getCoParticipacaoInfo()==null) || 
             (this.coParticipacaoInfo!=null &&
              this.coParticipacaoInfo.equals(other.getCoParticipacaoInfo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCoParticipacaoInfo() != null) {
            _hashCode += getCoParticipacaoInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CoParticipacaoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://qualicorp.spread.com.br/WebService/CoParticipacao", ">CoParticipacaoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coParticipacaoInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://qualicorp.spread.com.br/WebService/CoParticipacao", "coParticipacaoInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://qualicorp.spread.com.br/WebService/CoParticipacao", "CoParticipacaoInfo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

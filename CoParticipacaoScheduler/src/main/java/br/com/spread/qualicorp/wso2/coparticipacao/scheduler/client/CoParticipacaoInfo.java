/**
 * CoParticipacaoInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.spread.qualicorp.wso2.coparticipacao.scheduler.client;

public class CoParticipacaoInfo  implements java.io.Serializable {
    private long execucaoId;

    private java.math.BigInteger errorCode;

    private java.lang.String errorMessage;

    public CoParticipacaoInfo() {
    }

    public CoParticipacaoInfo(
           long execucaoId,
           java.math.BigInteger errorCode,
           java.lang.String errorMessage) {
           this.execucaoId = execucaoId;
           this.errorCode = errorCode;
           this.errorMessage = errorMessage;
    }


    /**
     * Gets the execucaoId value for this CoParticipacaoInfo.
     * 
     * @return execucaoId
     */
    public long getExecucaoId() {
        return execucaoId;
    }


    /**
     * Sets the execucaoId value for this CoParticipacaoInfo.
     * 
     * @param execucaoId
     */
    public void setExecucaoId(long execucaoId) {
        this.execucaoId = execucaoId;
    }


    /**
     * Gets the errorCode value for this CoParticipacaoInfo.
     * 
     * @return errorCode
     */
    public java.math.BigInteger getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this CoParticipacaoInfo.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.math.BigInteger errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the errorMessage value for this CoParticipacaoInfo.
     * 
     * @return errorMessage
     */
    public java.lang.String getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets the errorMessage value for this CoParticipacaoInfo.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(java.lang.String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CoParticipacaoInfo)) return false;
        CoParticipacaoInfo other = (CoParticipacaoInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.execucaoId == other.getExecucaoId() &&
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.errorMessage==null && other.getErrorMessage()==null) || 
             (this.errorMessage!=null &&
              this.errorMessage.equals(other.getErrorMessage())));
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
        _hashCode += new Long(getExecucaoId()).hashCode();
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getErrorMessage() != null) {
            _hashCode += getErrorMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CoParticipacaoInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://qualicorp.spread.com.br/WebService/CoParticipacao", "CoParticipacaoInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("execucaoId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://qualicorp.spread.com.br/WebService/CoParticipacao", "execucaoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://qualicorp.spread.com.br/WebService/CoParticipacao", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://qualicorp.spread.com.br/WebService/CoParticipacao", "errorMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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

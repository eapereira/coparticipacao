//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementa��o de Refer�ncia (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modifica��es neste arquivo ser�o perdidas ap�s a recompila��o do esquema de origem. 
// Gerado em: 2018.10.25 �s 12:03:15 PM BRT 
//


package br.com.spread.qualicorp.webservice.coparticipacaoreport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coParticipacaoReportInfo" type="{http://qualicorp.spread.com.br/WebService/CoParticipacaoReport}CoParticipacaoReportInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "coParticipacaoReportInfo"
})
@XmlRootElement(name = "CoParticipacaoReportResponse")
public class CoParticipacaoReportResponse {

    @XmlElement(required = true)
    protected CoParticipacaoReportInfo coParticipacaoReportInfo;

    /**
     * Obt�m o valor da propriedade coParticipacaoReportInfo.
     * 
     * @return
     *     possible object is
     *     {@link CoParticipacaoReportInfo }
     *     
     */
    public CoParticipacaoReportInfo getCoParticipacaoReportInfo() {
        return coParticipacaoReportInfo;
    }

    /**
     * Define o valor da propriedade coParticipacaoReportInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link CoParticipacaoReportInfo }
     *     
     */
    public void setCoParticipacaoReportInfo(CoParticipacaoReportInfo value) {
        this.coParticipacaoReportInfo = value;
    }

}

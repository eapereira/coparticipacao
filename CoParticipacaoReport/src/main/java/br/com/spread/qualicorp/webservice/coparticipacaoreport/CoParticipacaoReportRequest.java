//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2018.10.15 às 06:20:24 PM BRT 
//


package br.com.spread.qualicorp.webservice.coparticipacaoreport;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="empresaId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="mes" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="ano" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
    "empresaId",
    "mes",
    "ano"
})
@XmlRootElement(name = "CoParticipacaoReportRequest")
public class CoParticipacaoReportRequest {

    protected long empresaId;
    @XmlElement(required = true)
    protected BigInteger mes;
    @XmlElement(required = true)
    protected BigInteger ano;

    /**
     * Obtém o valor da propriedade empresaId.
     * 
     */
    public long getEmpresaId() {
        return empresaId;
    }

    /**
     * Define o valor da propriedade empresaId.
     * 
     */
    public void setEmpresaId(long value) {
        this.empresaId = value;
    }

    /**
     * Obtém o valor da propriedade mes.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMes() {
        return mes;
    }

    /**
     * Define o valor da propriedade mes.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMes(BigInteger value) {
        this.mes = value;
    }

    /**
     * Obtém o valor da propriedade ano.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAno() {
        return ano;
    }

    /**
     * Define o valor da propriedade ano.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAno(BigInteger value) {
        this.ano = value;
    }

}

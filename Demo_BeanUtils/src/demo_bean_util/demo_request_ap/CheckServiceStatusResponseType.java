//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.08 at 06:10:55 PM ICT 
//


package demo_bean_util.demo_request_ap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Check Service Status response type.
 * 
 * <p>Java class for CheckServiceStatusResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckServiceStatusResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://sita.aero/iborders/APP/APPService/V1}SpecificCountryHeaderType"/>
 *         &lt;element name="Result" type="{http://sita.aero/iborders/APP/APPService/V1}CheckManifestStatusResultType" minOccurs="0"/>
 *         &lt;element name="Error" type="{http://sita.aero/iborders/APP/APPService/V1}SystemErrorType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckServiceStatusResponseType", namespace = "http://sita.aero/iborders/APP/APPService/V1", propOrder = {
    "header",
    "result",
    "error"
})
public class CheckServiceStatusResponseType {

    @XmlElement(name = "Header", required = true)
    protected SpecificCountryHeaderType header;
    @XmlElement(name = "Result")
    protected CheckManifestStatusResultType result;
    @XmlElement(name = "Error")
    protected List<SystemErrorType> error;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link SpecificCountryHeaderType }
     *     
     */
    public SpecificCountryHeaderType getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpecificCountryHeaderType }
     *     
     */
    public void setHeader(SpecificCountryHeaderType value) {
        this.header = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link CheckManifestStatusResultType }
     *     
     */
    public CheckManifestStatusResultType getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckManifestStatusResultType }
     *     
     */
    public void setResult(CheckManifestStatusResultType value) {
        this.result = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the error property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SystemErrorType }
     * 
     * 
     */
    public List<SystemErrorType> getError() {
        if (error == null) {
            error = new ArrayList<SystemErrorType>();
        }
        return this.error;
    }

}
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.19 at 10:31:47 AM CET 
//


package cs.put.poznan.bsr.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authoryzation" type="{http://bsr.poznan.put.cs/ws}authoryzation"/>
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
    "authoryzation"
})
@XmlRootElement(name = "loginRequest")
public class LoginRequest {

    @XmlElement(required = true)
    protected Authoryzation authoryzation;

    /**
     * Gets the value of the authoryzation property.
     * 
     * @return
     *     possible object is
     *     {@link Authoryzation }
     *     
     */
    public Authoryzation getAuthoryzation() {
        return authoryzation;
    }

    /**
     * Sets the value of the authoryzation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Authoryzation }
     *     
     */
    public void setAuthoryzation(Authoryzation value) {
        this.authoryzation = value;
    }

}

package demo_bean_util.demo_request_ap;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderType", namespace = "http://sita.aero/iborders/APP/APPService/V1", propOrder = {
    "dateTime",
    "userId",
    "agency",
    "carrierPrivateData",
    "transactionId",
    "handleMultipleResponse",
    "timingInfo"
})
@XmlSeeAlso({
    SpecificCountryHeaderType.class
})
public class HeaderTypeNewV2 {
	
	@XmlElement(name = "DateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateTime;
    @XmlElement(name = "UserId", required = true)
    protected String userId;
    @XmlElement(name = "Agency", required = true)
    protected AgencyType agency;
    @XmlElement(name = "CarrierPrivateData")
    protected String carrierPrivateData;
    @XmlElement(name = "TransactionId")
    protected String transactionId;
    @XmlElement(name = "HandleMultipleResponse")
    protected Boolean handleMultipleResponse;
    @XmlElement(name = "TimingInfo")
    protected List<TimingInfoType> timingInfo;
	public XMLGregorianCalendar getDateTime() {
		return dateTime;
	}
	public void setDateTime(XMLGregorianCalendar dateTime) {
		this.dateTime = dateTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public AgencyType getAgency() {
		return agency;
	}
	public void setAgency(AgencyType agency) {
		this.agency = agency;
	}
	public String getCarrierPrivateData() {
		return carrierPrivateData;
	}
	public void setCarrierPrivateData(String carrierPrivateData) {
		this.carrierPrivateData = carrierPrivateData;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Boolean getHandleMultipleResponse() {
		return handleMultipleResponse;
	}
	public void setHandleMultipleResponse(Boolean handleMultipleResponse) {
		this.handleMultipleResponse = handleMultipleResponse;
	}
	public List<TimingInfoType> getTimingInfo() {
		return timingInfo;
	}
	public void setTimingInfo(List<TimingInfoType> timingInfo) {
		this.timingInfo = timingInfo;
	}
    
    
}

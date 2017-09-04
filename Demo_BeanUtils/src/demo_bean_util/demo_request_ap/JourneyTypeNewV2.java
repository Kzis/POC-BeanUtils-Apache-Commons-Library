package demo_bean_util.demo_request_ap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JourneyType", propOrder = {
    "scheduleType",
    "carrierType",
    "carrierCode",
    "serviceNumber",
    "departurePort",
    "arrivalPort",
    "departureDate",
    "checkInInfo",
    "expectedOriginInfo",
    "journeyInfo",
    "expectedDestinationInfo"
})
public class JourneyTypeNewV2 {
	
	@XmlElement(name = "ScheduleType")
    protected JourneyScheduleType scheduleType;
    @XmlElement(name = "CarrierType")
    protected CarrierType carrierType;
    @XmlElement(name = "CarrierCode")
    protected String carrierCode;
    @XmlElement(name = "ServiceNumber", required = true)
    protected String serviceNumber;
    @XmlElement(name = "DeparturePort", required = true)
    protected String departurePort;
    @XmlElement(name = "ArrivalPort", required = true)
    protected String arrivalPort;
    @XmlElement(name = "DepartureDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar departureDate;
    @XmlElement(name = "CheckInInfo")
    protected ServiceTransactionInfoType checkInInfo;
    @XmlElement(name = "ExpectedOriginInfo")
    protected ServiceTransactionInfoType expectedOriginInfo;
    @XmlElement(name = "JourneyInfo")
    protected List<JourneyInfoType> journeyInfo;
    @XmlElement(name = "ExpectedDestinationInfo")
    protected ServiceTransactionInfoType expectedDestinationInfo;
	public JourneyScheduleType getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(JourneyScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}
	public CarrierType getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(CarrierType carrierType) {
		this.carrierType = carrierType;
	}
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getDeparturePort() {
		return departurePort;
	}
	public void setDeparturePort(String departurePort) {
		this.departurePort = departurePort;
	}
	public String getArrivalPort() {
		return arrivalPort;
	}
	public void setArrivalPort(String arrivalPort) {
		this.arrivalPort = arrivalPort;
	}
	public XMLGregorianCalendar getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(XMLGregorianCalendar departureDate) {
		this.departureDate = departureDate;
	}
	public ServiceTransactionInfoType getCheckInInfo() {
		return checkInInfo;
	}
	public void setCheckInInfo(ServiceTransactionInfoType checkInInfo) {
		this.checkInInfo = checkInInfo;
	}
	public ServiceTransactionInfoType getExpectedOriginInfo() {
		return expectedOriginInfo;
	}
	public void setExpectedOriginInfo(ServiceTransactionInfoType expectedOriginInfo) {
		this.expectedOriginInfo = expectedOriginInfo;
	}
	public List<JourneyInfoType> getJourneyInfo() {
		 if (journeyInfo == null) {
	            journeyInfo = new ArrayList<JourneyInfoType>();
	        }
	        return this.journeyInfo;
	}
	public void setJourneyInfo(List<JourneyInfoType> journeyInfo) {
		this.journeyInfo = journeyInfo;
	}
	public ServiceTransactionInfoType getExpectedDestinationInfo() {
		return expectedDestinationInfo;
	}
	public void setExpectedDestinationInfo(
			ServiceTransactionInfoType expectedDestinationInfo) {
		this.expectedDestinationInfo = expectedDestinationInfo;
	}

    
}

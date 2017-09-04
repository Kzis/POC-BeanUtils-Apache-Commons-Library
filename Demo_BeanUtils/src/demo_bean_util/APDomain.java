package demo_bean_util;

import java.io.Serializable;

public class APDomain implements Serializable{

	private static final long serialVersionUID = -7328911788448322926L;
	
	private String userId;			//<app:UserId>
	private String userName;		//<app:UserId>
	private String transactionId;	//<app:TransactionId>
	
	//<app:Agency> เธ”เธถเธ�เธ�เธฒเธ� Table config
	private String iataCode;		//<com:IATACode>
	private String carrier;			//<com:Carrier>
	private String pseudoCity;		//<com:PseudoCity>
	private String country;			//<com:Country>
	
	//<app:Journey>
	private String marScheduleType;		//<com:ScheduleType>
	private String marCarrierType;		//<com:CarrierType>
	private String marServiceName;		//<com:ServiceNumber>	Marketing flight
	private String marDepPort;			//<com:DeparturePort>
	private String marArrPort;			//<com:ArrivalPort>
	private String marDepDate;			//<com:DepartureDate>
	private String marArrDate;
	private String marDepTime;
	private String marArrTime;
	private String carrierCode;
	
	//<com:JourneyInfo>
	private String scheduleType;		//<com:ScheduleType>
	private String carrierType;			//<com:CarrierType>
	private String serviceName; 		//<com:ServiceNumber>	Operating flight
	private String originPort; 			//<com:OriginPort>		DEP_PORT_JI
	private String originCountry; 		//<com:OriginCountry>
	private String destinationPort; 		//<com:DestinationPort> ARR_PORT_JI
	private String destinationCountry; 		//<com:DestinationCountry>
	private String depDate;
	private String depTime;
	private String arrDate;
	private String arrTime;
	
	//<com:TravellerInfo>
	private String expectedId;
	private String type;
	private String reference;				//<com:Reference>
	
	//<com:Identity>
	private String familyName;
	private String givenName;
	private String dateOfBirth;
	private String gender;
	private String nationality;
	private String documentType;
	private String documentNumber;
	private String documentExpireDate;
	private String issuingState;	
	
	private String overrideType;
	private String overrideCountry;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getPseudoCity() {
		return pseudoCity;
	}
	public void setPseudoCity(String pseudoCity) {
		this.pseudoCity = pseudoCity;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMarScheduleType() {
		return marScheduleType;
	}
	public void setMarScheduleType(String marScheduleType) {
		this.marScheduleType = marScheduleType;
	}
	public String getMarCarrierType() {
		return marCarrierType;
	}
	public void setMarCarrierType(String marCarrierType) {
		this.marCarrierType = marCarrierType;
	}
	public String getMarServiceName() {
		return marServiceName;
	}
	public void setMarServiceName(String marServiceName) {
		this.marServiceName = marServiceName;
	}
	public String getMarDepPort() {
		return marDepPort;
	}
	public void setMarDepPort(String marDepPort) {
		this.marDepPort = marDepPort;
	}
	public String getMarArrPort() {
		return marArrPort;
	}
	public void setMarArrPort(String marArrPort) {
		this.marArrPort = marArrPort;
	}
	public String getMarDepDate() {
		return marDepDate;
	}
	public void setMarDepDate(String marDepDate) {
		this.marDepDate = marDepDate;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public String getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getOriginPort() {
		return originPort;
	}
	public void setOriginPort(String originPort) {
		this.originPort = originPort;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
	public String getDepDate() {
		return depDate;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getArrDate() {
		return arrDate;
	}
	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public String getExpectedId() {
		return expectedId;
	}
	public void setExpectedId(String expectedId) {
		this.expectedId = expectedId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getDocumentExpireDate() {
		return documentExpireDate;
	}
	public void setDocumentExpireDate(String documentExpireDate) {
		this.documentExpireDate = documentExpireDate;
	}
	public String getIssuingState() {
		return issuingState;
	}
	public void setIssuingState(String issuingState) {
		this.issuingState = issuingState;
	}
	public String getOverrideType() {
		return overrideType;
	}
	public void setOverrideType(String overrideType) {
		this.overrideType = overrideType;
	}
	public String getOverrideCountry() {
		return overrideCountry;
	}
	public void setOverrideCountry(String overrideCountry) {
		this.overrideCountry = overrideCountry;
	}
	public String getMarArrDate() {
		return marArrDate;
	}
	public void setMarArrDate(String marArrDate) {
		this.marArrDate = marArrDate;
	}
	public String getMarDepTime() {
		return marDepTime;
	}
	public void setMarDepTime(String marDepTime) {
		this.marDepTime = marDepTime;
	}
	public String getMarArrTime() {
		return marArrTime;
	}
	public void setMarArrTime(String marArrTime) {
		this.marArrTime = marArrTime;
	}
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	

}

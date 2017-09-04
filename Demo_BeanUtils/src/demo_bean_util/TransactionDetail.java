package demo_bean_util;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Validate domain [after insert passenger data, we'll select them to validate]
 * @author Anusorn.l
 *
 */
@XmlRootElement
public class TransactionDetail extends APDomain{

	private static final long serialVersionUID = 2619084396547817460L;
	
	private String transDetId;
	private String scheduleId;
	private String countryOfBirth;
	private String travelType;
	private String checkInType;
	private String ggFlight;
	private String depCountry;
	private String arrCountry;
	
	private String cancel;
	private String direction; // O: Outbound, I: Inbound
	private String tbPort;
	private String tbDate;
	private String tbTime;
	
	private String timestamp;
	private String flagDOB;
	
	public String getTransDetId() {
		return transDetId;
	}
	public void setTransDetId(String transDetId) {
		this.transDetId = transDetId;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getCountryOfBirth() {
		return countryOfBirth;
	}
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getCheckInType() {
		return checkInType;
	}
	public void setCheckInType(String checkInType) {
		this.checkInType = checkInType;
	}
	public String getGgFlight() {
		return ggFlight;
	}
	public void setGgFlight(String ggFlight) {
		this.ggFlight = ggFlight;
	}
	public String getDepCountry() {
		return depCountry;
	}
	public void setDepCountry(String depCountry) {
		this.depCountry = depCountry;
	}
	public String getArrCountry() {
		return arrCountry;
	}
	public void setArrCountry(String arrCountry) {
		this.arrCountry = arrCountry;
	}
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getTbPort() {
		return tbPort;
	}
	public void setTbPort(String tbPort) {
		this.tbPort = tbPort;
	}
	public String getTbDate() {
		return tbDate;
	}
	public void setTbDate(String tbDate) {
		this.tbDate = tbDate;
	}
	public String getTbTime() {
		return tbTime;
	}
	public void setTbTime(String tbTime) {
		this.tbTime = tbTime;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getFlagDOB() {
		return flagDOB;
	}
	public void setFlagDOB(String flagDOB) {
		this.flagDOB = flagDOB;
	}
	
	
}

package demo_bean_util.demo_request_ap;

import java.util.ArrayList;
import java.util.List;

public class RequestNew {
	
//	HeaderType header;
//	JourneyType journey;
//	List<TravellerType> traveller;
	
	HeaderTypeNew header;
	JourneyTypeNew journey;
	List<TravellerTypeNew> traveller;
	
	public HeaderTypeNew getHeader() {
		return header;
	}
	public void setHeader(HeaderTypeNew header) {
		this.header = header;
	}
	public JourneyTypeNew getJourney() {
		return journey;
	}
	public void setJourney(JourneyTypeNew journey) {
		this.journey = journey;
	}
	public List<TravellerTypeNew> getTraveller() {
		if (traveller == null) {
	      traveller = new ArrayList<TravellerTypeNew>();
	  }
		return this.traveller;
	}
	public void setTraveller(List<TravellerTypeNew> traveller) {
		this.traveller = traveller;
	}
	
	
//	public HeaderType getHeader() {
//		return header;
//	}
//	public void setHeader(HeaderType header) {
//		this.header = header;
//	}
//	public JourneyType getJourney() {
//		return journey;
//	}
//	public void setJourney(JourneyType journey) {
//		this.journey = journey;
//	}
//	public List<TravellerType> getTraveller() {
//		if (traveller == null) {
//            traveller = new ArrayList<TravellerType>();
//        }
//		return this.traveller;
//	}
//	public void setTraveller(List<TravellerType> traveller) {
//		this.traveller = traveller;
//	}
	
	
	
}

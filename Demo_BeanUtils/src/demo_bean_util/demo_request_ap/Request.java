package demo_bean_util.demo_request_ap;

import java.util.ArrayList;
import java.util.List;

public class Request {
	
	HeaderTypeNewV2 header;
	JourneyTypeNewV2 journey;
	List<TravellerTypeNewV2> traveller;
	
	public HeaderTypeNewV2 getHeader() {
		return header;
	}
	public void setHeader(HeaderTypeNewV2 header) {
		this.header = header;
	}
	public JourneyTypeNewV2 getJourney() {
		return journey;
	}
	public void setJourney(JourneyTypeNewV2 journey) {
		this.journey = journey;
	}
	public List<TravellerTypeNewV2> getTraveller() {
		if (traveller == null) {
		      traveller = new ArrayList<TravellerTypeNewV2>();
		  }
			return this.traveller;
	}
	public void setTraveller(List<TravellerTypeNewV2> traveller) {
		this.traveller = traveller;
	}
	
	
}

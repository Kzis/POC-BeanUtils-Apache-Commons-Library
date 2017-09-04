package demo_bean_util;

public class BeanRequest {
	
	String id;
	String name;
	String request;
	
	Header header;
	Header HEADER;
	Journey journey;
	Traveller traveller;

	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Header getHEADER() {
		return HEADER;
	}
	public void setHEADER(Header hEADER) {
		HEADER = hEADER;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Header getHeader() {
//		return header;
//	}
//	public void setHeader(Header header) {
//		this.header = header;
//	}
	public Journey getJourney() {
		return journey;
	}
	public void setJourney(Journey journey) {
		this.journey = journey;
	}
	public Traveller getTraveller() {
		return traveller;
	}
	public void setTraveller(Traveller traveller) {
		this.traveller = traveller;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	
	

}

package demo_bean_util.demo_request_ap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TravellerType", propOrder = {
    "travellerInfo",
    "identity",
    "boardingPass",
    "pnr",
    "itinerary",
    "overrideCode"
})
public class TravellerTypeNewV2 {

	 @XmlElement(name = "TravellerInfo", required = true)
	    protected TravellerInfoType travellerInfo;
	    @XmlElement(name = "Identity", required = true)
	    protected IdentityType identity;
	    @XmlElement(name = "BoardingPass")
	    protected BoardingPassType boardingPass;
	    @XmlElement(name = "PNR")
	    protected PNRType pnr;
	    @XmlElement(name = "Itinerary")
	    protected ItineraryType itinerary;
	    @XmlElement(name = "OverrideCode")
	    protected List<OverrideType> overrideCode;
		public TravellerInfoType getTravellerInfo() {
			return travellerInfo;
		}
		public void setTravellerInfo(TravellerInfoType travellerInfo) {
			this.travellerInfo = travellerInfo;
		}
		public IdentityType getIdentity() {
			return identity;
		}
		public void setIdentity(IdentityType identity) {
			this.identity = identity;
		}
		public BoardingPassType getBoardingPass() {
			return boardingPass;
		}
		public void setBoardingPass(BoardingPassType boardingPass) {
			this.boardingPass = boardingPass;
		}
		public PNRType getPnr() {
			return pnr;
		}
		public void setPnr(PNRType pnr) {
			this.pnr = pnr;
		}
		public ItineraryType getItinerary() {
			return itinerary;
		}
		public void setItinerary(ItineraryType itinerary) {
			this.itinerary = itinerary;
		}
		public List<OverrideType> getOverrideCode() {
			 if (overrideCode == null) {
		            overrideCode = new ArrayList<OverrideType>();
		        }
		        return this.overrideCode;
		}
		public void setOverrideCode(List<OverrideType> overrideCode) {
			this.overrideCode = overrideCode;
		}
	    
	    
	
}

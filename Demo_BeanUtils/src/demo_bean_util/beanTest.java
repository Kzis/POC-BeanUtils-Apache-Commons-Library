package demo_bean_util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.PropertyConfigurator;

import util.CPUtil;
import util.calendar.CalendarUtil;
import util.xml.XMLUtil;
import demo_bean_util.demo_request_ap.AgencyType;
import demo_bean_util.demo_request_ap.BoardingPassType;
import demo_bean_util.demo_request_ap.CarrierType;
import demo_bean_util.demo_request_ap.CheckInTravellerRequestType;
import demo_bean_util.demo_request_ap.GenderType;
import demo_bean_util.demo_request_ap.HeaderType;
import demo_bean_util.demo_request_ap.HeaderTypeNew;
import demo_bean_util.demo_request_ap.HeaderTypeNewV2;
import demo_bean_util.demo_request_ap.IdentityType;
import demo_bean_util.demo_request_ap.JourneyInfoType;
import demo_bean_util.demo_request_ap.JourneyScheduleType;
import demo_bean_util.demo_request_ap.JourneyType;
import demo_bean_util.demo_request_ap.JourneyTypeNew;
import demo_bean_util.demo_request_ap.JourneyTypeNewV2;
import demo_bean_util.demo_request_ap.OverrideCodeType;
import demo_bean_util.demo_request_ap.OverrideType;
import demo_bean_util.demo_request_ap.Request;
import demo_bean_util.demo_request_ap.RequestNew;
import demo_bean_util.demo_request_ap.TravelDocumentType;
import demo_bean_util.demo_request_ap.TravellerInfoType;
import demo_bean_util.demo_request_ap.TravellerType;
import demo_bean_util.demo_request_ap.TravellerTypeNew;
import demo_bean_util.demo_request_ap.TravellerTypeNewV2;
import demo_bean_util.demo_request_ap.TravellerTypeType;
import demo_bean_util.domain.GlobalVariable;

public class beanTest {
	
	static final String DB_DRIVER  = "oracle.jdbc.driver.OracleDriver";  
	static final String DB_CONNECTION  = "jdbc:oracle:thin:@10.100.70.87:1521:ORCL";
	static final String DB_USER  = "cp_cambodia";
	static final String DB_PASSWORD  = "cp_Cambodia2016";
	
	public static void main(String[]args) throws Exception{
		
		try {
			
			//DATA FOR TEST NEW REQUEST[COPY DATA FOR GEN XML IN CP]
			List<RequestNew> listRequestNews = searchListRequest();
			
			System.out.println("listRequestNews size : " + listRequestNews.size());
			
			for(RequestNew requestNew : listRequestNews){
				
				testMapAPRequest(requestNew);
				
			}
			
			
			//DATA FOR TEST NEW REQUEST VERSION2[ERROR : TYPE MISMATCH]
//			List<Request> listRequest = searchListRequestV2();
//			
//			System.out.println("listRequestNews size : " + listRequest.size());
//			
//			for(Request request : listRequest){
//				
//				CheckInTravellerRequestType checkInTravellerRequestType = new CheckInTravellerRequestType();
//				
//				System.out.println("##### [Before BeanUtils.copyProperties] #####");
//				System.out.println("checkInTravellerRequestType : " + checkInTravellerRequestType);
//				
//				//copy properties from (target, source)
//				BeanUtils.copyProperty(checkInTravellerRequestType, "header" , request.getHeader());
//				BeanUtils.copyProperty(checkInTravellerRequestType, "journey" , request.getJourney());
//				BeanUtils.copyProperty(checkInTravellerRequestType, "traveller" , request.getTraveller());
//				
//				//Gen String XML
//				String xmlString = XMLUtil.objectToXMLString(GlobalVariable.JAXB_ENCODING, checkInTravellerRequestType);
//				
//				System.out.println(xmlString);
//				
//			}
			
			//Class Test Case : DemoCopy.java
			DemoCopy.basicObjectCopyTest_Object();
				
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
		}
		
	}
	

	private static void testMapAPRequest(RequestNew requestNew) throws Exception {

		// Process . . .
		
		// Send To AP
		processWithAP(requestNew);
		
		// Update
		
	}

	private static void processWithAP(RequestNew requestNew) throws Exception {
			
		CheckInTravellerRequestType checkInTravellerRequestType = new CheckInTravellerRequestType();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");
		System.out.println("checkInTravellerRequestType : " + checkInTravellerRequestType);
		
		//copy properties from (target, source)
		BeanUtils.copyProperties(checkInTravellerRequestType, requestNew);
		
		//Gen String XML
		String xmlString = XMLUtil.objectToXMLString(GlobalVariable.JAXB_ENCODING, checkInTravellerRequestType);
		
		System.out.println(xmlString);
		
		System.out.println();
		System.out.println();
	
	}
	
	private static List<Request> searchListRequestV2() throws Exception {

		Connection dbConnection = null;
		Statement statement = null;
		
		List<Request> listData = new ArrayList<Request>();
		Request request = new Request();
		
		HeaderTypeNewV2 header;
		JourneyTypeNewV2 journey;
		TravellerTypeNewV2 traveller;
		
		String selectTableSQL = DemoCopy.getSQLSearchListData();
		
		dbConnection = DemoCopy.getDBConnection();
		statement = dbConnection.createStatement();
		
		try {
			dbConnection = DemoCopy.getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rst = statement.executeQuery(selectTableSQL);	
			
			while (rst.next()) {
				
				header = new HeaderTypeNewV2();
				
				header.setUserId(rst.getString("USER_ID"));
				header.setTransactionId(getTransactionIdSeq());
			
				AgencyType agency = new AgencyType();
				agency.setCarrier("99");
				agency.setPseudoCity("BKK");
				agency.setCountry("TH");
				agency.setIATACode("00000000");
				header.setAgency(agency);
				
				journey = new JourneyTypeNewV2();
				
				switch (rst.getString("GG_FLIGHT")) {
				case "Y":
					journey.setScheduleType(JourneyScheduleType.SCHEDULED);
					break;
				case "N":
					journey.setScheduleType(JourneyScheduleType.UNSCHEDULED);
					break;
				case "":
					journey.setScheduleType(JourneyScheduleType.SCHEDULED);
					break;
				default:
					break;
				}
				
				switch (rst.getString("CARRIER_TYPE")) {
				case "A":
					journey.setCarrierType(CarrierType.AIRLINE);
					break;
				case "G":
					journey.setCarrierType(CarrierType.GENERAL_AVIATION);
					break;
				default:
					break;
				}
				
				journey.setCarrierCode(rst.getString("CARRIER_CODE"));
				journey.setServiceNumber(rst.getString("SERVICE_NUMBER"));
				journey.setDeparturePort(rst.getString("DEP_PORT"));
				journey.setArrivalPort(rst.getString("ARR_PORT"));
				journey.setDepartureDate(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("DEP_DATE_LOCAL")+rst.getString("DEP_TIME_LOCAL"),
						"YYYYMMDDHHmmss", Locale.ENGLISH, 
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				JourneyInfoType journeyInfo = new JourneyInfoType();
				
				switch (rst.getString("CARRIER_TYPE")) {
				case "A":
					journeyInfo.setCarrierType(CarrierType.AIRLINE);
					break;
				case "G":
					journeyInfo.setCarrierType(CarrierType.GENERAL_AVIATION);
					break;
				default:
					break;
				}
				
				switch (rst.getString("GG_FLIGHT")) {
				case "Y":
					journeyInfo.setScheduleType(JourneyScheduleType.SCHEDULED);
					break;
				case "N":
					journeyInfo.setScheduleType(JourneyScheduleType.UNSCHEDULED);
					break;
				case "":
					journeyInfo.setScheduleType(JourneyScheduleType.SCHEDULED);
					break;
				default:
					break;
				}
				
				journeyInfo.setServiceNumber(rst.getString("SERVICE_NUMBER"));
				journeyInfo.setOriginPort(rst.getString("DEP_PORT_JI"));
				journeyInfo.setOriginCountry(rst.getString("ORIGIN"));		
				journeyInfo.setDestinationPort(rst.getString("ARR_PORT_JI"));
				
				journeyInfo.setDepartureDate(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("DEP_DATE_LOCAL_JI")+rst.getString("DEP_TIME_LOCAL_JI"),
						"YYYY-MM-DDHH:mm:ss", Locale.ENGLISH,
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				journeyInfo.setDepartureTime(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("DEP_DATE_LOCAL_JI")+rst.getString("DEP_TIME_LOCAL_JI"),
						"YYYY-MM-DDHH:mm:ss", Locale.ENGLISH,
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				journeyInfo.setArrivalDate(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("ARR_DATE_LOCAL_JI") + rst.getString("ARR_TIME_LOCAL_JI"),
						"YYYY-MM-DDHH:mm:ss", Locale.ENGLISH,
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				journeyInfo.setArrivalTime(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("ARR_DATE_LOCAL_JI") + rst.getString("ARR_TIME_LOCAL_JI"),
						"YYYY-MM-DDHH:mm:ss", Locale.ENGLISH,
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				journey.getJourneyInfo().add(journeyInfo);
				
				traveller = new TravellerTypeNewV2();
				
				TravellerInfoType travellerInfo = new TravellerInfoType();
				travellerInfo.setSequenceNumber("001");
				travellerInfo.setExpectedId(header.getTransactionId());
				
				switch (rst.getString("TRAVELLER_TYPE")) {
				case "P":
					travellerInfo.setType(TravellerTypeType.PASSENGER);
					break;
				case "C":
					travellerInfo.setType(TravellerTypeType.OPERATING_CREW);
					break;
				case "X":
					travellerInfo.setType(TravellerTypeType.POSITIONING_CREW);
					break;		
				default:
					break;
				}	
				
				IdentityType identity = new IdentityType();
				identity.setFamilyName(rst.getString("FAMILY_NAME"));
				identity.setGivenNames(rst.getString("GIVEN_NAMES"));
			
				identity.setBirthDate(rst.getString("DATE_OF_BIRTH"));
				
				switch (rst.getString("GENDER")) {
				case "M":
					identity.setGender(GenderType.MALE);
					break;
				case "F":
					identity.setGender(GenderType.FEMALE);
					break;
				case "X":
					identity.setGender(GenderType.UNSPECIFIED);
					break;
				default:
					identity.setGender(GenderType.UNKNOWN);
					break;
				}
				
				identity.setNationality(rst.getString("NATIONALITY"));
				
				TravelDocumentType travelDocument = new TravelDocumentType();
				travelDocument.setNumber(rst.getString("DOCUMENT_NUMBER"));
				travelDocument.setType(rst.getString("DOCUMENT_TYPE"));
				travelDocument.setIssuingState(rst.getString("ISSUING_STATE"));
				travelDocument.setExpiryDate(
												CPUtil.convertCalendarToXMLGregorianCalendar
												(
												  CalendarUtil.convertDateString
												   ( 	CPUtil.convertDateToYYYYMMDD(rst.getString("DOCUMENT_EXPIREY_DATE"), GlobalVariable.DATE_FORMAT),
														GlobalVariable.DATE_TIME_FORMAT_YYYYMMDD, Locale.ENGLISH,
														GlobalVariable.DATE_TIME_FORMAT_YYYY_MM_DD, Locale.ENGLISH
													)
												)
											);
				
				identity.getTravelDocument().add(travelDocument);
				
				OverrideType overrideType = null;
				
				if(rst.getString("OVERRIDE_CODE") != null){
					
					overrideType = new OverrideType();
					
					overrideType.setCountry("TH");
					
					switch (rst.getString("OVERRIDE_CODE")) {
					case "A":
						overrideType.setType(OverrideCodeType.AIRLINE);
						break;
					case "G":
						overrideType.setType(OverrideCodeType.GOVERNMENT);
						break;
					default:
						break;
					}
				}
				
				traveller.setTravellerInfo(travellerInfo);
				traveller.setIdentity(identity);
				traveller.getOverrideCode().add(overrideType);
				
				request.setHeader(header);
				request.setJourney(journey);
				request.getTraveller().add(traveller);
				
				listData.add(request);
			
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		
		return listData;
		
	}

	private static List<RequestNew> searchListRequest() throws Exception {
		
		Connection dbConnection = null;
		Statement statement = null;
		
		List<RequestNew> listData = new ArrayList<RequestNew>();
		RequestNew requestNew = new RequestNew();
		
		HeaderTypeNew header;
		JourneyTypeNew journey;
		TravellerTypeNew traveller;
		
//		HeaderType header;
//		JourneyType journey;
//		TravellerType traveller;

		String selectTableSQL = DemoCopy.getSQLSearchListData();
		
		dbConnection = DemoCopy.getDBConnection();
		statement = dbConnection.createStatement();
				
		try {
			dbConnection = DemoCopy.getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rst = statement.executeQuery(selectTableSQL);	
			
			while (rst.next()) {
				
				header = new HeaderTypeNew();
//				header = new HeaderType();
				
				header.setUserId(rst.getString("USER_ID"));
				header.setTransactionId(getTransactionIdSeq());
//				header.setDateTime(CPUtil.convertCalendarToXMLGregorianCalendar("xxx"));
				
				AgencyType agency = new AgencyType();
				agency.setCarrier("99");
				agency.setPseudoCity("BKK");
				agency.setCountry("TH");
				agency.setIATACode("00000000");
				header.setAgency(agency);
				
				journey = new JourneyTypeNew();
//				journey = new JourneyType();
				
				switch (rst.getString("GG_FLIGHT")) {
				case "Y":
					journey.setScheduleType(JourneyScheduleType.SCHEDULED);
					break;
				case "N":
					journey.setScheduleType(JourneyScheduleType.UNSCHEDULED);
					break;
				case "":
					journey.setScheduleType(JourneyScheduleType.SCHEDULED);
					break;
				default:
					break;
				}
				
				switch (rst.getString("CARRIER_TYPE")) {
				case "A":
					journey.setCarrierType(CarrierType.AIRLINE);
					break;
				case "G":
					journey.setCarrierType(CarrierType.GENERAL_AVIATION);
					break;
				default:
					break;
				}
				
				journey.setCarrierCode(rst.getString("CARRIER_CODE"));
				journey.setServiceNumber(rst.getString("SERVICE_NUMBER"));
				journey.setDeparturePort(rst.getString("DEP_PORT"));
				journey.setArrivalPort(rst.getString("ARR_PORT"));
				journey.setDepartureDate(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("DEP_DATE_LOCAL")+rst.getString("DEP_TIME_LOCAL"),
						"YYYYMMDDHHmmss", Locale.ENGLISH, 
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				JourneyInfoType journeyInfo = new JourneyInfoType();
				
				switch (rst.getString("CARRIER_TYPE")) {
				case "A":
					journeyInfo.setCarrierType(CarrierType.AIRLINE);
					break;
				case "G":
					journeyInfo.setCarrierType(CarrierType.GENERAL_AVIATION);
					break;
				default:
					break;
				}
				
				switch (rst.getString("GG_FLIGHT")) {
				case "Y":
					journeyInfo.setScheduleType(JourneyScheduleType.SCHEDULED);
					break;
				case "N":
					journeyInfo.setScheduleType(JourneyScheduleType.UNSCHEDULED);
					break;
				case "":
					journeyInfo.setScheduleType(JourneyScheduleType.SCHEDULED);
					break;
				default:
					break;
				}
				
				journeyInfo.setServiceNumber(rst.getString("SERVICE_NUMBER"));
				journeyInfo.setOriginPort(rst.getString("DEP_PORT_JI"));
				journeyInfo.setOriginCountry(rst.getString("ORIGIN"));		
				journeyInfo.setDestinationPort(rst.getString("ARR_PORT_JI"));
				
				journeyInfo.setDepartureDate(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("DEP_DATE_LOCAL_JI")+rst.getString("DEP_TIME_LOCAL_JI"),
						"YYYY-MM-DDHH:mm:ss", Locale.ENGLISH,
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				journeyInfo.setDepartureTime(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("DEP_DATE_LOCAL_JI")+rst.getString("DEP_TIME_LOCAL_JI"),
						"YYYY-MM-DDHH:mm:ss", Locale.ENGLISH,
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				journeyInfo.setArrivalDate(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("ARR_DATE_LOCAL_JI") + rst.getString("ARR_TIME_LOCAL_JI"),
						"YYYY-MM-DDHH:mm:ss", Locale.ENGLISH,
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				journeyInfo.setArrivalTime(CPUtil.convertCalendarToXMLGregorianCalendar(CalendarUtil.convertDateString(rst.getString("ARR_DATE_LOCAL_JI") + rst.getString("ARR_TIME_LOCAL_JI"),
						"YYYY-MM-DDHH:mm:ss", Locale.ENGLISH,
						GlobalVariable.DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR, Locale.ENGLISH)));
				
				journey.getJourneyInfo().add(journeyInfo);
				
				traveller = new TravellerTypeNew();
//				traveller = new TravellerType();
				
				TravellerInfoType travellerInfo = new TravellerInfoType();
				travellerInfo.setSequenceNumber("001");
				travellerInfo.setExpectedId(header.getTransactionId());
				
				switch (rst.getString("TRAVELLER_TYPE")) {
				case "P":
					travellerInfo.setType(TravellerTypeType.PASSENGER);
					break;
				case "C":
					travellerInfo.setType(TravellerTypeType.OPERATING_CREW);
					break;
				case "X":
					travellerInfo.setType(TravellerTypeType.POSITIONING_CREW);
					break;		
				default:
					break;
				}	
				
				IdentityType identity = new IdentityType();
				identity.setFamilyName(rst.getString("FAMILY_NAME"));
				identity.setGivenNames(rst.getString("GIVEN_NAMES"));
			
				identity.setBirthDate(rst.getString("DATE_OF_BIRTH"));
				
				switch (rst.getString("GENDER")) {
				case "M":
					identity.setGender(GenderType.MALE);
					break;
				case "F":
					identity.setGender(GenderType.FEMALE);
					break;
				case "X":
					identity.setGender(GenderType.UNSPECIFIED);
					break;
				default:
					identity.setGender(GenderType.UNKNOWN);
					break;
				}
				
				identity.setNationality(rst.getString("NATIONALITY"));
				
				TravelDocumentType travelDocument = new TravelDocumentType();
				travelDocument.setNumber(rst.getString("DOCUMENT_NUMBER"));
				travelDocument.setType(rst.getString("DOCUMENT_TYPE"));
				travelDocument.setIssuingState(rst.getString("ISSUING_STATE"));
				travelDocument.setExpiryDate(
												CPUtil.convertCalendarToXMLGregorianCalendar
												(
												  CalendarUtil.convertDateString
												   ( 	CPUtil.convertDateToYYYYMMDD(rst.getString("DOCUMENT_EXPIREY_DATE"), GlobalVariable.DATE_FORMAT),
														GlobalVariable.DATE_TIME_FORMAT_YYYYMMDD, Locale.ENGLISH,
														GlobalVariable.DATE_TIME_FORMAT_YYYY_MM_DD, Locale.ENGLISH
													)
												)
											);
				
				identity.getTravelDocument().add(travelDocument);
				
				OverrideType overrideType = null;
				
				if(rst.getString("OVERRIDE_CODE") != null){
					
					overrideType = new OverrideType();
					
					overrideType.setCountry("TH");
					
					switch (rst.getString("OVERRIDE_CODE")) {
					case "A":
						overrideType.setType(OverrideCodeType.AIRLINE);
						break;
					case "G":
						overrideType.setType(OverrideCodeType.GOVERNMENT);
						break;
					default:
						break;
					}
				}
				
				traveller.setTravellerInfo(travellerInfo);
				traveller.setIdentity(identity);
				traveller.getOverrideCode().add(overrideType);
				
				requestNew.setHeader(header);
				requestNew.setJourney(journey);
				requestNew.getTraveller().add(traveller);
				
				listData.add(requestNew);
				
				//ค่าที่ไม่ได้ส่งไป GEN_XML แต่ต้องเอาไปใช้ในส่วน Process อื่น ๆ
//				TransactionDetail data = new TransactionDetail();	
//				data.setTransDetId(rst.getString("TRANS_DET_ID"));
//			
//				data.setCountryOfBirth(rst.getString("COUNTRY_OF_BIRTH"));
//				data.setTravelType(rst.getString("TRAVEL_TYPE"));
//				data.setCheckInType(rst.getString("CHECK_IN_TYPE"));
//				data.setOverrideType(rst.getString("OVERRIDE_CODE"));
//				
//				//----- additional
//				
//				data.setMarDepDate(rst.getString("DEP_DATE_LOCAL"));
//				data.setDepTime(rst.getString("DEP_TIME_LOCAL"));
//
//				data.setMarArrDate(rst.getString("ARR_DATE_LOCAL"));
//				data.setArrTime(rst.getString("ARR_TIME_LOCAL"));
//				
//				data.setTbPort(rst.getString("TRANS_PORT"));
//				data.setTbDate(rst.getString("TRANS_DATE_LOCAL"));
//				data.setTbTime(rst.getString("TRANS_TIME_LOCAL"));
//				
//				//----- additional2
//				
//				data.setCancel(rst.getString("CANCEL_STATUS"));
//				
//				data.setMarScheduleType(rst.getString("GG_FLIGHT_JI"));
//				data.setMarCarrierType(rst.getString("CARRIER_TYPE_JI"));									
//				
//				data.setDestinationCountry(rst.getString("DESTINATION"));
//				
//				data.setUserName(rst.getString("USERNAME"));
//				
//				data.setServiceName(rst.getString("OPERATING_FLIGHT"));
//				
//				data.setDepDate(rst.getString("DEP_DATE_LOCAL_JI"));
//				data.setDepTime(rst.getString("DEP_TIME_LOCAL_JI"));
//				
//				data.setArrDate(rst.getString("ARR_DATE_LOCAL_JI"));
//				data.setArrTime(rst.getString("ARR_TIME_LOCAL_JI"));
//				
//				data.setOverrideCountry(rst.getString("OVERRIDE_COUNTRY"));
				
				
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		
		return listData;
	}

	private static String getTransactionIdSeq() throws Exception {
		
		Connection dbConnection = null;
		Statement statement = null;
		
		String sql = "SELECT GET_TRANSACTION_ID AS TRANSACTION_ID FROM dual";
		
		dbConnection = DemoCopy.getDBConnection();
		statement = dbConnection.createStatement();
		
		String transactionId = null;
		
		try {
			dbConnection = DemoCopy.getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(sql);
		
			// execute select SQL stetement
			ResultSet rst = statement.executeQuery(sql);	
			
			while (rst.next()) {
				
				transactionId = rst.getString("TRANSACTION_ID");
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		
		return transactionId;
	}

	private static void testMapRequest() throws Exception {
		
		// TEST TSET
		
		HeaderType headerType = new HeaderType();
		headerType.setUserId("999");
		
		JourneyType journeyType = new JourneyType();
		journeyType.setCarrierCode("XX1234");
		
		//--
		List<TravellerType> traveller = new ArrayList<TravellerType>();
		
		BoardingPassType boardingPassType = new BoardingPassType();
		boardingPassType.setCarrierCode("XX1234");
		
		TravellerType travellerType = new TravellerType();
		travellerType.setBoardingPass(boardingPassType);
		
		traveller.add(travellerType);
		
		//--
		journeyType = new JourneyType();
		journeyType.setCarrierCode("XX7777");
		
		boardingPassType = new BoardingPassType();
		boardingPassType.setCarrierCode("XX7777");
		
		travellerType = new TravellerType();
		travellerType.setBoardingPass(boardingPassType);
		
		traveller.add(travellerType);
		
		//Set Object �������
		RequestNew requestNew = new RequestNew();
//		requestNew.setHeader(headerType);
//		requestNew.setJourney(journeyType);
//		requestNew.setTraveller(traveller);
		
		CheckInTravellerRequestType checkInTravellerRequestType = new CheckInTravellerRequestType();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");
		
		System.out.println("### requestNew");
		System.out.println("Object[Header] : User Id : " + requestNew.getHeader().getUserId());
		System.out.println("Object[Journey] : Carrier Code : " + requestNew.getJourney().getCarrierCode());
		System.out.println("Object[List : TravellerType] : Size : " + requestNew.getTraveller().size());
		
		for(int i = 0; i <= requestNew.getTraveller().size()-1; i++){
			System.out.println("Round " + (i+1)+ " : BoardingPass(Carrier Code) : " + requestNew.getTraveller().get(i).getBoardingPass().getCarrierCode() );
		}
		
		System.out.println("### checkInTravellerRequestType");
		if(checkInTravellerRequestType.getHeader() != null){
			System.out.println("Object[Header] : User Id : " + checkInTravellerRequestType.getHeader().getUserId());
		}else{
			System.out.println("Object[Header] : User Id : " + checkInTravellerRequestType.getHeader());
		}
		
		if(checkInTravellerRequestType.getJourney() != null){
			System.out.println("Object[Journey] : Carrier Code : " + checkInTravellerRequestType.getJourney().getCarrierCode());
		}else{
			System.out.println("Object[Journey] : Carrier Code : " + checkInTravellerRequestType.getJourney());
		}
		
		if( checkInTravellerRequestType.getTraveller() != null){
			
			if(checkInTravellerRequestType.getTraveller().size() > 0){
				for(int i = 0; i <= checkInTravellerRequestType.getTraveller().size()-1; i++){
					System.out.println("Round " + (i+1)+ " : BoardingPass(Carrier Code) : " + checkInTravellerRequestType.getTraveller().get(i).getBoardingPass().getCarrierCode() );
				}
			}else{
				System.out.println("Object[List : TravellerType] : Size : " + checkInTravellerRequestType.getTraveller().size());
			}
			
		}else{
			System.out.println("Object[List : TravellerType] : " + checkInTravellerRequestType.getTraveller());
		}
		
		System.out.println();
		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");
		
		//copy properties from (target, source)
		BeanUtils.copyProperties(checkInTravellerRequestType, requestNew);
		
		System.out.println("### requestNew");
		System.out.println("Object[Header] : User Id : " + requestNew.getHeader().getUserId());
		System.out.println("Object[Journey] : Carrier Code : " + requestNew.getJourney().getCarrierCode());
		System.out.println("Object[List : TravellerType] : Size : " + requestNew.getTraveller().size());
		
		for(int i = 0; i <= requestNew.getTraveller().size()-1; i++){
			System.out.println("Round " + (i+1)+ " : BoardingPass(Carrier Code) : " + requestNew.getTraveller().get(i).getBoardingPass().getCarrierCode() );
		}
		
		System.out.println("### checkInTravellerRequestType");
		if(checkInTravellerRequestType.getHeader() != null){
			System.out.println("Object[Header] : User Id : " + checkInTravellerRequestType.getHeader().getUserId());
		}else{
			System.out.println("Object[Header] : User Id : " + checkInTravellerRequestType.getHeader());
		}
		
		if(checkInTravellerRequestType.getJourney() != null){
			System.out.println("Object[Journey] : Carrier Code : " + checkInTravellerRequestType.getJourney().getCarrierCode());
		}else{
			System.out.println("Object[Journey] : Carrier Code : " + checkInTravellerRequestType.getJourney());
		}
		
		if( checkInTravellerRequestType.getTraveller() != null){
			
			if(checkInTravellerRequestType.getTraveller().size() > 0){
				for(int i = 0; i <= checkInTravellerRequestType.getTraveller().size()-1; i++){
					System.out.println("Round " + (i+1)+ " : BoardingPass(Carrier Code) : " + checkInTravellerRequestType.getTraveller().get(i).getBoardingPass().getCarrierCode() );
				}
			}else{
				System.out.println("Object[List : TravellerType] : Size : " + checkInTravellerRequestType.getTraveller().size());
			}
			
		}else{
			System.out.println("Object[List : TravellerType] : " + checkInTravellerRequestType.getTraveller());
		}
	}

}

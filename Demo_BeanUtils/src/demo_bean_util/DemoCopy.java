package demo_bean_util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class DemoCopy {
	
	static final String DB_DRIVER  = "oracle.jdbc.driver.OracleDriver";  
	static final String DB_CONNECTION  = "jdbc:oracle:thin:@10.100.70.87:1521:ORCL";
	static final String DB_USER  = "cp_cambodia";
	static final String DB_PASSWORD  = "cp_Cambodia2016";
	
	/**
	 * 
	 * @return : connection
	 * @throws Exception
	 */
	public static Connection getDBConnection() throws Exception {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
	
	/**
	 * Get SQL List Transaction in CP
	 * @return String SQL
	 * @throws Exception
	 */
	public static String getSQLSearchListData() throws Exception {

		String sql = "SELECT "
		+ "BTD.TRANS_DET_ID,BTD.TRAVELLER_TYPE, BTD.DOCUMENT_TYPE, BTD.DOCUMENT_NUMBER, BTD.DOCUMENT_EXPIREY_DATE, BTD.NATIONALITY," 
		+ "BTD.ISSUING_STATE, BTD.FAMILY_NAME, BTD.GIVEN_NAMES, BTD.DATE_OF_BIRTH, BTD.GENDER, BTD.COUNTRY_OF_BIRTH, BTD.TRAVEL_TYPE," 
		+ "BTD.CHECK_IN_TYPE, BTD.OVERRIDE_CODE,"
		+ "BTD.SERVICE_NUMBER,BTD.DEP_PORT,BTD.DEP_DATE_LOCAL,BTD.DEP_TIME_LOCAL,BTD.ARR_PORT,BTD.ARR_DATE_LOCAL,BTD.ARR_TIME_LOCAL,"
		+ "BTD.TRANS_PORT,BTD.TRANS_DATE_LOCAL,BTD.TRANS_TIME_LOCAL,"
		+ "BTD.CANCEL_STATUS,BTD.GG_FLIGHT AS GG_FLIGHT,SOS.GG_FLIGHT AS GG_FLIGHT_JI,RCE.CARRIER_TYPE,RCE.CARRIER_TYPE AS CARRIER_TYPE_JI,"
		+ "GET_COUNTRY_CODE_AL2(BTD.DEP_PORT)  AS ORIGIN,GET_COUNTRY_CODE_AL2(BTD.ARR_PORT) AS DESTINATION,BTD.CREATE_USER AS USER_ID,"
		+ "RCU.USERNAME,NVL(SOS.OPERATING_FLIGHT_NUMBER,SOS.SERVICE_NUMBER) AS OPERATING_FLIGHT,SOS.DEP_PORT AS DEP_PORT_JI,SOS.ARR_PORT AS ARR_PORT_JI,"
		+ "TO_CHAR(TO_DATE(SOS.DEP_DATETIME_LOCAL,'YYYYMMDDHH24MISS'),'DD/MM/YYYY HH24:MI:SS') AS F_DEP_DATE_LOCAL_JI,"
		+ "TO_CHAR(TO_DATE(SUBSTR(SOS.DEP_DATETIME_LOCAL,1,8),'YYYYMMDD'),'YYYY-MM-DD') AS DEP_DATE_LOCAL_JI,"
		+ "TO_CHAR (TO_DATE(SOS.DEP_DATETIME_LOCAL,'YYYYMMDDHH24MISS'),'HH24:MI:SS') AS DEP_TIME_LOCAL_JI ,"
		+ "TO_CHAR(TO_DATE(SOS.ARR_DATETIME_LOCAL,'YYYYMMDDHH24MISS'),'DD/MM/YYYY HH24:MI:SS') AS F_ARR_DATE_LOCAL_JI,"
		+ "TO_CHAR(TO_DATE(SUBSTR(SOS.ARR_DATETIME_LOCAL,1,8),'YYYYMMDD'),'YYYY-MM-DD') AS ARR_DATE_LOCAL_JI,"
		+ "TO_CHAR (TO_DATE(SOS.ARR_DATETIME_LOCAL,'YYYYMMDDHH24MISS'),'HH24:MI:SS')AS ARR_TIME_LOCAL_JI,"
		+ "GET_OVERRIDE_COUNTRY(BTD.OVERRIDE_CODE) AS OVERRIDE_COUNTRY,"
		+ "RCE.CARRIER_CODE AS CARRIER_CODE"
		+ " FROM BTS_TRANS_DET BTD,REG_CARRIER_USER RCU,REG_CARRIER RCE,SAS_SCHEDULE_OF_SERVICE SOS"
		+ " WHERE 1=1"
//		+ " AND PROCESS_STATUS = 'P'"
//		+ " AND BTD.GEN_STATUS = 'W'"
		+ " AND BTD.CREATE_USER = RCU.CARRIER_USER_ID"
		+ " AND RCE.CARRIER_ID =  RCU.CARRIER_ID"
		+ " AND SOS.SCHEDULE_ID = BTD.SCHEDULE_ID"
		+ " AND TRANS_MAS_ID = '7563'"
		+ " ORDER BY BTD.CREATE_DATE";
		
		return sql;
	}
	
	/**
	 * Get List Transaction in CP
	 * @return List Transaction
	 * @throws Exception
	 */
	public static List<TransactionDetail> searchListData() throws Exception {
		
		Connection dbConnection = null;
		Statement statement = null;
		
		List<TransactionDetail> listData = new ArrayList<TransactionDetail>();

		String selectTableSQL = getSQLSearchListData();
		
		dbConnection = getDBConnection();
		statement = dbConnection.createStatement();
				
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rst = statement.executeQuery(selectTableSQL);	
			
			while (rst.next()) {
				
				TransactionDetail data = new TransactionDetail();	
				data.setTransDetId(rst.getString("TRANS_DET_ID"));
				data.setType(rst.getString("TRAVELLER_TYPE"));
				data.setDocumentType(rst.getString("DOCUMENT_TYPE"));
				data.setDocumentNumber(rst.getString("DOCUMENT_NUMBER"));
				data.setDocumentExpireDate(rst.getString("DOCUMENT_EXPIREY_DATE"));
				data.setNationality(rst.getString("NATIONALITY"));
				data.setIssuingState(rst.getString("ISSUING_STATE"));
				data.setFamilyName(rst.getString("FAMILY_NAME"));
				data.setGivenName(rst.getString("GIVEN_NAMES"));
				data.setDateOfBirth(rst.getString("DATE_OF_BIRTH"));
				data.setGender(rst.getString("GENDER"));
				data.setCountryOfBirth(rst.getString("COUNTRY_OF_BIRTH"));
				data.setTravelType(rst.getString("TRAVEL_TYPE"));
				data.setCheckInType(rst.getString("CHECK_IN_TYPE"));
				data.setOverrideType(rst.getString("OVERRIDE_CODE"));
				
				//----- additional
				
				data.setMarServiceName(rst.getString("SERVICE_NUMBER"));
				
				data.setMarDepPort(rst.getString("DEP_PORT"));
				data.setMarDepDate(rst.getString("DEP_DATE_LOCAL"));
				data.setDepTime(rst.getString("DEP_TIME_LOCAL"));
				
				data.setMarArrPort(rst.getString("ARR_PORT"));
				data.setMarArrDate(rst.getString("ARR_DATE_LOCAL"));
				data.setArrTime(rst.getString("ARR_TIME_LOCAL"));
				
				data.setTbPort(rst.getString("TRANS_PORT"));
				data.setTbDate(rst.getString("TRANS_DATE_LOCAL"));
				data.setTbTime(rst.getString("TRANS_TIME_LOCAL"));
				
				//----- additional2
				
				data.setCancel(rst.getString("CANCEL_STATUS"));
				data.setGgFlight(rst.getString("GG_FLIGHT"));
				data.setMarScheduleType(rst.getString("GG_FLIGHT_JI"));
				data.setCarrierType(rst.getString("CARRIER_TYPE"));
				data.setMarCarrierType(rst.getString("CARRIER_TYPE_JI"));									
				data.setOriginCountry(rst.getString("ORIGIN"));
				data.setDestinationCountry(rst.getString("DESTINATION"));
				
				data.setUserId(rst.getString("USER_ID"));
				data.setUserName(rst.getString("USERNAME"));
				
				data.setServiceName(rst.getString("OPERATING_FLIGHT"));
				data.setOriginPort(rst.getString("DEP_PORT_JI"));
				data.setDestinationPort(rst.getString("ARR_PORT_JI"));
				
				data.setDepDate(rst.getString("DEP_DATE_LOCAL_JI"));
				data.setDepTime(rst.getString("DEP_TIME_LOCAL_JI"));
				
				data.setArrDate(rst.getString("ARR_DATE_LOCAL_JI"));
				data.setArrTime(rst.getString("ARR_TIME_LOCAL_JI"));
				
				data.setOverrideCountry(rst.getString("OVERRIDE_COUNTRY"));
				
				//----- additional3
				
				data.setCarrierCode(rst.getString("CARRIER_CODE"));
				
				listData.add(data);
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
	
	/**
	 * Copy Object A To Object B (Primitive Type) : Property type and name same as.
	 * @throws Exception
	 */
	public static void basicObjectCopyTest() throws Exception {
		
		//Bean Original
		Person person = new Person();
		person.setAge(14);
		person.setName("eiei");
		person.setXxx("This is XXX");
		
		//Bean Destination
		OtherPerson othePerson = new OtherPerson();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");

		System.out.println("### Person");
		System.out.println(person.getAge());
		System.out.println(person.getName());
		System.out.println(person.getXxx());

		System.out.println("### othePerson");
		System.out.println(othePerson.getAge());
//		System.out.println(othePerson.getName());
		System.out.println(othePerson.getYyy());
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(othePerson, person);

		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");

		System.out.println("### Person");
		System.out.println(person.getAge());
		System.out.println(person.getName());
		System.out.println(person.getXxx());

		System.out.println("### othePerson");
		System.out.println(othePerson.getAge());
//		System.out.println(othePerson.getName());
		System.out.println(othePerson.getYyy());

		
	}
	
	/**
	 * Copy Object A To Object B (Primitive Type) : Property type and name same as.
	 * Case Sensitive
	 * @throws Exception
	 */
	public static void basicObjectCopyTestCaseSensitive() throws Exception {
		
		//Bean Original
		Person person = new Person();
		person.setXxx("This is xxx");
		
		//Bean Destination
		OtherPerson othePerson = new OtherPerson();
				
		System.out.println("##### [Before BeanUtils.copyProperties] #####");
		
		System.out.println("### Person");
		System.out.println(person.getXxx());

		System.out.println("### othePerson");
		System.out.println(othePerson.getXXX());
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(othePerson, person);
		
		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");
		System.out.println("### Person");
		System.out.println(person.getXxx());

		System.out.println("### othePerson");
		System.out.println(othePerson.getXXX());
	
	}
	
	/**
	 * Copy Object A To Object B (Object Type) : Property type and name same as.
	 * @throws Exception
	 */
	public static void basicObjectCopyTest_Object() throws Exception{
		Header header = new Header();
		header.setHeaderId("H-1334");
		header.setHeaderType("HType-113");
		
		JourneyInfo journeyInfo = new JourneyInfo();
		journeyInfo.setJourneyInfoId("J-Info-1334");
		journeyInfo.setJourneyInfoName("JType-Info-2233");
		
		Journey journey = new Journey();
		journey.setJourneyId("J-1334");
		journey.setJourneyType("JType-544");
		journey.setJourneyInfo(journeyInfo);
		
		Traveller traveller = new Traveller();
		traveller.setTravellerId("T-1334");
		traveller.setTravellerType("TType-225");
		
		BeanOriginal beanOriginal = new BeanOriginal();
		beanOriginal.setId("ID-1234");
		beanOriginal.setName("BEAN NAME");
		beanOriginal.setOriginal("ORIGINAL");
		beanOriginal.setHeader(header);
		beanOriginal.setJourney(journey);
		beanOriginal.setTraveller(traveller);
		
		BeanRequest beanRequest = new BeanRequest();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");
		
		System.out.println("### BeanOriginal");
		System.out.println("Original Id : " + beanOriginal.getId());
		System.out.println("Original Name : " +beanOriginal.getName());
		System.out.println("Original : " +beanOriginal.getOriginal());
		System.out.println("-----");
		System.out.println("Header Id : " +beanOriginal.getHeader().getHeaderId());
		System.out.println("Header Name : " +beanOriginal.getHeader().getHeaderType());
		System.out.println("-----");
		System.out.println("Journey Id : " +beanOriginal.getJourney().getJourneyId());
		System.out.println("Journey Name : " +beanOriginal.getJourney().getJourneyType());
		System.out.println("-----");
		System.out.println("Journey Info Id : " +beanOriginal.getJourney().getJourneyInfo().getJourneyInfoId());
		System.out.println("Journey Info Name : " +beanOriginal.getJourney().getJourneyInfo().getJourneyInfoName());
		System.out.println("-----");
		System.out.println("Traveller Id : " +beanOriginal.getTraveller().getTravellerId());
		System.out.println("Traveller Name : " +beanOriginal.getTraveller().getTravellerType());
		
		System.out.println();
		
		System.out.println("### BeanRequest");
		System.out.println(beanRequest.getId());
		System.out.println(beanRequest.getName());
		System.out.println(beanRequest.getRequest());
		
		if(beanRequest.getHeader() != null){
			System.out.println(beanRequest.getHeader().getHeaderId());
			System.out.println(beanRequest.getHeader().getHeaderType());
		}else{
			System.out.println(beanRequest.getHeader());
			System.out.println(beanRequest.getHeader());
		}
		
		if(beanRequest.getJourney() != null){
			System.out.println(beanRequest.getJourney().getJourneyId());
			System.out.println(beanRequest.getJourney().getJourneyType());
			
			if(beanRequest.getJourney().getJourneyInfo() != null){
				System.out.println(beanRequest.getJourney().getJourneyInfo().getJourneyInfoId());
				System.out.println(beanRequest.getJourney().getJourneyInfo().getJourneyInfoName());
			}else{
				System.out.println(beanRequest.getJourney().getJourneyInfo());
				System.out.println(beanRequest.getJourney().getJourneyInfo());
			}
			
		}else{
			System.out.println(beanRequest.getJourney());
			System.out.println(beanRequest.getJourney());
			System.out.println(beanRequest.getJourney());
			System.out.println(beanRequest.getJourney());
		}
		
		if(beanRequest.getTraveller() != null){
			System.out.println(beanRequest.getTraveller().getTravellerId());
			System.out.println(beanRequest.getTraveller().getTravellerType());
		}else{
			System.out.println(beanRequest.getTraveller());
			System.out.println(beanRequest.getTraveller());
		}
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(beanRequest, beanOriginal);

		System.out.println("\n##### [After BeanUtils.copyProperties] #####");
		
		System.out.println("### BeanOriginal");
		System.out.println("Original Id : " + beanOriginal.getId());
		System.out.println("Original Name : " +beanOriginal.getName());
		System.out.println("Original : " +beanOriginal.getOriginal());
		System.out.println("-----");
		System.out.println("Header Id : " +beanOriginal.getHeader().getHeaderId());
		System.out.println("Header Name : " +beanOriginal.getHeader().getHeaderType());
		System.out.println("-----");
		System.out.println("Journey Id : " +beanOriginal.getJourney().getJourneyId());
		System.out.println("Journey Name : " +beanOriginal.getJourney().getJourneyType());
		System.out.println("-----");
		System.out.println("Journey Info Id : " +beanOriginal.getJourney().getJourneyInfo().getJourneyInfoId());
		System.out.println("Journey Info Name : " +beanOriginal.getJourney().getJourneyInfo().getJourneyInfoName());
		System.out.println("-----");
		System.out.println("Traveller Id : " +beanOriginal.getTraveller().getTravellerId());
		System.out.println("Traveller Name : " +beanOriginal.getTraveller().getTravellerType());
		
		System.out.println();
		
		System.out.println("### BeanRequest");
		System.out.println(beanRequest.getId());
		System.out.println(beanRequest.getName());
		System.out.println(beanRequest.getRequest());
		
		if(beanRequest.getHeader() != null){
			System.out.println(beanRequest.getHeader().getHeaderId());
			System.out.println(beanRequest.getHeader().getHeaderType());
		}else{
			System.out.println(beanRequest.getHeader());
			System.out.println(beanRequest.getHeader());
		}
		
		if(beanRequest.getJourney() != null){
			System.out.println(beanRequest.getJourney().getJourneyId());
			System.out.println(beanRequest.getJourney().getJourneyType());
			
			if(beanRequest.getJourney().getJourneyInfo() != null){
				System.out.println(beanRequest.getJourney().getJourneyInfo().getJourneyInfoId());
				System.out.println(beanRequest.getJourney().getJourneyInfo().getJourneyInfoName());
			}else{
				System.out.println(beanRequest.getJourney().getJourneyInfo());
				System.out.println(beanRequest.getJourney().getJourneyInfo());
			}
			
		}else{
			System.out.println(beanRequest.getJourney());
			System.out.println(beanRequest.getJourney());
			System.out.println(beanRequest.getJourney());
			System.out.println(beanRequest.getJourney());
		}
		
		if(beanRequest.getTraveller() != null){
			System.out.println(beanRequest.getTraveller().getTravellerId());
			System.out.println(beanRequest.getTraveller().getTravellerType());
		}else{
			System.out.println(beanRequest.getTraveller());
			System.out.println(beanRequest.getTraveller());
		}
	}
	
	/**
	 * Copy Object A To Object B (Object Type) : Property type and name same as.
	 * Case Sensitive
	 * @throws Exception
	 */
	public static void basicObjectCopyTest_ObjectCaseSensitive() throws Exception{
		
		Header header = new Header();
		header.setHeaderId("H-1334");
		header.setHeaderType("HType-113");
		
		BeanOriginal beanOriginal = new BeanOriginal();
		beanOriginal.setHeader(header);
		
		BeanRequest beanRequest = new BeanRequest();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");
		
		System.out.println("### BeanOriginal");
		System.out.println("Header Id : " +beanOriginal.getHeader().getHeaderId());
		System.out.println("Header Name : " +beanOriginal.getHeader().getHeaderType());
		System.out.println("-----");
		System.out.println("### BeanRequest");
		System.out.println("### Header");
		System.out.println("Header Id : " +beanRequest.getHeader());
		System.out.println("Header Name : " +beanRequest.getHeader());
		System.out.println("-----");
		System.out.println("### HEADER");
		System.out.println("Header Id : " +beanRequest.getHEADER());
		System.out.println("Header Name : " +beanRequest.getHEADER());
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(beanRequest, beanOriginal);

		System.out.println("\n##### [After BeanUtils.copyProperties] #####");
		
		System.out.println("### BeanOriginal");
		System.out.println("Header Id : " +beanOriginal.getHeader().getHeaderId());
		System.out.println("Header Name : " +beanOriginal.getHeader().getHeaderType());
		System.out.println("-----");
		System.out.println("### BeanRequest");
		System.out.println("### Header");
		System.out.println("Header Id : " +beanRequest.getHeader().getHeaderId());
		System.out.println("Header Name : " +beanRequest.getHeader().getHeaderType());
		System.out.println("-----");
		System.out.println("### HEADER");
		System.out.println("Header Id : " +beanRequest.getHEADER().getHeaderId());
		System.out.println("Header Name : " +beanRequest.getHEADER().getHeaderType());
		
		
	}
	
	
	/**
	 * Type 2 ######
	 * Copy Property A To Property B In Bean B (Primitive Type) : Property type same as but name mismatch
	 * @throws Exception
	 */
	public static void basicObjectCopyTestType2() throws Exception {
		
		//Bean Original
		Person person = new Person();
		person.setAge(14);
		person.setName("eiei");
		person.setXxx("This is XXX");
				
		//Bean Destination
		OtherPerson othePerson = new OtherPerson();
				
		System.out.println("##### [Before BeanUtils.copyProperties] #####");

		System.out.println("### Person");
		System.out.println(person.getAge());
		System.out.println(person.getName());
		System.out.println(person.getXxx());

		System.out.println("### othePerson");
		System.out.println(othePerson.getAge());
//		System.out.println(othePerson.getName());
		System.out.println(othePerson.getYyy());
				
		//Copy properties from (target, source)
		BeanUtils.copyProperties(othePerson, person);
		
		//Copy properties name not same
		BeanUtils.copyProperty(othePerson,"yyy", person.getXxx());

		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");

		System.out.println("### Person");
		System.out.println(person.getAge());
		System.out.println(person.getName());
		System.out.println(person.getXxx());

		System.out.println("### othePerson");
		System.out.println(othePerson.getAge());
//		System.out.println(othePerson.getName());
		System.out.println(othePerson.getYyy());
				
	}
	
	/**
	 * Type 2 ######
	 * Copy Property A To Property B In Bean B (Object) : Property type same as but name mismatch
	 * @throws Exception
	 */
	public static void basicObjectCopyTestType2_Object() throws Exception {
		
		//Bean Original
		Header header = new Header();
		header.setHeaderId("H-1334");
		header.setHeaderType("HType-113");
			
		BeanOriginal beanOriginal = new BeanOriginal();
		beanOriginal.setId("ID-1234");
		beanOriginal.setName("BEAN NAME");
		beanOriginal.setOriginal("ORIGINAL");
		beanOriginal.setHeaderNotSame(header);

		//Bean Destination
		BeanRequest beanRequest = new BeanRequest();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");
		
		System.out.println("### BeanOriginal");
		System.out.println("Original Id : " + beanOriginal.getId());
		System.out.println("Original Name : " +beanOriginal.getName());
		System.out.println("Original : " +beanOriginal.getOriginal());
		System.out.println("-----");
		System.out.println("Header Id : " +beanOriginal.getHeaderNotSame().getHeaderId());
		System.out.println("Header Name : " +beanOriginal.getHeaderNotSame().getHeaderType());
		
		System.out.println();
		
		System.out.println("### BeanRequest");
		System.out.println(beanRequest.getId());
		System.out.println(beanRequest.getName());
		System.out.println(beanRequest.getRequest());
		
		if(beanRequest.getHeader() != null){
			System.out.println(beanRequest.getHeader().getHeaderId());
			System.out.println(beanRequest.getHeader().getHeaderType());
		}else{
			System.out.println(beanRequest.getHeader());
			System.out.println(beanRequest.getHeader());
		}
				
		//Copy properties from (target, source)
//		BeanUtils.copyProperties(beanRequest, beanOriginal);
		
		//Copy properties name not same (Primitive)
//		BeanUtils.copyProperty(beanRequest,"request", beanOriginal.getOriginal());
		
		//Copy properties name not same (Object)
		BeanUtils.copyProperty(beanRequest,"header", beanOriginal.getHeaderNotSame());

		System.out.println("\n##### [After BeanUtils.copyProperties] #####");
		
		System.out.println("### BeanOriginal");
		System.out.println("Original Id : " + beanOriginal.getId());
		System.out.println("Original Name : " +beanOriginal.getName());
		System.out.println("Original : " +beanOriginal.getOriginal());
		System.out.println("-----");
		System.out.println("Header Id : " +beanOriginal.getHeaderNotSame().getHeaderId());
		System.out.println("Header Name : " +beanOriginal.getHeaderNotSame().getHeaderType());

		
		System.out.println();
		
		System.out.println("### BeanRequest");
		System.out.println(beanRequest.getId());
		System.out.println(beanRequest.getName());
		System.out.println(beanRequest.getRequest());
		
		if(beanRequest.getHeader() != null){
			System.out.println(beanRequest.getHeader().getHeaderId());
			System.out.println(beanRequest.getHeader().getHeaderType());
		}else{
			System.out.println(beanRequest.getHeader());
			System.out.println(beanRequest.getHeader());
		}

	}
	
	/**
	 * Copy Object B To Object A  : Property type and name same as.
	 * Object A : 4 Property but have 2 value (A1 , A2) , 2 null (B1 , B2)
	 * Object B : 4 Property but have 2 null (A1 , A2) , 2 value (B1 , B2)
	 * Result : Object A : A1 = null , A2 = null , B1 = "xxx" , B2 = "xxx"
	 * @throws Exception
	 */
	public static void copyCriteriaToCriteria() throws Exception {
		
		Criteria1 criteria1 = new Criteria1();
		criteria1.setA1(" A1 have val.");
		criteria1.setA2(" A2 have val.");
		
		Criteria2 criteria2 = new Criteria2();
		criteria2.setB1(" B1 have val.");
		criteria2.setB2(" B2 have val.");
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");
		System.out.println("### Criteria 1");
		System.out.println("A1 : " + criteria1.getA1());
		System.out.println("A2 : " + criteria1.getA2());
		System.out.println("B1 : " + criteria1.getB1());
		System.out.println("B2 : " + criteria1.getB2());

		System.out.println("### Criteria 2");
		System.out.println("A1 : " + criteria2.getA1());
		System.out.println("A2 : " + criteria2.getA2());
		System.out.println("B1 : " + criteria2.getB1());
		System.out.println("B2 : " + criteria2.getB2());
		
		System.out.println();
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(criteria1, criteria2);
		
		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");
		System.out.println("### Criteria 1");
		System.out.println("A1 : " + criteria1.getA1());
		System.out.println("A2 : " + criteria1.getA2());
		System.out.println("B1 : " + criteria1.getB1());
		System.out.println("B2 : " + criteria1.getB2());

		System.out.println("### Criteria 2");
		System.out.println("A1 : " + criteria2.getA1());
		System.out.println("A2 : " + criteria2.getA2());
		System.out.println("B1 : " + criteria2.getB1());
		System.out.println("B2 : " + criteria2.getB2());
		
	}
	
	/**
	 * Objective : Must copy criteria 2 ea which is not like to criteria main
	 * Concept 	: Criteria 1 --> CriteriaTemp 1
	 * 			: Criteria 2 --> CriteriaTemp 2
	 * 			: CriteriaTemp 1 --> CriteriaMain
	 * 			: CriteriaTemp 2 --> CriteriaMain
	 * @throws Exception
	 */
	public static void copyCriteriaToCriteriaTemp() throws Exception {

		CriteriaTable1 criteria1 = new CriteriaTable1();
		criteria1.setA1(" A1 have val.");
		criteria1.setB1(" B1 have val.");
		
		CriteriaTemp1 criteriaTemp1 = new CriteriaTemp1();
		criteriaTemp1.setCriteria1(criteria1);
		
		
		CriteriaTable2 criteria2 = new CriteriaTable2();
		criteria2.setA1(" TEST A1 NA ");
		criteria2.setA2(" A2 have val.");
		criteria2.setB2(" B2 have val.");
		
		CriteriaTemp2 criteriaTemp2 = new CriteriaTemp2();
		criteriaTemp2.setCriteria2(criteria2);
		
		Criteria3 criteria3 = new Criteria3();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");
		System.out.println("### Criteria 1");
		System.out.println("A1 : " + criteriaTemp1.getCriteria1().getA1());
		System.out.println("B1 : " + criteriaTemp1.getCriteria1().getB1());

		System.out.println("### Criteria 2");
		System.out.println("A2 : " + criteriaTemp2.getCriteria2().getA2());
		System.out.println("A2 : " + criteriaTemp2.getCriteria2().getB2());
		
		System.out.println("### Criteria 3");
		
		if(criteria3.getCriteria1() != null){
			System.out.println("A1 : " + criteria3.getCriteria1().getA1());
			System.out.println("B1 : " + criteria3.getCriteria1().getB1());
		}else{
			System.out.println("A1 : " + criteria3.getCriteria1());
			System.out.println("B1 : " + criteria3.getCriteria1());
		}
		
		if(criteria3.getCriteria2() != null){
			System.out.println("A2 : " + criteria3.getCriteria2().getA2());
			System.out.println("B2 : " + criteria3.getCriteria2().getB2());
		}else{
			System.out.println("A2 : " + criteria3.getCriteria2());
			System.out.println("B2 : " + criteria3.getCriteria2());
		}

		
		System.out.println();
		
		//copy properties from (target, source)
//		BeanUtils.copyProperties(criteria3, criteriaTemp1);
//		BeanUtils.copyProperties(criteria3, criteriaTemp2);
		BeanUtils.copyProperties(criteria3.getCriteria1(), criteria1);
		BeanUtils.copyProperties(criteria3.getCriteria2(), criteria2);
		
		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");
		System.out.println("### Criteria 1");
		System.out.println("A1 : " + criteriaTemp1.getCriteria1().getA1());
		System.out.println("B1 : " + criteriaTemp1.getCriteria1().getB1());

		System.out.println("### Criteria 2");
		System.out.println("A2 : " + criteriaTemp2.getCriteria2().getA2());
		System.out.println("A2 : " + criteriaTemp2.getCriteria2().getB2());
		
		
		System.out.println("### Criteria 3");
		
		if(criteria3.getCriteria1() != null){
			System.out.println("A1 : " + criteria3.getCriteria1().getA1());
			System.out.println("B1 : " + criteria3.getCriteria1().getB1());
		}else{
			System.out.println("A1 : " + criteria3.getCriteria1());
			System.out.println("B1 : " + criteria3.getCriteria1());
		}
		
		if(criteria3.getCriteria2() != null){
			System.out.println("A2 : " + criteria3.getCriteria2().getA2());
			System.out.println("B2 : " + criteria3.getCriteria2().getB2());
		}else{
			System.out.println("A2 : " + criteria3.getCriteria2());
			System.out.println("B2 : " + criteria3.getCriteria2());
		}
		
	}
	
	/**
	 * Copy Object A Extends B To Object C Extends D
	 * Case : Primitive type , Object , Object in Object
	 * @throws Exception
	 */
	public static void basicObjectExtendsCopyTest() throws Exception {
		
		//Bean Original
		BeanAnt beanAnt = new BeanAnt();
		beanAnt.setColour("RED");
		beanAnt.setLeg("8 Leg");
		beanAnt.setName("ANT NUMBER 1");
		
		beanAnt.setNest("In my home . . .");
		beanAnt.setIsBoss("Yes");
		
		Header header = new Header();
		header.setHeaderId("H-1334");
		header.setHeaderType("HType-113");
		
		//Object inside object original
		JourneyInfo journeyInfo = new JourneyInfo();
		journeyInfo.setJourneyInfoId("J-Info-1334");
		journeyInfo.setJourneyInfoName("JType-Info-2233");
		
		Journey journey = new Journey();
		journey.setJourneyId("J-1334");
		journey.setJourneyType("JType-544");
		journey.setJourneyInfo(journeyInfo);
		
		Traveller traveller = new Traveller();
		traveller.setTravellerId("T-1334");
		traveller.setTravellerType("TType-225");
		
		BeanRequest beanRequest = new BeanRequest();
		beanRequest.setHeader(header);
		beanRequest.setJourney(journey);
		beanRequest.setTraveller(traveller);
		
		beanAnt.setBeanRequest(beanRequest);
		
		//Bean Destination
		BeanAntH beanAntHuman = new BeanAntH();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");

		System.out.println("### Ant Class Extends Animal");
		System.out.println("Ant-Colour : " + beanAnt.getColour());
		System.out.println("Ant-Name : " + beanAnt.getName());
		System.out.println("Ant-Leg : " + beanAnt.getLeg());
		System.out.println("Ant-Nest : " + beanAnt.getNest());
		System.out.println("Ant-Is Boss : " + beanAnt.getIsBoss());

		System.out.println("### AntHuman Class Extends Human");
		System.out.println("AntHuman-Colour : " + beanAntHuman.getColour());
		System.out.println("AntHuman-Name : " + beanAntHuman.getName());
		System.out.println("AntHuman-Leg : " + beanAntHuman.getLeg());
		
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(beanAntHuman, beanAnt);

		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");
		System.out.println("### Ant Class Extends Animal");
		System.out.println("Ant-Colour : " + beanAnt.getColour());
		System.out.println("Ant-Name : " + beanAnt.getName());
		System.out.println("Ant-Leg : " + beanAnt.getLeg());
		System.out.println("Ant-Nest : " + beanAnt.getNest());
		System.out.println("Ant-Is Boss : " + beanAnt.getIsBoss());

		System.out.println("### AntHuman Class Extends Human");
		System.out.println("AntHuman-Colour : " + beanAntHuman.getColour());
		System.out.println("AntHuman-Name : " + beanAntHuman.getName());
		System.out.println("AntHuman-Leg : " + beanAntHuman.getLeg());
		
		
	}
	
	/**
	 * Copy Object A Extends B To Object C 
	 * Object A : 2 Property same as in C
	 * Object B : 1 Property same as in C
	 * @throws Exception
	 */
	public static void basicObjectExtendsCopyTest2() throws Exception {
		
		BeanAnt beanAnt = new BeanAnt();
		beanAnt.setColour("RED");
		beanAnt.setLeg("8 Leg");
		beanAnt.setName("ANE NUMBER 1");
		
		BeanUnicorn beanUnicorn = new BeanUnicorn();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");

		System.out.println("### Ant Class Extends Animal");
		System.out.println("Ant-Colour : " + beanAnt.getColour());
		System.out.println("Ant-Leg : " + beanAnt.getLeg());
		System.out.println("Ant-Name : " + beanAnt.getName());

		System.out.println("### Unicorn Class(Att Same)");
		System.out.println("Unicorn-Colour : " + beanUnicorn.getColour());
		System.out.println("Unicorn-Leg : " + beanUnicorn.getLeg());
		System.out.println("Unicorn-Name : " + beanUnicorn.getName());
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(beanUnicorn, beanAnt);

		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");
		
		System.out.println("### Ant Class Extends Animal");
		System.out.println("Ant-Colour : " + beanAnt.getColour());
		System.out.println("Ant-Leg : " + beanAnt.getLeg());
		System.out.println("Ant-Name : " + beanAnt.getName());

		System.out.println("### Unicorn Class(Att Same)");
		System.out.println("Unicorn-Colour : " + beanUnicorn.getColour());
		System.out.println("Unicorn-Leg : " + beanUnicorn.getLeg());
		System.out.println("Unicorn-Name : " + beanUnicorn.getName());
		
	}
	
	/**
	 * Copy Object A To Object B Extends C
	 * Object B : 1 Property same as in A
	 * Object C : 2 Property same as in A
	 * @throws Exception
	 */
	public static void basicObjectExtendsCopyTest3() throws Exception {

		BeanUnicorn beanUnicorn = new BeanUnicorn();
		beanUnicorn.setColour("Blue");
		beanUnicorn.setLeg("4 Leg");
		beanUnicorn.setName("Pegasas");
		
		BeanShark beanShark = new BeanShark();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");

		System.out.println("### Unicorn Class");
		System.out.println("Unicorn-Colour : " + beanUnicorn.getColour());
		System.out.println("Unicorn-Leg : " + beanUnicorn.getLeg());
		System.out.println("Unicorn-Name : " + beanUnicorn.getName());

		System.out.println("### Shark Class Extends Fish Class ");
		System.out.println("Shark-Colour : " + beanShark.getColour());
		System.out.println("Shark-Leg : " + beanShark.getLeg());
		System.out.println("Shark-Name : " + beanShark.getName());
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(beanShark, beanUnicorn);

		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");
				
		System.out.println("### Unicorn Class");
		System.out.println("Unicorn-Colour : " + beanUnicorn.getColour());
		System.out.println("Unicorn-Leg : " + beanUnicorn.getLeg());
		System.out.println("Unicorn-Name : " + beanUnicorn.getName());

		System.out.println("### Shark Class Extends Fish Class ");
		System.out.println("Shark-Colour : " + beanShark.getColour());
		System.out.println("Shark-Leg : " + beanShark.getLeg());
		System.out.println("Shark-Name : " + beanShark.getName());
		
	}
	
	/**
	 * Copy Object A To Object B
	 * Case : List(Primitive Type) , String To Int(Number) , Int To String(Number)
	 * @throws Exception
	 */
	public static void copyObjectListString() throws Exception {
		
		List<String> lisStrings = new ArrayList<String>();
		lisStrings.add("111");
		lisStrings.add("222");
		lisStrings.add("333");
		lisStrings.add("444");
			
		BeanList beanList = new BeanList();
		beanList.setListBean(lisStrings);
		beanList.setNumberInt(9);
		beanList.setNumberString("10");
		
		BeanOriginal beanOriginal = new BeanOriginal();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");

		System.out.println("### Bean List");
		System.out.println("NumberInt : " + beanList.getNumberInt());
		System.out.println("NumberString : " + beanList.getNumberString());
		System.out.println("List Size : " + beanList.getListBean().size());
		
		for(int i =0; i <= beanList.getListBean().size()-1; i++){
			System.out.println("List [" +(i+1)+"] :" + beanList.getListBean().get(i));
		}

		System.out.println("### Bean Original ");
		System.out.println("NumberInt(String Default) : " + beanOriginal.getNumberInt());
		System.out.println("NumberString(Int Default) : " + beanOriginal.getNumberString());
		if(beanOriginal.getListAAA() != null){
			System.out.println("List Size AAA : " + beanOriginal.getListAAA().size());
			
			for(int i =0; i <= beanOriginal.getListAAA().size()-1; i++){
				System.out.println("List [" +(i+1)+"] :" + beanOriginal.getListAAA().get(i));
			}
			
		}else{
			System.out.println("List Size AAA : " + beanOriginal.getListAAA());
		}
		
		if(beanOriginal.getListBean() != null){
			System.out.println("List Size Original : " + beanOriginal.getListBean().size());
			
			for(int i =0; i <= beanOriginal.getListBean().size()-1; i++){
				System.out.println("List [" +(i+1)+"] :" + beanOriginal.getListBean().get(i));
			}
		}else{
			System.out.println("List Size Original : " + beanOriginal.getListBean());
		}
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(beanOriginal, beanList);
		
		System.out.println();
		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");
		
		System.out.println("### Bean List");
		System.out.println("NumberInt : " + beanList.getNumberInt());
		System.out.println("NumberString : " + beanList.getNumberString());
		System.out.println("List Size : " + beanList.getListBean().size());
		
		for(int i =0; i <= beanList.getListBean().size()-1; i++){
			System.out.println("List [" +(i+1)+"] :" + beanList.getListBean().get(i));
		}

		System.out.println("### Bean Original ");
		System.out.println("NumberInt(String Default) : " + beanOriginal.getNumberInt());
		System.out.println("NumberString(Int Default) : " + beanOriginal.getNumberString());
		if(beanOriginal.getListAAA() != null){
			System.out.println("List Size AAA : " + beanOriginal.getListAAA().size());
			
			for(int i =0; i <= beanOriginal.getListAAA().size()-1; i++){
				System.out.println("List [" +(i+1)+"] :" + beanOriginal.getListAAA().get(i));
			}
			
		}else{
			System.out.println("List Size AAA : " + beanOriginal.getListAAA());
		}
		
		if(beanOriginal.getListBean() != null){
			System.out.println("List Size Original : " + beanOriginal.getListBean().size());
			
			for(int i =0; i <= beanOriginal.getListBean().size()-1; i++){
				System.out.println("List [" +(i+1)+"] :" + beanOriginal.getListBean().get(i));
			}
		}else{
			System.out.println("List Size Original : " + beanOriginal.getListBean());
		}
	
	}
	
	/**
	 * Copy Object A To Object B
	 * Case : List(Object)
	 * @throws Exception
	 */
	public static void copyObjectListObject() throws Exception {
		
		List<BeanAnt> listBeanAnt = new ArrayList<BeanAnt>();
		
		BeanAnt beanAnt = new BeanAnt();
		beanAnt.setNest("My home");
		beanAnt.setIsBoss("Yes");
		
		listBeanAnt.add(beanAnt);
		
		beanAnt = new BeanAnt();
		beanAnt.setLeg("8 Leg");
		beanAnt.setColour("Red");
		beanAnt.setName("Ku gaaaaaaaaa");
		beanAnt.setNest("Worlds");
		beanAnt.setIsBoss("Yes");
		
		listBeanAnt.add(beanAnt);
			
		BeanList beanList = new BeanList();
		beanList.setListBeanObject(listBeanAnt);
		
		BeanOriginal beanOriginal = new BeanOriginal();
		
		System.out.println("##### [Before BeanUtils.copyProperties] #####");
		
		System.out.println("### Bean List");
//		System.out.println("List Object Size : " + beanList.getListBeanObject().size());
		
		for(int i =0; i <= beanList.getListBeanObject().size()-1; i++){
			System.out.println("List [" +(i+1)+"] : Colour : " + beanList.getListBeanObject().get(i).getColour());
			System.out.println("List [" +(i+1)+"] : Leg : " + beanList.getListBeanObject().get(i).getLeg());
			System.out.println("List [" +(i+1)+"] : Name : " + beanList.getListBeanObject().get(i).getName());
			System.out.println("List [" +(i+1)+"] : Nest : " + beanList.getListBeanObject().get(i).getNest());
			System.out.println("List [" +(i+1)+"] : IsBoss : " + beanList.getListBeanObject().get(i).getIsBoss());
			System.out.println();
		}
		
		System.out.println("### Bean Original ");
		
		if(beanOriginal.getListBeanObject() != null){
//			System.out.println("List Object Size : " + beanOriginal.getListBeanObject().size());
			
			for(int i =0; i <= beanOriginal.getListBeanObject().size()-1; i++){
				System.out.println("List [" +(i+1)+"] : Colour : " + beanOriginal.getListBeanObject().get(i).getColour());
				System.out.println("List [" +(i+1)+"] : Leg : " + beanOriginal.getListBeanObject().get(i).getLeg());
//				System.out.println("List [" +(i+1)+"] : Name : " + beanOriginal.getListBeanObject().get(i).getName());
				System.out.println("List [" +(i+1)+"] : Nest : " + beanOriginal.getListBeanObject().get(i).getNest());
				System.out.println("List [" +(i+1)+"] : IsBoss : " + beanOriginal.getListBeanObject().get(i).getIsBoss());
				System.out.println();
			}
			
		}else{
			System.out.println("List Object Size : " + beanOriginal.getListBeanObject());
		}
		
		//Copy properties from (target, source)
		BeanUtils.copyProperties(beanOriginal, beanList);
				
		System.out.println();
		System.out.println("\n ##### [After BeanUtils.copyProperties] #####");
		
		System.out.println("### Bean List");
//		System.out.println("List Object Size : " + beanList.getListBeanObject().size());
		
		for(int i =0; i <= beanList.getListBeanObject().size()-1; i++){
			System.out.println("List [" +(i+1)+"] : Colour : " + beanList.getListBeanObject().get(i).getColour());
			System.out.println("List [" +(i+1)+"] : Leg : " + beanList.getListBeanObject().get(i).getLeg());
			System.out.println("List [" +(i+1)+"] : Name : " + beanList.getListBeanObject().get(i).getName());
			System.out.println("List [" +(i+1)+"] : Nest : " + beanList.getListBeanObject().get(i).getNest());
			System.out.println("List [" +(i+1)+"] : IsBoss : " + beanList.getListBeanObject().get(i).getIsBoss());
			System.out.println();
		}
		
		System.out.println("### Bean Original ");
		
		if(beanOriginal.getListBeanObject() != null){
//			System.out.println("List Object Size : " + beanOriginal.getListBeanObject().size());
			
			for(int i =0; i <= beanOriginal.getListBeanObject().size()-1; i++){
				System.out.println("List [" +(i+1)+"] : Colour : " + beanOriginal.getListBeanObject().get(i).getColour());
				System.out.println("List [" +(i+1)+"] : Leg : " + beanOriginal.getListBeanObject().get(i).getLeg());
//				System.out.println("List [" +(i+1)+"] : Name : " + beanOriginal.getListBeanObject().get(i).getName());
				System.out.println("List [" +(i+1)+"] : Nest : " + beanOriginal.getListBeanObject().get(i).getNest());
				System.out.println("List [" +(i+1)+"] : IsBoss : " + beanOriginal.getListBeanObject().get(i).getIsBoss());
				System.out.println();
			}
			
		}else{
			System.out.println("List Object Size : " + beanOriginal.getListBeanObject());
		}
		
	}
	
	
}

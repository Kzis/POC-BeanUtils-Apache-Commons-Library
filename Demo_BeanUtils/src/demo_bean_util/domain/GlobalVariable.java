package demo_bean_util.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;



public class GlobalVariable implements Serializable {

	private static final long serialVersionUID = 7226578754399777637L;

	public static final String CONFIG_PARAMETER_FILE = "parameter.xml";
	public static final String CONFIG_DATABBASE_PARAMETER_FILE = "dbconfig.xml";
	public static final String CONFIG_LOG4J_FILE = "log4j.properties";
	
	public static final String SOURCE_OF_DATA_GG = "1";
	public static final String SOURCE_OF_DATA_CP = "2";
	public static final String SOURCE_OF_DATA_OTHER = "3";
	
	public static final String XML_TYPE_CHECKIN = "1";
	public static final String XML_TYPE_CANCEL = "2";
	public static final String XML_TYPE_NOTIFY = "3";
	
	public static final String MASTER_THREAD_NAME = "M_BG";
	public static final String DETAIL_THREAD_NAME = "D_BG";
	
	public static final String TAG_SPLIT = ",";
	public static final String TAG_SLASH = "/";
	public static final String TAG_DAT = "-";
	
	public static final String JAXB_ENCODING = "UTP-8";
	public static final String CURRENT_DATE_DB_FORMAT = "DD/MM/YYYY HH24:mm:ss"; //เน�เธ�เน�เธ�เธฒเธ� DD/MM/YYYY HH:mm:ss เน€เธ�เธฃเธฒเธฐ GEN XML เธ�เธดเธ”
	
	public static final String TYPE_P = "P";
	public static final String TYPE_C = "C";
	public static final String TYPE_X = "X";
	
	public static final String DOCUMENT_TYPE_P = "P";
	public static final String DOCUMENT_TYPE_O = "O";
	public static final String DOCUMENT_TYPE_N = "N";
	
	public static final String GENDER_M = "M";
	public static final String GENDER_F = "F";
	public static final String GENDER_X = "X";
	
	public static final String TRAVEL_TYPE_N = "N";
	public static final String TRAVEL_TYPE_T = "T";
	public static final String TRAVEL_TYPE_X = "X";
	
	public static final String OVERRIDE_CODE_A = "A";
	public static final String OVERRIDE_CODE_G = "G";
	
	public static final String DUP_FLAG_TRUE = "T";
	public static final String DUP_FLAG_FALSE = "F";
	
	public static final String BOARDING_STATUS_I = "I";
	
	public static final String CANCEL_STATUS_Y = "Y";

	public static final String TIME_FORMAT = "%06d";
	public static final String TIME_FORMAT_ZERO = "000000";
	public static final String DATE_FORMAT = "dd-MMM-yy";
	public static final String SERVICE_NUMBER_FORMAT = "%04d";
	public static final String SECOND_TIME = "00";
	
	public static final String DATE_TIME_FORMAT_YYYYMMDD = "YYYYMMDD";
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD = "YYYY-MM-DD";
	public static final String DATE_TIME_FORMAT_FROM_BATCH = "YYYY-MM-DDHH:mm:ss";
	public static final String DATE_TIME_FORMAT_FROM_BATCH_SEMI = "YYYYMMDDHH:mm:ss";
	public static final String DATE_TIME_FORMAT_FROM_BATCH_EXPIRE = "YYYYMMDDHHmmss";
	public static final String DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR = "DD/MM/YYYY HH:mm:ss";
	public static final String DATE_TIME_FORMAT_TO_XML_GREGORIAN_CALENDAR_NOT_TIMEZONE = "DD/MM/YYYY";
	
	public static final String SEQUENCE_NUMBER = "001";
	
	public static final String DATA_SOURCE_BCH = "BCH"; //BY BATCH
	public static final String DATA_SOURCE_BFT = "BFT"; //BY FTP
	
	public static final String FORGET_DAY_FORMAT = "00";
	public static final String FORGET_MONTH_FORMAT = "00";
	public static final String FORGET_YEAR_FORMAT = "0000";
	
	public static final int START_MONTH = 1;
	public static final int END_MONTH = 12;
	
	//เธ�เธณเธซเธ�เธ”เน�เธกเน�เน�เธซเน�เธกเธตเธ�เธณเธ�เธงเธ� Thread เธซเธฅเธฑเธ�เน€เธ�เธดเธ�เธ�เธณเธซเธ�เธ”
	private static ThreadPoolExecutor mainThreadPoolExecutor = null;
	
	//เธ�เธณเธซเธ�เธ”เน�เธกเน�เน�เธซเน�เธกเธตเธ�เธณเธ�เธงเธ� Thread เธฃเธญเธ�เน€เธ�เธดเธ�เธ�เธณเธซเธ�เธ”
//	private static ThreadPoolExecutor subThreadPoolExecutor = null;
		
	//เน€เธ�เน�เธ� Thread เธชเธณเธซเธฃเธฑเธ� Kill เธซเธฒเธ�เธกเธตเธ�เธฒเธฃ update
	private static HashMap<String, String> mapThreadFuture = new HashMap<String, String>();
	
//	private static Agency agency = new Agency();

	public enum MsgType {
		CHECK_IN("1"), CANCEL("2"), NOTIFY("3");

		private String value;

		private MsgType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public enum DataSource {
		INDIVIDUAL("I"), BATCH_BROWSE("B"), BATCH_FTP("F"), CLOSE("C");
		
		private String value;
		
		private DataSource(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
	}
	
	public static ThreadPoolExecutor getMainThreadPoolExecutor() {
		return mainThreadPoolExecutor;
	}

	public static void setMainThreadPoolExecutor(ThreadPoolExecutor mainThreadPoolExecutor) {
		GlobalVariable.mainThreadPoolExecutor = mainThreadPoolExecutor;
	}

//	public static Agency getAgency() {
//		return agency;
//	}

//	public static void setAgency(ACgency agency) {
//		GlobalVariable.agency = agency;
//	}

	public static HashMap<String, String> getMapThreadFuture() {
		return mapThreadFuture;
	}

	public static void setMapThreadFuture(HashMap<String, String> mapThreadFuture) {
		GlobalVariable.mapThreadFuture = mapThreadFuture;
	}
	
}
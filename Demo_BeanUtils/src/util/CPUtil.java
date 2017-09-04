package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.crypto.Data;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import util.calendar.CalendarUtil;
import util.log.LogUtil;
import demo_bean_util.TransactionDetail;
import demo_bean_util.domain.GlobalVariable;

public class CPUtil {
	
	/**
	 * convert Calendar To XMLGregorianCalendar
	 * 
	 * length() == 10  format : yyyy-mm-dd : 2016-10-12
	 * length() != 10  format : dd/mm/yyyy hh:mm:ss  : 12/10/2016 10:20:59
	 * @param dateStr "DDslMMslYYYYblHH24clMIclSS"
	 * @return 
	 */
	public static XMLGregorianCalendar convertCalendarToXMLGregorianCalendar(String dateStr) throws Exception {
		XMLGregorianCalendar xmlGc = null;
		
		try {
			Calendar cal;
			GregorianCalendar gc = new GregorianCalendar(Locale.ENGLISH);
			
			if(dateStr == null || dateStr.isEmpty()){
				cal = Calendar.getInstance(Locale.ENGLISH); //DateTime Local
				

				gc.setTime(cal.getTime());
				xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				
			} else{
				
				if(dateStr.length() == 10){
					
					cal = CalendarUtil.getCalendarFromYYYYmiMMmiDD(dateStr, Locale.ENGLISH);
					
					gc.setTime(cal.getTime());
					xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
					xmlGc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
					xmlGc.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
					xmlGc.setSecond(DatatypeConstants.FIELD_UNDEFINED);
					xmlGc.setMinute(DatatypeConstants.FIELD_UNDEFINED);
					xmlGc.setHour(DatatypeConstants.FIELD_UNDEFINED);
				}else{
					cal = CalendarUtil.getCalendarFromDDslMMslYYYYblHH24clMIclSS(dateStr, Locale.ENGLISH);
					
					gc.setTime(cal.getTime());
					xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
					xmlGc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
					xmlGc.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
					
				}
			}
			
			
			
			
		} catch (Exception e) {
			throw e;
		}
		return xmlGc;
	}
	
	/**
	 * 
	 * @param date			Format : 30-Jan-15
	 * @param dateFormat	Format : dd-MMM-yy
	 * @return 20150130
	 * @throws Exception 
	 */
	public static String convertDateToYYYYMMDD(String date , String dateFormat) throws Exception{
		
		if(date == null || date.isEmpty()){
			return null;
		}
		
		String newDate = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
			sdf.setLenient(false);
			newDate = sdf2.format(sdf.parse(date));	
		} catch (Exception e) {
			throw e;
		}
		
		return newDate;
	}
	
	/**
	 * 
	 * @param timeToConvert		Format : xxx5 , xx15 , x450 , 1415 | Meaning x is blank
	 * @return 000500 , 001500 , 045000 , 141500
	 */
	public static String converTimeToHHMMSS(String timeToConvert) throws Exception{
		
		if(timeToConvert.isEmpty()){
			return "";
		}

		String time = null;
		
		try {
			
			time = String.format(GlobalVariable.TIME_FORMAT, Integer.parseInt(timeToConvert.concat(GlobalVariable.SECOND_TIME)));
			
		} catch (Exception e) {
			throw e;	
		}
		
		
		return time;
	}	
	
	public static String checkTravellerType(String travellerType) throws Exception{
		
		LogUtil.BATCH.info("********** checkTravellerType **********");
		
//		try{
//			if(!CPLogicValidate.checkTravellerType(travellerType)){
//				return "10086";
//			}
//		}catch(ServerValidateException e){
//			return "10086";
//		}catch (Exception e) {
//			return "10086";
//		}
		return "";
		
	}
	
	public static String checkDocumentType(String documentType) throws Exception{
		
		LogUtil.BATCH.info("********** checkDocumentType **********");
		
//		try {
//			if(!CPLogicValidate.checkDocumentType(documentType)){
//				return "10087";
//			}
//		} catch (ServerValidateException e) {
//			return "10087";
//		} catch (Exception e) {
//			return "10087";
//		}		
		return "";
		
	}
	
	public static String checkDocumentNumber(String documentNumber) throws Exception{
		
		LogUtil.BATCH.info("********** checkDocumentNumber **********");
		
		try {
			if(documentNumber == null){
				return "10088";
			}
		} catch (Exception e) {
			return "10088";
		}
		return "";
		
	}
	
	public static String checkDocumentExpireDate(TransactionDetail transactionDetail) throws Exception{
		
		LogUtil.BATCH.info("********** checkDocumentExpireDate **********");
		
		try{
			String documentExpireDate = CPUtil.convertDateToYYYYMMDD(transactionDetail.getDocumentExpireDate(), GlobalVariable.DATE_FORMAT);
			
			if(documentExpireDate == null){
				return "10089";
			}
		}
		catch(Exception e){
			return "10089";
		}
		return "";
	}
	
	public static String checkNationality(long count) throws Exception{
		
		LogUtil.BATCH.info("********** checkNationality **********");
		
		try {
			if(!(count > 0)){
				return "10090";
			}
		} catch (Exception e) {
			return "10090";
		}
		
		return "";
	}
	
	public static String checkIssueingState(long count) throws Exception{
		
		LogUtil.BATCH.info("********** checkIssuingState **********");
		
		try{
			if(!(count > 0)){
				return "10104";
			}
		}catch(Exception e){
			return "10104";
		}
		
		return "";
	}
	
	public static String checkFamilyName(String familyName) throws Exception {

		LogUtil.BATCH.info("********** checkFamilyName **********");
		
//		try {
//			if(!CPLogicValidate.checkFamilyName(familyName)){
//				return "10092";
//			}
//		} catch (ServerValidateException e) {
//			return "10092";
//		}catch (Exception e) {
//			return "10092";
//		}
		return "";
	}

	public static String checkGivenName(String givenName) throws Exception {
			
		LogUtil.BATCH.info("********** checkGivenName **********");
		
//		try {
//			if(!CPLogicValidate.checkGivenName(givenName)){
//				return "10093";
//			}
//		} catch (ServerValidateException e) {
//			return "10093";
//		} catch (Exception e){
//			return "10093";
//		}
		
		return "";
	}
	
	public static String checkDateOfBirth(TransactionDetail transactionDetail) throws Exception {
		
		LogUtil.BATCH.info("********** checkDateOfBirth **********");

		String day;
		String month;
		String year;
		Boolean flagDay;
		Boolean flagMonth;
		Boolean flagYear;
		String[] DOBSplit;
		String dateOfBirth;
		
		// Get เธ�เธตเธ�เธฑเธ�เธ�เธธเธ�เธฑเธ�
		int currentYear = Calendar.getInstance(Locale.ENGLISH).get(Calendar.YEAR);
		
		try{
			String DOB = transactionDetail.getDateOfBirth();
			
			
			if(DOB.indexOf(GlobalVariable.TAG_DAT) < 0 && DOB.indexOf(GlobalVariable.TAG_SLASH) < 0){
				return "10094";
			}else{
				
				DOB = DOB.replace(GlobalVariable.TAG_SLASH, GlobalVariable.TAG_DAT);
				DOBSplit = DOB.split(GlobalVariable.TAG_DAT);
				
				day = DOBSplit[0];
				month = DOBSplit[1];
				year = DOBSplit[2];
				
				flagDay = day.equals(GlobalVariable.FORGET_DAY_FORMAT);
				flagMonth = month.equals(GlobalVariable.FORGET_MONTH_FORMAT);
				flagYear = year.equals(GlobalVariable.FORGET_YEAR_FORMAT);
				
				if(flagDay || flagMonth ||  flagYear){ 
					
					if(!flagYear){
						if(!(Integer.parseInt(year) <= currentYear)){
							return "10094";
						}
					}
					
					if(!flagMonth){
						if( !(Integer.parseInt(month) >= GlobalVariable.START_MONTH && Integer.parseInt(month) <= GlobalVariable.END_MONTH) ){
							return "10094";
						}
					}
					
					transactionDetail.setFlagDOB("Y");
					transactionDetail.setDateOfBirth(year + GlobalVariable.TAG_DAT + month + GlobalVariable.TAG_DAT + day);
					
				}else{ 
					transactionDetail.setFlagDOB("N");
					
					// DOB เน�เธ�เธฅเธ�เน€เธ�เน�เธ� Format YYYYMMDD เน�เธ”เน� ?
					dateOfBirth = CPUtil.convertDateToYYYYMMDD(DOB, GlobalVariable.DATE_FORMAT);
					
					if(dateOfBirth == null){
						return "10094";
					}
				}
			}

		}catch(Exception e){
			return "10094";
		}
		
		return "";
	}
	
	public static String checkGender(String gender) throws Exception{
		
		LogUtil.BATCH.info("********** checkDateOfBirth **********");
		
//		try {
//			if(!CPLogicValidate.checkGender(gender)){
//				return "10095";
//		}
//		} catch (ServerValidateException e) {
//			return "10095";
//		} catch (Exception e) {
//			return "10095";
//		}
		return "";
	}
	
	public static String checkCountryOfBirth(long count) throws Exception{
		
		LogUtil.BATCH.info("********** checkCountryOfBirth **********");
		
		try {
			if(!(count > 0)){
				return "10105";
			}
		} catch (Exception e) {
			return "10105";
		}
		
		return "";
	}
	
	public static String checkTravelType(String travelType) throws Exception{
		
		LogUtil.BATCH.info("********** checkTravelType **********");
//		
//		try{
//			if(!CPLogicValidate.checkTravelType(travelType)){
//				return "10096";
//			}
//		}catch(ServerValidateException e){
//			return "10096";
//			
//		}catch(Exception e){
//			return "10096";
//		}
		return "";
	}
	
	public static String checkOverrideCode(String override) throws Exception{
		
		LogUtil.BATCH.info("********** checkOverrideCode **********");
		
//		try {
//			if(!CPLogicValidate.checkOverride(override)){
//				return "10097";
//			}
//		} catch (ServerValidateException e) {
//			return "10097";
//		} catch (Exception e) {
//			return "10097";
//		}
		return "";
		
	}
}

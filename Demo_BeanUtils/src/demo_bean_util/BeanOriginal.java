package demo_bean_util;

import java.util.ArrayList;
import java.util.List;

public class BeanOriginal {

	String id;
	String name;
	String original;
	
	Header header;
	Header headerNotSame;
	Journey journey;
	Traveller traveller;
	
	String numberInt;
	int numberString;
	List<String> listBean;
	
	List<Object> listAAA;

	List<BeanAnt> listBeanObject;
//	List<BeanRequest> listBeanObject;
	
	public void setHeaderNotSame(Header headerNotSame) {
		this.headerNotSame = headerNotSame;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public List<BeanAnt> getListBeanObject() {
		return listBeanObject;
	}

	public void setListBeanObject(List<BeanAnt> listBeanObject) {
		this.listBeanObject = listBeanObject;
	}

	public Header getHeaderNotSame() {
		return headerNotSame;
	}

	public int getNumberString() {
		return numberString;
	}

	public void setNumberString(int test2) {
		this.numberString = test2;
	}

	public String getNumberInt() {
		return numberInt;
	}

	public void setNumberInt(String test) {
		this.numberInt = test;
	}

	public List<String> getListBean() {
		return listBean;
	}

	public void setListBean(List<String> listBean) {
		this.listBean = listBean;
	}

	public List<Object> getListAAA() {
		return listAAA;
	}

	public void setListAAA(List<Object> listAAA) {
		this.listAAA = listAAA;
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

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

//	public Header getHeader() {
//		return header;
//	}
//
//	public void setHeader(Header header) {
//		this.header = header;
//	}

	public Traveller getTraveller() {
		return traveller;
	}

	public void setTraveller(Traveller traveller) {
		this.traveller = traveller;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}
	
	

}

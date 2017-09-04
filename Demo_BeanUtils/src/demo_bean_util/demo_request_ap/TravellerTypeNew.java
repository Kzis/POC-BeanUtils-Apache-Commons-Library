package demo_bean_util.demo_request_ap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import demo_bean_util.BeanAnt;

public class TravellerTypeNew extends TravellerType {
	
	String x;
	String y;
	
	BeanAnt aaa;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public BeanAnt getAaa() {
		return aaa;
	}

	public void setAaa(BeanAnt aaa) {
		this.aaa = aaa;
	}
	
	

}

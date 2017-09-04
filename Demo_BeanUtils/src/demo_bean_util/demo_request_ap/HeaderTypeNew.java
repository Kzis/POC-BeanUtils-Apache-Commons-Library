package demo_bean_util.demo_request_ap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import demo_bean_util.BeanAnt;

public class HeaderTypeNew extends HeaderType{
	
	//New Attr
	String x;
	String y;
	
	BeanAnt beanAAA;

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

	public BeanAnt getBeanAAA() {
		return beanAAA;
	}

	public void setBeanAAA(BeanAnt beanAAA) {
		this.beanAAA = beanAAA;
	}
	
	
	
}

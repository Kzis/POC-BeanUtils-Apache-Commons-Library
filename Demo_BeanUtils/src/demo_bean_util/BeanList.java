package demo_bean_util;

import java.util.List;

public class BeanList {
	
	int numberInt;
	String numberString;
	
	List<String> listBean;
	List<BeanAnt> listBeanObject;
	
	
	
	public String getNumberString() {
		return numberString;
	}

	public void setNumberString(String test2) {
		this.numberString = test2;
	}

	public int getNumberInt() {
		return numberInt;
	}

	public void setNumberInt(int test) {
		this.numberInt = test;
	}

	public List<BeanAnt> getListBeanObject() {
		return listBeanObject;
	}

	public void setListBeanObject(List<BeanAnt> listBeanObject) {
		this.listBeanObject = listBeanObject;
	}

	public List<String> getListBean() {
		return listBean;
	}

	public void setListBean(List<String> listBean) {
		this.listBean = listBean;
	}
	
	

}

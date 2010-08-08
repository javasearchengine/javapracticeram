package com.springexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ComplexBean {
	
	private Properties adminEmails;
	
	private ArrayList<VehicleInterface> someList;
	
//	private List<VehicleInterface> someList;
	
	public void setSomeList(ArrayList<VehicleInterface> someList) {
		this.someList = someList;
	}

	private Map<String, String> someMap;
	
	private Set<String> someSet;
 
	public void setAdminEmails(Properties adminEmails) {
		this.adminEmails = adminEmails;
	}

	public List<VehicleInterface> getSomeList() {
		return someList;
	}



	public Map<String, String> getSomeMap() {
		return someMap;
	}

	public void setSomeMap(Map<String, String> someMap) {
		this.someMap = someMap;
	}

	public Set<String> getSomeSet() {
		return someSet;
	}

	public void setSomeSet(Set<String> someSet) {
		this.someSet = someSet;
	}

	public Properties getAdminEmails() {
		return adminEmails;
	}
	

}

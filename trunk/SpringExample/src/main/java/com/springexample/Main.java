package com.springexample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		Vehicle1 Vehicle1Object = (Vehicle1) context.getBean("VehicleBean",Vehicle1.class);
		
		System.out.println("The type of the vehicle is "+Vehicle1Object.getVehicleType());
		System.out.println("This "+Vehicle1Object.getVehicleType() +" is manufactured in the year" + Vehicle1Object.getMfgyear()); 
		
		
	}

}

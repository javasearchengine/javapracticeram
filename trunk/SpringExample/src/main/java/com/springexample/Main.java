package com.springexample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		Car CarObject = (Car) context.getBean("CarBean",Car.class);
		
		System.out.println("This Car is manufactured in the year "+CarObject.getMfgyear());
		System.out.println("This Car has "+CarObject.getNoOfTyres()+" Tyres");
		
	}

}

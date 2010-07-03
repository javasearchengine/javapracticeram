package com.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HelloWorld {
	
	public static void main(String[] args){
		Collection<Car> carcollection = new ArrayList<Car>();
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();
		
		car1.setBrandname("HONDA");
		car2.setBrandname("AUDI");
		car3.setBrandname("Mercedez");
		
		car1.setYear(2009);
		car2.setYear(2008);
		car3.setYear(2007);
		
		carcollection.add(car1);
		carcollection.add(car2);
		carcollection.add(car3);
		
		Iterator<Car> it = carcollection.iterator();
		
		while (it.hasNext()) {
			Car car = (Car) it.next();
			System.out.println("Car Name is "+ car.getBrandname());
			System.out.println("Car manufacturing year is "+ car.getYear());
		}
		
	}
}

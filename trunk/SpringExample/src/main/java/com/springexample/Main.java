package com.springexample;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springexample.ExampleBean.InnerBean;

public class Main {
	
	public static void main(String[] args){
		
		AbstractApplicationContext context  = new ClassPathXmlApplicationContext("spring-config.xml"); 
//		FileSystemXmlApplicationContext("C:\\Users\\Ramesh\\workspace\\SpringExample\\src\\main\\resources\\spring-config.xml");
//		AbstractApplicationContext context = new 
		Vehicle1 Vehicle1Object = context.getBean("VehicleBean",Vehicle1.class);
		
		System.out.println("The type of the vehicle is "+Vehicle1Object.getVehicleType());
		System.out.println("This "+Vehicle1Object.getVehicleType() +" is manufactured in the year" + Vehicle1Object.getMfgyear()); 
		System.out.println("This vehicle has "+Vehicle1Object.getNoOfTyres()+ " tyres");
//		System.out.println("This "+Vehicle1Object.getVehicleType() + " has "+ Vehicle1Object.getAudioSystem().getPrice() +"INR worth audio system");
		System.out.println("The value of the sample string property of the vehicle1 is: "+ Vehicle1Object.getSampleStringProperty());
		InnerBean innerbean= context.getBean("InnerBean",InnerBean.class);
		
		System.out.println("The value of the inner bean property is :"+innerbean.getSampleProperty());
		
//		PrivateConstructor privateConstructorbean1 = context.getBean("PrivateConstructorBean1",PrivateConstructor.class);
//		PrivateConstructor privateConstructorbean2 = context.getBean("PrivateConstructorBean2",PrivateConstructor.class);
//		
		AudioSystem audioSystemBean2 = context.getBean("AudioSystemBean2",AudioSystem.class);
 		
		
//		System.out.println("The value of the Private Constructor bean1 property "+privateConstructorbean1.getSampleProperty());
//		System.out.println("The value of the Private Constructor bean2 property "+privateConstructorbean2.getSampleProperty());
		
		System.out.println("The price of the audio system created through factory method is :"+audioSystemBean2.getPrice());
		
		ComplexBean complexBean = context.getBean("ComplexBean",ComplexBean.class);
		
		complexBean.getAdminEmails().list(System.out);
		
//		String [] values = (String[])complexBean.getSomeMap().values().toArray();
		
//		System.out.println("lenght of the map converted string array is"+values.length);
		
//		for(int i=0;i<values.length;i++){
//			System.out.println("Values of the map are"+values[i]);
//		}
		
		System.out.println("********************************************************************");
		
		SampleBean sampleBean = context.getBean("SampleBean",SampleBean.class);
		
		System.out.println("Property one value : "+sampleBean.getBeanOne().getSamplePropertyBeanOne());
		System.out.println("Property two value : "+sampleBean.getBeanTwo().getSamplePropertyBeanTwo());
		
		context.registerShutdownHook();
			
		System.out.println("********************************************************************");
		
	}

}

package com.springexample;

public class PrivateConstructor {
	
	private String sampleProperty;
	
	private static PrivateConstructor privateConstructor = new PrivateConstructor();
	
	private PrivateConstructor(){
		
	}

	public void setSampleProperty(String sampleProperty) {
		this.sampleProperty = sampleProperty;
	}

	public String getSampleProperty() {
		return sampleProperty;
	}
	
	public static AudioSystem createPrivateConstructorInstance(){
		
		return new AudioSystem();
		
	}

}

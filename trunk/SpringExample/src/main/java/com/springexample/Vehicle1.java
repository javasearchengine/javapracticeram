package com.springexample;

public class Vehicle1 implements VehicleInterface {

	private int noOfTyres;

	private int mfgYear;
	
	private VehicleType vehicleType;
	
	private AudioSystem audioSystem;
	
	private String sampleStringProperty;

	public Vehicle1(int mfgyear,int noOfTyres) {
		super();
		this.setMfgyear(mfgyear);
		this.setNoOfTyres(noOfTyres);
	}

	public void setNoOfTyres(int noOfTyres) {
		this.noOfTyres = noOfTyres;
	}

	public int getNoOfTyres() {
		return noOfTyres;
	}

	public void setMfgyear(int mfgyear) {
		mfgYear = mfgyear;
	}

	public int getMfgyear() {
		return mfgYear;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setAudioSystem(AudioSystem audioSystem) {
		this.audioSystem = audioSystem;
	}

	public AudioSystem getAudioSystem() {
		return audioSystem;
	}

	public void setSampleStringProperty(String sampleStringProperty) {
		this.sampleStringProperty = sampleStringProperty;
	}

	public String getSampleStringProperty() {
		return sampleStringProperty;
	}

}

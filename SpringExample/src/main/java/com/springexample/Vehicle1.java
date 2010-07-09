package com.springexample;

public class Vehicle1 implements VehicleInterface {

	private int noOfTyres;

	private int mfgYear;
	
	private VehicleType vehicleType;

	public Vehicle1(int mfgyear) {
		super();
		this.setMfgyear(mfgyear);
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

}

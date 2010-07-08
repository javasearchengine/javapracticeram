package com.springexample;

public class Car implements VehicleInterface {

	private int noOfTyres;

	private int Mfgyear;

	public Car(int mfgyear) {
		super();
		this.setMfgyear(mfgyear);
	}

	public void RunsonWhat() {
		System.out.println("Car runs on Road");
	}

	public void setNoOfTyres(int noOfTyres) {
		this.noOfTyres = noOfTyres;
	}

	public int getNoOfTyres() {
		return noOfTyres;
	}

	public void setMfgyear(int mfgyear) {
		Mfgyear = mfgyear;
	}

	public int getMfgyear() {
		return Mfgyear;
	}

}

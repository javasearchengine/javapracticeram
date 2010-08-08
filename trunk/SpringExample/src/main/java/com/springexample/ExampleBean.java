package com.springexample;

public class ExampleBean {
	
	private String samplePropertyExampleBean;

	public void setSamplePropertyExampleBean(String samplePropertyExampleBean) {
		this.samplePropertyExampleBean = samplePropertyExampleBean;
	}

	public String getSamplePropertyExampleBean() {
		return samplePropertyExampleBean;
	}
	
	static class InnerBean{
		
		private String sampleProperty;

		public void setSampleProperty(String sampleProperty) {
			this.sampleProperty = sampleProperty;
		}

		public String getSampleProperty() {
			return sampleProperty;
		}
		
	}


}

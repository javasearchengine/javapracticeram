package com.springexample;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SampleBean {

	private BeanTypeOne beanOne;

	private BeanTypeTwo beanTwo;

	public void init() {

		System.out.println("This is from the init() method");
	}

	@PostConstruct
	public void initAnnotation() {

		System.out.println("This is from the initAnnotation() method");

	}

	public BeanTypeOne getBeanOne() {
		return beanOne;
	}

	public void setBeanOne(BeanTypeOne beanOne) {
		this.beanOne = beanOne;
	}

	public BeanTypeTwo getBeanTwo() {
		return beanTwo;
	}

	public void setBeanTwo(BeanTypeTwo beanTwo) {
		this.beanTwo = beanTwo;
	}

	public void destroy() {
		System.out.println("This is from the destroy() method");
		// System.out.println(5/0);
	}
	
	@PreDestroy
	public void destroyAnnotation() {
		System.out.println("This is from the destroyAnnotation() method");
		// System.out.println(5/0);
	}

}

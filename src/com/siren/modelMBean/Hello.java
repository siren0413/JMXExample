package com.siren.modelMBean;

public class Hello {

	private String name;

	public void printHello() {
		System.out.println("Hello World, " + name);
	}

	public void printHello(String name) {
		System.out.println("Hello Wrold, " + name);
	}

	// getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

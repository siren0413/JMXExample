package com.siren.modelMBean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.modelmbean.RequiredModelMBean;

import com.sun.jdmk.comm.HtmlAdaptorServer;


public class HelloAgent {

	public static void main(String[] args) throws Exception {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		ObjectName helloName = new ObjectName("com.siren.modelMBean:name=HelloWorld");
		//Hello hello = new Hello();
		//HelloDynamic hello = new HelloDynamic();
		RequiredModelMBean hello = ModelMBeanUtils.createModlerMBean();
		server.registerMBean(hello, helloName);
		
		
		ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter");
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		server.registerMBean(adapter, adapterName);
		adapter.start();
		System.out.println("start...");
	}
	
	
}

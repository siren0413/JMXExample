package com.siren.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.siren.dynamicMBean.HelloDynamic;
import com.sun.jdmk.comm.HtmlAdaptorServer;


public class HelloAgent {

	public static void main(String[] args) throws Exception {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		ObjectName helloName = new ObjectName("vincent:name=HelloWorld");
		Hello hello = new Hello();
		//HelloDynamic hello = new HelloDynamic();
		server.registerMBean(hello, helloName);
		
		
		Jack jack = new Jack();
		server.registerMBean(jack, new ObjectName("HelloAgent:name=jack"));
		jack.addNotificationListener(new HelloListener(), null, hello);
		
		ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter");
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		server.registerMBean(adapter, adapterName);
		adapter.start();
		System.out.println("start...");
	}
	
	
}

package com.siren.jmx;

import javax.management.Notification;
import javax.management.NotificationListener;

public class HelloListener implements NotificationListener{

	@Override
	public void handleNotification(Notification notification, Object handback) {
		
		System.out.println("type="+notification.getType());
		System.out.println("source="+notification.getSource());
		System.out.println("seq="+notification.getSequenceNumber());
		System.out.println("send time="+notification.getTimeStamp());
		System.out.println("message="+notification.getMessage());
		if(handback!=null) {
			if(handback instanceof Hello) {
				Hello hello = (Hello)handback;
				hello.printHello(notification.getMessage());
			}
		}
		
		
	}

	
}

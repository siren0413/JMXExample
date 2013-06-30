package com.siren.jmx;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Jack extends NotificationBroadcasterSupport implements JackMBean{

	private int seq = 0;
	
	@Override
	public void hi() {
		Notification n = new Notification("jack.hi", this, ++seq,System.currentTimeMillis(),"Jack");
		sendNotification(n);
	}

	
}

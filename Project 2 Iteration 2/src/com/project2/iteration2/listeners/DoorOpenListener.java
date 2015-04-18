package com.project2.iteration2.listeners;

import com.project2.iteration2.events.DoorOpenEvent;

public interface DoorOpenListener extends RefrigeratorEventListener {
	public void processEvent(DoorOpenEvent event);
	
}

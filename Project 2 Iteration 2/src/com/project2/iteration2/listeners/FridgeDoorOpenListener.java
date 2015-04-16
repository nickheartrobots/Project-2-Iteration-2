package com.project2.iteration2.listeners;

import com.project2.iteration2.events.FridgeDoorOpenEvent;

public interface FridgeDoorOpenListener extends RefrigeratorEventListener {
	public void processEvent(FridgeDoorOpenEvent event);
	
}

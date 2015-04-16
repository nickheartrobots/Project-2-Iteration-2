package com.project2.iteration2.listeners;

import com.project2.iteration2.events.FridgeDoorCloseEvent;

public interface FridgeDoorCloseListener extends RefrigeratorEventListener{
	public void processEvent(FridgeDoorCloseEvent event);

}

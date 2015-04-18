package com.project2.iteration2.listeners;

import com.project2.iteration2.events.DoorCloseEvent;

public interface DoorCloseListener extends RefrigeratorEventListener{
	public void processEvent(DoorCloseEvent event);

}

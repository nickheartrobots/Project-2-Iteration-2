package com.project2.iteration2.listeners;

import com.project2.iteration2.events.DoorOpenEvent;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Listens for DoorOpenEvents
 */
public interface DoorOpenListener extends RefrigeratorEventListener {
	public void processEvent(DoorOpenEvent event);
}

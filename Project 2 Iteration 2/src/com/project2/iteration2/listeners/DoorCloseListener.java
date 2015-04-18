package com.project2.iteration2.listeners;

import com.project2.iteration2.events.DoorCloseEvent;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Listens for DoorCloseEvents
 */
public interface DoorCloseListener extends RefrigeratorEventListener{
	public void processEvent(DoorCloseEvent event);
}

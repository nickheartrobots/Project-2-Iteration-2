package com.project2.iteration2.listeners;

import com.project2.iteration2.events.ClockTickedEvent;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Listens for ClockTickedEvents
 */
public interface ClockTickedListener extends RefrigeratorEventListener{
	public void processEvent(ClockTickedEvent event);
}

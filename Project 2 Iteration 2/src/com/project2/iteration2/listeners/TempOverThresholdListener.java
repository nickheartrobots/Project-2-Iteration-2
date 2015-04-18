package com.project2.iteration2.listeners;

import com.project2.iteration2.events.TempOverThresholdEvent;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Listens for TempOverThresholdEvent's
 */
public interface TempOverThresholdListener extends RefrigeratorEventListener{
	public void processEvent(TempOverThresholdEvent event);
}

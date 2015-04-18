package com.project2.iteration2.listeners;

import com.project2.iteration2.events.TempUnderThresholdEvent;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Listens for TempUnderThresholdEvent
 */
public interface TempUnderThresholdListener extends RefrigeratorEventListener {
	public void processEvent(TempUnderThresholdEvent event);

}

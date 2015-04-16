package com.project2.iteration2.listeners;

import com.project2.iteration2.events.FridgeTempUnderThresholdEvent;

public interface FridgeTempUnderThresholdListener extends RefrigeratorEventListener {
	
	public void processEvent(FridgeTempUnderThresholdEvent event);

}

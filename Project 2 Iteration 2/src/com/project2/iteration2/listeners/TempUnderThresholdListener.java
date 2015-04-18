package com.project2.iteration2.listeners;

import com.project2.iteration2.events.TempUnderThresholdEvent;

public interface TempUnderThresholdListener extends RefrigeratorEventListener {
	
	public void processEvent(TempUnderThresholdEvent event);

}

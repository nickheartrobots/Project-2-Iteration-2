package com.project2.iteration2.listeners;

import com.project2.iteration2.events.FridgeTempOverThresholdEvent;

public interface FridgeTempOverThresholdListener extends RefrigeratorEventListener{
	public void processEvent(FridgeTempOverThresholdEvent event);

}

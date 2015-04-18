package com.project2.iteration2.listeners;

import com.project2.iteration2.events.TempOverThresholdEvent;

public interface TempOverThresholdListener extends RefrigeratorEventListener{
	public void processEvent(TempOverThresholdEvent event);
}

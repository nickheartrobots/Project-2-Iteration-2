package com.project2.iteration2.listeners;

import com.project2.iteration2.events.ClockTickedEvent;

public interface ClockTickedListener {
	public void processEvent(ClockTickedEvent event);
}
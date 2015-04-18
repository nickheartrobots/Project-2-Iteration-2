package com.project2.iteration2.events;
import java.util.EventObject;

import com.project2.iteration2.listeners.RefrigeratorEventListener;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Abstract super class for Refrigerator events for Switchboard implementation
 * of the State Pattern.
 */
public abstract class RefrigeratorEvent extends EventObject {
	public RefrigeratorEvent(Object source) {
		super(source);
	}

	public abstract void connectToListener(RefrigeratorEventListener listener);
}

package com.project2.iteration2.events;
import java.util.EventObject;

import com.project2.iteration2.listeners.RefrigeratorEventListener;


public abstract class RefrigeratorEvent extends EventObject {

	public RefrigeratorEvent(Object source) {
		super(source);
	}

	public abstract void connectToListener(RefrigeratorEventListener listener);
}

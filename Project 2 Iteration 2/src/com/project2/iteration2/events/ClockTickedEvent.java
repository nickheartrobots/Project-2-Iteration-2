package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.ClockTickedListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;


public class ClockTickedEvent extends RefrigeratorEvent {
	public ClockTickedEvent(Object source) {
		super(source);
	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((ClockTickedListener) listener).processEvent(this);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}
		
	}

}

package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.TempOverThresholdListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Event created when Temp exceeds threshold.
 */
public class TempOverThresholdEvent extends RefrigeratorEvent{

	public TempOverThresholdEvent(Object source) {
		super(source);
	}

	/**
	 * passes the request to the listener passing in itself.
	 * 
	 * */
	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((TempOverThresholdListener) listener).processEvent(this);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}
	}
}

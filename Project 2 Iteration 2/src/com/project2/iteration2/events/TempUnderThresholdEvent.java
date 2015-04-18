package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.TempUnderThresholdListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Event created when the Temp is below the Threshold
 */
public class TempUnderThresholdEvent extends RefrigeratorEvent{

	public TempUnderThresholdEvent(Object source) {
		super(source);

	}

	/**
	 * passes the request to the listener passing in itself.
	 * 
	 */
	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((TempUnderThresholdListener) listener).processEvent(this);
		}catch(ClassCastException cce){

			cce.printStackTrace();
		}
	}
}

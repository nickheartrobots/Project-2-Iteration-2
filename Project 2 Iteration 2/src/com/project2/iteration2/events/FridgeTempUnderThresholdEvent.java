package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.FridgeTempUnderThresholdListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;


public class FridgeTempUnderThresholdEvent extends RefrigeratorEvent{

	public FridgeTempUnderThresholdEvent(Object source) {
		super(source);

	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((FridgeTempUnderThresholdListener) listener).processEvent(this);
		}catch(ClassCastException cce){

			cce.printStackTrace();
		}
	}

}

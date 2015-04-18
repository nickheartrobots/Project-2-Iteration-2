package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.TempUnderThresholdListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;


public class TempUnderThresholdEvent extends RefrigeratorEvent{

	public TempUnderThresholdEvent(Object source) {
		super(source);

	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((TempUnderThresholdListener) listener).processEvent(this);
		}catch(ClassCastException cce){

			cce.printStackTrace();
		}
	}

}

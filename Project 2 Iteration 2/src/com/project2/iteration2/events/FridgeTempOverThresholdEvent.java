package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.FridgeTempOverThresholdListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;


public class FridgeTempOverThresholdEvent extends RefrigeratorEvent{

	public FridgeTempOverThresholdEvent(Object source) {
		super(source);
	}

		@Override
		public void connectToListener(RefrigeratorEventListener listener) {
			try{
				((FridgeTempOverThresholdListener) listener).processEvent(this);
			}catch(ClassCastException cce){
				cce.printStackTrace();
			}
		
	}

}

package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.TempOverThresholdListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;


public class TempOverThresholdEvent extends RefrigeratorEvent{

	public TempOverThresholdEvent(Object source) {
		super(source);
	}

		@Override
		public void connectToListener(RefrigeratorEventListener listener) {
			try{
				((TempOverThresholdListener) listener).processEvent(this);
			}catch(ClassCastException cce){
				cce.printStackTrace();
			}
		
	}

}

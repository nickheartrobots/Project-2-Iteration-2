package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.FridgeDoorOpenListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;



public class FridgeDoorOpenEvent extends RefrigeratorEvent{

	public FridgeDoorOpenEvent(Object source) {
		super(source);
	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((FridgeDoorOpenListener) listener).processEvent(this);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}
		
	}

}



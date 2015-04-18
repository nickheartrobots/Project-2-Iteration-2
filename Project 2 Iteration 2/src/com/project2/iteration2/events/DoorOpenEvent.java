package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.DoorOpenListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;



public class DoorOpenEvent extends RefrigeratorEvent{

	public DoorOpenEvent(Object source) {
		super(source);
	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((DoorOpenListener) listener).processEvent(this);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}
		
	}

}



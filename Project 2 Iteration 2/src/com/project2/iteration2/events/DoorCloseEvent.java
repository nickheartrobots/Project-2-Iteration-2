package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.DoorCloseListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;


public class DoorCloseEvent extends RefrigeratorEvent{

	public DoorCloseEvent(Object source) {
		super(source);
	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((DoorCloseListener) listener).processEvent(this);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}
		
	}

}

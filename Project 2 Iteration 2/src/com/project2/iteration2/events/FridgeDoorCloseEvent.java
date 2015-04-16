package com.project2.iteration2.events;
import javax.swing.JOptionPane;

import com.project2.iteration2.listeners.FridgeDoorCloseListener;
import com.project2.iteration2.listeners.RefrigeratorEventListener;


public class FridgeDoorCloseEvent extends RefrigeratorEvent{

	public FridgeDoorCloseEvent(Object source) {
		super(source);
	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((FridgeDoorCloseListener) listener).processEvent(this);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}
		
	}

}

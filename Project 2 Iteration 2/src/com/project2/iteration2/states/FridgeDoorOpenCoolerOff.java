package com.project2.iteration2.states;

import com.project2.iteration2.GUI;
import com.project2.iteration2.events.ClockTickedEvent;
import com.project2.iteration2.events.FridgeDoorCloseEvent;
import com.project2.iteration2.events.FridgeDoorOpenEvent;
import com.project2.iteration2.events.FridgeTempOverThresholdEvent;
import com.project2.iteration2.listeners.ClockTickedListener;
import com.project2.iteration2.listeners.FridgeDoorCloseListener;
import com.project2.iteration2.listeners.FridgeDoorOpenListener;
import com.project2.iteration2.listeners.FridgeTempOverThresholdListener;

public class FridgeDoorOpenCoolerOff extends RefrigeratorState implements 
	FridgeDoorCloseListener, FridgeTempOverThresholdListener, FridgeDoorOpenListener, ClockTickedListener{

	private static FridgeDoorOpenCoolerOff instance;
	
	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorOpenCoolerOff(){
	}
	
	public static FridgeDoorOpenCoolerOff instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenCoolerOff();
		}
		return instance;
	}
	
	@Override
	public void processEvent(FridgeTempOverThresholdEvent event) {
		context.changeCurrentState(FridgeDoorOpenCoolerOn.instance());
		
	}

	@Override
	public void processEvent(FridgeDoorCloseEvent event) {
		context.changeCurrentState(FridgeDoorClosedCoolerOff.instance());
		
	}

	@Override
	public void run() {
		display.turnFridgeLightOn();
		display.turnFridgeCoolerOff();
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
		
	}
	
	@Override
	public void processEvent(FridgeDoorOpenEvent event) {
		//do nothing
		
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFridgeTemp(context.getFridgeTemp() + ((float) 1/(float) fridgeUp1DoorClosed));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
	
		if(context.getFridgeTemp() > fridgeHigh && context.getFridgeTemp() - fridgeHigh >= tempDiffToStartCoolFridge){
			context.handleEvent(new FridgeTempOverThresholdEvent(display));
		}	
	}

}

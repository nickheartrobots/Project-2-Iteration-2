package com.project2.iteration2.states;

import com.project2.iteration2.GUI;
import com.project2.iteration2.events.ClockTickedEvent;
import com.project2.iteration2.events.FridgeDoorCloseEvent;
import com.project2.iteration2.events.FridgeDoorOpenEvent;
import com.project2.iteration2.events.FridgeTempUnderThresholdEvent;
import com.project2.iteration2.listeners.ClockTickedListener;
import com.project2.iteration2.listeners.FridgeDoorCloseListener;
import com.project2.iteration2.listeners.FridgeDoorOpenListener;
import com.project2.iteration2.listeners.FridgeTempUnderThresholdListener;

public class FridgeDoorClosedCoolerOn extends RefrigeratorState implements 
	FridgeTempUnderThresholdListener, FridgeDoorOpenListener, ClockTickedListener, FridgeDoorCloseListener {

	private static FridgeDoorClosedCoolerOn instance;

	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorClosedCoolerOn(){
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static FridgeDoorClosedCoolerOn instance() {
		if (instance == null) {
			instance = new FridgeDoorClosedCoolerOn();
		}
		return instance;
	}

	@Override
	public void run() {
		display.turnFridgeLightOff();
		display.turnFridgeCoolerOn();
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processEvent(FridgeDoorOpenEvent event) {

		context.changeCurrentState(FridgeDoorOpenCoolerOn.instance());
	}

	@Override
	public void processEvent(FridgeTempUnderThresholdEvent event) {
		context.changeCurrentState(FridgeDoorClosedCoolerOff.instance());
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		
		context.setFridgeTemp(context.getFridgeTemp() - ((float) 1 / (float) minutesToCoolFridge1));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
		
		if((context.getFridgeTemp() < fridgeHigh) && (context.getFridgeTemp() - fridgeLow < tempDiffToStartCoolFridge)){
			context.handleEvent(new FridgeTempUnderThresholdEvent(display));
		}	
	}

	@Override
	public void processEvent(FridgeDoorCloseEvent event) {
		//do nothing
	}

}
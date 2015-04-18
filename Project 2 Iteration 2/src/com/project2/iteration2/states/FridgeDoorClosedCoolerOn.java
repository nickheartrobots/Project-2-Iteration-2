package com.project2.iteration2.states;

import com.project2.iteration2.GUI;
import com.project2.iteration2.events.ClockTickedEvent;
import com.project2.iteration2.events.DoorCloseEvent;
import com.project2.iteration2.events.DoorOpenEvent;
import com.project2.iteration2.events.TempUnderThresholdEvent;
import com.project2.iteration2.listeners.ClockTickedListener;
import com.project2.iteration2.listeners.DoorCloseListener;
import com.project2.iteration2.listeners.DoorOpenListener;
import com.project2.iteration2.listeners.TempUnderThresholdListener;

public class FridgeDoorClosedCoolerOn extends AbsFridgeState implements 
	TempUnderThresholdListener, DoorOpenListener, ClockTickedListener, DoorCloseListener {

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
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp());
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processEvent(DoorOpenEvent event) {

		context.changeFridgeCurrentState(FridgeDoorOpenCoolerOn.instance());
	}

	@Override
	public void processEvent(TempUnderThresholdEvent event) {
		context.changeFridgeCurrentState(FridgeDoorClosedCoolerOff.instance());
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFridgeTemp(context.getFridgeTemp() - ((float) 1 / (float) timeCool));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp());
		
		if((context.getFridgeTemp() < upperThreshold) && (context.getFridgeTemp() - lowerThreshold < tempDiffToStartCool)){
			context.handleFridgeEvent(new TempUnderThresholdEvent(this));
		}	
	}

	@Override
	public void processEvent(DoorCloseEvent event) {
		//do nothing
	}

}
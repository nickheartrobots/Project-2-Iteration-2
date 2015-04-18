package com.project2.iteration2.states;

import com.project2.iteration2.GUI;
import com.project2.iteration2.events.ClockTickedEvent;
import com.project2.iteration2.events.DoorCloseEvent;
import com.project2.iteration2.events.TempUnderThresholdEvent;
import com.project2.iteration2.listeners.ClockTickedListener;
import com.project2.iteration2.listeners.DoorCloseListener;
import com.project2.iteration2.listeners.TempUnderThresholdListener;

public class FridgeDoorOpenCoolerOn extends AbsFridgeState implements 
	DoorCloseListener, TempUnderThresholdListener, ClockTickedListener{
	private static FridgeDoorOpenCoolerOn instance;

	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorOpenCoolerOn(){
	}
	
	public static FridgeDoorOpenCoolerOn instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenCoolerOn();
		}
		return instance;
	}
	
	@Override
	public void processEvent(TempUnderThresholdEvent event) {
		context.changeFridgeCurrentState(FridgeDoorOpenCoolerOff.instance());
		
	}

	@Override
	public void processEvent(DoorCloseEvent event) {
		context.changeFridgeCurrentState(FridgeDoorClosedCoolerOn.instance());
		
	}

	@Override
	public void run() {
		display.turnFridgeCoolerOn();
		display.turnFridgeLightOn();
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp());
		
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFridgeTemp(context.getFridgeTemp() - ((float) 1 / (float) timeCool));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp());
		
		if((context.getFridgeTemp() < upperThreshold) && (context.getFridgeTemp() - lowerThreshold < tempDiffToStartCool)){
			context.handleFridgeEvent(new TempUnderThresholdEvent(display));
		}	
		
	}
}

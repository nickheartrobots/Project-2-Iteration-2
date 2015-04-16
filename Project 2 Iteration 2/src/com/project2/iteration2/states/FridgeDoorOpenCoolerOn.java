package com.project2.iteration2.states;

import com.project2.iteration2.GUI;
import com.project2.iteration2.events.ClockTickedEvent;
import com.project2.iteration2.events.FridgeDoorCloseEvent;
import com.project2.iteration2.events.FridgeTempUnderThresholdEvent;
import com.project2.iteration2.listeners.ClockTickedListener;
import com.project2.iteration2.listeners.FridgeDoorCloseListener;
import com.project2.iteration2.listeners.FridgeTempUnderThresholdListener;

public class FridgeDoorOpenCoolerOn extends AbsFridgeState implements 
	FridgeDoorCloseListener, FridgeTempUnderThresholdListener, ClockTickedListener{
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
	public void processEvent(FridgeTempUnderThresholdEvent event) {
		context.changeCurrentState(FridgeDoorOpenCoolerOff.instance());
		
	}

	@Override
	public void processEvent(FridgeDoorCloseEvent event) {
		context.changeCurrentState(FridgeDoorClosedCoolerOn.instance());
		
	}

	@Override
	public void run() {
		display.turnFridgeCoolerOn();
		display.turnFridgeLightOn();
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
		
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFridgeTemp(context.getFridgeTemp() - ((float) 1 / (float) timeCool));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
		
		if((context.getFridgeTemp() < upperThreshold) && (context.getFridgeTemp() - lowerThreshold < tempDiffToStartCool)){
			context.handleEvent(new FridgeTempUnderThresholdEvent(display));
		}	
		
	}




}

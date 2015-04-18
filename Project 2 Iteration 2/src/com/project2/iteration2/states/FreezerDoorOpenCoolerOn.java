package com.project2.iteration2.states;

import com.project2.iteration2.GUI;
import com.project2.iteration2.events.ClockTickedEvent;
import com.project2.iteration2.events.DoorCloseEvent;
import com.project2.iteration2.events.TempUnderThresholdEvent;
import com.project2.iteration2.listeners.ClockTickedListener;
import com.project2.iteration2.listeners.DoorCloseListener;
import com.project2.iteration2.listeners.TempUnderThresholdListener;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * State representing the operation of the Freezer when the Door is Open and the
 * Cooler is On.
 */
public class FreezerDoorOpenCoolerOn extends AbsFreezerState implements 
	DoorCloseListener, TempUnderThresholdListener, ClockTickedListener{
	private static FreezerDoorOpenCoolerOn instance;

	/**
	 * Private to make it a singleton
	 */
	private FreezerDoorOpenCoolerOn(){
	}
	
	/**
	 * For Singleton
	 * 
	 * @return only instance of FreezerDoorOpenCoolerOff
	 */
	public static FreezerDoorOpenCoolerOn instance() {
		if (instance == null) {
			instance = new FreezerDoorOpenCoolerOn();
		}
		return instance;
	}
	
	@Override
	public void processEvent(TempUnderThresholdEvent event) {
		context.changeFreezerCurrentState(FreezerDoorOpenCoolerOff.instance());
		
	}

	@Override
	public void processEvent(DoorCloseEvent event) {
		context.changeFreezerCurrentState(FreezerDoorClosedCoolerOn.instance());
		
	}

	@Override
	public void run() {
		display.turnFreezerCoolerOn();
		display.turnFreezerLightOn();
		((GUI)display).setFreezerTempLbl(context.getFreezerTemp());
		
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFreezerTemp(context.getFreezerTemp() - ((float) 1 / (float) timeCool));
		((GUI)display).setFreezerTempLbl(context.getFreezerTemp());
		
		if((context.getFreezerTemp() < upperThreshold) && (context.getFreezerTemp() - lowerThreshold < tempDiffToStartCool)){
			context.handleFreezerEvent(new TempUnderThresholdEvent(this));
		}	
		
	}
}

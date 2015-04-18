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

public class FreezerDoorClosedCoolerOn extends AbsFreezerState implements 
	TempUnderThresholdListener, DoorOpenListener, ClockTickedListener, DoorCloseListener {

	private static FreezerDoorClosedCoolerOn instance;

	/**
	 * Private to make it a singleton
	 */
	private FreezerDoorClosedCoolerOn(){
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static FreezerDoorClosedCoolerOn instance() {
		if (instance == null) {
			instance = new FreezerDoorClosedCoolerOn();
		}
		return instance;
	}

	@Override
	public void run() {
		display.turnFreezerLightOff();
		display.turnFreezerCoolerOn();
		((GUI)display).setFreezerTempLbl(context.getFreezerTemp());
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processEvent(DoorOpenEvent event) {

		context.changeFreezerCurrentState(FreezerDoorOpenCoolerOn.instance());
	}

	@Override
	public void processEvent(TempUnderThresholdEvent event) {
		context.changeFreezerCurrentState(FreezerDoorClosedCoolerOff.instance());
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFreezerTemp(context.getFreezerTemp() - ((float) 1 / (float) timeCool));
		((GUI)display).setFreezerTempLbl(context.getFreezerTemp());
		
		if((context.getFreezerTemp() < upperThreshold) && (context.getFreezerTemp() - lowerThreshold < tempDiffToStartCool)){
			context.handleFreezerEvent(new TempUnderThresholdEvent(this));
		}	
	}

	@Override
	public void processEvent(DoorCloseEvent event) {
		//do nothing
	}
}
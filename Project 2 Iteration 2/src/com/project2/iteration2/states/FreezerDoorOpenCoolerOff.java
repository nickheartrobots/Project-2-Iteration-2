package com.project2.iteration2.states;

import com.project2.iteration2.GUI;
import com.project2.iteration2.events.ClockTickedEvent;
import com.project2.iteration2.events.DoorCloseEvent;
import com.project2.iteration2.events.DoorOpenEvent;
import com.project2.iteration2.events.TempOverThresholdEvent;
import com.project2.iteration2.listeners.ClockTickedListener;
import com.project2.iteration2.listeners.DoorCloseListener;
import com.project2.iteration2.listeners.DoorOpenListener;
import com.project2.iteration2.listeners.TempOverThresholdListener;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * State representing the operation of the Freezer when the Door is Open and the
 * Cooler is Off.
 */
public class FreezerDoorOpenCoolerOff extends AbsFreezerState implements 
	DoorCloseListener, TempOverThresholdListener, DoorOpenListener, ClockTickedListener{

	private static FreezerDoorOpenCoolerOff instance;
	
	/**
	 * Private to make it a singleton
	 */
	private FreezerDoorOpenCoolerOff(){
	}
	
	/**
	 * For Singleton
	 * 
	 * @return only instance of FreezerDoorOpenCoolerOff
	 */
	public static FreezerDoorOpenCoolerOff instance() {
		if (instance == null) {
			instance = new FreezerDoorOpenCoolerOff();
		}
		return instance;
	}
	
	@Override
	public void processEvent(TempOverThresholdEvent event) {
		context.changeFreezerCurrentState(FreezerDoorOpenCoolerOn.instance());
		
	}

	@Override
	public void processEvent(DoorCloseEvent event) {
		context.changeFreezerCurrentState(FreezerDoorClosedCoolerOff.instance());
		
	}

	@Override
	public void run() {
		display.turnFreezerLightOn();
		display.turnFreezerCoolerOff();
		((GUI)display).setFreezerTempLbl(context.getFreezerTemp());
		
	}
	
	@Override
	public void processEvent(DoorOpenEvent event) {
		//do nothing
		
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFreezerTemp(context.getFreezerTemp() + ((float) 1/(float) timeTempRiseDoorClosed));
		((GUI)display).setFreezerTempLbl(context.getFreezerTemp());
	
		if(context.getFreezerTemp() > upperThreshold && context.getFreezerTemp() - upperThreshold >= tempDiffToStartCool){
			context.handleFreezerEvent(new TempOverThresholdEvent(this));
		}	
	}
}

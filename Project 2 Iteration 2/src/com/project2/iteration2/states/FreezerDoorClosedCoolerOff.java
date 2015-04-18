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
 * State representing the operation of the Freezer when the Door is Closed and the
 * Cooler is Off.
 */
public class FreezerDoorClosedCoolerOff extends AbsFreezerState 
	implements DoorOpenListener, TempOverThresholdListener, ClockTickedListener, DoorCloseListener{

	private static FreezerDoorClosedCoolerOff instance;
	
	/**
	 * Private to make it a singleton
	 */
	private FreezerDoorClosedCoolerOff(){
	}
	
	/**
	 * For Singleton
	 * 
	 * @return only instance of FreezerDoorOpenCoolerOff
	 */
	public static FreezerDoorClosedCoolerOff instance() {
		if (instance == null) {
			instance = new FreezerDoorClosedCoolerOff();			
		}
		return instance;
	}
	
	@Override
	public void run() {
		display.turnFreezerLightOff();
		display.turnFreezerCoolerOff();
		((GUI)display).setFreezerTempLbl(context.getFreezerTemp());
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * handle door open event
	 * 
	 */
	@Override
	public void processEvent(DoorOpenEvent event) {
		context.changeFreezerCurrentState(FreezerDoorOpenCoolerOff.instance());
	}
	
	/**
	 * 
	 * handle door close event, since people push buttons just to see what they do
	 */
	@Override
	public void processEvent(DoorCloseEvent event) {
		//do nothing
		
	}

	/**
	 * handle temp goes over threshold event
	 * 
	 */
	@Override
	public void processEvent(TempOverThresholdEvent event) {
		context.changeFreezerCurrentState(FreezerDoorClosedCoolerOn.instance());
		
	}
	
	/**
	 * handle clockTicked even
	 */
	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFreezerTemp(context.getFridgeTemp() + ((float) 1/(float) timeTempRiseDoorClosed));
		((GUI)display).setFreezerTempLbl(context.getFridgeTemp());

		if(context.getFreezerTemp() > upperThreshold && context.getFridgeTemp() - upperThreshold >= tempDiffToStartCool){
			context.handleFreezerEvent(new TempOverThresholdEvent(this));
		}	
	}
}

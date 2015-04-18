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

public class FridgeDoorClosedCoolerOff extends AbsFridgeState 
	implements DoorOpenListener, TempOverThresholdListener, ClockTickedListener, DoorCloseListener{

	private static FridgeDoorClosedCoolerOff instance;
	
	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorClosedCoolerOff(){
	}
	
	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static FridgeDoorClosedCoolerOff instance() {
		if (instance == null) {
			instance = new FridgeDoorClosedCoolerOff();			
		}
		return instance;
	}
	
	@Override
	public void run() {
		display.turnFridgeLightOff();
		display.turnFridgeCoolerOff();
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp());
	}

	@Override
	public void leave() {
	}
	
	/**
	 * handle door open event
	 * 
	 */
	@Override
	public void processEvent(DoorOpenEvent event) {
		context.changeFridgeCurrentState(FridgeDoorOpenCoolerOff.instance());
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
		context.changeFridgeCurrentState(FridgeDoorClosedCoolerOn.instance());
		
	}
	
	/**
	 * handle clockTicked even
	 */
	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFridgeTemp(context.getFridgeTemp() + ((float) 1/(float) timeTempRiseDoorClosed));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp());
	
		if(context.getFridgeTemp() > upperThreshold && context.getFridgeTemp() - upperThreshold >= tempDiffToStartCool){
			context.handleFridgeEvent(new TempOverThresholdEvent(this));
		}	
	}
}
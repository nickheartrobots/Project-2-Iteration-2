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
 * State representing the operation of the Fridge when the Door is Open and the
 * Cooler is Off.
 */
public class FridgeDoorOpenCoolerOff extends AbsFridgeState implements 
	DoorCloseListener, TempOverThresholdListener, DoorOpenListener, ClockTickedListener{

	private static FridgeDoorOpenCoolerOff instance;
	
	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorOpenCoolerOff(){
	}
	
	/**
	 * For Singleton
	 * 
	 * @return only instance of FreezerDoorOpenCoolerOff
	 */
	public static FridgeDoorOpenCoolerOff instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenCoolerOff();
		}
		return instance;
	}
	
	@Override
	public void processEvent(TempOverThresholdEvent event) {
		context.changeFridgeCurrentState(FridgeDoorOpenCoolerOn.instance());
	}

	@Override
	public void processEvent(DoorCloseEvent event) {
		context.changeFridgeCurrentState(FridgeDoorClosedCoolerOff.instance());
	}

	@Override
	public void run() {
		display.turnFridgeLightOn();
		display.turnFridgeCoolerOff();
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp());
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
		context.setFridgeTemp(context.getFridgeTemp() + ((float) 1/(float) timeTempRiseDoorClosed));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp());
	
		if(context.getFridgeTemp() > upperThreshold && context.getFridgeTemp() - upperThreshold >= tempDiffToStartCool){
			context.handleFridgeEvent(new TempOverThresholdEvent(this));
		}	
	}
}

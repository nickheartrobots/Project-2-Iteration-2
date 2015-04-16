package com.project2.iteration2.states;

import com.project2.iteration2.GUI;
import com.project2.iteration2.events.ClockTickedEvent;
import com.project2.iteration2.events.FridgeDoorCloseEvent;
import com.project2.iteration2.events.FridgeDoorOpenEvent;
import com.project2.iteration2.events.FridgeTempOverThresholdEvent;
import com.project2.iteration2.listeners.ClockTickedListener;
import com.project2.iteration2.listeners.FridgeDoorCloseListener;
import com.project2.iteration2.listeners.FridgeDoorOpenListener;
import com.project2.iteration2.listeners.FridgeTempOverThresholdListener;

public class FridgeDoorClosedCoolerOff extends AbsFridgeState 
	implements FridgeDoorOpenListener, FridgeTempOverThresholdListener, ClockTickedListener, FridgeDoorCloseListener{

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
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
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
	public void processEvent(FridgeDoorOpenEvent event) {

		context.changeCurrentState(FridgeDoorOpenCoolerOff.instance());
	}
	
	/**
	 * 
	 * handle door close event, since people push buttons just to see what they do
	 */


	@Override
	public void processEvent(FridgeDoorCloseEvent event) {
		//do nothing
		
	}

	/**
	 * handle temp goes over threshold event
	 * 
	 */

	@Override
	public void processEvent(FridgeTempOverThresholdEvent event) {
		context.changeCurrentState(FridgeDoorClosedCoolerOn.instance());
		
	}
	
	/**
	 * handle clockTicked even
	 */

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFridgeTemp(context.getFridgeTemp() + ((float) 1/(float) timeRiseDoorClosed));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");

	
		if(context.getFridgeTemp() > upperThreshold && context.getFridgeTemp() - upperThreshold >= tempDiffToStartCool){
			context.handleEvent(new FridgeTempOverThresholdEvent(display));
		}	
	}

}

package com.project2.iteration2.states;

import com.project2.iteration2.FileDecoder;
import com.project2.iteration2.RefrigeratorContext;
import com.project2.iteration2.RefrigeratorDisplay;

public abstract class AbsFridgeState {
	
	protected static RefrigeratorContext context;
	protected static RefrigeratorDisplay display;
	protected static int lowerThreshold; 
	protected static int upperThreshold; 
	protected static int timeTempRiseDoorClosed;
	protected static int timeTempRiseDoorOpen;  
	protected static int timeCool;
	protected static int tempDiffToStartCool;

	static{
		lowerThreshold = RefrigeratorContext.fridgeLowerThreshold;
		upperThreshold = RefrigeratorContext.fridgeUpperThreshold;
		timeTempRiseDoorClosed = RefrigeratorContext.fridgeTimeTempRiseDoorClosed;
		timeTempRiseDoorOpen = RefrigeratorContext.fridgeTimeTempRiseDoorOpen;
		timeCool = RefrigeratorContext.timeToCoolFridge;
		tempDiffToStartCool = RefrigeratorContext.tempDiffToStartCoolFridge;
	}
	
	/**
	 * Initializes the context and display
	 */
	protected AbsFridgeState() {
		context = RefrigeratorContext.instance();
		display = context.getDisplay();
	}
	
	/**
	 * Initializes the state
	 */
	public abstract void run();

	/**
	 * When the Refrigerator leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	public abstract void leave();
}

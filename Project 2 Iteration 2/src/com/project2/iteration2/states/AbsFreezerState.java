package com.project2.iteration2.states;

import com.project2.iteration2.RefrigeratorContext;
import com.project2.iteration2.RefrigeratorDisplay;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Abstract super class for States related to Freezer.
 */
public abstract class AbsFreezerState {
	// static fields for config information related to all subclasses
	protected static RefrigeratorContext context;
	protected static RefrigeratorDisplay display;
	protected static int lowerThreshold; 
	protected static int upperThreshold; 
	protected static int timeTempRiseDoorClosed;
	protected static int timeTempRiseDoorOpen;  
	protected static int timeCool;
	protected static int tempDiffToStartCool;

	// Static constructor to initialize static variables.
	static{
		lowerThreshold = RefrigeratorContext.freezerLowerThreshold;
		upperThreshold = RefrigeratorContext.freezerUpperThreshold;
		timeTempRiseDoorClosed = RefrigeratorContext.freezerTimeTempRiseDoorClosed;
		timeTempRiseDoorOpen = RefrigeratorContext.freezerTimeTempRiseDoorOpen;
		timeCool = RefrigeratorContext.timeToCoolFreezer;
		tempDiffToStartCool = RefrigeratorContext.tempDiffToStartCoolFreezer;
	}
	
	/**
	 * Initializes the context and display
	 */
	protected AbsFreezerState() {
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

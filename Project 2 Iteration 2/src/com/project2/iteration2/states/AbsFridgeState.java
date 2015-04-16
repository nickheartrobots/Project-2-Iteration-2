package com.project2.iteration2.states;

import com.project2.iteration2.RefrigeratorContext;
import com.project2.iteration2.RefrigeratorDisplay;

public abstract class AbsFridgeState {
	
	protected static RefrigeratorContext context;
	protected static RefrigeratorDisplay display;
	protected static int lowerThreshold; 
	protected static int upperThreshold; 
	protected static int timeRiseDoorClosed;
	protected static int timeRiseDoorOpen;  
	protected static int timeCool;
	protected static int tempDiffToStartCool;

	/**
	 * Initializes the context and display
	 */
	protected AbsFridgeState() {
		context = RefrigeratorContext.instance();
		display = context.getDisplay();
	}
	
	public static void initialize(){
		int[] fridgeData = context.getFridgeData();
		lowerThreshold = fridgeData[0];
		upperThreshold = fridgeData[1];
		timeRiseDoorClosed = fridgeData[2];
		timeRiseDoorOpen = fridgeData[3];
		tempDiffToStartCool = fridgeData[4];
		timeCool = fridgeData[5];
		for(int i = 0; i < fridgeData.length; i++){
			System.out.println("fridgeData "+ fridgeData[i]);
		}
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

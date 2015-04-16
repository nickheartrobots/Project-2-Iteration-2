package com.project2.iteration2.states;

import com.project2.iteration2.RefrigeratorContext;
import com.project2.iteration2.RefrigeratorDisplay;

public abstract class RefrigeratorState {
	
	protected static RefrigeratorContext context;
	protected static RefrigeratorDisplay display;
	protected static int fridgeLow; 
	protected static int fridgeHigh; 
	protected static int fridgeUp1DoorClosed;
	protected static int fridgeUp1DoorOpen;  
	protected static int tempDiffToStartCoolFridge;
	protected static int minutesToCoolFridge1;

	/**
	 * Initializes the context and display
	 */
	protected RefrigeratorState() {
		context = RefrigeratorContext.instance();
		display = context.getDisplay();
	}
	
	public static void initialize(){
		int[] fridgeData = context.getFridgeData();
		fridgeLow = fridgeData[0];
		fridgeHigh = fridgeData[1];
		fridgeUp1DoorClosed = fridgeData[2];
		fridgeUp1DoorOpen = fridgeData[3];
		tempDiffToStartCoolFridge = fridgeData[4];
		minutesToCoolFridge1 = fridgeData[5];
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

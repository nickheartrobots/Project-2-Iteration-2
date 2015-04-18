package com.project2.iteration2;

import com.project2.iteration2.events.RefrigeratorEvent;
import com.project2.iteration2.listeners.RefrigeratorEventListener;
import com.project2.iteration2.states.AbsFreezerState;
import com.project2.iteration2.states.FreezerDoorClosedCoolerOff;
import com.project2.iteration2.states.FridgeDoorClosedCoolerOff;
import com.project2.iteration2.states.AbsFridgeState;

public class RefrigeratorContext {
	private static RefrigeratorDisplay refrigeratorDisplay;

	private AbsFridgeState fridgeState;
	private AbsFreezerState freezerState;
	
	private static RefrigeratorContext instance;

	private float roomTemp;
	private float fridgeTemp;
	private float freezerTemp;
	
	// config file variables
	public static int fridgeLowerThreshold; 
	public static int fridgeUpperThreshold; 
	public static int freezerLowerThreshold;
	public static int freezerUpperThreshold; 
	public static int roomLow;
	public static int roomHigh; 
	public static int fridgeTimeTempRiseDoorClosed;
	public static int fridgeTimeTempRiseDoorOpen;  
	public static int freezerTimeTempRiseDoorClosed; 
	public static int freezerTimeTempRiseDoorOpen;
	public static int tempDiffToStartCoolFridge;
	public static int tempDiffToStartCoolFreezer;
	public static int timeToCoolFridge;
	public static int timeToCoolFreezer;
	
	/**
	 * Make it a singleton
	 */
	private RefrigeratorContext() {
		instance = this;
		refrigeratorDisplay = GUI.instance();
	}

	/**
	 * Return the instance
	 * 
	 * @return the object
	 */
	public static RefrigeratorContext instance() {
		if (instance == null) {
			instance = new RefrigeratorContext();
		}
		return instance;
	}

	/**
	 * lets door closed state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		fridgeState = FridgeDoorClosedCoolerOff.instance();
		freezerState = FreezerDoorClosedCoolerOff.instance();

		fridgeTemp = (roomHigh + roomLow)/2;
		freezerTemp = roomLow/2;

		fridgeState.run();
		freezerState.run();
	}

	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState
	 *            the next state
	 */
	public void changeFridgeCurrentState(AbsFridgeState nextState) {
		fridgeState = nextState;
		nextState.run();
	}

	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState
	 *            the next state
	 */
	public void changeFreezerCurrentState(AbsFreezerState nextState) {
		freezerState = nextState;
		nextState.run();
	}

	/**
	 * Gets the display
	 * 
	 * @return the display
	 */
	public RefrigeratorDisplay getDisplay() {
		return refrigeratorDisplay;
	}
	
	/**
	 * handler method used for routing events related to fridge
	 * @param event
	 */
	public void handleFridgeEvent(RefrigeratorEvent event){
		try{
			event.connectToListener((RefrigeratorEventListener) fridgeState);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}	
	}
	
	/**
	 * handler method used for routing events related to freezer
	 * @param event
	 */
	public void handleFreezerEvent(RefrigeratorEvent event){
		try{
			event.connectToListener((RefrigeratorEventListener) freezerState);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}	
	}
	
	/**
	 * Getter for fridge temp
	 * @return float
	 */
	public float getFridgeTemp(){
		return fridgeTemp;
	}
	
	/**
	 * Getter for freezer temp
	 * @return float
	 */
	public float getFreezerTemp(){
		return freezerTemp;
	}
	
	/**
	 * Setter for fridge temp
	 * @param temp
	 */
	public void setFridgeTemp(float temp){
		fridgeTemp = temp;
	}
	
	/**
	 * Setter for fridge temp
	 * @param temp
	 */
	public void setFreezerTemp(float temp){
		freezerTemp = temp;
	}

	/**
	 * Called by the GUI when the button is pressed.  Sets default temp values
	 * to room temp based on 1) from Miscellaneous in the spec.
	 * @param temp - Room temp
	 */
	public void setRoomTemp(float temp){
		// verifies in range
		if (temp <= roomHigh && temp >= roomLow){
			fridgeTemp = temp;
			freezerTemp = temp;
			roomTemp = temp;
			((GUI) refrigeratorDisplay).setRoomFieldText("");
			((GUI) refrigeratorDisplay).setErrorLbl("");
			((GUI) refrigeratorDisplay).setErrorLblVisible(false);
		} else {
			((GUI) refrigeratorDisplay).setErrorLblVisible(true);
			((GUI) refrigeratorDisplay).setErrorLbl("Temp outside of range. (" + roomLow + " - " + roomHigh + ")");
		}
	}
	
	/**
	 * takes in an int[] that represents the configuration of the refrigerator
	 * @param config
	 */
	public void setConfig(int[] config) {
		fridgeLowerThreshold = config[0]; 
		fridgeUpperThreshold = config[1]; 
		freezerLowerThreshold = config[2];
		freezerUpperThreshold = config[3]; 
		roomLow = config[4];
		roomHigh = config[5]; 
		fridgeTimeTempRiseDoorClosed = config[6];
		fridgeTimeTempRiseDoorOpen = config[7]; 
		freezerTimeTempRiseDoorClosed = config[8]; 
		freezerTimeTempRiseDoorOpen = config[9];
		tempDiffToStartCoolFridge = config[10];
		tempDiffToStartCoolFreezer = config[11];
		timeToCoolFridge = config[12];
		timeToCoolFreezer = config[13];
	}
}

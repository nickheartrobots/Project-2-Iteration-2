package com.project2.iteration2;
import java.util.Observable;
import java.util.Observer;

import com.project2.iteration2.events.ClockTickedEvent;
import com.project2.iteration2.events.RefrigeratorEvent;
import com.project2.iteration2.listeners.RefrigeratorEventListener;
import com.project2.iteration2.states.FridgeDoorClosedCoolerOff;
import com.project2.iteration2.states.AbsFridgeState;


public class RefrigeratorContext implements Observer{
	private static RefrigeratorDisplay refrigeratorDisplay;
	private AbsFridgeState currentState;
	private static RefrigeratorContext instance;

	private float roomTemp;
	private float fridgeTemp;
	private float freezerTemp;
	
	private int[] fridgeData = new int[6];;
	private int[] freezerData = new int[6];
	
	// config file variables

	private int freezerLow;
	private int freezerHigh; 
	private int roomLow;
	private int roomHigh; 
	private int freezerUp1DoorClosed; 
	private int freezerUp1DoorOpen;
	private int tempDifftoStartCoolFreezer;
	private int minutesToCoolFreezer1;
	
	

	/**
	 * Make it a singleton
	 */
	private RefrigeratorContext() {
		instance = this;
		Clock.instance().addObserver(instance);
		refrigeratorDisplay = GUI.instance();
		currentState = FridgeDoorClosedCoolerOff.instance();


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
		instance.changeCurrentState(FridgeDoorClosedCoolerOff.instance());
		currentState.run();
		fridgeTemp = (roomHigh + roomLow)/2;
		freezerTemp = roomLow/2;
		System.out.println(" intialize fridgeTemp " + fridgeTemp);
		
		//sets all input variables in the RefrigeratorState superclass
		currentState.initialize();
		
	}

	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState
	 *            the next state
	 */
	public void changeCurrentState(AbsFridgeState nextState) {
		currentState = nextState;
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
	
	public void handleEvent(RefrigeratorEvent event){
		try{
			event.connectToListener((RefrigeratorEventListener) currentState);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}	
	}
	
	public float getFridgeTemp(){
		return fridgeTemp;
	}
	
	public float getFreezerTemp(){
		return freezerTemp;
	}
	
	public void setFridgeTemp(float temp){
		fridgeTemp = temp;
	}
	
	public void setFreezerTemp(float temp){
		freezerTemp = temp;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("----Tick----");
		handleEvent(new ClockTickedEvent(this));
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
	
	public int[] getFridgeData(){
		return fridgeData;
	}
	
	/**
	 * Method called by GUI to initialized default/config variables.
	 * The ordering is as follows:
	 * 
	 * 		fridgeLow = data[0]; 
			fridgeHigh = data[1]; 
			freezerLow = data[2];
			freezerHigh = data[3]; 
			roomLow = data[4];
			roomHigh = data[5]; 
			fridgeUp1DoorClosed = data[6];
			fridgeUp1DoorOpen = data[7]; 
			freezerUp1DoorClosed = data[8]; 
			freezerUp1DoorOpen = data[9];
			tempDiffToStartCoolFridge = data[10];
			tempDifftoStartCoolFreezer = data[11];
			minutesToCoolFridge1 = data[12];
			minutesToCoolFreezer1 = data[13];
	 * @param data
	 */
	public void setData(int[] data){
		if(data.length == 14){
			roomLow = data[4];
			roomHigh = data[5]; 
			
			
			fridgeData[0] = data[0];
			fridgeData[1] = data[1];
			fridgeData[2] = data[6];
			fridgeData[3] = data[7];
			fridgeData[4] = data[10];
			fridgeData[5] = data[12];	
							
			
			
			freezerLow = data[2];
			freezerHigh = data[3]; 	
			freezerUp1DoorClosed = data[8]; 
			freezerUp1DoorOpen = data[9];
			tempDifftoStartCoolFreezer = data[11];
			minutesToCoolFreezer1 = data[13];
		}
	}

}

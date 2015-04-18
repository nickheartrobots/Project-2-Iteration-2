package com.project2.iteration2;

import com.project2.iteration2.events.DoorCloseEvent;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Represents the fridge door close button
 */
public class FridgeDoorCloseButton extends GUIButton{
	/**
	 * Crates the button with the required label
	 * 
	 * @param string
	 *            the label
	 */
	public FridgeDoorCloseButton(String string) {
		super(string);
	}

	/**
	 * Tell the context to send it to the right listener
	 */
	@Override
	public void inform(RefrigeratorContext context, RefrigeratorDisplay source) {
		context.handleFridgeEvent(new DoorCloseEvent(source));
	}
}

package com.project2.iteration2;

import com.project2.iteration2.events.DoorCloseEvent;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * Represents the freezer door close button
 */
public class FreezerDoorCloseButton extends GUIButton{
	/**
	 * Crates the button with the required label
	 * 
	 * @param string
	 *            the label
	 */
	public FreezerDoorCloseButton(String string) {
		super(string);
	}

	/**
	 * Tell the context to send it to the right listener
	 */
	@Override
	public void inform(RefrigeratorContext context, RefrigeratorDisplay source) {
		context.handleFreezerEvent(new DoorCloseEvent(source));
	}
}

package com.project2.iteration2;

import com.project2.iteration2.events.DoorCloseEvent;

public class FridgeDoorCloseButton extends GUIButton{
	public FridgeDoorCloseButton(String string) {
		super(string);
	}

	@Override
	public void inform(RefrigeratorContext context, RefrigeratorDisplay source) {
		context.handleFridgeEvent(new DoorCloseEvent(source));
	}
}

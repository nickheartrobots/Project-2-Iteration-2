package com.project2.iteration2;

import com.project2.iteration2.events.DoorCloseEvent;

public class FreezerDoorCloseButton extends GUIButton{

	public FreezerDoorCloseButton(String string) {
		super(string);
	}

	@Override
	public void inform(RefrigeratorContext context, RefrigeratorDisplay source) {
		context.handleFreezerEvent(new DoorCloseEvent(source));
	}
}

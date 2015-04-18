package com.project2.iteration2;

import com.project2.iteration2.events.DoorOpenEvent;

public class FreezerDoorOpenButton extends GUIButton{
	
	public FreezerDoorOpenButton(String string){
		super(string);
	}
	
	@Override
	public void inform(RefrigeratorContext context, RefrigeratorDisplay source){
		context.handleFreezerEvent(new DoorOpenEvent(source));
	}
}

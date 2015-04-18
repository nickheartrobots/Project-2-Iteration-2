package com.project2.iteration2;

import com.project2.iteration2.events.DoorOpenEvent;

public class FridgeDoorOpenButton extends GUIButton{
	
	public FridgeDoorOpenButton(String string){
		super(string);
	}
	
	@Override
	public void inform(RefrigeratorContext context, RefrigeratorDisplay source){
		context.handleFridgeEvent(new DoorOpenEvent(source));
	}
}

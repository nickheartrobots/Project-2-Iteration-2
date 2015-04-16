import javax.swing.JOptionPane;


public class FridgeTempUnderThresholdEvent extends RefrigeratorEvent{

	public FridgeTempUnderThresholdEvent(Object source) {
		super(source);

	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((FridgeTempUnderThresholdListener) listener).processEvent(this);
		}catch(ClassCastException cce){

			cce.printStackTrace();
		}
	}

}

package com.project2.iteration2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Nick Clarity Alicia Struble, Maya Gaforova
 * Project 2 Iteration 1
 * Apr 15, 2015
 * 
 * GUI for Project 2 Iteration 2.  Class is titled "Project2Iteration1" because
 * it contains the main method and it will be run from the command line with 
 * that title.
 */
public class GUI extends RefrigeratorDisplay implements ActionListener {
	private RefrigeratorFrame frame;

	private int fridgeHigh;
	private int fridgeLow;
	private int freezerHigh;
	private int freezerLow;

	public static final String FRIDGE_LIGHT_ON = "Fridge light <on>";
	public static final String FRIDGE_LIGHT_OFF = "Fridge light <off>";
	public static final String FREEZER_LIGHT_ON = "Freezer light <on>";
	public static final String FREEZER_LIGHT_OFF = "Freezer light <off>";
	public static final String FRIDGE_COOLING_ON = "Fridge <cooling>";
	public static final String FRIDGE_COOLING_OFF = "Fridge <idle>";
	public static final String FREEZER_COOLING_ON = "Freezer <cooling>";
	public static final String FREEZER_COOLING_OFF = "Freezer <idle>";
	public static final String FRIDGE_TEMP = "Fridge temp";
	public static final String FREEZER_TEMP = "Freezer temp";

	private Point defaultLocation;
	private final int frameWidth = 525;
	private final int frameHeight = 375;

	private JButton openFridgeDoor;
	private JButton closeFridgeDoor;
	private JButton openFreezerDoor;
	private JButton closeFreezerDoor;

	private JButton setRoomTemp;
	private JButton setFridgeTemp;
	private JButton setFreezerTemp;

	private JTextField roomField;
	private JTextField fridgeField;
	private JTextField freezerField;

	private JLabel fridgeCoolingLbl;
	private JLabel fridgeTempLbl;
	private JLabel fridgeLightLbl;
	private JLabel freezerLightLbl;
	private JLabel freezerTempLbl;
	private JLabel freezerCoolingLbl;
	private JLabel errorLbl;

	/**
	 * Constructor with a File param.
	 * @param file is the config File passed in as a commandline arg.
	 */
	public GUI(String path){
		try{
			context.setConfig(FileDecoder.instance().parseFile(path));
		} catch(FileNotFoundException fnfe){
//			FIXME, popup
			System.out.println("File Not Fount");
			System.exit(0);
		}
		
		frame = new RefrigeratorFrame();
		
		context.initialize();

		// for constraints on the GUI input variables
		fridgeHigh = context.fridgeUpperThreshold;
		fridgeLow = context.fridgeLowerThreshold;
		freezerHigh = context.freezerUpperThreshold;
		freezerLow = context.freezerLowerThreshold;
		
		frame.setVisible(true);
		Clock.instance();		//Starts the Clock
	}

	/**
	 * Getter for Fridge Cooling Label text
	 * @return String
	 */
	public String getFridgeCoolingLbl() {
		return fridgeCoolingLbl.getText();
	}

	/**
	 * Sets Fridge Cooling Label to ON
	 * 
	 */
	public void turnFridgeCoolerOn() {
		this.fridgeCoolingLbl.setText(FRIDGE_COOLING_ON);
		frame.repaint();
	}

	/**
	 * Sets Fridge Cooling Label to OFF
	 * 
	 */
	public void turnFridgeCoolerOff() {
		this.fridgeCoolingLbl.setText(FRIDGE_COOLING_OFF);
		frame.repaint();
	}

	/**
	 * Getter for Fridge Temp Label text
	 * @return String
	 */
	public String getFridgeTempLbl() {
		return fridgeTempLbl.getText();
	}

	/**
	 * Sets Fridge light label to OFF
	 * 
	 */
	@Override
	public void turnFridgeLightOff() {
		this.fridgeLightLbl.setText(FRIDGE_LIGHT_OFF);
		frame.repaint();
	}

	/**
	 * Sets Fridge light label to ON 
	 */
	@Override
	public void turnFridgeLightOn() {
		this.fridgeLightLbl.setText(FRIDGE_LIGHT_ON);
		frame.repaint();
	}

	/**
	 * Sets label for Fridge temp, so the simulated temperature is visible
	 * @param string
	 */

	public void setFridgeTempLbl(float temp) {
		fridgeTempLbl.setText("Fridge Temp " + "<" + String.format("%2.3f", temp) + ">");
		frame.repaint();
	}

	/**
	 * Getter for Fridge Light Label text
	 * @return String
	 */
	public String getFridgeLightLbl() {
		return fridgeLightLbl.getText();
	}

	/**
	 * Getter for Freezer Light Label text
	 * @return String
	 */
	public String getFreezerLightLbl() {
		return freezerLightLbl.getText();
	}

	/**
	 * Getter for Freezer Temp Label text
	 * @return String
	 */
	public String getFreezerTempLbl() {
		return freezerTempLbl.getText();
	}

	/**
	 * Setter for Freezer Temp Label
	 * @param freezerTempLbl - String
	 */
	public void setFreezerTempLbl(float temp) {
		this.freezerTempLbl.setText("Freezer Temp " + "<" + String.format("%2.3f", temp) + ">");
		frame.repaint();
	}

	/**
	 * Getter for Freezer Cooling Label text
	 * @return String
	 */
	public String getFreezerCoolingLbl() {
		return freezerCoolingLbl.getText();
	}

	/**
	 * Getter for Error Label text
	 * @return String
	 */
	public String getErrorLbl(){
		return errorLbl.getText();
	}

	/**
	 * Setter for Error Label text
	 * @param string - String
	 */
	public void setErrorLbl(String string){
		this.errorLbl.setText(string);
		frame.repaint();
	}

	/**
	 * Setter for Error Label visibility
	 * @param bool - boolean
	 */
	public void setErrorLblVisible(boolean bool){
		errorLbl.setVisible(bool);
	}

	/**
	 * Getter for Room Field text
	 * @return String
	 */
	public String getRoomFieldText(){
		return roomField.getText();
	}

	/**
	 * Setter for Room Field text
	 * @param string - String
	 */
	public void setRoomFieldText(String string){
		roomField.setText(string);
	}

	/**
	 * Setter for Fridge Field text
	 * @param string - String
	 */
	public void setFridgeFieldText(String string){
		fridgeField.setText(string);
	}

	/**
	 * Setter for Freezer Field text
	 * @param string - String
	 */
	public void setFreezerFieldText(String string){
		freezerField.setText(string);
	}

	/**
	 * Sets Freezer Light Label to ON
	 */
	@Override
	public void turnFreezerLightOn() {
		freezerLightLbl.setText(FREEZER_LIGHT_ON);

	}

	/**
	 * Sets Freezer Light Label to OFF
	 */
	@Override
	public void turnFreezerLightOff() {
		freezerLightLbl.setText(FREEZER_LIGHT_OFF);

	}

	/**
	 * Sets Freezer Cooling Label to OFF
	 */
	@Override
	public void turnFreezerCoolerOn() {
		freezerCoolingLbl.setText(FREEZER_COOLING_ON);

	}

	/**
	 * Sets Freezer Cooling Label to OFF
	 */
	@Override
	public void turnFreezerCoolerOff() {
		freezerCoolingLbl.setText(FREEZER_COOLING_OFF);

	}

	/**
	 * @author Nick Clarity
	 * Project 2 Iteration 2
	 * Apr 17, 2015
	 * 
	 * GUI Frame
	 */
	private class RefrigeratorFrame extends JFrame{
		public RefrigeratorFrame(){
			add(new Panel());

			centerGUI();
			setLocation(defaultLocation);
			setTitle("Refrigerator Dashboard");
			setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			setPreferredSize(new Dimension(frameWidth, frameHeight));
			pack();
		}
		
		/*
		 * Centers the GUI on he screen instead of it appear at the top left corner.
		 */
		private void centerGUI() {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) screenSize.getWidth();
			int height = (int) screenSize.getHeight();

			width /= 2;
			height /= 2;

			defaultLocation = new Point(width - (frameWidth / 2), height
					- (frameHeight / 2));
		}
	}
	
	/**
	 * @author Nick Clarity
	 * Project 2 Iteration 1
	 * Apr 1, 2015
	 * 
	 * Instantiates, Aligns and Adds all components to the screen.
	 */
	private class Panel extends JPanel{
		public Panel(){
			setLayout(null);

			JLabel lblNewLabel = new JLabel("Room Temp:");
			lblNewLabel.setBounds(27, 29, 115, 14);
			add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("Desired Fridge Temp:");
			lblNewLabel_1.setBounds(27, 56, 133, 14);
			add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("Desired Freezer Temp:");
			lblNewLabel_2.setBounds(27, 84, 133, 14);
			add(lblNewLabel_2);

			setRoomTemp = new JButton("Set Room Temp");
			// for verifying input data before passing it to context
			setRoomTemp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try{
						String t1 = roomField.getText();
						float t2 = Float.parseFloat(t1);
						setErrorLbl("");
						setErrorLblVisible(false);

						context.setRoomTemp(t2);
					}catch(NumberFormatException nfe){
						roomField.setText("");

						setErrorLblVisible(true);
						setErrorLbl("Room Temp must be a number.");
					}

				}
			});
			setRoomTemp.setBounds(280, 23, 200, 23);
			add(setRoomTemp);

			setFridgeTemp = new JButton("Set Desired Fridge Temp");
			// for verifying input data before passing it to context
			setFridgeTemp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try{
						String t1 = fridgeField.getText();
						float t2 = Float.parseFloat(t1);
						setErrorLbl("");
						setErrorLblVisible(false);
						if(t2 >= fridgeLow && t2 <= fridgeHigh){
							context.setFridgeTemp(t2);
						}else{
							setErrorLblVisible(true);
							setErrorLbl("Fridge Temp must be between " + fridgeLow + " and " + fridgeHigh + ".");
						}
					}catch(NumberFormatException nfe){
						fridgeField.setText("");
						setErrorLblVisible(true);
						setErrorLbl("Fridge Temp must be a number.");
					}		        	
				}});
			setFridgeTemp.setBounds(280, 52, 200, 23);
			add(setFridgeTemp);

			setFreezerTemp = new JButton("Set Desired Freezer Temp");
			// for verifying input data before passing it to context
			setFreezerTemp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try{
						String t1 = freezerField.getText();
						float t2 = Float.parseFloat(t1);

						setErrorLbl("");
						setErrorLblVisible(false);
						if(t2 >= freezerLow && t2 <= freezerHigh){
							context.setFreezerTemp(t2);
						}else{
							setErrorLblVisible(true);
							setErrorLbl("Freezer Temp must be between " + freezerLow + " and " + freezerHigh + ".");
					}
					}catch(NumberFormatException nfe){
						freezerField.setText("");

						setErrorLblVisible(true);
						setErrorLbl("Freezer Temp must be a number.");
					}
				}
			});
			setFreezerTemp.setBounds(280, 80, 200, 23);
			add(setFreezerTemp);

			roomField = new JTextField();
			roomField.setBounds(190, 24, 50, 20);
			add(roomField);
			roomField.setColumns(10);

			fridgeField = new JTextField();
			fridgeField.setBounds(190, 53, 50, 20);
			add(fridgeField);
			fridgeField.setColumns(10);

			freezerField = new JTextField();
			freezerField.setBounds(190, 81, 50, 20);
			add(freezerField);
			freezerField.setColumns(10);

			openFridgeDoor = new FridgeDoorOpenButton("Open Fridge Door");
			openFridgeDoor.addActionListener(GUI.this);
			openFridgeDoor.setBounds(69, 125, 150, 23);
			add(openFridgeDoor);

			openFreezerDoor = new FreezerDoorOpenButton("Open Freezer Door");
			openFreezerDoor.addActionListener(GUI.this);
			openFreezerDoor.setBounds(69, 159, 150, 23);
			add(openFreezerDoor);

			closeFridgeDoor = new FridgeDoorCloseButton("Close Fridge Door");
			closeFridgeDoor.addActionListener(GUI.this);
			closeFridgeDoor.setBounds(263, 125, 150, 23);
			add(closeFridgeDoor);

			closeFreezerDoor = new FreezerDoorCloseButton("Close Freezer Door");
			closeFreezerDoor.addActionListener(GUI.this);
			closeFreezerDoor.setBounds(263, 159, 150, 23);
			add(closeFreezerDoor);

			JLabel lblNewLabel_3 = new JLabel("Status");
			lblNewLabel_3.setBounds(39, 207, 46, 14);
			add(lblNewLabel_3);

			fridgeLightLbl = new JLabel("Fridge Light <on/off>");
			fridgeLightLbl.setBounds(49, 232, 150, 14);
			add(fridgeLightLbl);

			fridgeTempLbl = new JLabel("Fridge temp <nn>");
			fridgeTempLbl.setBounds(49, 257, 150, 14);
			add(fridgeTempLbl);

			fridgeCoolingLbl = new JLabel("Fridge <cooling/idle>");
			fridgeCoolingLbl.setBounds(49, 282, 150, 14);
			add(fridgeCoolingLbl);

			freezerLightLbl = new JLabel("Freezer Light <on/off>");
			freezerLightLbl.setBounds(256, 232, 150, 14);
			add(freezerLightLbl);

			freezerTempLbl = new JLabel("Freezer Temp <nn>");
			freezerTempLbl.setBounds(256, 257, 150, 14);
			add(freezerTempLbl);

			freezerCoolingLbl = new JLabel("Freezer <cooling/idle>");
			freezerCoolingLbl.setBounds(256, 282, 150, 14);
			add(freezerCoolingLbl);

			errorLbl = new JLabel();
			errorLbl.setBounds(20, 310, 400, 14);
			errorLbl.setForeground(Color.red);
			errorLbl.setVisible(false);
			add(errorLbl);
		}
	}

	/** 
	 * calls the inform() method on the GUIButton that was clicked
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		((GUIButton) event.getSource()).inform(context, this);
	}

	/**
	 * Main method
	 * 
	 * If no args are passed in the default config file is used.
	 * 
	 * @param args - path to config file.
	 */
	public static void main(String[] args) {
		if(args.length > 0){
			new GUI(args[0]);
		} else {
			new GUI("default_values.txt");
		}
	}
}
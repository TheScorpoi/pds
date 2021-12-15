package ModelViewControl_I;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThermometerApp {
	/**
	 * The main program creates the user interface for the thermometer
	 * application and creates an initial thermometer.
	 * @param args Default is mercury if no arguments are given.  
	 * 	-d gives a digital thermometer.
	 */

	 
	public static void main(String[] args) {
		// Create the thermometer.
		int startingTemp = 50;

		final Thermometer thermometer;
		thermometer = new Thermometer (startingTemp, -10, 120);
		
		// Create the frame
		JFrame frame = new JFrame ("Thermometer");
		Container mainPanel = frame.getContentPane();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		// Create the panel where the user can type in a new temperature.
		JPanel valuePanel = new JPanel();
		valuePanel.add (new JLabel ("Enter a temperature to display"));
		JTextField tempField = new JTextField (5);
		tempField.addActionListener (new ActionListener () {

			public void actionPerformed(ActionEvent event) {
				try {
					int newTemp = Integer.parseInt (((JTextField) event.getSource()).getText());
					thermometer.setCurrent (newTemp);
				} catch (NumberFormatException e) {
					// Ignore anything other than integers
				}
			}
		});
		valuePanel.add(tempField);
		mainPanel.add (valuePanel);
		
		// Add the thermometers
		mainPanel.add(thermometer);
						
		// Display the frame
		frame.pack();
		frame.setVisible (true);
	}
}

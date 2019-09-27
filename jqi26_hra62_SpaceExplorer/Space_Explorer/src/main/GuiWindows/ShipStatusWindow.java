package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.GameEnvironment;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements the window for displaying the ships status to the player
 * @author Jackie Qiu
 *
 */
public class ShipStatusWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public ShipStatusWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeShipStatusWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Ship Status");
		window.setBounds(100, 100, 650, 134);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel statusLabel = new JLabel(environment.getCurrentCrew().getShip().status());
		statusLabel.setBounds(10, 11, 614, 14);
		window.getContentPane().add(statusLabel);
		
		JButton finishedButton = new JButton("OK");
		finishedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		finishedButton.setBounds(276, 61, 89, 23);
		window.getContentPane().add(finishedButton);
	}
}

package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.GameEnvironment;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The class implements the window for when the user chooses to visit the nearest outpost
 * where they can buy items or view their inventory
 * @author Jackie Qiu
 *
 */
public class OutpostWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public OutpostWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeOutpostWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("We've arrived at the nearest outpost");
		titleLabel.setBounds(10, 11, 614, 14);
		window.getContentPane().add(titleLabel);
		
		JLabel whatToDoLabel = new JLabel("What would you like to do?");
		whatToDoLabel.setBounds(10, 29, 614, 14);
		window.getContentPane().add(whatToDoLabel);
		
		JButton marketButton = new JButton("Visit the market");
		marketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchMarketWindow();
				finishedWindow();
			}
		});
		marketButton.setBounds(204, 53, 218, 23);
		window.getContentPane().add(marketButton);
		
		JButton inventoryButton = new JButton("View your inventory");
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchInventoryWindow();
				finishedWindow();
			}
		});
		inventoryButton.setBounds(204, 87, 218, 23);
		window.getContentPane().add(inventoryButton);
		
		JButton exitButton = new JButton("Leave the outpost");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchMainGameWindow();
				finishedWindow();
			}
		});
		exitButton.setBounds(457, 227, 167, 23);
		window.getContentPane().add(exitButton);
	}

}

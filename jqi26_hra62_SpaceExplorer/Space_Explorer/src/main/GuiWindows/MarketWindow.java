package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.GameEnvironment;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements the window for visiting the market at an outpost
 * @author Jackie Qiu
 *
 */
public class MarketWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public MarketWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeMarketWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel greetingsLabel = new JLabel("Greetings human");
		greetingsLabel.setBounds(10, 11, 414, 14);
		window.getContentPane().add(greetingsLabel);
		
		JLabel greeting2Label = new JLabel("What are you looking for?");
		greeting2Label.setBounds(10, 36, 414, 14);
		window.getContentPane().add(greeting2Label);
		
		JButton foodButton = new JButton("Food Items");
		foodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchFoodItemWindow();
				finishedWindow();
			}
		});
		foodButton.setBounds(216, 61, 195, 23);
		window.getContentPane().add(foodButton);
		
		JButton medicineButton = new JButton("Medical Items");
		medicineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchMedicalItemWindow();
				finishedWindow();
			}
		});
		medicineButton.setBounds(216, 95, 195, 23);
		window.getContentPane().add(medicineButton);
		
		JButton exitButton = new JButton("Leave market");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchOutpostWindow();
				finishedWindow();
			}
		});
		exitButton.setBounds(484, 227, 140, 23);
		window.getContentPane().add(exitButton);
		
		
	}
}
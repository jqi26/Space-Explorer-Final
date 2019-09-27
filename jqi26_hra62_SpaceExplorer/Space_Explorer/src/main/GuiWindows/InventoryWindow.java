package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.GameEnvironment;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements the window to display the crews current inventory to the player
 * @author Jackie Qiu
 *
 */
public class InventoryWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public InventoryWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeInventoryWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel inventoryLabel = new JLabel("<html>" + environment.getCurrentCrew().showInventory().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		inventoryLabel.setVerticalAlignment(SwingConstants.TOP);
		inventoryLabel.setBounds(10, 11, 614, 191);
		window.getContentPane().add(inventoryLabel);
		
		JButton exitButton = new JButton("Back");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		exitButton.setBounds(535, 227, 89, 23);
		window.getContentPane().add(exitButton);
	}

}

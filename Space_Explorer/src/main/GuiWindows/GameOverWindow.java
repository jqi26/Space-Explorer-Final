package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import main.GameEnvironment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements the window to display the game over screen to the user
 * @author Jackie Qiu
 *
 */
public class GameOverWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public GameOverWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeGameOverWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel summaryLabel = new JLabel("<html>" + environment.gameOver().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		summaryLabel.setVerticalAlignment(SwingConstants.TOP);
		summaryLabel.setBounds(10, 11, 614, 184);
		window.getContentPane().add(summaryLabel);
		
		JButton exitButton = new JButton("Exit Game");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		exitButton.setBounds(245, 226, 132, 23);
		window.getContentPane().add(exitButton);
	}

}

package main.GuiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.GameEnvironment;

/**
 * This class implements a window which is used multiple times throughout the game to display a statement, which is passed to it as an argument.
 * This statement will then be displayed on the statement window, along with a Back to Main Menu button which will complete the 
 * necessary checks to ensure that when the user has finished the game or they have run out of ways to win they will be shown the Game Over
 * window instead.
 * @author Hamesh Ravji
 *
 */
public class StatementWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The statement we wish to display to the user.
	 */
	private String statement;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 * @param statement The string we wish to display to the user
	 */
	public StatementWindow(GameEnvironment incomingGameEnvironment, String statement) {
		this.statement = statement;
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeStatementWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 224);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblStatement = new JLabel("<html>"+statement+"</html>");
		lblStatement.setBounds(17, 19, 607, 82);
		window.getContentPane().add(lblStatement);
		
		JButton btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (environment.getNumPartsFound() == environment.getNumPartsRequired() ||
					environment.getCurrentCrew().getCrew().size() == 0) {
					environment.launchGameOverWindow();
				}
				else {
					environment.launchMainGameWindow();
				}
				finishedWindow();
			}
		});
		btnBackToMain.setBounds(216, 135, 202, 23);
		window.getContentPane().add(btnBackToMain);
	}


}
package main.GuiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.CrewMember;
import main.GameEnvironment;


/**
 * This class implements the window allowing the user to chose whether they would like the crew member to consume food or use a medical item from the ship's inventory.
 * @author Hamesh Ravji
 *
 */
public class FoodOrMedicalWindow {
	
	/**
	 * Keeps track of the current game environment
	 */
	private GameEnvironment environment;
	
	private JFrame window;
	
	/**
	 * The CrewMember the player would like to consume an item
	 */
	private CrewMember chosenCrewMember;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 * @param member The CrewMember the player wishes to complete the action
	 */
	public FoodOrMedicalWindow(GameEnvironment incomingGameEnvironment, CrewMember member) {
		chosenCrewMember = member;
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeFoodOrMedicalWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Choose Action");
		window.setBounds(100, 100, 650, 302);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblQuestion = new JLabel("<html>Would you like "+chosenCrewMember.getName()+" to eat food or use medical supplies from your crew's inventory?</html>");
		lblQuestion.setBounds(10, 11, 551, 23);
		window.getContentPane().add(lblQuestion);
		
		JButton btnFood = new JButton("Eat food from the crew's inventory");
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (environment.getCurrentCrew().getFoodItems().size() > 0) {
					environment.launchConsumeFoodWindow(chosenCrewMember);
					finishedWindow();
				} else {
					environment.launchStatementWindow("There is no food in the crew's inventory.");
					finishedWindow();
				}
			}
		});
		btnFood.setBounds(84, 45, 463, 23);
		window.getContentPane().add(btnFood);
		
		JButton btnMedical = new JButton("Use medical supplies from the crew's inventory");
		btnMedical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (environment.getCurrentCrew().getMedicalItems().size() > 0) {
					environment.launchUseMedicalWindow(chosenCrewMember);
					finishedWindow();
				} else {
					environment.launchStatementWindow("There are no medical supplies in the crew's inventory.");
					finishedWindow();
				}
			}
		});
		btnMedical.setBounds(84, 79, 463, 23);
		window.getContentPane().add(btnMedical);
	}

}
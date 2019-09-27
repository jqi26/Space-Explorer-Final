package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import main.CrewMember;
import main.GameEnvironment;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * This class implements the window for the main screen of the game which the player interacts with
 * continuously in order to progress
 * @author Jackie Qiu
 *
 */
public class MainGameWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	/**
	 * Whether or not the game should end to stop running the rest of the code
	 */
	private boolean gameOver = false;

	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public MainGameWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeMainGameWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 318);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);

		JLabel currentDayLabel = new JLabel("The current day is: " + environment.getCurrentDay());
		currentDayLabel.setBounds(10, 11, 614, 14);
		window.getContentPane().add(currentDayLabel);

		JLabel partsFoundLabel = new JLabel("You have found " + environment.getNumPartsFound() + "/" + environment.getNumPartsRequired() + " parts");
		partsFoundLabel.setBounds(10, 36, 614, 14);
		window.getContentPane().add(partsFoundLabel);

		JLabel daysRemainingLabel = new JLabel("You have " + (environment.getDays() - environment.getCurrentDay()) + " days left to return home.");
		daysRemainingLabel.setBounds(10, 61, 614, 14);
		window.getContentPane().add(daysRemainingLabel);

		JLabel whatToDoLabel = new JLabel("What would you like to do?");
		whatToDoLabel.setBounds(10, 86, 614, 14);
		window.getContentPane().add(whatToDoLabel);

		JButton option1Button = new JButton("1. View the status of a crew member");
		option1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchCrewMemberStatusWindow();
				finishedWindow();
			}
		});
		option1Button.setHorizontalAlignment(SwingConstants.LEFT);
		option1Button.setBounds(124, 111, 389, 23);
		window.getContentPane().add(option1Button);

		JButton option2Button = new JButton("2. View the status of your ship");
		option2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchShipStatusWindow();
				finishedWindow();
			}
		});
		option2Button.setHorizontalAlignment(SwingConstants.LEFT);
		option2Button.setBounds(124, 145, 389, 23);
		window.getContentPane().add(option2Button);

		JButton option3Button = new JButton("3. Visit the nearest outpost");
		option3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchOutpostWindow();
				finishedWindow();
			}
		});
		option3Button.setHorizontalAlignment(SwingConstants.LEFT);
		option3Button.setBounds(124, 179, 389, 23);
		window.getContentPane().add(option3Button);

		JButton option4Button = new JButton("4. Choose actions for your crew members");
		option4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<CrewMember> membersWithRemainingActions = new ArrayList<CrewMember>();
				for (CrewMember currentCrewMember: environment.getCurrentCrew().getCrew()) {
					if (currentCrewMember.getNumActions() > 0) {
						membersWithRemainingActions.add(currentCrewMember);
					}
				}
				if (membersWithRemainingActions.size() > 0) {
					environment.launchChooseCrewMemberWindow();
				} else {
					environment.launchStatementWindow("There are no crew members with remaining actions.");
				}
				finishedWindow();
			}
		});
		option4Button.setHorizontalAlignment(SwingConstants.LEFT);
		option4Button.setBounds(124, 213, 389, 23);
		window.getContentPane().add(option4Button);

		JButton option5Button = new JButton("5. Skip to the next day");
		option5Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (environment.getCurrentDay() < environment.getDays()) {
					environment.setCurrentDay(environment.getCurrentDay() + 1);
					if (environment.getNumPartsFound() < environment.getNumPartsRequired() && environment.getCurrentCrew().getCrew().size() != 0) {
						environment.getCurrentCrew().updateStatistics();
						
						/* Second condition of the || ensures that the player has enough crew members to travel to other planets
						 * if there are still parts on other planets */
						if (environment.getCurrentCrew().getCrew().size() == 0 || (environment.getCurrentCrew().getCrew().size() == 1 && 
								(environment.getCurrentPlanet().getPartFound() == true || 
								environment.getNumPartsRequired() - environment.getNumPartsFound() > 1))) {
							gameOver = true;
							environment.launchGameOverWindow();
							finishedWindow();
						}
						
					}
					
					if (gameOver == false) {
						environment.getRandomEventGenerator().randomEvent();
						if (environment.getRandomEventMessage() != "" || environment.getAdjustmentsMessage().trim().length() > 0) {
							environment.launchWarningWindow();
							finishedWindow();
						}
					}
				}
				else {
					environment.launchGameOverWindow();
					finishedWindow();
				}
				currentDayLabel.setText("The current day is: " + environment.getCurrentDay());
				daysRemainingLabel.setText("You have " + (environment.getDays() - environment.getCurrentDay()) + " days left to return home.");
			}
		});
		option5Button.setHorizontalAlignment(SwingConstants.LEFT);
		option5Button.setBounds(124, 247, 389, 23);
		window.getContentPane().add(option5Button);
	}
}

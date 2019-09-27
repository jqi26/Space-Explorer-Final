package main.GuiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.FoodItems.Biscuit;
import main.FoodItems.Pizza;
import main.FoodItems.Ramen;
import main.FoodItems.Ration;
import main.FoodItems.Salad;
import main.FoodItems.SpaceFruit;
import main.MedicalItems.Bandage;
import main.MedicalItems.MedPack;
import main.MedicalItems.SpacePlagueCure;
import main.GameEnvironment;
import main.CrewMember;
import main.MedicalItem;
import main.FoodItem;


/**
 * This class implements the window for choosing a crew member action, which is shown when the user has chosen their crew member 
 * they would like to complete the action.
 * 
 * @author Hamesh Ravji
 * @author Jackie Qiu
 *
 */
public class ChooseActionWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The chosen crew member to complete the task
	 */
	private CrewMember chosenCrewMember;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 * @param member The CrewMember the player wishes to complete an action
	 */
	public ChooseActionWindow(GameEnvironment incomingGameEnvironment, CrewMember member) {
		chosenCrewMember = member;
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeChooseActionWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Choose Action");
		window.setBounds(100, 100, 650, 296);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblQuestion = new JLabel("What action would you like the crew member to complete?");
		lblQuestion.setBounds(10, 11, 577, 23);
		window.getContentPane().add(lblQuestion);
		
		JButton btnFoodMedical = new JButton("1. Eat food or use medical supplies");
		btnFoodMedical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.launchFoodOrMedicalWindow(chosenCrewMember);
				finishedWindow();
			}
		});
		btnFoodMedical.setBounds(108, 64, 418, 23);
		window.getContentPane().add(btnFoodMedical);
		
		JButton btnSleep = new JButton("2. Get some Sleep");
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chosenCrewMember.sleep();
				environment.launchStatementWindow("<html>"+chosenCrewMember.getName() + 
						" went to sleep for a bit.<br>The tiredness level of "+chosenCrewMember.getName()+" is now "+chosenCrewMember.getTiredness()+" and they are feeling fresh.</html>");
				finishedWindow();
			}
		});
		btnSleep.setBounds(108, 98, 418, 23);
		window.getContentPane().add(btnSleep);
		
		JButton btnCompleteRepairs = new JButton("3. Repair the Shields of the Ship");
		btnCompleteRepairs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				environment.getCurrentCrew().getShip().performRepairs(chosenCrewMember);
				environment.launchStatementWindow("<html>The repairs have now been completed on the ship.<br>"+environment.getCurrentCrew().getShip().status()+"</html>");
				finishedWindow();
			}
		});
		btnCompleteRepairs.setBounds(108, 132, 418, 23);
		window.getContentPane().add(btnCompleteRepairs);
		
		JButton btnSearchThePlanet = new JButton("4. Search the Planet for SpaceShip Parts");
		btnSearchThePlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String startSearch = chosenCrewMember.getName()+" has gone to search the planet "+environment.getCurrentPlanet().getName()+" for parts.<br>";
				String searchResult = environment.getCurrentPlanet().searchPlanet(chosenCrewMember);
				environment.launchStatementWindow(startSearch + searchResult);
				finishedWindow();
			}
		});
		btnSearchThePlanet.setBounds(108, 166, 418, 23);
		window.getContentPane().add(btnSearchThePlanet);
		
		JButton btnPilotTheShip = new JButton("5. Pilot the Ship to a new Planet");
		btnPilotTheShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<CrewMember> remainingCrewMembers = new ArrayList<CrewMember>();
				for (CrewMember currentCrewMember: environment.getCurrentCrew().getCrew()) {
					if (currentCrewMember.getNumActions() > 0 && currentCrewMember != chosenCrewMember) {
						remainingCrewMembers.add(currentCrewMember);
					}
				}
				String startString = "Travelling to a new planet requires two pilots, both of which need to have at least one remaining action each. The ship's shield level must also be at least 10.";
				if (!(environment.getCurrentPlanet().getPartFound())) { 
					/* part for that planet is yet to be found */
					environment.launchStatementWindow(startString+"<br>You have not found the part on this planet yet, find the part first.");
				} else if (environment.getCurrentCrew().getShip().getShieldLevel() < 10) {
					/* shield level not high enough */
					environment.launchStatementWindow(startString+"<br>"+environment.getCurrentCrew().getShip().getName()+"'s Shield Level is currently too low, I suggest you have a crew member take a look at it.");
				} else if (remainingCrewMembers.size() > 0) {
					/* all requirements met, travels to new planet */
					environment.launchNewPlanetWindow(remainingCrewMembers, chosenCrewMember);
				} else {
					/* not enough crew members with remaining actions to travel to a new planet */
					environment.launchStatementWindow(startString+"<br>Sorry, you don't have any other crew members with remaining actions to help pilot the ship.");
				}
				finishedWindow();
			}
		});
		btnPilotTheShip.setBounds(108, 200, 418, 23);
		window.getContentPane().add(btnPilotTheShip);
	}

}
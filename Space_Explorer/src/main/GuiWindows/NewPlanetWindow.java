package main.GuiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.CrewMember;
import main.GameEnvironment;

/**
 * This class implements the window for choosing a second pilot then calls the method for traveling to a new planet
 * @author Hamesh Ravji
 *
 */
public class NewPlanetWindow {
	
	

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The first member of the crew that was initially chosen to complete the action
	 */
	private CrewMember firstPilot;
	
	/**
	 * The second member of the crew which is needed in order to pilot the ship to a new planet
	 */
	private CrewMember secondPilot;
	
	/**
	 * An ArrayList of the remaining crew members with actions left, other than the first pilot. The second pilot
	 * is chosen from this list
	 */
	private ArrayList<CrewMember> remainingCrewMembers;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 * @param remainingCrewMembers An ArrayList of CrewMembers that are still alive
	 * @param firstPilot The CrewMember that was chosen as the first pilot
	 */
	public NewPlanetWindow(GameEnvironment incomingGameEnvironment, ArrayList<CrewMember> remainingCrewMembers, CrewMember firstPilot) {
		this.firstPilot = firstPilot;
		this.remainingCrewMembers = remainingCrewMembers;
		environment = incomingGameEnvironment;
		
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		environment.closeNewPlanetWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Travel to a new Planet");
		window.setBounds(100, 100, 650, 356);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		
		
		JLabel lblQuestion = new JLabel("Which Crew Member would you like to select as a co-pilot?");
		lblQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuestion.setBounds(10, 11, 565, 23);
		window.getContentPane().add(lblQuestion);
		
		JLabel lblCoPilotStats = new JLabel("");
		lblCoPilotStats.setVerticalAlignment(SwingConstants.TOP);
		lblCoPilotStats.setBounds(301, 68, 288, 178);
		window.getContentPane().add(lblCoPilotStats);
		
		JComboBox<CrewMember> possibleCoPilots = new JComboBox<CrewMember>();
		possibleCoPilots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrewMember chosenCrewMember = (CrewMember)possibleCoPilots.getSelectedItem();
				lblCoPilotStats.setText("<html>" + chosenCrewMember.status().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + 
						"<br>Actions Remaining: "+chosenCrewMember.getNumActions()+"</html>");
			}
		});
		for (CrewMember currentCrewMember: remainingCrewMembers) {
			possibleCoPilots.addItem(currentCrewMember);
		}
		secondPilot = (CrewMember)possibleCoPilots.getSelectedItem();
		
		possibleCoPilots.setBounds(10, 68, 270, 23);
		window.getContentPane().add(possibleCoPilots);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondPilot = (CrewMember)possibleCoPilots.getSelectedItem();
				String message = environment.getCurrentCrew().getShip().travelToNewPlanet(firstPilot, secondPilot, environment);
				environment.launchStatementWindow(message);
				finishedWindow();
			}
		});
		btnOk.setBounds(275, 271, 89, 23);
		window.getContentPane().add(btnOk);
		
		JLabel lblInstruction = new JLabel("Select the crew member from the drop down menu:");
		lblInstruction.setBounds(10, 34, 545, 23);
		window.getContentPane().add(lblInstruction);
			

		

		
	}
}
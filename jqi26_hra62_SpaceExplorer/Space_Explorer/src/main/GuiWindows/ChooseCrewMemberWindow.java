package main.GuiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.CrewMember;
import main.GameEnvironment;


/**
 * This class implements the window for choosing the crew member that the user would like to complete an action. 
 * Only shown if there are remaining crew members with actions.
 * 
 * @author Hamesh Ravji
 *
 */
public class ChooseCrewMemberWindow {


	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public ChooseCrewMemberWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		environment.closeChooseCrewMemberWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Choose Crew Member");
		window.setBounds(100, 100, 650, 409);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblQuestion = new JLabel("Which crew member would you like to complete the action?");
		lblQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuestion.setBounds(17, 19, 565, 23);
		window.getContentPane().add(lblQuestion);
		
		JLabel lblCrewMemberStatistics = new JLabel("");
		lblCrewMemberStatistics.setVerticalAlignment(SwingConstants.TOP);
		lblCrewMemberStatistics.setBounds(304, 152, 288, 136);
		window.getContentPane().add(lblCrewMemberStatistics);
		
		JComboBox<CrewMember> remainingCrewMembersWithActions = new JComboBox<CrewMember>();
		remainingCrewMembersWithActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrewMember chosenCrewMember = (CrewMember)remainingCrewMembersWithActions.getSelectedItem();
				lblCrewMemberStatistics.setText("<html>" + chosenCrewMember.status().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + 
						"<br>Actions Remaining: "+chosenCrewMember.getNumActions()+"</html>");
			}
		});
		for (CrewMember currentCrewMember: environment.getCurrentCrew().getCrew()) {
			if (currentCrewMember.getNumActions() > 0) {
				remainingCrewMembersWithActions.addItem(currentCrewMember);
			}
		}
		remainingCrewMembersWithActions.setBounds(17, 113, 270, 23);
		window.getContentPane().add(remainingCrewMembersWithActions);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrewMember chosenCrewMember = (CrewMember)remainingCrewMembersWithActions.getSelectedItem();
				environment.launchChooseActionWindow(chosenCrewMember);
				finishedWindow();
			}
		});
		btnOk.setBounds(274, 315, 89, 23);
		window.getContentPane().add(btnOk);
		
		JLabel lblInstruction = new JLabel("Select the crew member from the drop down menu:");
		lblInstruction.setBounds(17, 71, 545, 23);
		window.getContentPane().add(lblInstruction);
		
		JLabel lblCrewMemberStatistics_1 = new JLabel("Crew Member Statistics:");
		lblCrewMemberStatistics_1.setBounds(304, 116, 288, 23);
		window.getContentPane().add(lblCrewMemberStatistics_1);
		

		
	}
}
package main.GuiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.CrewMember;
import main.GameEnvironment;
import main.MedicalItem;

/**
 * This class implements a window allowing the user to choose which medical item they would like the chosen crew member to use.
 * @author Hamesh Ravji
 *
 */
public class UseMedicalWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The crew member the player wants to use the medical item
	 */
	private CrewMember chosenCrewMember;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 * @param member The CrewMember the player wishes to use the medical item
	 */
	public UseMedicalWindow(GameEnvironment incomingGameEnvironment, CrewMember member) {
		chosenCrewMember = member;
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeUseMedicalWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 390);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("<html>Here is a list of the Medical Items currently in the Crew's Inventory:</html>");
		lblTitle.setBounds(10, 11, 614, 23);
		window.getContentPane().add(lblTitle);
		
		JLabel lblMedicalStats = new JLabel("");
		lblMedicalStats.setVerticalAlignment(SwingConstants.TOP);
		lblMedicalStats.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedicalStats.setBounds(296, 87, 300, 190);
		window.getContentPane().add(lblMedicalStats);
		
		JComboBox<MedicalItem> medicalItemsAvailable = new JComboBox<MedicalItem>();
		medicalItemsAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicalItem chosenMedicalItem = (MedicalItem)medicalItemsAvailable.getSelectedItem();
				String cureString = "No";
				if (chosenMedicalItem.getCuresSpacePlague()) {
					cureString = "Yes";
				}
				
				lblMedicalStats.setText("<html>Name: " + chosenMedicalItem + "<br>Increases health by: " + chosenMedicalItem.getHealAmount()+"<br>Cures Space Plague: "+cureString+"</html>");
			}
		});
		for (MedicalItem medicalItem: environment.getCurrentCrew().getMedicalItems()) {
			medicalItemsAvailable.addItem(medicalItem);
		}
		medicalItemsAvailable.setBounds(10, 45, 269, 23);
		window.getContentPane().add(medicalItemsAvailable);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicalItem chosenMedicalItem = (MedicalItem)medicalItemsAvailable.getSelectedItem();
				chosenCrewMember.heal(chosenMedicalItem);
				ArrayList<MedicalItem> tempMedicalItems = environment.getCurrentCrew().getMedicalItems();
				tempMedicalItems.remove(chosenMedicalItem);
				environment.getCurrentCrew().setMedicalItems(tempMedicalItems);
				environment.launchStatementWindow("The health of " + chosenCrewMember.getName() + " is now " + chosenCrewMember.getHealth()+".");
				finishedWindow();
			}
		});
		btnOk.setBounds(271, 317, 89, 23);
		window.getContentPane().add(btnOk);
		
	
	}

}

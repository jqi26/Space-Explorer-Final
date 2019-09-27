package main.GuiWindows;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.GameEnvironment;
import main.Ship;

/**
 * This class implements the setup window so that the player may choose the number of days the adventure
 * will last, their crew members, and name the ship and crew
 * @author Jackie Qiu
 * @author Hamesh Ravji
 *
 */
public class SetupWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	private JTextField crewName;
	/**
	 * A list containing the integer choices for the crew member types
	 */
	private ArrayList<Integer> crewChoices = new ArrayList<Integer>();
	/**
	 * A list of names for the crew members chosen by the player
	 */
	private ArrayList<String> crewNames = new ArrayList<String>();
	private JTextField name1;
	private JTextField name2;
	private JTextField name3;
	private JTextField name4;
	private JTextField shipName;

	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public SetupWindow(GameEnvironment incomingEnvironment) {
		environment = incomingEnvironment;
		initialize();
		window.setVisible(true);
	}
	
	public void closeWindow() {
		window.dispose();
	}
	
	public void finishedWindow() {
		environment.closeSetupWindow(this);
	}
	
	/**
	 * This method is called each time a Crew Member type button is clicked, checks each selected box to determine if they are 
	 * empty. If empty the crew member type as a string replaces the text in the selected box. Else continues to search the selected
	 * boxes until an empty box is found or no boxes left, in that case it would mean that the user has already selected the 
	 * maximum number of Crew Member types.
	 * @param sel1 The first JButton containing the first CrewMember type that the user has selected
	 * @param sel2 The second JButton containing the second CrewMember type that the user has selected
	 * @param sel3 The third JButton containing the third CrewMember type that the user has selected
	 * @param sel4 The fourth JButton containing the fourth CrewMember type that the user has selected
	 * @param type The CrewMember type as a String that the user has selected
	 */
	public void updateSelected(JButton sel1, JButton sel2, JButton sel3, JButton sel4, String type) {
		if (sel1.getText()=="") {
			sel1.setText(type);
		} else if (crewChoices.size() > 1 && sel2.getText()=="") {
			sel2.setText(type);
		} else if (crewChoices.size() > 2 && sel3.getText()=="") {
			sel3.setText(type);
		} else if (crewChoices.size() > 3 && sel4.getText()=="") {
			sel4.setText(type);
		}
	}
	
	/**
	 * This method shuffles the text in the selected boxes, required method in order to get balanced crewChoices indexing. The method 
	 * is called whenever a CrewMember type is unselected. Method moves strings in the boxes to the left until there are no gaps between 
	 * selected boxes.
	 * @param sel1 The first JButton containing the first CrewMember type that the user has selected.
	 * @param sel2 The second JButton containing the second CrewMember type that the user has selected.
	 * @param sel3 The third JButton containing the third CrewMember type that the user has selected.
	 * @param sel4 The fourth JButton containing the fourth CrewMember type that the user has selected.
	 */
	public void shuffleSelected(JButton sel1, JButton sel2, JButton sel3, JButton sel4) {
		ArrayList<String> tempTypes = new ArrayList<String>();
		if (sel1.getText()!="") {
			tempTypes.add(sel1.getText());
		} 
		if (sel2.getText()!="") {
			tempTypes.add(sel2.getText());
		} 
		if (sel3.getText()!="") {
			tempTypes.add(sel3.getText());
		} 
		if (sel4.getText()!="") {
			tempTypes.add(sel4.getText());
		}
		
		if (tempTypes.size() > 0) {
			sel1.setText(tempTypes.get(0));
			if (tempTypes.size() > 1) {
				sel2.setText(tempTypes.get(1));
				if (tempTypes.size() > 2) {
					sel3.setText(tempTypes.get(2));
					if (tempTypes.size() > 3) {
						sel4.setText(tempTypes.get(3));
					} else {
						sel4.setText("");
					}
				} else {
					sel4.setText("");
					sel3.setText("");
				}
			} else {
				sel4.setText("");
				sel3.setText("");
				sel2.setText("");
			}
		} else {
			sel4.setText("");
			sel3.setText("");
			sel2.setText("");
			sel1.setText("");
		}
	}
	
	/**
	 * A method to add a crew member to the list of choices for crew types while ensuring
	 * the number of choices does not exceed the maximum of 4
	 * @param choice The integer corresponding to the type the user wants
	 */
	public void addToCrewMembers(int choice) {
		if (crewChoices.size() < 4) {
			crewChoices.add(choice);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Setup");
		window.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to Space Explorer!");
		welcomeLabel.setBounds(10, 11, 614, 14);
		window.getContentPane().add(welcomeLabel);
		
		JLabel daysLable = new JLabel("How many days will your adventure last?");
		daysLable.setBounds(10, 36, 318, 14);
		window.getContentPane().add(daysLable);
		
		JSlider daysSlider = new JSlider();
		daysSlider.setMajorTickSpacing(1);
		daysSlider.setPaintLabels(true);
		daysSlider.setPaintTicks(true);
		daysSlider.setMaximum(10);
		daysSlider.setMinimum(3);
		daysSlider.setBounds(338, 23, 286, 39);
		window.getContentPane().add(daysSlider);
		
		JLabel createCrewLabel = new JLabel("Create a crew");
		createCrewLabel.setBounds(10, 61, 318, 14);
		window.getContentPane().add(createCrewLabel);
		
		JLabel crewNameLabel = new JLabel("What is your crew called?");
		crewNameLabel.setBounds(10, 85, 318, 14);
		window.getContentPane().add(crewNameLabel);
		
		crewName = new JTextField();
		crewName.setBounds(348, 82, 276, 20);
		window.getContentPane().add(crewName);
		crewName.setColumns(10);
		
		JLabel crewSelectLabel = new JLabel("Select 2-4 crew members from the 6 following types");
		crewSelectLabel.setBounds(10, 110, 528, 14);
		window.getContentPane().add(crewSelectLabel);
		
		JButton btnMember1 = new JButton("");
		JButton btnMember2 = new JButton("");
		JButton btnMember3 = new JButton("");
		JButton btnMember4 = new JButton("");
		
		btnMember1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnMember1.getText() != "") {
					btnMember1.setText("");
					crewChoices.remove(0);
					shuffleSelected(btnMember1, btnMember2, btnMember3, btnMember4);
				}
			}
		});
		btnMember1.setBounds(82, 356, 111, 23);
		window.getContentPane().add(btnMember1);
		
		
		btnMember2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnMember2.getText() != "") {
					btnMember2.setText("");
					crewChoices.remove(1);
					shuffleSelected(btnMember1, btnMember2, btnMember3, btnMember4);
				}
			}
		});
		btnMember2.setBounds(203, 356, 109, 23);
		window.getContentPane().add(btnMember2);
		
		
		btnMember3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnMember3.getText() != "") {
					btnMember3.setText("");
					crewChoices.remove(2);
					shuffleSelected(btnMember1, btnMember2, btnMember3, btnMember4);
				}
			}
		});
		btnMember3.setBounds(322, 356, 112, 23);
		window.getContentPane().add(btnMember3);
		
		
		btnMember4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnMember4.getText() != "") {
					btnMember4.setText("");
					crewChoices.remove(3);
					shuffleSelected(btnMember1, btnMember2, btnMember3, btnMember4);
				}
			}
		});
		btnMember4.setBounds(444, 356, 111, 23);
		window.getContentPane().add(btnMember4);
		
		JLabel selectedTypesLabel = new JLabel("Selected crew members:");
		selectedTypesLabel.setBounds(10, 331, 229, 14);
		window.getContentPane().add(selectedTypesLabel);
		
		JLabel namesLabel = new JLabel("Names:");
		namesLabel.setBounds(10, 390, 229, 14);
		window.getContentPane().add(namesLabel);
		
		name1 = new JTextField();
		name1.setBounds(82, 413, 111, 20);
		window.getContentPane().add(name1);
		name1.setColumns(10);
		
		name2 = new JTextField();
		name2.setColumns(10);
		name2.setBounds(203, 413, 109, 20);
		window.getContentPane().add(name2);
		
		name3 = new JTextField();
		name3.setColumns(10);
		name3.setBounds(322, 413, 112, 20);
		window.getContentPane().add(name3);
		
		name4 = new JTextField();
		name4.setColumns(10);
		name4.setBounds(444, 413, 111, 20);
		window.getContentPane().add(name4);
		
		JButton explorerButton = new JButton("1. Explorer, an expert at searching Planets");
		explorerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCrewMembers(1);
				updateSelected(btnMember1, btnMember2, btnMember3, btnMember4, "Explorer");
			}
		});
		explorerButton.setBounds(91, 127, 451, 23);
		window.getContentPane().add(explorerButton);
		
		JButton engineerButton = new JButton("2. Engineer, an expert at repairing Ships");
		engineerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCrewMembers(2);
				updateSelected(btnMember1, btnMember2, btnMember3, btnMember4, "Engineer");
			}
		});
		engineerButton.setBounds(91, 161, 451, 23);
		window.getContentPane().add(engineerButton);
		
		JButton bodyguardButton = new JButton("3. Bodyguard, unmatched when it comes to toughness");
		bodyguardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCrewMembers(3);
				updateSelected(btnMember1, btnMember2, btnMember3, btnMember4, "Bodyguard");
			}
		});
		bodyguardButton.setBounds(91, 195, 451, 23);
		window.getContentPane().add(bodyguardButton);
		
		JButton scavengerButton = new JButton("4. Scavenger, decent at searching and repairing");
		scavengerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCrewMembers(4);
				updateSelected(btnMember1, btnMember2, btnMember3, btnMember4, "Scavenger");
			}
		});
		scavengerButton.setBounds(91, 229, 451, 23);
		window.getContentPane().add(scavengerButton);
		
		JButton survivalistButton = new JButton("5. Survivalist, decent at searching with good toughness");
		survivalistButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCrewMembers(5);
				updateSelected(btnMember1, btnMember2, btnMember3, btnMember4, "Survivalist");
			}
		});
		survivalistButton.setBounds(91, 263, 451, 23);
		window.getContentPane().add(survivalistButton);
		
		JButton mechanicButton = new JButton("6. Mechanic, decent at repairing with good toughness");
		mechanicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCrewMembers(6);
				updateSelected(btnMember1, btnMember2, btnMember3, btnMember4, "Mechanic");
			}
		});
		mechanicButton.setBounds(91, 297, 451, 23);
		window.getContentPane().add(mechanicButton);
		
		JLabel shipNameLabel = new JLabel("What is the name of your ship?");
		shipNameLabel.setBounds(10, 458, 229, 14);
		window.getContentPane().add(shipNameLabel);
		
		shipName = new JTextField();
		shipName.setColumns(10);
		shipName.setBounds(348, 455, 276, 20);
		window.getContentPane().add(shipName);
		
		JLabel notEnoughChoicesLabel = new JLabel("");
		notEnoughChoicesLabel.setBounds(20, 493, 342, 14);
		window.getContentPane().add(notEnoughChoicesLabel);
		window.setBounds(100, 100, 650, 566);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton startButton = new JButton("Start!");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (crewChoices.size() < 2) {
					notEnoughChoicesLabel.setText("You must choose at least 2 crew members!");
				}
				else {
					int days = daysSlider.getValue();
					String name = crewName.getText();
					Ship ship = new Ship(shipName.getText());
					crewNames.add(name1.getText());
					crewNames.add(name2.getText());
					if (crewChoices.size() > 2) {
						crewNames.add(name3.getText());
					}
					if (crewChoices.size() > 3) {
						crewNames.add(name4.getText());
					}
					environment.setup(days, name, crewChoices, crewNames, ship);
					finishedWindow();
				}
			}
		});
		startButton.setBounds(535, 493, 89, 23);
		window.getContentPane().add(startButton);
		
	}
}
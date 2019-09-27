package main.GuiWindows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.CrewMember;
import main.FoodItem;
import main.GameEnvironment;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * This class implements a window allowing the user to choose which food item they would like the chosen crew member to consume.
 * @author Hamesh Ravji
 *
 */
public class ConsumeFoodWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The crew member completing the action
	 */
	private CrewMember chosenCrewMember;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 * @param member The CrewMember the player wishes to consume the food
	 */
	public ConsumeFoodWindow(GameEnvironment incomingGameEnvironment, CrewMember member) {
		chosenCrewMember = member;
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeConsumeFoodWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 390);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("<html>Here is a list of the Food Items currently in the Crew's Inventory:</html>");
		lblTitle.setBounds(10, 11, 614, 23);
		window.getContentPane().add(lblTitle);
		
		JLabel lblFoodStats = new JLabel("");
		lblFoodStats.setVerticalAlignment(SwingConstants.TOP);
		lblFoodStats.setHorizontalAlignment(SwingConstants.LEFT);
		lblFoodStats.setBounds(296, 87, 300, 190);
		window.getContentPane().add(lblFoodStats);
		
		JComboBox<FoodItem> foodItemsAvailable = new JComboBox<FoodItem>();
		foodItemsAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodItem chosenFoodItem = (FoodItem)foodItemsAvailable.getSelectedItem();
				
				lblFoodStats.setText("<html>Name: " + chosenFoodItem + "<br>Reduces hunger by: " + chosenFoodItem.getNutrition()+"</html>");
			}
		});
		for (FoodItem medicalItem: environment.getCurrentCrew().getFoodItems()) {
			foodItemsAvailable.addItem(medicalItem);
		}
		foodItemsAvailable.setBounds(10, 45, 269, 23);
		window.getContentPane().add(foodItemsAvailable);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodItem chosenFoodItem = (FoodItem)foodItemsAvailable.getSelectedItem();
				chosenCrewMember.feed(chosenFoodItem);
				ArrayList<FoodItem> tempFoodItems = environment.getCurrentCrew().getFoodItems();
				tempFoodItems.remove(chosenFoodItem);
				environment.getCurrentCrew().setFoodItems(tempFoodItems);
				environment.launchStatementWindow("The hunger level of " + chosenCrewMember.getName() + " is now " + chosenCrewMember.getHunger()+".");
				finishedWindow();
			}
		});
		btnOk.setBounds(273, 317, 89, 23);
		window.getContentPane().add(btnOk);
	}

}
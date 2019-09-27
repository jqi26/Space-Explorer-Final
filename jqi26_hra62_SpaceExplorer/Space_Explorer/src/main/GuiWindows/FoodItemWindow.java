package main.GuiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

import main.FoodItem;
import main.GameEnvironment;
import main.FoodItems.Biscuit;
import main.FoodItems.Pizza;
import main.FoodItems.Ramen;
import main.FoodItems.Ration;
import main.FoodItems.Salad;
import main.FoodItems.SpaceFruit;

/**
 * This class implements the window for allowing the user to view and purchase food items
 * @author Jackie Qiu
 *
 */
public class FoodItemWindow {

	private JFrame window;
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * The constructor which initialises and sets the window to visible
	 * @param incomingGameEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public FoodItemWindow(GameEnvironment incomingGameEnvironment) {
		environment = incomingGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	public void closeWindow() {
		window.dispose();
	}

	public void finishedWindow() {
		environment.closeFoodItemWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setBounds(100, 100, 650, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new MigLayout("", "[264.00px][406.00][grow]", "[1px][][329.00,grow][]"));
		
		JLabel chosenItemLabel = new JLabel("");
		chosenItemLabel.setVerticalAlignment(SwingConstants.TOP);
		window.getContentPane().add(chosenItemLabel, "cell 0 2");
		
		JLabel newInventoryLabel = new JLabel("");
		newInventoryLabel.setVerticalAlignment(JLabel.TOP);
		window.getContentPane().add(newInventoryLabel, "cell 1 2");
		
		JComboBox<FoodItem> foodList = new JComboBox<FoodItem>();
		window.getContentPane().add(foodList, "cell 0 1");
		Biscuit newBiscuit = new Biscuit();
		Salad newSalad = new Salad();
		Ramen newRamen = new Ramen();
		Ration newRation = new Ration();
		SpaceFruit newSpaceFruit = new SpaceFruit();
		Pizza newPizza = new Pizza();
		
		foodList.addItem(newBiscuit);
		foodList.addItem(newSalad);
		foodList.addItem(newRamen);
		foodList.addItem(newRation);
		foodList.addItem(newSpaceFruit);
		foodList.addItem(newPizza);
		
		FoodItem chosenFood = (FoodItem)foodList.getSelectedItem();
		chosenItemLabel.setText("<html>Name: " + chosenFood + "<br>Reduces hunger by " + chosenFood.getNutrition() + "%<br> Price: $" + chosenFood.getPrice() + "<br>Would you like to buy it?</html>");
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodItem chosenFood = (FoodItem)foodList.getSelectedItem();
				if (environment.getCurrentCrew().getBalance() >= chosenFood.getPrice()) {
					environment.getCurrentCrew().buyItem(chosenFood);
					newInventoryLabel.setText("<html>" + environment.getCurrentCrew().showInventory().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
				}
				else {
					newInventoryLabel.setText("You can't afford this!");
				}
			}
		});
		window.getContentPane().add(buyButton, "cell 0 3");	
		
		JButton exitButton = new JButton("Back");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		window.getContentPane().add(exitButton, "cell 2 3");
		
		foodList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodItem chosenFood = (FoodItem)foodList.getSelectedItem();
				chosenItemLabel.setText("<html>Name: " + chosenFood + "<br>Reduces hunger by " + chosenFood.getNutrition() + "%<br> Price: $" + chosenFood.getPrice() + "<br>Would you like to buy it?</html>");
				
			}
		});
	}

}

package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;

import main.FoodItems.Biscuit;
import main.FoodItems.Pizza;
import main.FoodItems.Ramen;
import main.FoodItems.Ration;
import main.FoodItems.Salad;
import main.FoodItems.SpaceFruit;
import main.MedicalItems.Bandage;
import main.MedicalItems.MedPack;
import main.MedicalItems.SpacePlagueCure;

/**
 * This class implements the crew. Each crew has a list of members, medical items, food items, a ship, a balance, and a name.
 * Each time the game is run, an object of type Crew is created. Each game can only have one Crew object. It implements Observer
 * and observes RandomEventGenerator to be informed whenever a random event occurs
 * 
 * @author Hamesh Ravji
 * @author Jackie Qiu
 *
 */

public class Crew implements java.util.Observer {
	
	/**
	 * The name of the crew
	 */
	private String name;
	
	/**
	 * The crew's ship for traveling
	 */
	private Ship ship;
	
	/**
	 * The crew's balance for buying items
	 */
	private int balance;
	
	/**
	 * The list of CrewMember objects
	 */
	private ArrayList<CrewMember> crew;
	
	/**
	 * The list of MedicalItem objects for healing
	 */
	private ArrayList<MedicalItem> medicalItems;
	
	/**
	 * The list of FoodItem objects for eating
	 */
	private ArrayList<FoodItem> foodItems;
	
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;

	
	/**
	 * A getter to retrieve the name of the Crew
	 * @return name of the crew
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter to retrieve the crew's ship
	 * @return the ship object
	 */
	public Ship getShip() {
		return ship;
	}
	
	/**
	 * A getter to retrieve the list of crew member objects
	 * @return list of CrewMember objects
	 */
	public ArrayList<CrewMember> getCrew() {
		return crew;
	}
	
	/**
	 * A getter to retrieve the list of medical item objects
	 * @return list of medical item objects
	 */
	public ArrayList<MedicalItem> getMedicalItems() {
		return medicalItems;
	}
	
	/**
	* A setter for the medical supplies		
	* @param tempMedicalItems An ArrayList of MedicalItems that we wish to set as the new medical items		
	*/		
	public void setMedicalItems(ArrayList<MedicalItem> tempMedicalItems) {		
		medicalItems = tempMedicalItems;		
	}
	
	/**
	 * A getter to retrieve the list of food item objects
	 * @return list of food item objects
	 */
	public ArrayList<FoodItem> getFoodItems() {
		return foodItems;
	}
	
	/**		
	 * A setter for the food items
	 * @param tempFoodItems An ArrayList of FoodItems that we wish to set as the new food items		
	 */		
	public void setFoodItems(ArrayList<FoodItem> tempFoodItems) {		
		foodItems = tempFoodItems;		
	}
	
	/**
	 * A getter to retrieve the crew's balance
	 * @return the crew's balance
	 */
	public int getBalance() {
		return balance;
	}
	
	/**
	 * A method to add a random amount of money ranging between 1 and 50 to the balance
	 * @return randomMoneyAmount The amount(integer) of money added to the balance
	 */
	public int addMoneyFound() {
		Random rand = new Random();
		int randomMoneyAmount = rand.nextInt(49)+1;
		balance += randomMoneyAmount;
		return randomMoneyAmount;
	}
	
	/**
	 * The Constructor, takes parameters which are passed from setup
	 * @param name A string of the name of the Crew that the user chose in the setup
	 * @param ship The Ship object that the user named in the setup
	 * @param initialBalance The amount of money that the crew starts with as an integer
	 * @param crew The ArrayList of CrewMember objects that the user chose in the setup
	 * @param initialMedicalItems The ArrayList of initial MedicalItem objects
	 * @param initialFoodItems The ArrayList of initial FoodItem objects
	 * @param incomingEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public Crew(String name, Ship ship, int initialBalance, ArrayList<CrewMember> crew, ArrayList<MedicalItem> initialMedicalItems, ArrayList<FoodItem> initialFoodItems, GameEnvironment incomingEnvironment) {
		this.name = name;
		this.ship = ship;
		this.crew = crew;
		this.medicalItems = initialMedicalItems;
		this.foodItems = initialFoodItems;
		this.balance = initialBalance;
		environment = incomingEnvironment;
	}
	
	
	/**
	 * Removes the given CrewMember from the list of CrewMember objects
	 * @param crewMemberToRemove The CrewMember to be removed
	 */
	public void removeCrewMember(CrewMember crewMemberToRemove) {
		crew.remove(crewMemberToRemove);
	}
	
	
	/**
	 * Adds a new medical item to the crew's inventory of medical items
	 * @param medicine the new MedicalItem
	 */
	public void addMedicalItem(MedicalItem medicine) {
		medicalItems.add(medicine);
	}
	
	/**
	 * Adds a new food item to the crew's inventory of food items
	 * @param food the new FoodItem
	 */
	public void addFoodItem(FoodItem food) {
		foodItems.add(food);
	}
	
	/**
	 * A setter which updates the balance whenever necessary
	 * @param newBalance the new integer balance amount
	 */
	public void setBalance(int newBalance) {
		balance = newBalance;
	}
	
	/**
	 * Purchases an item by adding the item to the player's inventory and subtracting the appropriate amount from the 
	 * player's balance
	 * @param item The item(either a FoodItem or MedicalItem object) the player wants to purchase
	 */
	public void buyItem(Object item) {
		if (item instanceof FoodItem) {
			FoodItem foodItem = (FoodItem)item;
			addFoodItem(foodItem);
			setBalance(getBalance() - foodItem.getPrice());
		}
		else {
			MedicalItem medicalItem = (MedicalItem)item;
			addMedicalItem(medicalItem);
			setBalance(getBalance() - medicalItem.getPrice());
		}
	}
	
	/**
	 * This method adds a random food item to the ArrayList of foodItems
	 * @return foodItemToAdd The FoodItem that was added to the list of foodItems
	 */
	public FoodItem addRandomFoodItem() {
		Random rand = new Random();
		ArrayList<FoodItem> totalFoodItems = new ArrayList<FoodItem>();
		Biscuit foundBiscuit = new Biscuit();
		Pizza foundPizza = new Pizza();
		Ramen foundRamen = new Ramen();
		Ration foundRation = new Ration();
		Salad foundSalad = new Salad();
		SpaceFruit foundSpaceFruit = new SpaceFruit();
		totalFoodItems.add(foundBiscuit);
		totalFoodItems.add(foundPizza);
		totalFoodItems.add(foundRamen);
		totalFoodItems.add(foundRation);
		totalFoodItems.add(foundSalad);
		totalFoodItems.add(foundSpaceFruit);
		FoodItem foodItemToAdd = totalFoodItems.get(rand.nextInt(totalFoodItems.size()));
		addFoodItem(foodItemToAdd);
		return foodItemToAdd;
	}
	
	/**
	 * This method adds a random medical item to the ArrayList of medicalItems
	 * @return medicalItemToAdd The MedicalItem that was added to the list of medicalItems
	 */
	public MedicalItem addRandomMedicalItem() {
		Random rand = new Random();
		ArrayList<MedicalItem> totalMedicalItems = new ArrayList<MedicalItem>();
		Bandage foundBandage = new Bandage();
		MedPack foundMedPack = new MedPack();
		SpacePlagueCure foundSpacePlagueCure = new SpacePlagueCure();
		totalMedicalItems.add(foundBandage);
		totalMedicalItems.add(foundMedPack);
		totalMedicalItems.add(foundSpacePlagueCure);
		MedicalItem medicalItemToAdd = totalMedicalItems.get(rand.nextInt(totalMedicalItems.size()));
		addMedicalItem(medicalItemToAdd);
		return medicalItemToAdd;
	}
	
	/**
	 * Produces a summary of the players inventory such as the balance, items and their quantities
	 * @return A string showing the players inventory
	 */
	public String showInventory() {
		String result = "";
		result += "Current balance: $" + getBalance() + "\n";
		
		TreeSet<String> foodCounts = new TreeSet<String>();
		for (FoodItem food: getFoodItems()) {
			foodCounts.add(food.getName() + "(" + Collections.frequency(getFoodItems(), food) + ")");
		}
		result += "Food items: ";
		for (String food: foodCounts) {
			result += food + ", ";
		}
		
		TreeSet<String> medicineCounts = new TreeSet<String>();
		for (MedicalItem medicine: getMedicalItems()) {
			medicineCounts.add(medicine.getName() + "(" + Collections.frequency(getMedicalItems(), medicine) + ")");
		}
		result += "\nMedical items: ";
		for (String medicine: medicineCounts) {
			result += medicine + ", ";
		}
		return result;
	}

	/**
	 * Implements the update method which is called whenever it is notified by the RandomEventGenerator class which Crew
	 * observes. Whenever the generator object of RandomEventGenerator notifies it observers, this method is called
	 * which uses the included argument to determine what needs to be updated in Crew
	 * @param generator The RandomEventGenerator that Crew observes
	 * @param arg The argument which could be an ArrayList of CrewMembers, a FoodItem, a MedicalItem, or a double
	 */
	@Override
	public void update(Observable generator, Object arg) {
		if (arg instanceof ArrayList<?>) {
			ArrayList<CrewMember> newlyInfected;
			newlyInfected = (ArrayList<CrewMember>)arg;
			for (CrewMember crewMember: newlyInfected) {
				crewMember.setHasSpacePlague(true);
			}
		}
		else if (arg instanceof FoodItem) {
			FoodItem stolenFood;
			stolenFood = (FoodItem)arg;
			foodItems.remove(stolenFood);
		}
		else if (arg instanceof MedicalItem) {
			MedicalItem stolenMedicine;
			stolenMedicine = (MedicalItem)arg;
			medicalItems.remove(stolenMedicine);
		}
		else {
			double damage;
			damage = (double)arg;
			if (ship.getShieldLevel() - damage < 0) {
				ship.setShieldLevel(0);
			}
			else {
				ship.setShieldLevel(ship.getShieldLevel() - damage);
			}
		}
	}
	
	/**
	 * For each crew member, it calls the endOfDayAdjustments method from CrewMember to adjust their statistics as necessary.
	 * If any crew members are dead after the adjustments, they are removed from the list of crew members
	 */
	public void updateStatistics() {
		String result = "";
		ArrayList<CrewMember> crewCopy = new ArrayList<CrewMember>(getCrew());
		for (CrewMember person: crewCopy) {
			String warnings = person.endOfDayAdjustments();
			result += warnings;
			if (person.getIsDead() == true) {
				removeCrewMember(person);
			}
		}
		environment.setAdjustmentsMessage(result);
	}
}


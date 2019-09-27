package main;

import java.util.ArrayList;

import main.CrewMemberTypes.Bodyguard;
import main.CrewMemberTypes.Engineer;
import main.CrewMemberTypes.Explorer;
import main.CrewMemberTypes.Mechanic;
import main.CrewMemberTypes.Scavenger;
import main.CrewMemberTypes.Survivalist;
import main.FoodItems.Ration;
import main.FoodItems.Salad;
import main.GuiWindows.ChooseActionWindow;
import main.GuiWindows.ChooseCrewMemberWindow;
import main.GuiWindows.ConsumeFoodWindow;
import main.GuiWindows.CrewMemberStatusWindow;
import main.GuiWindows.FoodItemWindow;
import main.GuiWindows.FoodOrMedicalWindow;
import main.GuiWindows.GameOverWindow;
import main.GuiWindows.IntroWindow;
import main.GuiWindows.InventoryWindow;
import main.GuiWindows.MainGameWindow;
import main.GuiWindows.MarketWindow;
import main.GuiWindows.MedicalItemWindow;
import main.GuiWindows.NewPlanetWindow;
import main.GuiWindows.OutpostWindow;
import main.GuiWindows.SetupWindow;
import main.GuiWindows.ShipStatusWindow;
import main.GuiWindows.StatementWindow;
import main.GuiWindows.UseMedicalWindow;
import main.GuiWindows.WarningWindow;
import main.MedicalItems.Bandage;
import main.MedicalItems.MedPack;

/**
 * This class implements the game itself. It calls the methods of other classes to perform the main features of the game, such
 * as setting up the game, creating the game over statistics, and opening and closing the GUI windows whenever required.
 * 
 * @author Hamesh Ravji
 * @author Jackie Qiu
 *
 */
public class GameEnvironment {

	/**
	 * How many days the adventure will last, which determines how many parts there are to find and how much time the player
	 * has to find them
	 */
	private int days;
	/**
	 * How many parts are required to be found in order to win
	 */
	private int numPartsRequired;
	/**
	 * How many parts have been found so far
	 */
	private int numPartsFound;
	/**
	 * Keeps track of how many days have passed
	 */
	private int currentDay;
	/**
	 * Keeps track of the crew
	 */
	private Crew currentCrew;
	/**
	 * Remembers how many crew members the player started with for scoring purposes
	 */
	private int initialCrewSize;
	/**
	 * Holds a list of planets that the Crew can travel to
	 */
	private ArrayList<Planet> planetList;
	/**
	 * Holds the planet object the crew is current on
	 */
	private Planet currentPlanet;
	/**
	 * Stores what message needs to be displayed to the user if a random event occurs
	 */
	private String randomEventMessage;
	/**
	 * Stores what message needs to be displayed to the user when a crew member needs attention following the end of day adjustments
	 */
	private String adjustmentsMessage;
	/**
	 * The random event generator that is used to generate random events when necessary
	 */
	private RandomEventGenerator newEvent;


	/**
	 * A method which sets up the game by taking information from the SetupWindow according to the players preferences
	 * @param days An integer of how many days the adventure will last
	 * @param name A string for the name of the crew
	 * @param crewChoices An ArrayList of integers corresponding to what types of crew members the player has chosen
	 * @param crewNames An ArrayList of strings containing the names of each crew member
	 * @param ship A Ship object that the crew will use
	 */
	public void setup(int days, String name, ArrayList<Integer> crewChoices, ArrayList<String> crewNames, Ship ship) {	
		this.days = days;
		addPlanets();					
		currentPlanet = planetList.get(0);
		numPartsRequired = (days * 2) / 3;
		numPartsFound = 0;
		currentDay = 0; 
		ArrayList<CrewMember> crew = createCrewMembers(crewChoices, crewNames);
		int balance = 50;

		MedPack starterMedPack = new MedPack();
		Bandage starterBandage = new Bandage();
		ArrayList<MedicalItem> initialMedicalItems = new ArrayList<MedicalItem>();
		initialMedicalItems.add(starterMedPack);
		initialMedicalItems.add(starterBandage);

		Ration starterRation = new Ration();
		Salad starterSalad = new Salad();
		ArrayList<FoodItem> initialFoodItems = new ArrayList<FoodItem>();
		initialFoodItems.add(starterRation);
		initialFoodItems.add(starterSalad);

		currentDay = 0;
		currentCrew = new Crew(name, ship, balance, crew, initialMedicalItems, initialFoodItems, this);
		initialCrewSize = currentCrew.getCrew().size();
		newEvent = new RandomEventGenerator(this);
	}
	
	/**
	 * A helper method for setup() that adds all the planets to the planetList ArrayList which stores Planet objects
	 */
	public void addPlanets() {
		planetList = new ArrayList<Planet>();		

		Planet crudonus = new Planet("Crudonus", this);		
		Planet dromiaa5m = new Planet("Dromia A5M", this);		
		Planet ilrunia = new Planet("Ilrunia", this);		
		Planet nikoter = new Planet("Nikoter", this);		
		Planet phion556 = new Planet("Phion 556", this);		
		Planet tacronoe = new Planet("Tacronoe", this);		

		planetList.add(crudonus);		
		planetList.add(dromiaa5m);		
		planetList.add(ilrunia);		
		planetList.add(nikoter);		
		planetList.add(phion556);		
		planetList.add(tacronoe);
	}

	/**
	 * A helper method for setup() that creates the CrewMember objects and returns them in an ArrayList
	 * @param crewChoices An ArrayList of integers corresponding to the crew members chosen from the setup screen
	 * @param crewNames An ArrayList of Strings containing the names of the crew members chosen in the setup screen
	 * @return An ArrayList of CrewMembers
	 */
	public ArrayList<CrewMember> createCrewMembers(ArrayList<Integer> crewChoices, ArrayList<String> crewNames) {
		ArrayList<CrewMember> crew = new ArrayList<CrewMember>();
		int i = 0;
		for (int choice: crewChoices) {
			switch(choice) {
			case 1:
				Explorer crew1 = new Explorer(crewNames.get(i));
				crew.add(crew1);
				break;
			case 2:
				Engineer crew2 = new Engineer(crewNames.get(i));
				crew.add(crew2);
				break;
			case 3:
				Bodyguard crew3 = new Bodyguard(crewNames.get(i));
				crew.add(crew3);
				break;
			case 4:
				Scavenger crew4 = new Scavenger(crewNames.get(i));
				crew.add(crew4);
				break;
			case 5:
				Survivalist crew5 = new Survivalist(crewNames.get(i));
				crew.add(crew5);
				break;
			case 6:
				Mechanic crew6 = new Mechanic(crewNames.get(i));
				crew.add(crew6);
				break;
			}
			i += 1;
		}
		return crew;
	}



	/**
	 * Controls what to display to the player once the game is over. A game over
	 * occurs when all the parts have been found, the player has ran out of days,
	 * or when all crew members have died(or when one crew member remains and cannot pilot the ship alone). 
	 * It informs the player whether they won or not and calculates the score 
	 * taking into account numerous statistics and displays the final score
	 * @return A string summary of how the player did in the game
	 */
	public String gameOver() {
		String result = "";
		boolean victory  = false;
		if (numPartsFound == numPartsRequired) {
			result += "Congratulations! You found all the missing parts and have made it back to Earth\n";
			victory = true;
		}
		else if (currentCrew.getCrew().size() == 0) {
			result += "Your entire crew has died\n";
			result += "Game Over\n";
		}
		else if (currentCrew.getCrew().size() == 1) {
			result += "A crew member has died and you do not have enough crew members to pilot the ship and find all the parts\n";
			result += "Game Over\n";
		}
		else {
			result += "You have ran out of time and the adventure has concluded\n";
			result += "Game Over\n";
		}
		result += "Name of ship: " + currentCrew.getShip().getName() + "\n";
		result += "Number of days passed: " + currentDay + "\n";
		result += "Number of parts found: " + numPartsFound + "/" + numPartsRequired + "\n";
		int score = 0;
		if (victory) {
			score += 1000;
		}
		score += 500 / initialCrewSize; //Bigger bonus for a smaller crew
		score += (numPartsFound / numPartsRequired) * 500; //To give points for how many parts were found
		score += (currentCrew.getCrew().size() / initialCrewSize) * 500; //Bonus for keeping crew members alive
		score += 50 * days; //Bonus for longer games
		result += "Final score: " + score;
		return result;
	}

	public void launchIntroWindow() {
		IntroWindow introWindow = new IntroWindow(this);
	}

	public void closeIntroWindow(IntroWindow introWindow) {
		introWindow.closeWindow();
		launchSetupWindow();
	}

	public void launchSetupWindow() {
		SetupWindow setupWindow = new SetupWindow(this);
	}

	public void closeSetupWindow(SetupWindow setupWindow) {
		setupWindow.closeWindow();
		launchMainGameWindow();
	}

	public void launchMainGameWindow() {
		MainGameWindow mainGameWindow = new MainGameWindow(this);
	}

	public void closeMainGameWindow(MainGameWindow mainGameWindow) {
		mainGameWindow.closeWindow();
	}

	public void launchCrewMemberStatusWindow() {
		CrewMemberStatusWindow crewMemberStatusWindow = new CrewMemberStatusWindow(this);
	}

	public void closeCrewMemberStatusWindow(CrewMemberStatusWindow crewMemberStatusWindow) {
		crewMemberStatusWindow.closeWindow();
		launchMainGameWindow();
	}

	public void launchShipStatusWindow() {
		ShipStatusWindow shipStatusWindow = new ShipStatusWindow(this);
	}

	public void closeShipStatusWindow(ShipStatusWindow shipStatusWindow) {
		shipStatusWindow.closeWindow();
		launchMainGameWindow();
	}

	public void launchOutpostWindow() {
		OutpostWindow outpostWindow = new OutpostWindow(this);
	}

	public void closeOutpostWindow(OutpostWindow outpostWindow) {
		outpostWindow.closeWindow();
	}

	public void launchInventoryWindow() {
		InventoryWindow inventoryWindow = new InventoryWindow(this);
	}

	public void closeInventoryWindow(InventoryWindow inventoryWindow) {
		inventoryWindow.closeWindow();
		launchOutpostWindow();
	}

	public void launchMarketWindow() {
		MarketWindow marketWindow = new MarketWindow(this);
	}

	public void closeMarketWindow(MarketWindow marketWindow) {
		marketWindow.closeWindow();
	}

	public void launchFoodItemWindow() {
		FoodItemWindow foodItemWindow = new FoodItemWindow(this);
	}

	public void closeFoodItemWindow(FoodItemWindow foodItemWindow) {
		foodItemWindow.closeWindow();
		launchMarketWindow();
	}

	public void launchMedicalItemWindow() {
		MedicalItemWindow medicalItemWindow = new MedicalItemWindow(this);
	}

	public void closeMedicalItemWindow(MedicalItemWindow medicalItemWindow) {
		medicalItemWindow.closeWindow();
		launchMarketWindow();
	}

	public void launchGameOverWindow() {
		GameOverWindow gameOverWindow = new GameOverWindow(this);
	}

	public void closeGameOverWindow(GameOverWindow gameOverWindow) {
		gameOverWindow.closeWindow();
	}

	public void launchWarningWindow() {
		WarningWindow warningWindow = new WarningWindow(this);
	}

	public void closeWarningWindow(WarningWindow warningWindow) {
		warningWindow.closeWindow();
		launchMainGameWindow();
	}

	public void launchChooseCrewMemberWindow() {
		ChooseCrewMemberWindow chooseCrewMemberWindow = new ChooseCrewMemberWindow(this);
	}

	public void closeChooseCrewMemberWindow(ChooseCrewMemberWindow chooseCrewMemberWindow) {
		chooseCrewMemberWindow.closeWindow();
	}

	public void launchNewPlanetWindow(ArrayList<CrewMember> remainingCrewMembers, CrewMember member) {
		NewPlanetWindow newPlanetWindow = new NewPlanetWindow(this, remainingCrewMembers, member);
	}

	public void closeNewPlanetWindow(NewPlanetWindow newPlanetWindow) {
		newPlanetWindow.closeWindow();
	}

	public void launchChooseActionWindow(CrewMember chosenCrewMember) {
		ChooseActionWindow chooseActionWindow = new ChooseActionWindow(this, chosenCrewMember);
	}

	public void closeChooseActionWindow(ChooseActionWindow chooseActionWindow) {
		chooseActionWindow.closeWindow();
	}

	public void launchFoodOrMedicalWindow(CrewMember chosenCrewMember) {
		FoodOrMedicalWindow foodOrMedicalWindow = new FoodOrMedicalWindow(this, chosenCrewMember);
	}

	public void closeFoodOrMedicalWindow(FoodOrMedicalWindow foodOrMedicalWindow) {
		foodOrMedicalWindow.closeWindow();
	}

	public void launchConsumeFoodWindow(CrewMember chosenCrewMember) {
		ConsumeFoodWindow consumeFoodWindow = new ConsumeFoodWindow(this, chosenCrewMember);
	}

	public void closeConsumeFoodWindow(ConsumeFoodWindow consumeFoodWindow) {
		consumeFoodWindow.closeWindow();
	}

	public void launchUseMedicalWindow(CrewMember chosenCrewMember) {
		UseMedicalWindow useMedicalWindow = new UseMedicalWindow(this, chosenCrewMember);
	}

	public void closeUseMedicalWindow(UseMedicalWindow useMedicalWindow) {
		useMedicalWindow.closeWindow();
	}

	public void launchStatementWindow(String statement) {
		StatementWindow statementWindow = new StatementWindow(this, statement);
	}

	public void closeStatementWindow(StatementWindow statementWindow) {
		statementWindow.closeWindow();
	}

	public int getCurrentDay() {
		return currentDay;
	}

	public int getNumPartsFound() {
		return numPartsFound;
	}

	public int getNumPartsRequired() {
		return numPartsRequired;
	}

	public int getDays() {
		return days;
	}

	public Crew getCurrentCrew() {
		return currentCrew;
	}

	public void setCurrentDay(int newDay) {
		currentDay = newDay;
	}

	public String getRandomEventMessage() {
		return randomEventMessage;
	}

	public String getAdjustmentsMessage() {
		return adjustmentsMessage;
	}

	public void setAdjustmentsMessage(String newMessage) {
		adjustmentsMessage = newMessage;
	}

	public Planet getCurrentPlanet() {
		return currentPlanet;
	}

	public void setCurrentPlanet(Planet newPlanet) {
		currentPlanet = newPlanet;
	}

	public void setNumPartsFound(int newValue) {
		numPartsFound = newValue;
	}

	public ArrayList<Planet> getPlanetList() {
		return planetList;
	}

	public void setRandomEventMessage(String newMessage) {
		randomEventMessage = newMessage;
	}

	public RandomEventGenerator getRandomEventGenerator() {
		return newEvent;
	}

	/**
	 * Marks the starting point of the program which creates a GameEnvironment, then launches the introductory window of the game
	 * @param args the supplied command-line arguments as an array of String objects
	 */
	public static void main(String[] args) {
		GameEnvironment newGame = new GameEnvironment();
		newGame.launchIntroWindow();
	}

}

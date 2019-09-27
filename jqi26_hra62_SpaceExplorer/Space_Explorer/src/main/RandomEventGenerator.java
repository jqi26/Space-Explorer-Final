package main;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * This class is used to generate the various random events that can occur in the game. Alien pirates stealing an item and an outbreak
 * of space plague can occur at each day while going through an asteroid belt could occur each time crew travels to a new planet. It
 * extends Observable where is it observed by Crew, notifying it whenever an event occurs
 * @author Jackie Qiu
 *
 */

public class RandomEventGenerator extends Observable {
	/**
	 * Keeps track of the current state of the game
	 */
	private GameEnvironment environment;

	/**
	 * The constructor which creates a new random event generator and sets the crew as an observer
	 * @param incomingEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public RandomEventGenerator(GameEnvironment incomingEnvironment) {
		environment = incomingEnvironment;
		addObserver(environment.getCurrentCrew());
	}

	/**
	 * A method which uses Random to generate random events each day. An item could be stolen by space pirates, one or more crew members
	 * could get space plague, or nothing at all happens. Uses a random number generator to determine which event occurs. There is a 
	 * 1/4 chance of space pirates, a 1/4 chance of space plague, and a 1/2 chance of nothing
	 */
	public void randomEvent() {
		Random rand = new Random();
		int num = rand.nextInt(100);

		if (num >= 0 && num <= 24) {
			stealItem(rand);
		}

		else if (num >= 25 && num <= 49) {
			spacePlagueInfection(rand);
		}

		else {
			environment.setRandomEventMessage("");
		}
	}
	
	
	/**
	 * Uses random to determine if a food item or medical item is stolen, if there are any food or medical items available to steal.
	 * Updates the random event message displayed to the player appropriately. Notifies the Crew observer, passing the stolen object as
	 * a parameter so that Crew can remove it from their inventory
	 * @param rand a Random object to generate random numbers
	 */
	public void stealItem(Random rand) {
		String result = "Oh no! Alien pirates have boarded " + environment.getCurrentCrew().getShip().getName() + "\n";
		if (environment.getCurrentCrew().getFoodItems().size() == 0 && environment.getCurrentCrew().getMedicalItems().size() == 0) {
			environment.setRandomEventMessage(result + "You have nothing for them to steal! They awkwardly leave the ship");
		}

		else {
			int toSteal = rand.nextInt(2);
			if (environment.getCurrentCrew().getFoodItems().size() == 0 || (toSteal == 0 && environment.getCurrentCrew().getMedicalItems().size() != 0)) {
				MedicalItem stolenMedicalItem = stealMedicalItem(rand);
				environment.setRandomEventMessage(result + "The pirates have stolen a " + stolenMedicalItem + " medical item!");
				setChanged();
				notifyObservers(stolenMedicalItem);
			}
			else {
				FoodItem stolenFoodItem = stealFoodItem(rand);
				environment.setRandomEventMessage(result + "The pirates have stolen a " + stolenFoodItem + " food item!");
				setChanged();
				notifyObservers(stolenFoodItem);
			}
		}
	}

	/**
	 * Called from the stealItem method to determine which food item is stolen
	 * @param rand a Random object to generate random numbers
	 * @return The FoodItem to steal
	 */
	public FoodItem stealFoodItem(Random rand) {
		int toSteal = rand.nextInt(environment.getCurrentCrew().getFoodItems().size());
		FoodItem stolenFoodItem = environment.getCurrentCrew().getFoodItems().get(toSteal);
		return stolenFoodItem;
	}

	/**
	 * Called from the stealItem method to determine which medical item is stolen
	 * @param rand a Random object to generate random numbers
	 * @return The MedicalItem to steal
	 */
	public MedicalItem stealMedicalItem(Random rand) {
		int toSteal = rand.nextInt(environment.getCurrentCrew().getMedicalItems().size());
		MedicalItem stolenMedicalItem = environment.getCurrentCrew().getMedicalItems().get(toSteal);
		return stolenMedicalItem;
	}
	
	/**
	 * Controls who and how many people are infected with space plague at once, using a random number generator. Updates the random
	 * event message to display who was recently infected. Notifies the Crew observer with an ArrayList of crew members that were infected
	 * so that each Crew Member can be set to having Space Plague if needed.
	 * @param rand a Random object to generate random numbers
	 */
	public void spacePlagueInfection(Random rand) {
		ArrayList<CrewMember> newlyInfected = new ArrayList<CrewMember>();
		String line = "";
		int firstInfected = rand.nextInt(environment.getCurrentCrew().getCrew().size());
		if (!environment.getCurrentCrew().getCrew().get(firstInfected).getHasSpacePlague()) {
			newlyInfected.add(environment.getCurrentCrew().getCrew().get(firstInfected));
			environment.getCurrentCrew().getCrew().get(firstInfected).setHasSpacePlague(true);
			line += environment.getCurrentCrew().getCrew().get(firstInfected).getName() + ", ";
		}

		for (CrewMember crewMember: environment.getCurrentCrew().getCrew()) {
			int getsSpacePlague = rand.nextInt(100);
			if (getsSpacePlague >= 0 && getsSpacePlague <= 24 && !crewMember.getHasSpacePlague()) {
				newlyInfected.add(crewMember);
				line += crewMember.getName() + ", ";
			}
		}

		if (newlyInfected.size() != 0) {
			environment.setRandomEventMessage("Oh no! Space Plague has hit the crew! Luckily it isn't contagious\nThese crew members have just contracted space plague: " + line + 
					"\nThose infected will continue to lose health each day. You can cure them using Space Plague Cure.");
			setChanged();
			notifyObservers(newlyInfected);
		}
		else {
			environment.setRandomEventMessage("");
		}
	}

	/**
	 * Called whenever the player chooses to go to a new planet. There is a 1/3 chance of going through an asteroid belt.
	 * The damage that it does to the ship's shield scales based on current shield level. The higher the level, the 
	 * more damage is mitigated. Notifies the Crew observer with the double damage amount to update the Ship's shield level 
	 * as appropriate
	 * @return boolean true if ship goes through asteroid belt, false otherwise.
	 */
	public boolean asteroidBelt() {
		Random rand = new Random();
		int chance = rand.nextInt(3);
		if (chance == 0) {
			double damage = 50 - (environment.getCurrentCrew().getShip().getShieldLevel() / 5);
			setChanged();
			notifyObservers(damage);
			return true;

		} else {
			return false;
		}
	}
}

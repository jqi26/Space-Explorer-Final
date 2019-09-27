package main;

import java.util.Random;

/**
 * 
 * This class implements Planets. Each planet has a name and an indicator for whether the part on that planet has been found.
 * Each planet can be searched for parts or supplies
 * 
 * @author Hamesh Ravji
 * @author Jackie Qiu
 *
 */
public class Planet {
	
	/**
	 * The name of the planet.
	 */
	private String name;
	
	/**
	 * Keeps track if the part on that planet has been found. All planets have a part which needs to be found
	 */
	private boolean partFound = false;
	
	/**
	* Keeps track of the current state of the game
	*/
	private GameEnvironment environment;
	
	/**
	 * A getter for the name of the planet.
	 * @return the name of the planet
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter for the partFound attribute.
	 * @return the partFound attribute
	 */
	public boolean getPartFound() {
		return partFound;
	}
	
	/**
	 * A setter for the partFound attribute.
	 * @param partFound the boolean value to set partFound to
	 */
	public void setPartFound(boolean partFound) {
		this.partFound = partFound;
	}
	
	/**
	 * The constructor for the Planet class.
	 * @param name The string representing the name of the planet
	 * @param incomingEnvironment The GameEnvironment which keeps track of the state of the game
	 */
	public Planet(String name, GameEnvironment incomingEnvironment) {
		this.name = name;
		this.environment = incomingEnvironment;
	}

	/**
	 * Uses random to determine what the crew member finds when searching the planet
	 * @param chosenCrewMember The CrewMember selected to perform the search
	 * @return A string stating what the crew member found while searching
	 */
	public String searchPlanet(CrewMember chosenCrewMember) {
		String result;
		Random rand = new Random();
		chosenCrewMember.setNumActions((chosenCrewMember.getNumActions())-1);
		int randomInt = rand.nextInt(100);
		double probabilityOfFind = 40 + 10*chosenCrewMember.getSearching()*((100-chosenCrewMember.getTiredness())/100);
		if (randomInt <= probabilityOfFind) {
			/* crew member finds something, we choose between parts, medical items, and food items */
			result = partsMedicalOrFood(rand, chosenCrewMember);
			
		} else {
			
			int probabilityFindsMoney = 50;
			randomInt = rand.nextInt(100);
			if (randomInt >= probabilityFindsMoney) {
				/* Money found */
				int randomMoneyAmount = environment.getCurrentCrew().addMoneyFound();
				result = chosenCrewMember.getName()+" has found $"+randomMoneyAmount+" while searching!";
			} else {
				/* Nothing found */
				result = chosenCrewMember.getName()+" has returned with... nothing.";
			}

		}
		return result;
	}
	
	/**
	 * A helper method which manages whether the crew member finds a spaceship part, a food item or a medical item
	 * @param rand The Random object used for generating random numbers
	 * @param chosenCrewMember The CrewMember selected to perform the search
	 * @return A string describing what was found
	 */
	public String partsMedicalOrFood(Random rand, CrewMember chosenCrewMember) {
		int randomInt = rand.nextInt(100);
		boolean partJustFound;
		boolean foodFound;
		int firstBound;
		
		if (partFound) {
			firstBound = 50;
			partJustFound = false;
			foodFound = (randomInt >= firstBound);
		} else {
			firstBound = 34;
			/* secondBound only used when no part has been found on the current planet yet. */
			int secondBound = 33;
			partJustFound = (randomInt >= firstBound);
			foodFound = (randomInt >= firstBound+secondBound);
		}
		
		return itemFound(chosenCrewMember, partJustFound, foodFound);
	}
	
	/**
	 * Updates the crews inventory as necessary depending on what item was found, and returns a string describing what was found
	 * @param chosenCrewMember The CrewMember selected to perform the search
	 * @param partJustFound A boolean of whether the part on this planet was just found by this crew member
	 * @param foodFound A boolean of whether a food item was found
	 * @return A string describing what was found
	 */
	public String itemFound(CrewMember chosenCrewMember, boolean partJustFound, boolean foodFound) {
		String result;
		if (partJustFound) {
			partFound = true;
			environment.setNumPartsFound(environment.getNumPartsFound()+1);
			result = chosenCrewMember.getName()+" has found a spaceship part!<br>"+environment.getCurrentCrew().getName()+" has now found "+
			environment.getNumPartsFound()+"/"+environment.getNumPartsRequired()+" spaceship parts.";
		} else if (foodFound) {
			FoodItem foodItemToAdd = environment.getCurrentCrew().addRandomFoodItem();
			result = chosenCrewMember.getName()+" has found a "+foodItemToAdd.getName()+"!";
		} else { 
			MedicalItem medicalItemToAdd = environment.getCurrentCrew().addRandomMedicalItem();
			result = chosenCrewMember.getName()+" has found a "+medicalItemToAdd.getName()+"!";
		}
		return result;
	}
	
}
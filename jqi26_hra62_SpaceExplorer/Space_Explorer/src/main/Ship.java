package main;

import java.util.Random;

/**
 * This class implements the space ship that the Crew uses to travel to other planets
 * @author Jackie Qiu
 *
 */

public class Ship {
	/**
	 * The name of the ship
	 */
	private String name;
	/**
	 * The current shield level of the ship, which must be at least 10.0 to travel
	 */
	private double shieldLevel = 100;
	
	/**
	 * The constructor of the Ship class
	 * @param name The String of the name of the ship
	 */
	public Ship(String name) {
		this.name = name;
	}
	
	/**
	 * Performs repairs on the ship's shields. The amount repaired depends
	 * on the repairing skill of the person chosen to perform repairs
	 * @param person The chosen CrewMember who will perform the repairs
	 */
	public void performRepairs(CrewMember person) {
		shieldLevel += person.getRepairing() + 10*person.getRepairing()*((100-person.getTiredness())/100);				
		if (shieldLevel > 100) {
			shieldLevel = 100;
		}
		person.setNumActions(person.getNumActions()-1);
	}
	
	/**
	 * A setter which changes the shield level when necessary, such as when
	 * going through an asteroid belt. 
	 * @param newLevel The new shield level double that the current level needs to be changed to
	 * @return The new shield level
	 */
	public double setShieldLevel(double newLevel) {
		shieldLevel = newLevel;
		return shieldLevel;
	}
	
	/**		
	 * A getter which returns the shield level of the ship
	 * @return shieldLevel of the ship		
	 */		
	public double getShieldLevel() {		
		return shieldLevel;
	}
	
	/**
	 * A getter which returns the name of the ship
	 * @return The name of the ship
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A method which returns a description of the status of the ship
	 * @return A status description
	 */
	public String status() {
		return "The current shield level of " + name + " is " + shieldLevel;
	}
	
	/**
	 * A method to pilot the ship to a random new planet. Traveling requires two crew members with actions remaining. Can potentially
	 * go through an asteroid belt, causing damage to the ship
	 * @param pilot The first CrewMember to pilot the ship
	 * @param copilot The second CrewMember to help pilot the ship
	 * @param environment The current GameEnvironment representing the state of the game
	 * @return A string describing what planet we arrived at and whether the crew went though an asteroid belt
	 */
	public String travelToNewPlanet(CrewMember pilot, CrewMember copilot, GameEnvironment environment) {
		String result = "";
		Random rand = new Random();
		environment.getPlanetList().remove(environment.getCurrentPlanet());
		environment.setCurrentPlanet(environment.getPlanetList().get(rand.nextInt(environment.getPlanetList().size())));
		pilot.setNumActions((pilot.getNumActions())-1);
		copilot.setNumActions((copilot.getNumActions())-1);
		if (environment.getRandomEventGenerator().asteroidBelt()) {
			result += "Oh no! We came across an asteroid belt and were forced to go through it<br>The shields of the ship took some damage<br>The ship's shield level is now: " + environment.getCurrentCrew().getShip().getShieldLevel();
		}
		result += "<br>Your crew, "+environment.getCurrentCrew().getName()+", is now on the planet "+environment.getCurrentPlanet().getName();
		return result;
	}
}
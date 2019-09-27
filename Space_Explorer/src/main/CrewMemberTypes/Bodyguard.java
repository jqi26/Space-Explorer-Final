package main.CrewMemberTypes;

import main.CrewMember;

/**
 * This class implements the Bodyguard CrewMember type, who excels at toughness
 * @author Hamesh Ravji
 *
 */
public class Bodyguard extends CrewMember {
	
	/**
	 * The constructor which uses the constructor of CrewMember
	 * @param name A string for the name of the crew member chosen by the player
	 */
	public Bodyguard(String name) {
		super(name, "Bodyguard", 3, 1, 1);
	}
}

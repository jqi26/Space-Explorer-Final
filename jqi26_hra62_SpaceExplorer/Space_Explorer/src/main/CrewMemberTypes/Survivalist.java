package main.CrewMemberTypes;

import main.CrewMember;

/**
 * This class implements the Survivalist CrewMember type, who is good at searching with decent toughness
 * @author Hamesh Ravji
 *
 */
public class Survivalist extends CrewMember {

	/**
	 * The constructor which uses the constructor of CrewMember
	 * @param name A string for the name of the crew member chosen by the player
	 */
	public Survivalist(String name) {
		super(name, "Survivalist", 2, 1, 2);
	}

}

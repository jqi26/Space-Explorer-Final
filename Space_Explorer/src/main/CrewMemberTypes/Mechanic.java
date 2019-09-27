package main.CrewMemberTypes;

import main.CrewMember;

/**
 * This class implements the Mechanic CrewMember type, who is good at repairing with decent toughness
 * @author Hamesh Ravji
 *
 */
public class Mechanic extends CrewMember {

	/**
	 * The constructor uses the constructor of CrewMember
	 * @param name A string for the name of the crew member chosen by the player
	 */
	public Mechanic(String name) {
		super(name, "Mechanic", 2, 2, 1);
	}

}

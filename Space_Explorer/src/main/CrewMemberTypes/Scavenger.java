package main.CrewMemberTypes;

import main.CrewMember;

/**
 * This class implements the Scavenger CrewMember type, who is equally good at searching and repairing
 * @author Hamesh Ravji
 *
 */
public class Scavenger extends CrewMember {

	/**
	 * The constructor which uses the constructor of CrewMember
	 * @param name A string for the name of the crew member chosen by the player
	 */
	public Scavenger(String name) {
		super(name, "Scavenger", 1, 2, 2);
	}

}

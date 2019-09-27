package main.CrewMemberTypes;

import main.CrewMember;

/**
 * This class implements the Engineer CrewMember type, who excels at repairing
 * @author Hamesh Ravji
 *
 */
public class Engineer extends CrewMember {

	/**
	 * The constructor which uses the constructor of CrewMember
	 * @param name A string for the name of the crew member chosen by the player
	 */
	public Engineer(String name) {
		super(name, "Engineer", 1, 3, 1);
	}

}

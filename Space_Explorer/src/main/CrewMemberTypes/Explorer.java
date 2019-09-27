package main.CrewMemberTypes;

import main.CrewMember;

/**
 * This class implement the Explorer CrewMember type, who excels at searching
 * @author Hamesh Ravji
 *
 */
public class Explorer extends CrewMember {

	/**
	 * The constructor which uses the constructor of CrewMember
	 * @param name A string for the name of the crew member chosen by the player
	 */
	public Explorer(String name) {
		super(name, "Explorer", 1, 1, 3);
	}

}

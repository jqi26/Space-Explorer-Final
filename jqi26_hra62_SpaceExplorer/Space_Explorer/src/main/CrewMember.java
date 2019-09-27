package main;
/**
 * The constructor of the superclass, which is called by subclasses of CrewMember. 
 * Each crew member has a name, health level, tiredness level, and a hunger level.
 * When a crew member dies, their isDead boolean value is updated to true.
 * Each of these crew members have an associated type, provided by the subclasses 
 * along with corresponding toughness, repairing, and searching values.
 * Each of the crew members also has an associated number of actions.
 * 
 * @author Hamesh Ravji
 * @author Jackie Qiu
 *
 */
public class CrewMember {
	
	/**
	 * The name of the CrewMember
	 */
	private String name;
	
	/**
	 * The type of CrewMember (either Engineer, Explorer, Bodyguard, Scavenger, Mechanic or Survivalist), which determines
	 * their proficiency at performing tasks
	 */
	private String type;
	
	/**
	 * The health of the CrewMember (acts as percentage, thus max is 100)
	 */
	private double health = 100;
	
	/**
	 * The tiredness level of the CrewMember (acts as percentage)
	 */
	private double tiredness = 0;
	
	/**
	 * Keeps track if the CrewMember is dead or not
	 */
	private boolean isDead = false;
	
	/**
	 * The hunger level of the CrewMember
	 */
	private double hunger = 0;
	
	/**
	 * The toughness level of the CrewMember (min=1, max=3) (dependent on type/subclass)
	 */
	private int toughness;
	
	/**
	 * The repairing level of the CrewMember (min=1, max=3) (dependent on type/subclass)
	 */
	private int repairing;
	
	/**
	 * The searching level of the CrewMember (min=1, max=3) (dependent on type/subclass)
	 */
	private int searching;
	
	/**
	 * Whether or not the CrewMember has the Space Plague status effect
	 */
	private boolean hasSpacePlague;
	
	/**		
	 * The number of actions remaining for the CrewMember (min=0, max=2). Resets to max each day	
	 */		
	private int numActions = 2;
	
	/**		
	 * A getter for the number of actions remaining for the CrewMember	
	 * @return number of actions remaining for the CrewMember		
	 */		
	public int getNumActions() {		
		return numActions;		
	}
	
	/**		
	 * A setter for the number of actions. Sets the number of actions to the new number passed through as a parameter	
	 * @param tempActions integer number of actions to set for the CrewMember		
	 */		
	public void setNumActions(int tempActions) {		
		numActions = tempActions;		
	}
	
	/**
	 * A getter for the CrewMember's name
	 * @return name of the crew member
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter for the type of CrewMember
	 * @return type of the crew member
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * A getter for the health level of the CrewMember
	 * @return health level of crew member
	 */
	public double getHealth() {
		return health;
	}
	
	/**
	 * A getter for the tiredness level of the CrewMember
	 * @return tiredness level of crew member
	 */
	public double getTiredness() {
		return tiredness;
	}
	
	/**
	 * A setter for the tiredness level of the CrewMember
	 * @param value Double to set the tiredness level to
	 */
	public void setTiredness(double value) {
		tiredness = value;
	}
	
	/**
	 * A getter for the isDead boolean
	 * @return the isDead boolean of the CrewMember object
	 */
	public boolean getIsDead() {
		return isDead;
	}
	
	/**
	 * A setter for the isDead boolean, true of crew member is dead
	 * @param value Value to set the boolean isDead to
	 */
	public void setIsDead(boolean value) {
		isDead = value;
	}
	
	/**
	 * A getter for the hunger level of the CrewMember
	 * @return hunger level of the crew member
	 */
	public double getHunger() {
		return hunger;
	}
	
	/**
	 * A getter for the toughness level of the CrewMember
	 * @return toughness level of the crew member
	 */
	public int getToughness() {
		return toughness;
	}
	
	/**
	 * A getter for the repairing level of the CrewMember
	 * @return repairing level of the crew member
	 */
	public int getRepairing() {
		return repairing;
	}
	
	/**
	 * A getter for the searching level of the CrewMember
	 * @return searching level of the crew member
	 */
	public int getSearching() {
		return searching;
	}
	
	/**
	 * The Constructor for the CrewMember, objects created during setup
	 * @param name String name of the crew member
	 * @param type A string representing the type of the crew member
	 * @param toughness toughness level integer of the crew member
	 * @param repairing repairing level integer of the crew member
	 * @param searching searching level integer of the crew member
	 */
	public CrewMember(String name, String type, int toughness, int repairing, int searching) {
		this.name = name;
		this.type = type;
		this.toughness = toughness;
		this.repairing = repairing;
		this.searching = searching;
	}
	
	/**
	 * When called, feeds the crew member with the food item. Changes hunger level accordingly, minimum hunger is 0%
	 * @param foodItem FoodItem that the crew member is about to eat
	 */
	public void feed(FoodItem foodItem) {
		if (hunger-foodItem.getNutrition() < 0) {
			hunger = 0;
		} else {
			hunger -= foodItem.getNutrition();
		}
		numActions -= 1;
	}
	
	/**
	 * When called, heals the crew member with the medical item. Changes health level accordingly, max health level is 100%
	 * @param medicalItem MedicalItem that the crew member uses to heal
	 */
	public void heal(MedicalItem medicalItem) {
		if (medicalItem.getCuresSpacePlague()) {		
			setHasSpacePlague(false);		
		}
		if (health+medicalItem.getHealAmount() > 100) {
			health = 100;
		} else {
			health += medicalItem.getHealAmount();
		}
		numActions -= 1;
	}
	
	/**
	 * Used to obtain a description of the crew members various statistics
	 * @return a String description of the crew members various statistics
	 */
	public String status() {
		String result = "Status of " + name + ":\nType: " + type + "\nHealth: " + health + "\nHunger: " + hunger + "\nTiredness: " + tiredness;
		if (hasSpacePlague == true) {
			result += "\nHas Space Plague: Yes";
		}
		else {
			result += "\nHas Space Plague: No";
		}
		return result;
	}
	
	/**
	 * A setter to set whether the crew member has Space Plague or not
	 * @param status A boolean of whether the crew member has Space Plague or not
	 */
	public void setHasSpacePlague(boolean status) {
		hasSpacePlague = status;
	}
	
	/**
	 * A getter to determine whether the crew member has Space Plague or not
	 * @return whether the crew member has Space Plague or not
	 */
	public boolean getHasSpacePlague() {
		return hasSpacePlague;
	}
	
	/**
	 * Simulates the effects of hunger and tiredness on a human, which occurs at the end of each day.
	 * Hunger and tiredness increase, the amount depending on the toughness of the crew member.
	 * If the crew member's hunger level is too high, their health will drop by an amount each day,
	 * the amount depending on toughness. Similarly, space plague will also drop health each day
	 * with the amount depending on toughness. If the health drops to or below 0, they are set to dead.
	 * The number of actions available is also reset back to 2.
	 * @return A string containing any warnings regarding the crew members statistics, such as when they
	 * are getting hungry or tired
	 */
	public String endOfDayAdjustments() {
		String result = "";
		hunger += 40 - toughness * 10;
		tiredness += 40 - toughness * 10;
		
		if (hunger > 100) {
			hunger = 100;
		}
		
		if (tiredness > 100) {
			tiredness = 100;
		}
		
		if (hasSpacePlague) {
			health -= (60 - toughness * 10);
		}
		
		if (hunger >= 80 && hunger < 90) {
			health -= (30 - toughness * 5); 
		}
		else if (hunger >= 90) {
			health -= (40 - toughness * 5); 
		}
		
		if (health <= 0) {
			setIsDead(true);
			result += name + " has died\n";
		}
		
		else {
			result += createWarningMessages();
			numActions = 2;
		}
		return result;
	}
	
	/**
	 * Adds any warnings to the string of warnings in endOfDayAdjustments() relating to when hunger or tiredness get too high, or when they
	 * have space plague
	 * @return A string of any warnings
	 */
	public String createWarningMessages() {
		String result = "";
		if (hunger >= 80) {
			result += name + " is starving! They will continue to lose health each day until they are fed\n";
		}
		if (tiredness >= 80) {
			result += name + " is exhausted! They will not perform actions with maximum efficiency until they get some sleep\n";
		}
		if (hasSpacePlague == true) {
			result += name + " has Space Plague! They will continue to lose a lot of health each day until they are cured\n";
		}
		return result;
	}
	
	/**
	 * One of the actions that the CrewMember can perform, decreases the tiredness amount by 40
	 */
	public void sleep() {
		if (tiredness <= 40) {
			tiredness = 0;
		} else {
			tiredness -= 40;
		}
		numActions -= 1;
	}
	
	/**
	 * Overrides the default toString method in order to return the name of the crew member when the CrewMember object is printed
	 * @return The name of the CrewMember
	 */
	@Override
	public String toString() {
		return name;
	}
}

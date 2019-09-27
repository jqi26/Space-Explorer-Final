package main;

/**
 * This class implements the generic superclass of a medical item, which
 * is used to heal or cure crew members by consuming them
 * @author Jackie Qiu
 *
 */

public class MedicalItem {
	/**
	 * The name of the medical item
	 */
	private String name;
	/**
	 * The amount of health that is gained when consumed
	 */
	private double healAmount;
	/**
	 * Whether or not the medical item cures Space Plague
	 */
	private boolean curesSpacePlague;
	/**
	 * The cost of the medical item
	 */
	private int price;
	
	/**
	 * The constructor of the superclass, which is called by subclasses of
	 * MedicalItem
	 * @param name The String representing the name of the medical item
	 * @param healAmount The health that is gained as a double
	 * @param curesSpacePlague A boolean of whether it has the ability to cure Space Plague
	 * @param price The integer cost of the medical item
	 */
	public MedicalItem(String name, double healAmount, boolean curesSpacePlague, int price) {
		this.name = name;
		this.healAmount = healAmount;
		this.curesSpacePlague = curesSpacePlague;
		this.price = price;
	}
	
	/**
	 * A getter to retrieve the name of the medical item
	 * @return The name of the item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter to retrieve the healing amount of the healing item
	 * @return The amount of health replenished
	 */
	public double getHealAmount() {
		return healAmount;
	}
	
	/**
	 * A getter to determine whether the healing item cures Space Plague
	 * @return Whether it cures Space Plague
	 */
	public boolean getCuresSpacePlague() {
		return curesSpacePlague;
	}
	
	/**
	 * A getter to retrieve the price of the healing item
	 * @return the price of the item
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Overrides the default equals method to enable the comparison of MedicalItem objects based on the name of the type of medicine.
	 * This is necessary to get the amount of each MedicalItem in the players inventory.
	 * @param o The other Object to compare to, which is casted to a MedicalItem
	 */
	@Override
	public boolean equals(Object o) {
		MedicalItem m;
		m=(MedicalItem)o;
		if (this.name == m.getName()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Overrides the default toString method in order to return the name of the medical item when a MedicalItem object is printed
	 * @return The name of the MedicalItem
	 */
	@Override
	public String toString() {
		return name;
	}
}

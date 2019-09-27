package main;

/**
 * This class implements the generic superclass of a food item, which
 * is used to reduce the hunger of crew members by consuming them
 * @author Jackie Qiu
 *
 */

public class FoodItem {
	/**
	 * The name of the food item
	 */
	private String name;
	/**
	 * The nutritional value of the food. Higher nutrition replenishes
	 * more health
	 */
	private double nutrition;
	/**
	 * The price of this food item
	 */
	private int price;
	
	/**
	 * The constructor of the superclass, which is called by subclasses of
	 * FoodItem
	 * @param name A string representing the name of the food item
	 * @param nutrition The nutritional double value of the food
	 * @param price The integer price of this food item
	 */
	public FoodItem(String name, double nutrition, int price) {
		this.name = name;
		this.nutrition = nutrition;
		this.price = price;
	}
	
	/**
	 * A getter to retrieve the name of the food item
	 * @return the name of the food
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter to retrieve the nutrition amount
	 * @return The nutrition of the food
	 */
	public double getNutrition() {
		return nutrition;
	}
	
	/** 
	 * A getter to retrieve the cost of the food
	 * @return the price of the food
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Overrides the default equals method to enable the comparison of FoodItem objects based on the name of the type of food.
	 * This is necessary to get the amount of each FoodItem in the players inventory.
	 * @param o The other Object to compare to, which is casted to a FoodItem
	 */
	@Override
	public boolean equals(Object o) {
		FoodItem f;
		f=(FoodItem)o;
		if (this.name == f.getName()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Overrides the default toString method in order to return the name of the food item when a FoodItem object is printed
	 * @return the name of the FoodItem
	 */
	@Override
	public String toString() {
		return name;
	}
}

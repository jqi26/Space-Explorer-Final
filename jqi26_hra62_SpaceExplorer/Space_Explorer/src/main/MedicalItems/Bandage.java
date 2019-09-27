package main.MedicalItems;

import main.MedicalItem;

/**
 * This class implements the Bandage healing item
 * 
 * @author Jackie Qiu
 *
 */

public class Bandage extends MedicalItem {
	
	/**
	 * The constructor which uses the constructor of MedicalItem
	 */
	public Bandage() {
		super("Bandage", 10, false, 10);
	}
}

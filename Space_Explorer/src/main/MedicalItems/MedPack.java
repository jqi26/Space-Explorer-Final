package main.MedicalItems;

import main.MedicalItem;

/**
 * This class implements the MedPack healing item
 * 
 * @author Jackie Qiu
 *
 */

public class MedPack extends MedicalItem {
	
	/**
	 * The constructor which uses the constructor of MedicalItem
	 */
	public MedPack() {
		super("MedPack", 50, false, 40);
	}
}

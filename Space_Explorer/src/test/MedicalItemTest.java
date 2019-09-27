package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.MedicalItems.Bandage;
import main.MedicalItems.MedPack;
import main.MedicalItems.SpacePlagueCure;

class MedicalItemTest {

	@Test
	void equalsTest() {
		Bandage bandage1 = new Bandage();
		Bandage bandage2 = new Bandage();
		MedPack medpack1 = new MedPack();
		assertTrue(bandage1.equals(bandage2));
		assertFalse(bandage1.equals(medpack1));
	}
	
	@Test
	void toStringTest() {
		SpacePlagueCure newSpacePlagueCure = new SpacePlagueCure();
		assertEquals("Space Plague Cure", newSpacePlagueCure.toString());
	}

}

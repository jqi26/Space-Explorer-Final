package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.MedicalItems.MedPack;

class MedPackTest {

	@Test
	void constructorTest() {
		MedPack newMedPack = new MedPack();
		assertEquals("MedPack", newMedPack.getName());
		assertEquals(50, newMedPack.getHealAmount());
		assertFalse(newMedPack.getCuresSpacePlague());
		assertEquals(40, newMedPack.getPrice());
	}

}

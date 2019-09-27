package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.MedicalItems.Bandage;

class BandageTest {

	@Test
	void constructorTest() {
		Bandage newBandage = new Bandage();
		assertEquals("Bandage", newBandage.getName());
		assertEquals(10, newBandage.getHealAmount());
		assertFalse(newBandage.getCuresSpacePlague());
		assertEquals(10, newBandage.getPrice());
	}

}

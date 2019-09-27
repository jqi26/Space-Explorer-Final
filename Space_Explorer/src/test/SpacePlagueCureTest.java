package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.MedicalItems.SpacePlagueCure;

class SpacePlagueCureTest {

	@Test
	void constructorTest() {
		SpacePlagueCure newSpacePlagueCure = new SpacePlagueCure();
		assertEquals("Space Plague Cure", newSpacePlagueCure.getName());
		assertEquals(0, newSpacePlagueCure.getHealAmount());
		assertTrue(newSpacePlagueCure.getCuresSpacePlague());
		assertEquals(50, newSpacePlagueCure.getPrice());
	}

}

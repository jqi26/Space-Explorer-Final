package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.CrewMemberTypes.Bodyguard;

class BodyguardTest {

	@Test
	void constructorTest() {
		Bodyguard newBodyguard = new Bodyguard("John");
		assertEquals("John", newBodyguard.getName());
		assertEquals("Bodyguard", newBodyguard.getType());
		assertEquals(3, newBodyguard.getToughness());
		assertEquals(1, newBodyguard.getRepairing());
		assertEquals(1, newBodyguard.getSearching());
	}

}

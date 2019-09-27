package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.CrewMemberTypes.Scavenger;

class ScavengerTest {

	@Test
	void constructorTest() {
		Scavenger newScavenger = new Scavenger("John");
		assertEquals("John", newScavenger.getName());
		assertEquals("Scavenger", newScavenger.getType());
		assertEquals(1, newScavenger.getToughness());
		assertEquals(2, newScavenger.getRepairing());
		assertEquals(2, newScavenger.getSearching());
	}

}

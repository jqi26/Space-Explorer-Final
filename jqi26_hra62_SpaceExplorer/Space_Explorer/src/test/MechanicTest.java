package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.CrewMemberTypes.Mechanic;

class MechanicTest {

	@Test
	void constructorTest() {
		Mechanic newMechanic = new Mechanic("John");
		assertEquals("John", newMechanic.getName());
		assertEquals("Mechanic", newMechanic.getType());
		assertEquals(2, newMechanic.getToughness());
		assertEquals(2, newMechanic.getRepairing());
		assertEquals(1, newMechanic.getSearching());
	}

}

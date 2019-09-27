package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.CrewMemberTypes.Engineer;

class EngineerTest {

	@Test
	void constructorTest() {
		Engineer newEngineer = new Engineer("John");
		assertEquals("John", newEngineer.getName());
		assertEquals("Engineer", newEngineer.getType());
		assertEquals(1, newEngineer.getToughness());
		assertEquals(3, newEngineer.getRepairing());
		assertEquals(1, newEngineer.getSearching());
	}

}

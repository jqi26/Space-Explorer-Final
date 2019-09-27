package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.CrewMemberTypes.Survivalist;

class SurvivalistTest {

	@Test
	void constructorTest() {
		Survivalist newSurvivalist = new Survivalist("John");
		assertEquals("John", newSurvivalist.getName());
		assertEquals("Survivalist", newSurvivalist.getType());
		assertEquals(2, newSurvivalist.getToughness());
		assertEquals(1, newSurvivalist.getRepairing());
		assertEquals(2, newSurvivalist.getSearching());
	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.CrewMemberTypes.Explorer;

class ExplorerTest {

	@Test
	void constructorTest() {
		Explorer newExplorer = new Explorer("John");
		assertEquals("John", newExplorer.getName());
		assertEquals("Explorer", newExplorer.getType());
		assertEquals(1, newExplorer.getToughness());
		assertEquals(1, newExplorer.getRepairing());
		assertEquals(3, newExplorer.getSearching());
	}

}

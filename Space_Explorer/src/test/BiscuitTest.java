package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.FoodItems.Biscuit;

class BiscuitTest {

	@Test
	void constructorTest() {
		Biscuit newBiscuit = new Biscuit();
		assertEquals("Biscuit", newBiscuit.getName());
		assertEquals(10, newBiscuit.getNutrition());
		assertEquals(10, newBiscuit.getPrice());
	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.FoodItems.Ration;

class RationTest {

	@Test
	void constructorTest() {
		Ration newRation = new Ration();
		assertEquals("Ration", newRation.getName());
		assertEquals(40, newRation.getNutrition());
		assertEquals(35, newRation.getPrice());
	}

}

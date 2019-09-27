package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.FoodItems.Salad;

class SaladTest {

	@Test
	void constructorTest() {
		Salad newSalad = new Salad();
		assertEquals("Salad", newSalad.getName());
		assertEquals(20, newSalad.getNutrition());
		assertEquals(20, newSalad.getPrice());
	}

}

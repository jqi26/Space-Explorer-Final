package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.FoodItems.Ramen;

class RamenTest {

	@Test
	void constructorTest() {
		Ramen newRamen = new Ramen();
		assertEquals("Ramen", newRamen.getName());
		assertEquals(25, newRamen.getNutrition());
		assertEquals(25, newRamen.getPrice());
	}

}

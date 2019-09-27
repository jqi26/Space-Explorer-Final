package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.FoodItems.Ration;
import main.FoodItems.Salad;
import main.FoodItems.SpaceFruit;

class FoodItemTest {

	@Test
	void equalsTest() {
		Ration ration1 = new Ration();
		Ration ration2 = new Ration();
		Salad salad1 = new Salad();
		assertTrue(ration1.equals(ration2));
		assertFalse(ration1.equals(salad1));
	}

	@Test
	void toStringTest() {
		SpaceFruit newSpaceFruit = new SpaceFruit();
		assertEquals("Space Fruit", newSpaceFruit.toString());
	}
}

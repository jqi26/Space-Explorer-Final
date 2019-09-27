package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.FoodItems.SpaceFruit;

class SpaceFruitTest {

	@Test
	void constructorTest() {
		SpaceFruit newSpaceFruit = new SpaceFruit();
		assertEquals("Space Fruit", newSpaceFruit.getName());
		assertEquals(50, newSpaceFruit.getNutrition());
		assertEquals(40, newSpaceFruit.getPrice());
	}

}

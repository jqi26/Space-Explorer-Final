package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.FoodItems.Pizza;

class PizzaTest {

	@Test
	void constructorTest() {
		Pizza newPizza = new Pizza();
		assertEquals("Pizza", newPizza.getName());
		assertEquals(75, newPizza.getNutrition());
		assertEquals(60, newPizza.getPrice());
	}

}

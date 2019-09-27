package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.GameEnvironment;
import main.Planet;

class PlanetTest {

	@Test
	void constructorTest() {
		GameEnvironment testEnvironment = new GameEnvironment();
		Planet earth = new Planet("Earth", testEnvironment);
		assertEquals("Earth", earth.getName());
		assertFalse(earth.getPartFound());
	}

}

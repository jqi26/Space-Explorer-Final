package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.GameEnvironment;
import main.Planet;
import main.Ship;
import main.CrewMemberTypes.Engineer;
import main.CrewMemberTypes.Survivalist;

class ShipTest {
	
	@Test
	public void testContructor() {
		Ship newShip = new Ship("Rocket Ship");
		assertEquals("Rocket Ship", newShip.getName());
		assertEquals(100, newShip.getShieldLevel());
	}
	
	@Test
	public void performRepairsTest() {
		Ship newShip = new Ship("Rocket Ship");
		newShip.setShieldLevel(50);
		Engineer newEngineer = new Engineer("John");
		newShip.performRepairs(newEngineer);
		assertEquals(83, newShip.getShieldLevel());
		assertEquals(1, newEngineer.getNumActions());
		
		newShip.setShieldLevel(0);
		newShip.performRepairs(newEngineer);
		assertEquals(33, newShip.getShieldLevel());
		assertEquals(0, newEngineer.getNumActions());
		
		Survivalist newSurvivalist = new Survivalist("James");
		newShip.setShieldLevel(50);
		newShip.performRepairs(newSurvivalist);
		assertEquals(61, newShip.getShieldLevel());
		assertEquals(1, newSurvivalist.getNumActions());
	}
		
	@Test
	public void shieldLevelCannotExceed100() {
		Ship newShip = new Ship("Rocket Ship");
		Engineer newEngineer = new Engineer("John");
		newShip.setShieldLevel(90);
		newShip.performRepairs(newEngineer);
		assertEquals(100, newShip.getShieldLevel());
		
		newShip.setShieldLevel(100);
		newShip.performRepairs(newEngineer);
		assertEquals(100, newShip.getShieldLevel());
	}
		
	@Test
	public void tirednessAffectsRepairsTest() {
		Ship newShip = new Ship("Rocket Ship");
		Engineer newEngineer = new Engineer("John");
		newShip.setShieldLevel(50);
		newShip.performRepairs(newEngineer);
		assertEquals(83, newShip.getShieldLevel());
		newShip.setShieldLevel(50);
		newEngineer.endOfDayAdjustments();
		newShip.performRepairs(newEngineer);
		assertEquals(74, newShip.getShieldLevel());
		newShip.setShieldLevel(50);
		newEngineer.endOfDayAdjustments();
		newShip.performRepairs(newEngineer);
		assertEquals(65, newShip.getShieldLevel());
		
		newEngineer.setTiredness(100);
		newShip.setShieldLevel(50);
		newShip.performRepairs(newEngineer);
		assertEquals(53, newShip.getShieldLevel());
	}
	
	@Test
	public void statusTest() {
		Ship newShip = new Ship("Rocket Ship");
		assertEquals("The current shield level of Rocket Ship is 100.0", newShip.status());
		newShip.setShieldLevel(50);
		assertEquals("The current shield level of Rocket Ship is 50.0", newShip.status());
		Engineer newEngineer = new Engineer("John");
		newShip.performRepairs(newEngineer);
		assertEquals("The current shield level of Rocket Ship is 83.0", newShip.status());
	}
	
	@Test
	public void travelToNewPlanetTest() {
		Ship newShip = new Ship("Rocket Ship");
		ArrayList<Integer> crewChoices = new ArrayList<Integer>();
		crewChoices.add(1);
		crewChoices.add(2);
		ArrayList<String> crewNames = new ArrayList<String>();
		crewNames.add("John");
		crewNames.add("Jane");
		GameEnvironment testEnvironment = new GameEnvironment();
		testEnvironment.setup(3, "Cool Crew", crewChoices, crewNames, newShip);
		Planet initialPlanet = testEnvironment.getCurrentPlanet();
		newShip.travelToNewPlanet(testEnvironment.getCurrentCrew().getCrew().get(0), 
				testEnvironment.getCurrentCrew().getCrew().get(1), testEnvironment);
		assertFalse(initialPlanet == testEnvironment.getCurrentPlanet());
		assertEquals(testEnvironment.getCurrentCrew().getCrew().get(0).getNumActions(), 1);
		assertEquals(testEnvironment.getCurrentCrew().getCrew().get(1).getNumActions(), 1);
	}

}

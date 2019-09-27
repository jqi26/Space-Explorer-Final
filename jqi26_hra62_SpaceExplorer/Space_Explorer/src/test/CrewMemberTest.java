package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.CrewMemberTypes.Bodyguard;
import main.CrewMemberTypes.Engineer;
import main.CrewMemberTypes.Explorer;
import main.CrewMemberTypes.Mechanic;
import main.CrewMemberTypes.Scavenger;
import main.CrewMemberTypes.Survivalist;
import main.FoodItems.Biscuit;
import main.MedicalItems.Bandage;
import main.MedicalItems.SpacePlagueCure;

class CrewMemberTest {

	@Test
	void feedTest() {
		Engineer newEngineer = new Engineer("John");
		newEngineer.endOfDayAdjustments();
		Biscuit newBiscuit = new Biscuit();
		assertEquals(30, newEngineer.getHunger());
		newEngineer.feed(newBiscuit);
		assertEquals(1, newEngineer.getNumActions());
		assertEquals(20, newEngineer.getHunger());
	}
		
	@Test
	void hungerCannotGoBelow0Test() {
		Biscuit newBiscuit = new Biscuit();
		Explorer newExplorer = new Explorer("James");
		assertEquals(0, newExplorer.getHunger());
		newExplorer.feed(newBiscuit);
		assertEquals(0, newExplorer.getHunger());
	}

	@Test
	void healTest() {
		Engineer newEngineer = new Engineer("John");
		newEngineer.endOfDayAdjustments();
		newEngineer.endOfDayAdjustments();
		newEngineer.endOfDayAdjustments();
		assertEquals(65.0, newEngineer.getHealth());
		Bandage newBandage = new Bandage();
		newEngineer.heal(newBandage);
		assertEquals(75.0, newEngineer.getHealth());
		assertEquals(1, newEngineer.getNumActions());
	}
	
	@Test
	void cannotHealAbove100Test() {
		Bandage newBandage = new Bandage();
		Explorer newExplorer = new Explorer("James");
		assertEquals(100.0, newExplorer.getHealth());
		newExplorer.heal(newBandage);
		assertEquals(100.0, newExplorer.getHealth());
	}
	
	@Test
	void canCureSpacePlagueTest() {
		Bodyguard newBodyguard = new Bodyguard("Jeremy");
		newBodyguard.setHasSpacePlague(true);
		assertTrue(newBodyguard.getHasSpacePlague());
		SpacePlagueCure newCure = new SpacePlagueCure();
		newBodyguard.heal(newCure);
		assertFalse(newBodyguard.getHasSpacePlague());
	}
	
	@Test
	void statusTest() {
		Engineer newEngineer = new Engineer("John");
		assertEquals("Status of John:\nType: Engineer\nHealth: 100.0\nHunger: 0.0\nTiredness: 0.0\nHas Space Plague: No", newEngineer.status());
		newEngineer.endOfDayAdjustments();
		assertEquals("Status of John:\nType: Engineer\nHealth: 100.0\nHunger: 30.0\nTiredness: 30.0\nHas Space Plague: No", newEngineer.status());
		Survivalist newSurvivalist = new Survivalist("Jess");
		newSurvivalist.setHasSpacePlague(true);
		assertEquals("Status of Jess:\nType: Survivalist\nHealth: 100.0\nHunger: 0.0\nTiredness: 0.0\nHas Space Plague: Yes", newSurvivalist.status());
	}
	
	@Test
	void endOfDayAdjustmentsTest() {
		Scavenger newScavenger = new Scavenger("John");
		String warnings = newScavenger.endOfDayAdjustments();
		assertEquals(100.0, newScavenger.getHealth());
		assertEquals(30.0, newScavenger.getHunger());
		assertEquals(30.0, newScavenger.getTiredness());
		assertFalse(newScavenger.getIsDead());
		assertEquals("", warnings);
		
		newScavenger.endOfDayAdjustments();
		assertEquals(100.0, newScavenger.getHealth());
		assertEquals(60.0, newScavenger.getHunger());
		assertEquals(60.0, newScavenger.getTiredness());
		assertFalse(newScavenger.getIsDead());
		
		String warnings2 = newScavenger.endOfDayAdjustments();
		assertEquals(65.0, newScavenger.getHealth());
		assertEquals(90.0, newScavenger.getHunger());
		assertEquals(90.0, newScavenger.getTiredness());
		assertFalse(newScavenger.getIsDead());
		assertEquals("John is starving! They will continue to lose health each day until they are fed\nJohn is exhausted! They will not perform actions with maximum efficiency until they get some sleep\n",
				warnings2);
	
		Survivalist newSurvivalist = new Survivalist("Jess");
		for (int i=0; i < 4; i++) {
			newSurvivalist.endOfDayAdjustments();
		}
		assertEquals(80.0, newSurvivalist.getHunger());
		assertEquals(80.0, newSurvivalist.getHealth());	
	}
		
	@Test
	void crewMemberDiesTest() {
		Scavenger newScavenger = new Scavenger("John");
		assertFalse(newScavenger.getIsDead());
		for (int i=0; i<4; i++) {
			newScavenger.endOfDayAdjustments();
		}
		String warnings3 = newScavenger.endOfDayAdjustments();
		assertTrue(newScavenger.getHealth() <= 0);
		assertEquals(100.0, newScavenger.getHunger());
		assertEquals(100.0, newScavenger.getTiredness());
		assertTrue(newScavenger.getIsDead());
		assertEquals("John has died\n", warnings3);
	}
		
	@Test
	void spacePlagueReducesHealthTest() {
		Mechanic newMechanic = new Mechanic("Jane");
		assertEquals(100.0, newMechanic.getHealth());
		newMechanic.setHasSpacePlague(true);
		String warnings4 = newMechanic.endOfDayAdjustments();
		assertTrue(newMechanic.getHasSpacePlague());
		assertEquals(60.0, newMechanic.getHealth());
		assertEquals(20.0, newMechanic.getHunger());
		assertEquals(20.0, newMechanic.getTiredness());
		assertFalse(newMechanic.getIsDead());
		assertEquals("Jane has Space Plague! They will continue to lose a lot of health each day until they are cured\n", warnings4);
	}
	
	@Test
	void numActionsResetTest() {
		Bodyguard newBodyguard = new Bodyguard("Jessica");
		newBodyguard.setNumActions(0);
		assertEquals(0, newBodyguard.getNumActions());
		newBodyguard.endOfDayAdjustments();
		assertEquals(2, newBodyguard.getNumActions());
	}
	
	@Test
	void sleepTest() {
		Explorer newExplorer = new Explorer("John");
		newExplorer.endOfDayAdjustments();
		newExplorer.endOfDayAdjustments();
		assertEquals(60.0, newExplorer.getTiredness());
		newExplorer.sleep();
		assertEquals(20.0, newExplorer.getTiredness());
		assertEquals(1, newExplorer.getNumActions());
	}
	
	@Test
	void tirednessCannotGoBelow0Test() {
		Explorer newExplorer = new Explorer("John");
		assertEquals(0.0, newExplorer.getTiredness());
		newExplorer.sleep();
		assertEquals(0.0, newExplorer.getTiredness());
	}
	
	@Test
	void toStringTest() {
		Scavenger newScavenger = new Scavenger("John");
		assertEquals("John", newScavenger.toString());
		
		Engineer newEngineer = new Engineer("Jane");
		assertEquals("Jane", newEngineer.toString());
	}
}

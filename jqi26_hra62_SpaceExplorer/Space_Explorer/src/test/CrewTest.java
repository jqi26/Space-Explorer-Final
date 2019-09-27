package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Crew;
import main.CrewMember;
import main.FoodItem;
import main.GameEnvironment;
import main.MedicalItem;
import main.Ship;
import main.CrewMemberTypes.Bodyguard;
import main.CrewMemberTypes.Engineer;
import main.CrewMemberTypes.Explorer;
import main.FoodItems.Biscuit;
import main.FoodItems.Pizza;
import main.FoodItems.Ramen;
import main.MedicalItems.Bandage;
import main.MedicalItems.SpacePlagueCure;

class CrewTest {
	
	private Crew newCrew;
	private GameEnvironment newGame;
	private Engineer newEngineer;
	private Explorer newExplorer;
	
	@BeforeEach
	void init() {
		newEngineer = new Engineer("John");
		newExplorer = new Explorer("James");
		ArrayList<CrewMember> crewMembers = new ArrayList<CrewMember>();
		crewMembers.add(newEngineer);
		crewMembers.add(newExplorer);
		Biscuit newBiscuit = new Biscuit();
		Bandage newBandage = new Bandage();
		ArrayList<FoodItem> initialFoodItems = new ArrayList<FoodItem>();
		ArrayList<MedicalItem> initialMedicalItems = new ArrayList<MedicalItem>();
		initialFoodItems.add(newBiscuit);
		initialMedicalItems.add(newBandage);
		newGame = new GameEnvironment();
		Ship newShip = new Ship("Rocket Ship");
		newCrew = new Crew("SENG201", newShip, 50, crewMembers, initialMedicalItems, initialFoodItems, newGame);
	}

	@Test
	void constructorTest() {
		assertEquals("SENG201", newCrew.getName());
		assertEquals(50, newCrew.getBalance());
		assertEquals("The current shield level of Rocket Ship is 100.0", newCrew.getShip().status());
		ArrayList<CrewMember> crewMembers = new ArrayList<CrewMember>();
		crewMembers.add(newEngineer);
		crewMembers.add(newExplorer);
		Biscuit newBiscuit = new Biscuit();
		Bandage newBandage = new Bandage();
		ArrayList<FoodItem> initialFoodItems = new ArrayList<FoodItem>();
		ArrayList<MedicalItem> initialMedicalItems = new ArrayList<MedicalItem>();
		initialFoodItems.add(newBiscuit);
		initialMedicalItems.add(newBandage);
		assertEquals(initialFoodItems, newCrew.getFoodItems());
		assertEquals(initialMedicalItems, newCrew.getMedicalItems());
		assertEquals(crewMembers, newCrew.getCrew());
	}
	
	@Test
	void buyFoodItemTest() {
		newCrew.setBalance(150);
		Pizza newPizza = new Pizza();
		newCrew.buyItem(newPizza);
		ArrayList<FoodItem> toCompare = new ArrayList<FoodItem>();
		Biscuit newBiscuit = new Biscuit();
		toCompare.add(newBiscuit);
		toCompare.add(newPizza);
		assertEquals(toCompare, newCrew.getFoodItems());
		assertEquals(90, newCrew.getBalance());
	}
	
	@Test
	void buyMedicalItemTest() {
		newCrew.setBalance(90);
		SpacePlagueCure newSpacePlagueCure = new SpacePlagueCure();
		newCrew.buyItem(newSpacePlagueCure);
		ArrayList<MedicalItem> toCompare2 = new ArrayList<MedicalItem>();
		Bandage newBandage = new Bandage();
		toCompare2.add(newBandage);
		toCompare2.add(newSpacePlagueCure);
		assertEquals(toCompare2, newCrew.getMedicalItems());
		assertEquals(40, newCrew.getBalance());
	}
	
	@Test
	void showInventory() {
		assertEquals("Current balance: $50\nFood items: Biscuit(1), \nMedical items: Bandage(1), ", newCrew.showInventory());
		Ramen newRamen = new Ramen();
		newCrew.buyItem(newRamen);
		assertEquals("Current balance: $25\nFood items: Biscuit(1), Ramen(1), \nMedical items: Bandage(1), ", newCrew.showInventory());
		Bandage newBandage = new Bandage();
		newCrew.buyItem(newBandage);
		assertEquals("Current balance: $15\nFood items: Biscuit(1), Ramen(1), \nMedical items: Bandage(2), ", newCrew.showInventory());
	}
	
	@Test
	void removeCrewMemberTest() {
		newCrew.removeCrewMember(newExplorer);
		ArrayList<CrewMember> remainingCrewMembers = new ArrayList<CrewMember>();
		remainingCrewMembers.add(newEngineer);
		assertEquals(remainingCrewMembers, newCrew.getCrew());
	}
	
	@Test
	void updateStatisticsTest() {
		Bodyguard newBodyguard = new Bodyguard("James");
		newCrew.removeCrewMember(newExplorer);
		newCrew.getCrew().add(newBodyguard);
		
		assertEquals(100.0, newEngineer.getHealth());
		assertEquals(0.0, newEngineer.getHunger());
		assertEquals(0.0, newEngineer.getTiredness());
		assertEquals(100.0, newEngineer.getHealth());
		assertEquals(0.0, newBodyguard.getHunger());
		assertEquals(0.0, newBodyguard.getTiredness());
		
		newCrew.updateStatistics();
		assertEquals(100.0, newEngineer.getHealth());
		assertEquals(30.0, newEngineer.getHunger());
		assertEquals(30.0, newEngineer.getTiredness());
		assertEquals(100.0, newEngineer.getHealth());
		assertEquals(10.0, newBodyguard.getHunger());
		assertEquals(10.0, newBodyguard.getTiredness());
		
		newCrew.updateStatistics();
		newCrew.updateStatistics();
		assertEquals("John is starving! They will continue to lose health each day until they are fed\nJohn is exhausted! They will not perform actions with maximum efficiency until they get some sleep\n",
				newGame.getAdjustmentsMessage());
		newCrew.updateStatistics();
		newCrew.updateStatistics();
		assertEquals("John has died\n", newGame.getAdjustmentsMessage());
		ArrayList<CrewMember> remainingCrewMembers = new ArrayList<CrewMember>();
		remainingCrewMembers.add(newBodyguard);
		assertEquals(remainingCrewMembers, newCrew.getCrew());
	}
	
	@Test
	void addMoneyFoundTest() {
		int oldBalance = newCrew.getBalance();
		newCrew.addMoneyFound();
		assertTrue(oldBalance < newCrew.getBalance());
	}
	
	@Test 
	void addRandomFoodItemTest() {
		ArrayList<FoodItem> oldFoodList = new ArrayList<>(newCrew.getFoodItems());
		newCrew.addRandomFoodItem();
		assertTrue(oldFoodList.size() < newCrew.getFoodItems().size());
	}
	
	@Test 
	void addRandomMedicalItemTest() {
		ArrayList<MedicalItem> oldMedicineList = new ArrayList<>(newCrew.getMedicalItems());
		newCrew.addRandomMedicalItem();
		assertTrue(oldMedicineList.size() < newCrew.getMedicalItems().size());
	}
	
	@Test
	void updateSpacePlagueOutbreakTest() {
		assertFalse(newEngineer.getHasSpacePlague());
		assertFalse(newExplorer.getHasSpacePlague());
		ArrayList<CrewMember> newlyInfected = new ArrayList<CrewMember>();
		newlyInfected.add(newEngineer);
		newlyInfected.add(newExplorer);
		newCrew.update(newGame.getRandomEventGenerator(), newlyInfected);
		assertTrue(newEngineer.getHasSpacePlague());
		assertTrue(newExplorer.getHasSpacePlague());
		
		newEngineer.setHasSpacePlague(false);
		newExplorer.setHasSpacePlague(false);
		newlyInfected.remove(1);
		newCrew.update(newGame.getRandomEventGenerator(), newlyInfected);
		assertTrue(newEngineer.getHasSpacePlague());
		assertFalse(newExplorer.getHasSpacePlague());
	}
	
	@Test
	void updateStolenFoodTest() {
		ArrayList<FoodItem> empty = new ArrayList<FoodItem>();
		Biscuit newBiscuit = new Biscuit();
		newCrew.update(newGame.getRandomEventGenerator(), newBiscuit);
		assertEquals(empty, newCrew.getFoodItems());
	}
	
	@Test
	void updateStolenMedicineTest() {
		ArrayList<MedicalItem> empty = new ArrayList<MedicalItem>();
		Bandage newBandage = new Bandage();
		newCrew.update(newGame.getRandomEventGenerator(), newBandage);
		assertEquals(empty, newCrew.getMedicalItems());
	}
	
	@Test
	void updateAsteroidBeltDamageTest() {
		assertEquals(100, newCrew.getShip().getShieldLevel());
		newCrew.update(newGame.getRandomEventGenerator(), 20.0);
		assertEquals(80, newCrew.getShip().getShieldLevel());
	}
	
	@Test
	void shipShieldsCannotGoBelow0Test() {
		assertEquals(100, newCrew.getShip().getShieldLevel());
		newCrew.update(newGame.getRandomEventGenerator(), 110.0);
		assertEquals(0, newCrew.getShip().getShieldLevel());
	}
}

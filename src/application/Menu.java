package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.ItemsDao;
import dao.CharactersDao;
import entity.Character;

public class Menu {

	private CharactersDao charactersDao = new CharactersDao();
	private ItemsDao itemsDao = new ItemsDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Characters",
			"Display Specific Character Details",
			"Display Specific Characters Loot",
			"Display Loot Bank",
			"Create Character",
			"Generate Loot",
			"Assign Loot to Character",
			"Remove Loot from Character",
			"Delete Character",
			"Delete Item");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			try {
				if (selection.equals("1")) {
					displayCharacters();
				} else if (selection.equals("2")) {
					displayCharacter();
				} else if (selection.equals("3")) {
					displayInventory();
				} else if (selection.equals("4")) {
					displayLootBank();
				} else if (selection.equals("5")) {
					createCharacter();
				} else if (selection.equals("6")) {
					generateLoot();
				} else if (selection.equals("7")) {
					assignLootToCharacter();
				} else if (selection.equals("8")) {
					removeLootFromCharacter();
				} else if (selection.equals("9")) {
					deleteCharacter();
				} else if (selection.equals("10")) {
					deleteItem();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Press Enter to continue...");
			scanner.nextLine();
		} while (!selection.equals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an Option:\n-----------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayCharacters() throws SQLException {
		charactersDao.getCharacters();
	}
	
	private void displayCharacter() throws SQLException {
		Character character = charactersDao.getCharacterByID(scanner.nextInt());
		System.out.println("Name: " + character.getName() + "  Race: " + character.getRace() + "  Class: " + character.getCaste());
	}
	
	private void displayInventory() throws SQLException {
		System.out.println("Enter Character ID: ");
		int id = scanner.nextInt();
		charactersDao.getCharacterInventoryByCharacterID(id);
	}
	
	private void displayLootBank() throws SQLException {
		itemsDao.getLootBank();
		
	}
	
	private void createCharacter() throws SQLException {
		System.out.println("Enter Name: ");
		String name = scanner.nextLine();	
		System.out.println("Enter Race: ");
		String race = scanner.nextLine();	
		System.out.println("Enter Class: ");
		String caste = scanner.nextLine();
		charactersDao.createCharacter(name, race, caste);
	}
	
	private void generateLoot() throws SQLException {
		String quality = itemsDao.randomQuality();
		String item = itemsDao.RW();
		String plus_stat = itemsDao.randomAttribute();
		itemsDao.createWeapon(quality, item, plus_stat);
	}
	
	private void assignLootToCharacter() throws SQLException {
		System.out.println("Enter Character ID: ");
		int character_id = scanner.nextInt();
		System.out.println("Enter Weapon ID");
		int id = scanner.nextInt();
		itemsDao.assignOwnership(character_id, id);
	}
	
	private void removeLootFromCharacter() throws SQLException {
		System.out.println("Enter Character ID: ");
		int id = scanner.nextInt();
		itemsDao.removeOwnership(id);
	}
	
	private void deleteCharacter() throws SQLException {
		charactersDao.deleteCharacter(scanner.nextInt());
	}
	
	private void deleteItem() throws SQLException {
		System.out.println("Enter Weapon ID: ");
		int id = scanner.nextInt();
		itemsDao.deleteItem(id);
	}
}

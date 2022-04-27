package tietokannat;

import java.util.List;
import java.util.Scanner;

public class ShoppingListApp {
	private static ShoppingListItemDao dao = new JDBCShoppingListItemDao();

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the shopping list app!\n");
		listCommands();
		System.out.println();

		boolean running = true;

		while (running) {
			System.out.printf("> ");
			String command = input.next();
			String parameter = input.nextLine().trim();

			switch (command) {
			case "help":
				System.out.println();
				listCommands();
				System.out.println();
				break;
			case "list":
				System.out.println();
				listAllItems();
				break;
			case "add":
				System.out.println();
				addItem(parameter);
				System.out.println();
				break;
			case "remove":
				System.out.println();
				removeItem(parameter);
				System.out.println();
				break;
			case "get":
				System.out.println();
				long id = Long.parseLong(parameter);
				getItem(id);
				System.out.println();
				break;
			case "quit":
				running = false;
				System.out.println();
				break;
			default:
				System.out.println("\nUnknown command");
				break;
			}

		}

		System.out.println("Happy shopping!");
		input.close();

	}

	private static void listCommands() {
		System.out.println("Available commands:\n" + " help\n" + " list\n" + " add [product name]\n"
				+ " remove [product name]\n" + " get [product id]\n" + " quit");
	}

	private static void listAllItems() {
		List<ShoppingListItem> items = dao.getAllItems();
		int row = 0;

		// lasketaan tietokannan pisimmän rivin pituus
		for (ShoppingListItem item : items) {
			if (item.getTitle().length() > row) {
				row = item.getTitle().length();
			}
		}

		// tulostetaan tietokanta laatikkorakenteeseen pisimmän rivin mukaan
		if (row == 0) {
			System.out.println("\nList is empty\n");
		} else if (row < 9) {
			printFrame(5, 8, 0, 8);
		} else {
			printFrame(row, row + 3, row - 5, row + 3);
		}
	}

	private static void addItem(String title) {
		ShoppingListItem newItem = new ShoppingListItem(title);

		if (dao.addItem(newItem)) {
			System.out.println("Successfully added " + title);
		} else {
			System.out.println("Product already exists");
		}
	}

	private static void removeItem(String title) {
		ShoppingListItem newItem = new ShoppingListItem(title);

		if (dao.removeItem(newItem)) {
			System.out.println("Successfully removed " + title);
		} else {
			System.out.println("Product not found");
		}
	}

	private static void getItem(long id) {
		ShoppingListItem result = dao.getItem(id);

		if (result != null) {
			System.out.println(result.getId() + " " + result.getTitle());
		} else {
			System.out.println("ID not found");
		}
	}

	private static void printFrame(int i, int j, int k, int l) {
		// luodaan tietokannalle laatikkorakenne
		List<ShoppingListItem> items = dao.getAllItems();
		String underscore = "_";
		String upperscore = "‾";
		String space = " ";

		System.out.println(
				"Shopping list contents:\n" + space + underscore.repeat(j) + "\n|ID|Title" + space.repeat(k) + "|");

		// tulostetaan tietokanta ja lasketaan laatikoiden reunojen etäisyys
		for (ShoppingListItem item : items) {
			if (item.getId() < 10) {
				System.out.println("|" + item.getId() + space + "|" + item.getTitle()
						+ space.repeat(i - item.getTitle().length()) + "|");
			} else {
				System.out.println(
						"|" + item.getId() + "|" + item.getTitle() + space.repeat(i - item.getTitle().length()) + "|");
			}
		}
		System.out.println(space + upperscore.repeat(l));
	}

}

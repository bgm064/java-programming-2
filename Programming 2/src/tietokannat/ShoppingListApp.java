package tietokannat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShoppingListApp {
	public static final String URL = "jdbc:sqlite:D:\\sqlite\\shoppingList.sqlite";

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
				break;
			case "list":
				System.out.println();
				listAllItems();
				break;
			case "add":
				System.out.println();
				addItem(parameter);
				break;
			case "remove":
				System.out.println();
				removeItem(parameter);
				break;
			case "quit":
				running = false;
				break;
			}

			System.out.println();

		}

		System.out.println("Bye!");
		input.close();

	}

	private static void listCommands() {
		System.out.println("Available commands:\n" + " help\n" + " list\n" + " add [product name]\n"
				+ " remove [product name]\n" + " quit");
	}

	private static void listAllItems() {
		System.out.println("Shopping list contents:");

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		// muodostetaan yhteys tietokantaan
		try {
			connection = DriverManager.getConnection(URL);

			// suoritetaan kysely
			statement = connection.prepareStatement("SELECT * FROM ShoppingListItem");
			results = statement.executeQuery();

			// tulostetaan kaikki tuloksena saadut rivit
			while (results.next()) {
				long id = results.getLong("id");
				String title = results.getString("title");
				System.out.println("(" + id + ")" + " " + title);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// suljetaan resurssit käänteisessä järjestyksessä
			if (results != null) {
				try {
					results.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static void addItem(String title) {
		Connection connection = null;
		PreparedStatement statement = null;

		// muodostetaan yhteys tietokantaan
		try {
			connection = DriverManager.getConnection(URL);

			// suoritetaan kysely
			statement = connection.prepareStatement("INSERT INTO ShoppingListItem (title) VALUES (?)");
			statement.setString(1, title);
			statement.executeUpdate();

			System.out.println("Successfully added " + title);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// suljetaan resurssit käänteisessä järjestyksessä
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static void removeItem(String title) {
		Connection connection = null;
		PreparedStatement statement = null;

		// muodostetaan yhteys tietokantaan
		try {
			connection = DriverManager.getConnection(URL);

			// suoritetaan kysely
			statement = connection.prepareStatement("DELETE FROM ShoppingListItem WHERE title = ?");
			statement.setString(1, title);
			statement.executeUpdate();

			System.out.println("Successfully removed " + title);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// suljetaan resurssit käänteisessä järjestyksessä
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}

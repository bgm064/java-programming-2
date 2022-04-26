package tietokannat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// JDBCShoppingListItemDao toteuttaa rajapinnan ja sisältää konkreettisen SQL-logiikan

public class JDBCShoppingListItemDao implements ShoppingListItemDao {
	private static final String URL = "jdbc:sqlite:D:\\sqlite\\shoppingList.sqlite";

	@Override
	public List<ShoppingListItem> getAllItems() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		List<ShoppingListItem> items = new ArrayList<>();

		try {
			// muodostetaan yhteys tietokantaan
			connection = DriverManager.getConnection(URL);

			// muodostetaan ja suoritetaan kysely
			statement = connection.prepareStatement("SELECT * FROM ShoppingListItem");
			results = statement.executeQuery();

			// tulostetaan kaikki tuloksena saadut rivit
			while (results.next()) {
				long id = results.getLong("id");
				String title = results.getString("title");
				ShoppingListItem newItem = new ShoppingListItem(id, title);
				items.add(newItem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// suljetaan resurssit käänteisessä järjestyksessä
			close(results, statement, connection);
		}
		return items;
	}

	@Override
	public ShoppingListItem getItem(long id) {
		List<ShoppingListItem> items = getAllItems();

		for (ShoppingListItem item : items) {
			if (item.getId() == id) {
				return new ShoppingListItem(item.getId(), item.getTitle());
			}
		}
		return null;
	}

	@Override
	public boolean addItem(ShoppingListItem newItem) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			// tarkistetaan onko tuote jo olemassa
			List<ShoppingListItem> items = getAllItems();

			for (ShoppingListItem listItem : items) {
				if (listItem.getTitle().equalsIgnoreCase(newItem.getTitle())) {
					return false;
				}
			}

			connection = DriverManager.getConnection(URL);
			statement = connection.prepareStatement("INSERT INTO ShoppingListItem (title) VALUES (?)",
					statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newItem.getTitle());
			int count = statement.executeUpdate();

			results = statement.getGeneratedKeys();
			results.next();

			int generatedId = results.getInt(1);
			newItem.setId(generatedId);

			// tarkistetaan lisättiinkö uusi rivi
			if (count > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(results, statement, connection);
		}
		return false;

	}

	@Override
	public boolean removeItem(ShoppingListItem item) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		List<ShoppingListItem> items = getAllItems();

		try {
			connection = DriverManager.getConnection(URL);

			statement = connection.prepareStatement("DELETE FROM ShoppingListItem WHERE title = ?");

			for (ShoppingListItem listItem : items) {
				if (item.getTitle().equalsIgnoreCase(listItem.getTitle())) {
					statement.setString(1, listItem.getTitle());
					if (statement.executeUpdate() > 0) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(results, statement, connection);
		}
		return false;
	}

	private void close(ResultSet results, PreparedStatement statement, Connection connection) {
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
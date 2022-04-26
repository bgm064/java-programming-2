package tietokannat;

import java.util.List;

// ShoppingListItemDao on rajapinta, joka määrittelee, mitä operaatioita DAO-luokan on toteutettava

public interface ShoppingListItemDao {

	public List<ShoppingListItem> getAllItems();

	public ShoppingListItem getItem(long id);

	public boolean addItem(ShoppingListItem newItem);

	public boolean removeItem(ShoppingListItem item);
}
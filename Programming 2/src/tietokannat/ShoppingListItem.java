package tietokannat;

// ShoppingListItem on "Model"-luokka, eli se mallintaa yksittäistä data-alkiota

public class ShoppingListItem {

	private long id;
	private String title;

	public ShoppingListItem(String title) {
		this.title = title;
	}

	public ShoppingListItem(long id, String title) {
		this.id = id;
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

}
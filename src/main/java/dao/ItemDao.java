package dao;

import java.util.List;

import logic.Item;

public interface ItemDao {

	List<Item> list();

	void insert(Item item);

	Item detail(String id);

	void update(Item item);

	void delete(String id);

	

}

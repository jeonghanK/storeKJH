package logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class Cart {
	// itemList : ������ ��ϵ� ��ٱ��� ��ǰ
	private List<ItemSet> itemList = new ArrayList<ItemSet>();

	public List<ItemSet> getItemList() {
		return itemList;
	}

	// itemList : �߰��� ��ǰ
	public void push(ItemSet itemSet) {
		for (ItemSet is : itemList) {
			// �̹� �߰��� ������ ��ǰ id�� ���� �߰��� ��ǰ id�� ���� ��ǰ�� �־�?
			if (is.getItem().getId() == itemSet.getItem().getId()) {
				is.setQuantity(is.getQuantity() + itemSet.getQuantity()); // ������ ������ ���� �߰��� ��ǰ�� ������ ����.
				return;
			}
		}
		itemList.add(itemSet);
	}

	public boolean isEmpty() {
		return itemList == null || itemList.size() == 0;
	}

	// ���� ��ٱ��Ͽ� �ִ� ��ǰ ������ ������ ����
	public int getTotalAmount() {
		int total = 0;
		for (ItemSet is : itemList) {
			total += is.getItem().getPrice() * is.getQuantity();
		}
		return total;
	}

	public void clearAll(HttpSession session) {
		itemList = new ArrayList<ItemSet>();
		session.setAttribute("CART", this);
	}
}

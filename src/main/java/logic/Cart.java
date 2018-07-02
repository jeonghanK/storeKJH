package logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class Cart {
	// itemList : 기존에 등록된 장바구니 상품
	private List<ItemSet> itemList = new ArrayList<ItemSet>();

	public List<ItemSet> getItemList() {
		return itemList;
	}

	// itemList : 추가될 상품
	public void push(ItemSet itemSet) {
		for (ItemSet is : itemList) {
			// 이미 추가된 기존의 상품 id와 내가 추가할 상품 id가 같은 상품이 있어?
			if (is.getItem().getId() == itemSet.getItem().getId()) {
				is.setQuantity(is.getQuantity() + itemSet.getQuantity()); // 기존의 수량에 내가 추가할 상품의 수량을 더함.
				return;
			}
		}
		itemList.add(itemSet);
	}

	public boolean isEmpty() {
		return itemList == null || itemList.size() == 0;
	}

	// 현재 장바구니에 있는 상품 가격의 총합을 리턴
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

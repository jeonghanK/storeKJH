package logic;

import java.io.Serializable;
import java.util.Date;

public class SaleItem implements Serializable {
	private Integer saleId;
	private Integer saleItemId;
	private Integer itemId;
	private Integer quantity;
	private Date updateTime;
	private Item item;

	// 매개변수 없는 생성자가 반드시 필요함. mapper로 객체를 만드는데 매개변수가 있으면 만들 수 없다. 그래서 매개변수 없는 생성자를 mapper를 쓸때에 항상 만들어줘야한다.
	// 모든 객체는 생성자가 있어야 만들 수 있다.
	// 생성자를 아예 만들지 않았다면 기본적으로 컴파일러가 생성자를 만들어줘서 괜찮지만, 기존에 생성자가 있고 매개변수가 있기때문에 매개변수 없는 생성자를 만들어줘야한다.
	public SaleItem() {} 
	
	public SaleItem(Integer saleId, int saleItemId, ItemSet itemSet, Date currentTime) {
		this.saleId = saleId;
		this.saleItemId = saleItemId;
		this.item = itemSet.getItem();
		this.itemId = item.getId();
		this.updateTime = currentTime;
		this.quantity = itemSet.getQuantity();
	}

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public Integer getSaleItemId() {
		return saleItemId;
	}

	public void setSaleItemId(Integer saleItemId) {
		this.saleItemId = saleItemId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "SaleItem [saleId=" + saleId + ", saleItemId=" + saleItemId + ", itemId=" + itemId + ", quantity="
				+ quantity + ", item=" + item + "]";
	}
}

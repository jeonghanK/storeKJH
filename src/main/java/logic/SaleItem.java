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

	// �Ű����� ���� �����ڰ� �ݵ�� �ʿ���. mapper�� ��ü�� ����µ� �Ű������� ������ ���� �� ����. �׷��� �Ű����� ���� �����ڸ� mapper�� ������ �׻� ���������Ѵ�.
	// ��� ��ü�� �����ڰ� �־�� ���� �� �ִ�.
	// �����ڸ� �ƿ� ������ �ʾҴٸ� �⺻������ �����Ϸ��� �����ڸ� ������༭ ��������, ������ �����ڰ� �ְ� �Ű������� �ֱ⶧���� �Ű����� ���� �����ڸ� ���������Ѵ�.
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

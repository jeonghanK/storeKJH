package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale implements Serializable {
	private Integer saleId;
	private User user;
	private Date updateTime;
	private Integer amount;
	private List<SaleItem> saleItemList = new ArrayList<SaleItem>();

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public List<SaleItem> getSaleItemList() {
		return saleItemList;
	}

	public void setSaleItemList(List<SaleItem> saleItemList) {
		this.saleItemList = saleItemList;
	}

	@Override
	public String toString() {
		return "Sale [saleId=" + saleId + ", user=" + user + ", amount=" + amount + "]";
	}
}

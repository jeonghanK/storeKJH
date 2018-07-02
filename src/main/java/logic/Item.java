package logic;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class Item implements Serializable {
	private Integer id;
	
	// 검증을 위한 어노테이션들
	@NotEmpty(message = "상품명을 등록하세요") // null + 빈문자열인 경우 : 파라미터 자체에 name이라는 파라미터가없으면 null, 스페이스바만 친다거나 그러면 빈문자열. 반드시 올바른 값을 입력해야된다는 것.
	private String name;
	
	@NotNull(message = "가격을 등록하세요")
	@Min(value = 10, message = "가격은 10원이상 가능합니다.")
	@Max(value = 500000, message = "가격은 50만원이하만 가능합니다.")
	private Integer price;
	
	@NotEmpty(message = "상품내용을 등록하세요.") // 반드시 입력
	private String description;
	private String pictureUrl;
	private MultipartFile picture;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", pictureUrl=" + pictureUrl + ", picture=" + picture + "]";
	}
}

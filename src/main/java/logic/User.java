package logic;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class User implements Serializable { // 시리얼라이져블 안에는 멤버가 없고, 객체를 외부로 전송 할지말지만 결정함.
	@Size(min = 3, max = 10, message = "아이디는 3자이상 10자 이하로 입력하세요")
	private String userId;
	@Size(min = 3, max = 12, message = "비밀번호는 3자이상 10자 이하로 입력하세요")
	private String password;
	@NotEmpty(message = "사용자 이름은 필수 입니다.")
	private String userName;
	private String postcode;
	private String phoneNo;
	private String address;
	private String email;
	@Past(message = "생일은 과거 날짜만 가능합니다.")
	private Date birthDay;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", userName=" + userName + ", postcode=" + postcode
				+ ", phoneNo=" + phoneNo + ", address=" + address + ", email=" + email + "]";
	}
}

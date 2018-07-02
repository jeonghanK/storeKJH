package logic;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class User implements Serializable { // �ø���������� �ȿ��� ����� ����, ��ü�� �ܺη� ���� ���������� ������.
	@Size(min = 3, max = 10, message = "���̵�� 3���̻� 10�� ���Ϸ� �Է��ϼ���")
	private String userId;
	@Size(min = 3, max = 12, message = "��й�ȣ�� 3���̻� 10�� ���Ϸ� �Է��ϼ���")
	private String password;
	@NotEmpty(message = "����� �̸��� �ʼ� �Դϴ�.")
	private String userName;
	private String postcode;
	private String phoneNo;
	private String address;
	private String email;
	@Past(message = "������ ���� ��¥�� �����մϴ�.")
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

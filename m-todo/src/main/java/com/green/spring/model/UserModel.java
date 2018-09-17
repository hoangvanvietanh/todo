package com.green.spring.model;


import com.green.spring.entity.User;

public class UserModel {
	private int id;
	private String email;
	private String name;
	private String password;
	private String gender;
	private String birthDate;
	private String address;
	private String phone;
	private String avatar;
	
	public void formUser(User user) {
		this.setId(user.getId());
		this.setName(user.getName());
		this.setPassword(user.getPassword());
		this.setGender(user.getGender());
		this.setBirthDate(user.getBirthDate());
		this.setAddress(user.getAddress());
		this.setAvatar(user.getAvatar());
		this.setPhone(user.getPhone());
		this.setEmail(user.getEmail());
	}
	public User toUser() {
		User user = new User();
		user.setId(this.getId());
		user.setName(this.getName());
		user.setPassword(this.getPassword());
		user.setGender(this.getGender());
		user.setBirthDate(this.getBirthDate());
		user.setAddress(this.getAddress());
		user.setAvatar(this.getAvatar());
		user.setPhone(this.getPhone());
		user.setEmail(this.getEmail());
		return user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
}

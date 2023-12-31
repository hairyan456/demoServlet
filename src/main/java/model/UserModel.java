package model;

import Respository.RoleRespository;

public class UserModel {
	private int id;
	private String email,password,fullname,avatar;
	private int role_id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public UserModel() {
	}
	public UserModel(int id, String email, String password, String fullname, String avatar, int role_id) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.role_id = role_id;
	}
	
	@Override
	public String toString() {
		return fullname;
	}
	
	public String getRoleNameFromRoleID() {
		return RoleRespository.getRoleNameFromRoleID(this.role_id).substring(5,RoleRespository.getRoleNameFromRoleID(this.role_id).length());
	}
}

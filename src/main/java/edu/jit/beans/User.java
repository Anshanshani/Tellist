package edu.jit.beans;

public class User  {

	private int id;
	private String job;
	private String username;
	private String password;
	private String gender;
	private String phone;
	private String address;
	
	public User() {
	}
	public User(int id, String job, String username, String password, String gender, String phone) {
		this.id = id;
		this.job = job;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
	}
//	setter & getter : source¡ª¡ªgenCode
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public User(int id,  String username,String gender,String phone,String address) {
		this.id = id;
		this.job = job;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
	}
//	setter & getter : source¡ª¡ªgenCode
	public int GetId() {
		return id;
	}
	public void SetId(int id) {
		this.id = id;
	}
	public String GetJob() {
		return job;
	}
	public void SetJob(String job) {
		this.job = job;
	}
	public String GetUsername() {
		return username;
	}
	public void SetUsername(String username) {
		this.username = username;
	}
	public String GetPassword() {
		return password;
	}
	public void SetPassword(String password) {
		this.password = password;
	}
	public String GetGender() {
		return gender;
	}
	public void SetGender(String gender) {
		this.gender = gender;
	}
	
	public String GetPhone() {
		return phone;
	}
	public void SetPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
//	toString·½·¨
	@Override
	public String toString() {
		return "User [id=" + id + ", job=" + job + ",username=" + username + ", password=" + password + ",gender=" + gender + ",  phone="
				+ phone +",address=" + address +   "]";
	}
	
	

}


package bean;

import java.util.Date;

public class Account {
	private String username;
	private String password;
	private int role;
	private Date createTime;
	
	public Account(String username, String password, int role, Date createTime) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.createTime = createTime;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", role=" + role + ", createTime="
				+ createTime + "]";
	}
}

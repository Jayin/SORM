package io.github.jayin.test.entity;

public class Admin extends BasicEntity {

	private String adminName;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "Admin [adminName=" + adminName + super.toString()+"]";
	}

}

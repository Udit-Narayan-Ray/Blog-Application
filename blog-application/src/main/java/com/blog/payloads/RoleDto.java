package com.blog.payloads;

public class RoleDto {

	private Integer roleId;

	private String role;

	public RoleDto() {
		// TODO Auto-generated constructor stub
	}

	public RoleDto(Integer roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "RoleDto [roleId=" + roleId + ", role=" + role + "]";
	}

}

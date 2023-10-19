package org.diving.equipment.api.vo.auth;

import java.util.Set;

public class UserConnection {
	
	private String name;

	private Set<String> role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

		
}

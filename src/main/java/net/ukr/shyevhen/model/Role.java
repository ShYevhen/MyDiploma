package net.ukr.shyevhen.model;

public enum Role {
	ADMIN, OPERATOR, USER;

	@Override
	public String toString() {
		return "ROLE_" + name();
	}
}

package com.lucasian.sample;

import com.lucasian.bpm.ProcessUserFinder;

public class UserFinderSample implements ProcessUserFinder {

	public String findCurrentUser() {
		return "admin";
	}

	public boolean isUserAdmin(String user) {
		return "admin".equals(user);
	}

	public boolean isUserValid(String user) {
		return "admin".equals(user);
	}

}

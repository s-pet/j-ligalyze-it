package com.github.s_pet.jliga.client;

public class LigaMgr extends LigaMgmt {
	
	private static LigaMgr instance = null;
	
	private LigaMgr() {
		// private default constructor
	}
	
	public static LigaMgr getInstance() {
		if (instance == null) {
			instance = new LigaMgr();
		}
		return instance;
	}

}

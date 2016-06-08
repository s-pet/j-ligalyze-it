package com.github.s_pet.jliga.client.samples;

import java.util.Iterator;
import java.util.List;

import com.github.s_pet.jliga.client.LigaMgr;
import com.github.s_pet.jliga.entity.Team;

public class ListTeams {

	public static void main(String[] args) {
		
		System.out.println("List of all teams in JLiga:");
		
		LigaMgr ligaMgr = LigaMgr.getInstance();
		
		List<Team> teams = ligaMgr.getTeams();
		Iterator<Team> iter = teams.iterator();
		while (iter.hasNext()) {
			Team team = iter.next();
			System.out.println(team.getName());
		}
		
	}

}

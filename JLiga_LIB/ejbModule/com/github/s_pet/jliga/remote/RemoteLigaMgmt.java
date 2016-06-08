package com.github.s_pet.jliga.remote;

import java.util.List;

import javax.ejb.Remote;

import com.github.s_pet.jliga.entity.Season;
import com.github.s_pet.jliga.entity.Team;

@Remote
public interface RemoteLigaMgmt {
	
	/**
	 * JNDI GlassFish Lookup
	 */
	String LOOKUP_GF = "java:global/Liga/LigaMgmt";
	
	/**
	 * JNDI JBoss Lookup
	 */
	String LOOKUP_JB = "java:Liga/LigaMgmt";
	
	
	/**
	 * Store season in database.
	 * 
	 * @param season The season to be stored.
	 * 
	 * @return The stored season.
	 */
	Season storeSeason(Season season);
	
	
	/**
	 * Retrieve season by it' name.
	 * 
	 * @param name Name of the season to retrieve.
	 * 
	 * @return The season with the specified name or null.
	 */
	Season getSeason(String name);
	
	
	/**
	 * Retrieve a list of all seasons.
	 * 
	 * @return List of all seasons.
	 */
	List<Season> getSeasons();
	
	
	/**
	 * Store team in database.
	 * 
	 * @param team The team to be stored.
	 *
	 * @return The stored team.
	 */
	Team storeTeam(Team team);

	
	/**
	 * Retrieve count of teams.
	 * 
	 * @return The count of teams.
	 */
	long countTeams();
	
	
	/**
	 * Retrieve a list of all teams.
	 * 
	 * @return List of all teams.
	 */
	List<Team> getTeams();
	
	
	/**
	 * Retrieve a team by it's name.
	 * 
	 * @param name The name of the team.
	 * 
	 * @return The team with the specified name or null.
	 */
	Team getTeam(String name);
	

}

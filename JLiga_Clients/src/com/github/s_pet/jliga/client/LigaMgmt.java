package com.github.s_pet.jliga.client;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.github.s_pet.jliga.entity.Season;
import com.github.s_pet.jliga.entity.Team;
import com.github.s_pet.jliga.remote.RemoteLigaMgmt;

public class LigaMgmt implements RemoteLigaMgmt {
		
	private RemoteLigaMgmt remote = null;
	
	
	protected LigaMgmt() {
		initRemoteContext();
	}
	
	private void initRemoteContext() {
		Context ctx;
		try {
			ctx = new InitialContext();
			remote = (RemoteLigaMgmt) ctx.lookup(RemoteLigaMgmt.LOOKUP_GF);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Team storeTeam(Team team) {
		return remote.storeTeam(team);
	}
	
	@Override
	public Team getTeam(String name) {
		return remote.getTeam(name);
	}

	@Override
	public List<Team> getTeams() {
		return remote.getTeams();
	}

	
	@Override
	public long countTeams() {
		return remote.countTeams();
	}

	@Override
	public Season storeSeason(Season season) {
		return remote.storeSeason(season);
	}

	@Override
	public Season getSeason(String name) {
		return remote.getSeason(name);
	}

	@Override
	public List<Season> getSeasons() {
		return remote.getSeasons();
	}

}

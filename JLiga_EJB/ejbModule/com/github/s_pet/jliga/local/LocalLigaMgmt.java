package com.github.s_pet.jliga.local;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.github.s_pet.jliga.entity.Match;
import com.github.s_pet.jliga.entity.Season;
import com.github.s_pet.jliga.entity.Team;
import com.github.s_pet.jliga.local.interceptor.StatefulSessionBeanInterceptor;
import com.github.s_pet.jliga.remote.RemoteLigaMgmt;

@Stateful(mappedName=RemoteLigaMgmt.LOOKUP_GF, name=RemoteLigaMgmt.LOOKUP_JB)
@Interceptors(StatefulSessionBeanInterceptor.class)
public class LocalLigaMgmt implements RemoteLigaMgmt {
	
	@PersistenceContext(unitName = "Liga")
	private EntityManager em;


	@Override
	public Team storeTeam(Team team) {
		em.persist(team);
		return team;
	}
	
	
	@Override
	public Team getTeam(String name) {
		Query query = em.createNamedQuery("getTeamByName", Team.class);
		query.setParameter("name", name);		
		Team team = null;
		try {
			team = (Team) query.getSingleResult();
		} catch (NoResultException e) {
			team = null;
		}
		return team;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Team> getTeams() {
		Query query = em.createNamedQuery("getTeams", Team.class);
		return (List<Team>) query.getResultList();
	}

	
	@Override
	public long countTeams() {
		Query query = em.createNamedQuery("countTeams", Long.class);
		return (long) query.getSingleResult();
	}


	@Override
	public Season storeSeason(Season season) {
		em.persist(season);
		return season;
	}


	@Override
	public Season getSeason(String name) {
		Query query = em.createNamedQuery("getSeasonByName", Team.class);
		query.setParameter("name", name);		
		Season season = null;
		try {
			season = (Season) query.getSingleResult();
		} catch (NoResultException e) {
			season = null;
		}
		return season;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Season> getSeasons() {
		Query query = em.createNamedQuery("getSeasons", Season.class);
		return (List<Season>) query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> getSeasonNames() {
		Query query = em.createNamedQuery("getSeasonNames", String.class);
		return (List<String>) query.getResultList();
	}


	@Override
	public List<Team> getTeams(Season season) {
		List<Team> teams = new ArrayList<Team>();
		Iterator<Match> iter = season.getMatchdays().get(1).getMatches().iterator();
		while (iter.hasNext()) {
			Match match = iter.next();
			teams.add(match.getTeamHome());
			teams.add(match.getTeamAway());
		}
		return teams;
	}

}

package com.github.s_pet.jliga.client.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.s_pet.jliga.client.LigaMgr;
import com.github.s_pet.jliga.entity.Match;
import com.github.s_pet.jliga.entity.Matchday;
import com.github.s_pet.jliga.entity.Season;
import com.github.s_pet.jliga.entity.Team;

public class InitJLiga {
	
	private static LigaMgr ligaMgr = LigaMgr.getInstance();
	
	private static Map<String, String> replacements;
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Initializing JLiga database...");	
		replacements = new HashMap<String, String>();
		
		initJTeam("data/D1-Teams-Replacements.csv");
		initJSeason("Bundesliga 2010/2011", "data/D1-2010-2011.csv");
		initJSeason("Bundesliga 2011/2012", "data/D1-2011-2012.csv");
		initJSeason("Bundesliga 2012/2013", "data/D1-2012-2013.csv");
		initJSeason("Bundesliga 2013/2014", "data/D1-2013-2014.csv");
		initJSeason("Bundesliga 2014/2015", "data/D1-2014-2015.csv");
		initJSeason("Bundesliga 2015/2016", "data/D1-2015-2016.csv");
		
		System.out.println("Done.");
	}
	
	
	private static void initJTeam(String dataJTeam) throws IOException {	
		InputStream is = InitJLiga.class.getResourceAsStream(dataJTeam);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ( (line = br.readLine()) != null ) {
			String[] fields = line.split(",");
			Team team = new Team();
			team.setName(fields[0].trim());
			if (ligaMgr.getTeam(team.getName()) == null) {
				team = ligaMgr.storeTeam(team);
				System.out.println("Added " + team);
			}
			for (int i = 0; i < fields.length; i++) {
				replacements.put(fields[i].trim(), team.getName());
			}
		}
		br.close();
		isr.close();
		is.close();
	}
	
	private static void initJSeason(String seasonName, String dataJSeason) throws IOException {
		if (ligaMgr.getSeason(seasonName) == null) {
			Season season = new Season();
			season.setName(seasonName);
			season.setMatchdays(initMatchdays(season, dataJSeason));
			season = ligaMgr.storeSeason(season);
			System.out.println("Added " + season);
		}
	}
	
	private static Map<Integer, Matchday> initMatchdays(Season season, String dataJSeason) throws IOException {
		Map<Integer, Matchday> matchdays = new HashMap<Integer, Matchday>();
		InputStream is = InitJLiga.class.getResourceAsStream(dataJSeason);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		br.readLine(); // ignore the header line
		for (int i = 1; i <= 34; i++) {
			Matchday matchday = new Matchday();
			matchday.setNumber(i);
			matchday.setSeason(season);
			matchday.setMatches(initMatches(matchday, br));
			matchdays.put(i, matchday);
			System.out.println("Added " + matchday);
		}
		br.close();
		isr.close();
		is.close();
		return matchdays;
	}
	
	private static List<Match> initMatches(Matchday matchday, BufferedReader br) throws IOException {
		List<Match> matches = new ArrayList<Match>();
		for (int i = 1; i <= 9; i++) {
			String[] entries = br.readLine().split(",");
			Team teamHome = new Team();
			teamHome.setName(replacements.get(entries[2].trim()));
			Team teamAway = new Team();
			teamAway.setName(replacements.get(entries[3].trim()));
			Match match = new Match();
			match.setMatchday(matchday);
			match.setTeamHome(ligaMgr.getTeam(replacements.get(entries[2].trim())));
			match.setTeamAway(ligaMgr.getTeam(replacements.get(entries[3].trim())));
			match.setGoalsHome(Integer.parseInt(entries[4].trim()));
			match.setGoalsAway(Integer.parseInt(entries[5].trim()));
			matches.add(match);
			System.out.println("Added " + match);
		}
		return matches;
	}


}

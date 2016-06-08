package com.github.s_pet.jliga.client.samples;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.s_pet.jliga.client.LigaMgr;
import com.github.s_pet.jliga.entity.Match;
import com.github.s_pet.jliga.entity.Matchday;
import com.github.s_pet.jliga.entity.Season;

public class ListSeason_2015_2016 {
	
public static void main(String[] args) {
		
		System.out.println("Season \"Bundesliga 2015/2016\" with matchdays:");
		
		LigaMgr ligaMgr = LigaMgr.getInstance();
		
		Season season = ligaMgr.getSeason("Bundesliga 2015/2016");
		Map<Integer, Matchday> matchdays = season.getMatchdays();
		for (int day = 1; day <= 34; day++) {
			Matchday matchday = matchdays.get(day);
			System.out.println("Matchday " + day + ":");
			List<Match> matches = matchday.getMatches();
			Iterator<Match> iterMatches = matches.iterator();
			while (iterMatches.hasNext()) {
				Match match = iterMatches.next();
				System.out.printf("%25s - %-25s  %2d : %-2d\n", 
						match.getTeamHome().getName(), match.getTeamAway().getName(),
						match.getGoalsHome(), match.getGoalsAway());
			}
		}
	}
	

}

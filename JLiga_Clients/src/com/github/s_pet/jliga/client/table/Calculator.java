package com.github.s_pet.jliga.client.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.s_pet.jliga.client.LigaMgr;
import com.github.s_pet.jliga.entity.Match;
import com.github.s_pet.jliga.entity.Season;
import com.github.s_pet.jliga.entity.Team;


/**
 * @author soenke
 *
 */
public abstract class Calculator {
	
	/**
	 * Managing access to the JLiga database.
	 */
	private final LigaMgr ligaMgr = LigaMgr.getInstance();
	
	/**
	 * Calculate the tables of a season.
	 * 
	 * @param season the season.
	 * 
	 * @return map with the tables ordered by the matchday.
	 */
	abstract public Map<Integer, LigaTable> calculateTables(Season season);
	
	/**
	 * Do the rating for the home team.
	 * 
	 * @param entry The entry of the table.
	 * @param match The match to be rated.
	 * @return The new entry of the table.
	 */
	abstract protected TableEntry doRatingHome(TableEntry entry, Match match);
	
	/**
	 * Do the rating for the away team.
	 * 
	 * @param entry The entry of the table.
	 * @param match The match to be rated.
	 * @return The new entry of the table.
	 */
	abstract protected TableEntry doRatingAway(TableEntry entry, Match match);
	
	/**
	 * Get the zeroed table.
	 * 
	 * @param season The season.
	 * @param kind The TableKind.
	 * @return The zero table.
	 */
	final protected LigaTable getZeroTable(Season season, TableKind kind) {
		LigaTable table = new LigaTable();
		table.setSeasonName(season.getName());
		table.setSeasonDay(0);
		table.setTableKind(kind);
		List<TableEntry> entries = new ArrayList<TableEntry>();
		Iterator<Team> iter = ligaMgr.getTeams(season).iterator();
		while (iter.hasNext()) {
			TableEntry entry = new TableEntry();
			entry.setTeam(iter.next());
			entries.add(entry);
		}
		table.setTableEntries(entries);
		return table;
	}
	
	/**
	 * Calculate the table of the next day.
	 * 
	 * @param oldTable The old table.
	 * @param matches The list of matches of the matchday.
	 * @return The new table.
	 */
	final private LigaTable getTableOfNextDay(LigaTable oldTable, List<Match> matches) {
		LigaTable table = new LigaTable();
		
		table.setSeasonName(oldTable.getSeasonName());
		table.setSeasonDay(oldTable.getSeasonDay() + 1);
		table.setTableKind(oldTable.getTableKind());
		
		List<TableEntry> entries = new ArrayList<TableEntry>();
		Iterator<TableEntry> iterEntries = oldTable.getTableEntries().iterator();
		while (iterEntries.hasNext()) {
			TableEntry oldEntry = iterEntries.next();
			TableEntry entry = new TableEntry();
			entry.setTeam(oldEntry.getTeam());
			entry.setPoints(oldEntry.getPoints());
			entry.setGoals(oldEntry.getGoals());
			entry.setGoalsAgainst(oldEntry.getGoalsAgainst());
			
			Iterator<Match> iterMatches = matches.iterator();
			while (iterMatches.hasNext()) {
				Match match = iterMatches.next();
				entry = doRatingHome(entry, match);
				entry = doRatingAway(entry, match);
			}
			entries.add(entry);
		}
		table.setTableEntries(entries);
		return table;
	}

	/**
	 * Calculate the tables of a special kind for a whole season.
	 * @param season The season.
	 * @param kind The TableKind.
	 * @return The map of tables of the given kind for the given season.
	 */
	final protected Map<Integer, LigaTable> calculateTables(Season season, TableKind kind) {
		Map<Integer, LigaTable> tables = new HashMap<Integer, LigaTable>();
		tables.put(0, getZeroTable(season, kind));
		for (int day = 1; day <= season.getMatchdays().size(); day++) {
			LigaTable oldTable = tables.get(day - 1);
			List<Match> matches = season.getMatchdays().get(day).getMatches();
			tables.put(day, getTableOfNextDay(oldTable, matches));
		}
		return tables;
	}
}

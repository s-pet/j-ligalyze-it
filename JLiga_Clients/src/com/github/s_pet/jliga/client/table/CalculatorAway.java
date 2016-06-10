/**
 * 
 */
package com.github.s_pet.jliga.client.table;

import java.util.Map;

import com.github.s_pet.jliga.entity.Match;
import com.github.s_pet.jliga.entity.Season;

/**
 * @author soenke
 *
 */
public class CalculatorAway extends Calculator {

	/* (non-Javadoc)
	 * @see com.github.s_pet.jliga.client.table.Calculator#calculateTables(com.github.s_pet.jliga.entity.Season)
	 */
	@Override
	public Map<Integer, LigaTable> calculateTables(Season season) {
		return super.calculateTables(season, TableKind.AwayTable);
	}

	/* (non-Javadoc)
	 * @see com.github.s_pet.jliga.client.table.Calculator#doRatingHome(com.github.s_pet.jliga.client.table.TableEntry, com.github.s_pet.jliga.entity.Match)
	 */
	@Override
	protected TableEntry doRatingHome(TableEntry entry, Match match) {
		return entry;
	}

	/* (non-Javadoc)
	 * @see com.github.s_pet.jliga.client.table.Calculator#doRatingAway(com.github.s_pet.jliga.client.table.TableEntry, com.github.s_pet.jliga.entity.Match)
	 */
	@Override
	protected TableEntry doRatingAway(TableEntry entry, Match match) {
		TableEntry newEntry = entry;
		if (entry.getTeam().equals(match.getTeamAway())) {
			if (match.getGoalsAway() > match.getGoalsHome()) {
				newEntry.setPoints(entry.getPoints() + 3);
			}
			if (match.getGoalsAway() == match.getGoalsHome()) {
				newEntry.setPoints(entry.getPoints() + 1);
			}
			newEntry.setGoals(entry.getGoals() + match.getGoalsAway());
			newEntry.setGoalsAgainst(entry.getGoalsAgainst() + match.getGoalsHome());
		}
		return newEntry;
	}

}

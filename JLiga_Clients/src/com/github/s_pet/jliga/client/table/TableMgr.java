/**
 * 
 */
package com.github.s_pet.jliga.client.table;

import java.util.HashMap;
import java.util.Map;

import com.github.s_pet.jliga.entity.Season;


/**
 * @author soenke
 *
 */
public class TableMgr {

	private static TableMgr instance = null;
	
	private CalculatorOverall calculatorOverall;
	private CalculatorHome calculatorHome;
	private CalculatorAway calculatorAway;
	
	private Map<Season, Map<Integer, LigaTable>> overallTables;
	private Map<Season, Map<Integer, LigaTable>> homeTables;
	private Map<Season, Map<Integer, LigaTable>> awayTables;
	
	private TableMgr() {
		calculatorOverall = new CalculatorOverall();
		calculatorHome = new CalculatorHome();
		calculatorAway = new CalculatorAway();
		overallTables = new HashMap<Season, Map<Integer, LigaTable>>();
		homeTables = new HashMap<Season, Map<Integer, LigaTable>>();
		awayTables = new HashMap<Season, Map<Integer, LigaTable>>();
	}
	
	/**
	 * Get the instance of the TableMgr.
	 * 
	 * @return The instance.
	 */
	public static TableMgr getInstance() {
		if (instance == null) {
			instance = new TableMgr();
		}
		return instance;
	}
	
	/**
	 * Get the  specified table.
	 * 
	 * @param season The specified season.
	 * @param seasonDay The specified day of the season (aka matchday).
	 * @param tableKind The specified TableKind.
	 * @return The specified table.
	 */
	public LigaTable getTable(Season season, int seasonDay, TableKind tableKind) {
		LigaTable table = null;
		switch (tableKind) {
		case OverallTable:
			if (!overallTables.containsKey(season)) {
				overallTables.put(season, calculatorOverall.calculateTables(season));
			}
			table = overallTables.get(season).get(seasonDay);
			break;
		case HomeTable:
			if (!homeTables.containsKey(season)) {
				homeTables.put(season, calculatorHome.calculateTables(season));
			}
			table = homeTables.get(season).get(seasonDay);
			break;
		case AwayTable:
			if (!awayTables.containsKey(season)) {
				awayTables.put(season, calculatorAway.calculateTables(season));
			}
			table = awayTables.get(season).get(seasonDay);
			break;
		default:
			System.out.println("TableMgr recognized unsupported TableKind: " + tableKind);
			System.exit(0);
		}
		return table;
	}
}

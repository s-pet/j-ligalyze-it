package com.github.s_pet.jliga.client.table;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class LigaTable {
	
	private String seasonName;
	private int seasonDay; 
	private TableKind tableKind;
	private List<TableEntry> tableEntries;
	
	
	/**
	 * @return the seasonName
	 */
	public String getSeasonName() {
		return seasonName;
	}
	/**
	 * @param seasonName the seasonName to set
	 */
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	/**
	 * @return the seasonDay
	 */
	public int getSeasonDay() {
		return seasonDay;
	}
	/**
	 * @param day the seasonDay to set
	 */
	public void setSeasonDay(int day) {
		this.seasonDay = day;
	}
	/**
	 * @return the tableKind
	 */
	public TableKind getTableKind() {
		return tableKind;
	}
	/**
	 * @param tableKind the tableKind to set
	 */
	public void setTableKind(TableKind kind) {
		this.tableKind = kind;
	}
	/**
	 * @return the tableEntries
	 */
	public List<TableEntry> getTableEntries() {
		return tableEntries;
	}
	/**
	 * @param tableEntries the tableEntries to set
	 */
	public void setTableEntries(List<TableEntry> entries) {
		this.tableEntries = entries;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + seasonDay;
		result = prime * result + ((tableEntries == null) ? 0 : tableEntries.hashCode());
		result = prime * result + ((tableKind == null) ? 0 : tableKind.hashCode());
		result = prime * result + ((seasonName == null) ? 0 : seasonName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LigaTable)) {
			return false;
		}
		LigaTable other = (LigaTable) obj;
		if (seasonDay != other.seasonDay) {
			return false;
		}
		if (tableEntries == null) {
			if (other.tableEntries != null) {
				return false;
			}
		} else if (!tableEntries.equals(other.tableEntries)) {
			return false;
		}
		if (tableKind != other.tableKind) {
			return false;
		}
		if (seasonName == null) {
			if (other.seasonName != null) {
				return false;
			}
		} else if (!seasonName.equals(other.seasonName)) {
			return false;
		}
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Collections.sort(tableEntries);
		String s = String.format("%s, day %d, %s\n", seasonName, seasonDay, tableKind);
		int pos = 1;
		Iterator<TableEntry> iter = tableEntries.iterator();
		while (iter.hasNext()) {
			TableEntry entry = iter.next();
			s += String.format("%2d. %s\n", pos++, entry);
		}
		return s;
	}

}

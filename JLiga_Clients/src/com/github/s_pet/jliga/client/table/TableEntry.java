package com.github.s_pet.jliga.client.table;

import com.github.s_pet.jliga.entity.Team;

public class TableEntry implements Comparable<TableEntry> {
	
	private Team team;
	private int points;
	private int goals;
	private int goalsAgainst;
	
	
	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}
	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}
	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	/**
	 * @return the goals
	 */
	public int getGoals() {
		return goals;
	}
	/**
	 * @param goals the goals to set
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}
	/**
	 * @return the goalsAgainst
	 */
	public int getGoalsAgainst() {
		return goalsAgainst;
	}
	/**
	 * @param goalsAgainst the goalsAgainst to set
	 */
	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + goals;
		result = prime * result + goalsAgainst;
		result = prime * result + points;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		if (!(obj instanceof TableEntry)) {
			return false;
		}
		TableEntry other = (TableEntry) obj;
		if (goals != other.goals) {
			return false;
		}
		if (goalsAgainst != other.goalsAgainst) {
			return false;
		}
		if (points != other.points) {
			return false;
		}
		if (team == null) {
			if (other.team != null) {
				return false;
			}
		} else if (!team.equals(other.team)) {
			return false;
		}
		return true;
	}
	
	
	public int goalDifference() {
		return goals - goalsAgainst;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%-25s  %2d   %2d : %-2d ", team.getName(), points, goals, goalsAgainst);
	}
	
	
	@Override
	public int compareTo(TableEntry o) {
		if (o == null) {
			return 1;
		}
		if (points != o.points) {
			return o.points - points;
		}
		if (goalDifference() != o.goalDifference()) {
			return o.goalDifference() - goalDifference();
		}
		return o.goals - goals;
	}

	
}

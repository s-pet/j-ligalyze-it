package com.github.s_pet.jliga.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@EntityListeners(com.github.s_pet.jliga.entity.listener.JLigaEntityListener.class)
@Table(name="JMatch")
public class Match implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="MATCHDAY_ID")
	private Matchday matchday;
		
	@ManyToOne(optional=false)
	private Team teamHome;
	
	@ManyToOne(optional=false)
	private Team teamAway;
	
	@Column(nullable=true)
	private int goalsHome;
	
	@Column(nullable=true)
	private int goalsAway;

	
	/**
	 * @return the id
	 */
	@SuppressWarnings("unused")
	private int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the matchday
	 */
	public Matchday getMatchday() {
		return matchday;
	}

	/**
	 * @param matchday the matchday to set
	 */
	public void setMatchday(Matchday matchday) {
		this.matchday = matchday;
	}

	/**
	 * @return the teamHome
	 */
	public Team getTeamHome() {
		return teamHome;
	}

	/**
	 * @param teamHome the teamHome to set
	 */
	public void setTeamHome(Team teamHome) {
		this.teamHome = teamHome;
	}

	/**
	 * @return the teamAway
	 */
	public Team getTeamAway() {
		return teamAway;
	}

	/**
	 * @param teamAway the teamAway to set
	 */
	public void setTeamAway(Team teamAway) {
		this.teamAway = teamAway;
	}

	/**
	 * @return the goalsHome
	 */
	public int getGoalsHome() {
		return goalsHome;
	}

	/**
	 * @param goalsHome the goalsHome to set
	 */
	public void setGoalsHome(int goalsHome) {
		this.goalsHome = goalsHome;
	}

	/**
	 * @return the goalsAway
	 */
	public int getGoalsAway() {
		return goalsAway;
	}

	/**
	 * @param goalsAway the goalsAway to set
	 */
	public void setGoalsAway(int goalsAway) {
		this.goalsAway = goalsAway;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matchday == null) ? 0 : matchday.hashCode());
		result = prime * result + ((teamAway == null) ? 0 : teamAway.hashCode());
		result = prime * result + ((teamHome == null) ? 0 : teamHome.hashCode());
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
		if (!(obj instanceof Match)) {
			return false;
		}
		Match other = (Match) obj;
		if (matchday == null) {
			if (other.matchday != null) {
				return false;
			}
		} else if (!matchday.equals(other.matchday)) {
			return false;
		}
		if (teamAway == null) {
			if (other.teamAway != null) {
				return false;
			}
		} else if (!teamAway.equals(other.teamAway)) {
			return false;
		}
		if (teamHome == null) {
			if (other.teamHome != null) {
				return false;
			}
		} else if (!teamHome.equals(other.teamHome)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Match [" + (matchday != null ? "matchday=" + matchday + ", " : "")
				+ (teamHome != null ? "teamHome=" + teamHome + ", " : "")
				+ (teamAway != null ? "teamAway=" + teamAway : "") + "]";
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toStringWithResult() {
		return "Match [" + (matchday != null ? "matchday=" + matchday + ", " : "")
				+ (teamHome != null ? "teamHome=" + teamHome + ", " : "")
				+ (teamAway != null ? "teamAway=" + teamAway + ", " : "") + "goalsHome=" + goalsHome + ", goalsAway="
				+ goalsAway + "]";
	}
	
	
}

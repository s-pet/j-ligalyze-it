package com.github.s_pet.jliga.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@EntityListeners(com.github.s_pet.jliga.entity.listener.JLigaEntityListener.class)
@Table(name="JMatchday")
public class Matchday implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, updatable=false)
	private int number;
	
	@ManyToOne 
	@JoinColumn(name="SEASON_ID")
	private Season season;
	
	@OneToMany(mappedBy="matchday", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Match> matches;


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
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}


	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}


	/**
	 * @return the season
	 */
	public Season getSeason() {
		return season;
	}


	/**
	 * @param season the season to set
	 */
	public void setSeason(Season season) {
		this.season = season;
	}


	/**
	 * @return the matches
	 */
	public List<Match> getMatches() {
		return matches;
	}


	/**
	 * @param matches the matches to set
	 */
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((season == null) ? 0 : season.hashCode());
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
		if (!(obj instanceof Matchday)) {
			return false;
		}
		Matchday other = (Matchday) obj;
		if (number != other.number) {
			return false;
		}
		if (season == null) {
			if (other.season != null) {
				return false;
			}
		} else if (!season.equals(other.season)) {
			return false;
		}
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Matchday [number=" + number + ", " + (season != null ? "season=" + season : "") + "]";
	}
	
}

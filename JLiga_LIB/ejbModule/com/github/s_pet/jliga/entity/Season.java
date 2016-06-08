package com.github.s_pet.jliga.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@EntityListeners(com.github.s_pet.jliga.entity.listener.JLigaEntityListener.class)
@NamedQueries({
	@NamedQuery(name="getSeasons", query="select x from Season x"),
	@NamedQuery(name="getSeasonByName", query="select x from Season x where x.name = :name")
})
@Table(name="JSeason")
public class Season implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true, nullable=false, length=32)
	private String name;
	
	@OneToMany(mappedBy="season", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Map<Integer, Matchday> matchdays;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the matchdays
	 */
	public Map<Integer, Matchday> getMatchdays() {
		return matchdays;
	}

	/**
	 * @param matchdays the matchdays to set
	 */
	public void setMatchdays(Map<Integer, Matchday> matchdays) {
		this.matchdays = matchdays;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof Season)) {
			return false;
		}
		Season other = (Season) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Season [" + (name != null ? "name=" + name : "") + "]";
	}

	
	

}

package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Sport implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sportNumber;
	private String izena;
	@OneToMany(fetch=FetchType.EAGER, targetEntity=Event.class, mappedBy="sport",cascade=CascadeType.PERSIST)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Event> events=new ArrayList<Event>();
	
	public Sport() {
		super();
	}
	
	public Integer getSportNumber() {
		return sportNumber;
	}

	public void setSportNumber(Integer sportNumber) {
		this.sportNumber = sportNumber;
	}

	public Sport(String izena) {
		this.izena=izena;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public Event addEvent(String description,Date eventDate) {
		Event e = new Event(description, eventDate, this);
		this.events.add(e);
		return e;
	}

	@Override
	public String toString() {
		return this.izena;
	}
	
	@Override
	public boolean equals(Object o) {
		Sport sp = (Sport) o;
		if(sp==null) {
			return false;
		}
		return this.izena.equals(sp.getIzena());
	}
}

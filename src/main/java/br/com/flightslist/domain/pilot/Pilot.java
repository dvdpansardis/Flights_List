package br.com.flightslist.domain.pilot;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.flightslist.domain.flight.Flight;
import br.com.flightslist.domain.pilot.type.TypePilot;

/**
 * This class represent of Pilot entity, using the patther DDD.
 * 
 * @author David
 * @version 1.0
 *
 */
@Entity
@Table(name = "FL_PILOT")
public class Pilot {

	@Id
	@Column(name = "ID_PILOT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "PILOT_NAME")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE_PILOT")
	private TypePilot typePilot;

	@ManyToMany(mappedBy = "pilots")
	private Set<Flight> flights;

	public Pilot() {

	}

	public Pilot(String name, TypePilot typePilot) {
		this.name = name;
		this.typePilot = typePilot;
	}

	public String getName() {
		return this.name;
	}
	
	public TypePilot getTypePilot() {
		return typePilot;
	}
	
	@Override
	public String toString() {
		return "[Name Pilot: " + this.name + "]";
	}
}

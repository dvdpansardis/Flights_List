package br.com.flightslist.domain.flight;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.flightslist.domain.flight.type.StatusFlight;
import br.com.flightslist.domain.pilot.Pilot;

/**
 * This class represent of Flight entity, using the patther DDD.
 * 
 * @author David
 * @version 1.0
 *
 */
@Entity
@Table(name = "FL_FLIGHT")
public class Flight {

	@Id
	@Column(name = "ID_FLIGHT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "CODE_FLIGHT")
	private String codeFlight;

	@Column(name = "GATE_DEPARTURE")
	private String gateDeparture;

	@Column(name = "NUMBER_PASSANGERS")
	private Integer numberPassengers = 0;

	@Column(name = "TIME_DEPARTURE")
	private LocalDateTime timeDeparture;

	@Column(name = "TIME_ARRIVED")
	private LocalDateTime timeArrived;

	@Column(name = "CITY_DEPARTURE")
	private String cityDeparture;

	@Column(name = "CITY_ARRIVED")
	private String cityArrived;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "FL_PILOTS_TO_FLIGHTS", joinColumns = @JoinColumn(name = "ID_FLIGHT", referencedColumnName = "ID_FLIGHT"), inverseJoinColumns = @JoinColumn(name = "ID_PILOT", referencedColumnName = "ID_PILOT"))
	private Set<Pilot> pilots;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS_FLIGHT")
	private StatusFlight status;

	public Flight() {

	}

	public Flight(String codeFlight, String gateDeparture, LocalDateTime timeDeparture, LocalDateTime timeArrived,
			String cityDeparture, String cityArrived, Set<Pilot> pilots, StatusFlight status) {
		this.codeFlight = codeFlight.toUpperCase();
		this.gateDeparture = gateDeparture.toUpperCase();
		this.timeDeparture = timeDeparture;
		this.timeArrived = timeArrived;
		this.cityDeparture = cityDeparture.toUpperCase();
		this.cityArrived = cityArrived.toUpperCase();
		this.pilots = pilots;
		this.status = status;
	}

	public String getCodeFlight() {
		return this.codeFlight;
	}
	
	public String getGateDeparture() {
		return gateDeparture;
	}

	public Integer getNumberPassengers() {
		return numberPassengers;
	}

	public LocalDateTime getTimeDeparture() {
		return timeDeparture;
	}

	public LocalDateTime getTimeArrived() {
		return timeArrived;
	}

	public String getCityDeparture() {
		return cityDeparture;
	}

	public String getCityArrived() {
		return cityArrived;
	}

	public StatusFlight getStatus() {
		return status;
	}

	public Set<Pilot> getPilots() {
		return Collections.unmodifiableSet(this.pilots);
	}

	public void addPasanger(final Integer numberOfPassangers) {
		this.numberPassengers += numberOfPassangers;
	}
	
	@Override
	public String toString() {
		return "[Code Flight: " + this.codeFlight + ", City Departure: " + this.cityDeparture + ", City Arraived: "
				+ this.cityArrived + "]";
	}
}

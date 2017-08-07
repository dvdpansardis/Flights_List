package br.com.flightslist.domain.flight.vo;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.flightslist.domain.flight.Flight;
import br.com.flightslist.domain.pilot.vo.FullDescritionPilot;
import br.com.flightslist.util.View;

/**
 * This class is used like a VO Pattern, to transfer data of Front to Back and
 * contrary form. This is used to transfer a full description of a flight.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public class FullDescriptionFlight {

	@JsonView(View.Summary.class)
	private String codeFlight;

	@JsonView(View.Summary.class)
	private String cityDeparture;

	@JsonView(View.Summary.class)
	private String cityArrived;

	@JsonView(View.Summary.class)
	private String dateTimeDeparture;

	@JsonView(View.Summary.class)
	private String dateTimeArrived;

	@JsonView(View.Summary.class)
	private String passanger;

	@JsonView(View.Summary.class)
	private String status;

	@JsonView(View.Summary.class)
	private String gate;

	@JsonView(View.Summary.class)
	private Collection<FullDescritionPilot> pilots = new HashSet<>();

	public FullDescriptionFlight() {

	}

	public FullDescriptionFlight(Flight flight) {
		this.codeFlight = flight.getCodeFlight();
		this.cityDeparture = flight.getCityDeparture();
		this.cityArrived = flight.getCityArrived();
		this.dateTimeDeparture = flight.getTimeDeparture().toString();
		this.dateTimeArrived = flight.getTimeArrived().toString();
		this.passanger = flight.getNumberPassengers().toString();
		this.status = flight.getStatus().toString();
		this.gate = flight.getGateDeparture();
		flight.getPilots().forEach(Pilot -> {
			this.pilots.add(new FullDescritionPilot(Pilot));
		});
	}

	public String getCodeFlight() {
		return codeFlight;
	}

	public String getCityDeparture() {
		return cityDeparture;
	}

	public String getCityArrived() {
		return cityArrived;
	}

	public String getDateTimeDeparture() {
		return dateTimeDeparture;
	}

	public String getDateTimeArrived() {
		return dateTimeArrived;
	}

	public String getPassanger() {
		return passanger;
	}

	public String getStatus() {
		return status;
	}

	public Collection<FullDescritionPilot> getPilots() {
		return pilots;
	}

	@Override
	public String toString() {
		return "[Code Flight: " + this.codeFlight + ", City Departure: " + this.cityDeparture + ", City Arrived: "
				+ this.getCityArrived() + ", Date Time Departure: " + this.dateTimeDeparture + ", Data Time Arraived: "
				+ this.getDateTimeArrived() + ", Number of Passangers: " + this.getPassanger() + ", Gate: " + this.gate
				+ ", Status: " + this.status + ", Pilots: " + this.pilots + "]";
	}

}

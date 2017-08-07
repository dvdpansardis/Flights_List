package br.com.flightslist.domain.flight.vo;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.flightslist.domain.flight.Flight;
import br.com.flightslist.util.View;

/**
 * This class is used like a VO Pattern, to transfer data of Front to Back and
 * contrary form. this is used to transfer a basic data of a flight.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public class SimpleFlight {

	@JsonView(View.Summary.class)
	private String codeFlight;

	@JsonView(View.Summary.class)
	private String cityDeparture;

	@JsonView(View.Summary.class)
	private String cityArrived;

	@JsonView(View.Summary.class)
	private String dateTimeDeparture;

	public SimpleFlight(Flight flight) {
		this.codeFlight = flight.getCodeFlight();
		this.cityDeparture = flight.getCityDeparture();
		this.cityArrived = flight.getCityArrived();
		this.dateTimeDeparture = flight.getTimeDeparture().toString();
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

	@Override
	public String toString() {
		return "[Code Flight: " + this.codeFlight + ", City Departure: " + this.cityDeparture + ", City Arrived: "
				+ this.getCityArrived() + ", Date Time Departure: " + this.dateTimeDeparture + "]";
	}
}

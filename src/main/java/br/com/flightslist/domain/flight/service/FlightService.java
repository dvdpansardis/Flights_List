package br.com.flightslist.domain.flight.service;

import java.util.Collection;

import br.com.flightslist.domain.flight.Flight;
import br.com.flightslist.domain.flight.dto.FlightFilter;
import br.com.flightslist.domain.flight.vo.FilterRequest;
import br.com.flightslist.domain.flight.vo.FullDescriptionFlight;
import br.com.flightslist.domain.flight.vo.SimpleFlight;

/**
 * Interface to expose the methods of service to manipulate flights.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public interface FlightService {

	/**
	 * Get the basics information of all the flights.
	 * 
	 * @return Collection Flight list all flights with the basic information.
	 */
	Collection<Flight> getAllFlights();

	/**
	 * Get the basics information of all the flights.
	 * 
	 * @return Collection Flight list all flights with the basic information.
	 */
	Collection<SimpleFlight> getAllSimpleFlights();

	/**
	 * Get the basics information of all the flights by filter.
	 * 
	 * @param flightFilter used to transfer data to filter
	 * @return Collection Flight list all flights with the basic information.
	 */
	Collection<SimpleFlight> filter(final FlightFilter flightFilter);

	/**
	 * Get the basics information of all the flights by filter.
	 * 
	 * @param filterRequest used to transfer data to filter from frontend
	 * @return Collection Flight list all flights with the basic information.
	 */
	Collection<SimpleFlight> filter(final FilterRequest filterRequest);

	/**
	 * Get distinct list of all Code Flights.
	 * 
	 * @return Collection Flight list all Code Flights.
	 */
	Collection<String> getAllCodeFlight();

	/**
	 * Get distinct list of all Citys Departure.
	 * 
	 * @return Collection Flight list all Citys Departure.
	 */
	Collection<String> getAllCityDeparture();

	/**
	 * Get distinct list of all Citys Arrived.
	 * 
	 * @return Collection Flight list all Citys Arrived.
	 */
	Collection<String> getAllCityArrived();

	/**
	 * Get a full description of a Flight specific, filtered by Code Flight.
	 * 
	 * @param codeFlight
	 *            number of Code Flight.
	 * @return FullDescriptionFlight full description of this flight.
	 */
	FullDescriptionFlight getFullDescritionFlight(final String codeFlight);

}

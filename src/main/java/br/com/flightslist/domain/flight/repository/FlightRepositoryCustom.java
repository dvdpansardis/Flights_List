package br.com.flightslist.domain.flight.repository;

import java.util.Collection;

import br.com.flightslist.domain.flight.Flight;
import br.com.flightslist.domain.flight.dto.FlightFilter;

/**
 * Interface to expose the methods search customizeds to filter flights.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public interface FlightRepositoryCustom {

	/**
	 * Filter all flights based on the <code>FlightFilter</code> parameter.
	 * 
	 * @param flightFilter
	 *            with the data to filter all flights.
	 * @return Collection Flight list of all flights selecteds.
	 * @throws IllegalArgumentException
	 *             used when the <code>FlightFilter</code> parameter is
	 *             inconsistent.
	 */
	Collection<Flight> filter(final FlightFilter flightFilter) throws IllegalArgumentException;

}

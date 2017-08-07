package br.com.flightslist.domain.flight.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.flightslist.domain.flight.Flight;

/**
 * This interface extends <code>JpaRepository</code> and has methods to list
 * distinct values from the entity and filter flight by codeFlight.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public interface FlightRepository extends JpaRepository<Flight, Long> {

	/**
	 * Get a distinct list of Code Fligths.
	 * 
	 * @return Collection String list of Code Flights.
	 */
	@Query("select distinct(f.codeFlight) from Flight f order by f.codeFlight")
	public Collection<String> getAllCodeFlight();

	/**
	 * Get a distinct list of Citys of Departure.
	 * 
	 * @return Collection String list of Citys of Departure.
	 */
	@Query("select distinct(f.cityDeparture) from Flight f order by f.cityDeparture")
	public Collection<String> getAllCityDeparture();

	/**
	 * Get a distinct list of Citys of Arrived.
	 * 
	 * @return Collection String list of Citys of Arrived.
	 */
	@Query("select distinct(f.cityArrived) from Flight f order by f.cityArrived")
	public Collection<String> getAllCityArrived();

	/**
	 * Get a <code>Flight</code> selected by number of Code Flight.
	 * 
	 * @param codeFlight
	 *            a number of specific Code Flight.
	 * @return Flight selected by filter.
	 */
	@Query("select f from Flight f where f.codeFlight = :codeFlight")
	public Flight findByCodeFlight(@Param("codeFlight") final String codeFlight);

}

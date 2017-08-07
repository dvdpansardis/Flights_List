package br.com.flightslist.domain.flight.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flightslist.domain.flight.Flight;
import br.com.flightslist.domain.flight.dto.FlightFilter;
import br.com.flightslist.domain.flight.repository.FlightRepository;
import br.com.flightslist.domain.flight.repository.FlightRepositoryCustom;
import br.com.flightslist.domain.flight.type.StatusFlight;
import br.com.flightslist.domain.flight.vo.FilterRequest;
import br.com.flightslist.domain.flight.vo.FullDescriptionFlight;
import br.com.flightslist.domain.flight.vo.SimpleFlight;

/**
 * Implementation of <code>FlightService</code>.
 * 
 * @see <code>br.com.flightslist.domain.flight.service.FlightService</code>
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
@Service(value = "flightServiceImpl")
public class FlightServiceImpl implements FlightService {

	/**
	 * Autowired of FlightRepository to access methods that manipulate the Entity.
	 */
	@Autowired
	private FlightRepository flightRepository;

	/**
	 * Autowired of FlightRepositoryCustom to access methods that manipulate the Entity.
	 */
	@Autowired
	private FlightRepositoryCustom flightRepositoryCustom;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<SimpleFlight> getAllSimpleFlights() {
		return mapper(flightRepository.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<String> getAllCodeFlight() {
		return flightRepository.getAllCodeFlight();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<String> getAllCityDeparture() {
		return flightRepository.getAllCityDeparture();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<String> getAllCityArrived() {
		return flightRepository.getAllCityArrived();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<SimpleFlight> filter(final FlightFilter flightFilter) {
		try {
			return mapper(flightRepositoryCustom.filter(flightFilter));
		} catch (IllegalArgumentException e) {
			return this.getAllSimpleFlights();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FullDescriptionFlight getFullDescritionFlight(final String codeFlight) {
		Flight flight = flightRepository.findByCodeFlight(codeFlight);

		if (flight != null)
			return new FullDescriptionFlight(flight);
		else
			return new FullDescriptionFlight();
	}

	/**
	 * Mapper a Collection of <code>Flights</code> to a Collection of <code>SimpleFlight</code>
	 * 
	 * @param flights Collection of <code>Flight</code>
	 * @return Collection<SimpleFlight> list mapped.
	 */
	private Collection<SimpleFlight> mapper(final Collection<Flight> flights) {
		Collection<SimpleFlight> simpleFlights = new ArrayList<>();
		flights.forEach(Flight -> {
			simpleFlights.add(new SimpleFlight(Flight));
		});
		return simpleFlights;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<SimpleFlight> filter(FilterRequest filterRequest) {
		FlightFilter flightFilter = new FlightFilter();

		flightFilter.setCodeFlight(filterRequest.getCodeFlight());
		flightFilter.setStatus(filterRequest.getStatus() == null ? StatusFlight.NONE
				: StatusFlight.fromString(filterRequest.getStatus()));
		flightFilter.setCityDeparture(filterRequest.getCityDeparture());
		flightFilter.setCityArrived(filterRequest.getCityArrived());
		flightFilter.setFirstDate(
				filterRequest.getFirstDate().isEmpty() ? null : LocalDateTime.parse(filterRequest.getFirstDate()));
		flightFilter.setLastDate(
				filterRequest.getLastDate().isEmpty() ? null : LocalDateTime.parse(filterRequest.getLastDate()));

		return this.filter(flightFilter);
	}
}

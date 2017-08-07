package br.com.flightslist.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.flightslist.domain.flight.service.FlightService;
import br.com.flightslist.domain.flight.vo.FilterRequest;
import br.com.flightslist.domain.flight.vo.FullDescriptionFlight;
import br.com.flightslist.domain.flight.vo.SimpleFlight;

/**
 * Controller of entity <code>Flight</code>.
 * 
 * @author David Pansardis
 * @version 1.0
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/Flight")
public class FlightController {

	/**
	 * Autowired of FlightService to access the core business.
	 */
	@Autowired
	private FlightService flightService;

	/**
	 * Get request to get the basic list of all flights on database.
	 * 
	 * @return Collection SimpleFlight a list with the basic data of flights.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Collection<SimpleFlight> getAllSimpleFlights() {
		return flightService.getAllSimpleFlights();
	}

	/**
	 * Get a full description of code flight passed for parameter.
	 * 
	 * @param codeFlight
	 *            number of specific flight.
	 * @return FullDescriptionFlight a full description of flight.
	 */
	@RequestMapping(value = "/{codeFlight}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public FullDescriptionFlight getFullDescritionFlight(@PathVariable("codeFlight") String codeFlight) {
		return flightService.getFullDescritionFlight(codeFlight);
	}

	/**
	 * Do a filter on flights using the json passed and return all the basic
	 * data of flights in the filter.
	 * 
	 * @param filterRequest
	 *            a object to mapping the parameters.
	 * @return Collection SimpleFlight a list with the basic data of flights.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Collection<SimpleFlight> filter(@RequestBody FilterRequest filterRequest) {
		return flightService.filter(filterRequest);
	}
}

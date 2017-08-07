package br.com.flightslist.domain.pilot.service;

import java.util.Collection;

import br.com.flightslist.domain.pilot.Pilot;

/**
 * Interface to expose the methods of service to manipulate pilots.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public interface PilotService {

	/**
	 * Get the information of all the pilots.
	 * 
	 * @return Collection Flight list all pilots.
	 */
	Collection<Pilot> getAll();

}

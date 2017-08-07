package br.com.flightslist.domain.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flightslist.domain.pilot.Pilot;

/**
 * This interface extends <code>JpaRepository</code> and has methods to
 * manipulate values from the entity Pilot.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public interface PilotRepository extends JpaRepository<Pilot, Long> {

}

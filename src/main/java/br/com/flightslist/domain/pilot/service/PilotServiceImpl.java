package br.com.flightslist.domain.pilot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flightslist.domain.pilot.Pilot;
import br.com.flightslist.domain.pilot.repository.PilotRepository;

/**
 * Implementation of <code>PilotService</code>.
 * 
 * @see <code>br.com.flightslist.domain.pilot.service.PilotService</code>
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
@Service("pilotServiceImpl")
public class PilotServiceImpl implements PilotService {

	/**
	 * Autowired of PilotRepository to access methods that manipulate the
	 * Entity.
	 */
	@Autowired
	private PilotRepository pilotRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Pilot> getAll() {
		return pilotRepository.findAll();
	}
}

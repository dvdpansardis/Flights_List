package br.com.flightslist.pilot.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.flightslist.domain.pilot.Pilot;
import br.com.flightslist.domain.pilot.repository.PilotRepository;
import br.com.flightslist.domain.pilot.type.TypePilot;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PilotRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PilotRepository pilotRepository;

	@Test
	public void testPilotFound1Record() {
		Pilot pilotTest1 = new Pilot("David", TypePilot.PILOT);
		Pilot pilotTest2 = new Pilot("Leticia", TypePilot.COPILOT);

		entityManager.persist(pilotTest1);
		entityManager.persist(pilotTest2);
		entityManager.flush();

		List<Pilot> founds = pilotRepository.findAll();

		assertTrue(founds.size() == 2);
	}
}

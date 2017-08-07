package br.com.flightslist.flight.repository;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.flightslist.domain.flight.Flight;
import br.com.flightslist.domain.flight.dto.FlightFilter;
import br.com.flightslist.domain.flight.repository.FlightRepository;
import br.com.flightslist.domain.flight.repository.FlightRepositoryCustom;
import br.com.flightslist.domain.flight.repository.FlightRepositoryCustomImpl;
import br.com.flightslist.domain.flight.type.StatusFlight;
import br.com.flightslist.domain.pilot.Pilot;
import br.com.flightslist.domain.pilot.type.TypePilot;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FlightRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@TestConfiguration
	static class FlightRepositoryCustomImplTestContextConfiguration {

		@Bean
		public FlightRepositoryCustom flightRepositoryCustom() {
			return new FlightRepositoryCustomImpl();
		}
		
	}

	@Autowired
	private FlightRepositoryCustom flightRepositoryCustom;

	@Autowired
	private FlightRepository flightRepository;
	
	@Before
	public void setUp() {
		LocalDateTime timeDeparture = LocalDateTime.of(2017, Month.AUGUST, 05, 17, 0);
		LocalDateTime timeArrived = LocalDateTime.of(2017, Month.AUGUST, 05, 23, 0);

		Set<Pilot> pilots = new HashSet<>();

		Pilot pilotTest = new Pilot("David", TypePilot.PILOT);

		pilots.add(pilotTest);

		Flight flightTest = new Flight("XX1111", "01", timeDeparture, timeArrived, "SJK", "NYC", pilots,
				StatusFlight.ON_TIME);

		entityManager.persist(flightTest);
		entityManager.flush();
	}

	@Test
	public void testFlightFilterFoundByCodeFlight() {

		FlightFilter flightFilterTest = new FlightFilter();

		flightFilterTest.setCodeFlight("XX1111");

		List<Flight> founds = new ArrayList<>(flightRepositoryCustom.filter(flightFilterTest));

		assertTrue(founds.size() == 1);
	}

	@Test
	public void testFlightFilterByCitys() {

		FlightFilter flightFilterTest = new FlightFilter();

		flightFilterTest.setCityDeparture("SJK");
		flightFilterTest.setCityArrived("NYC");

		Collection<Flight> founds = flightRepositoryCustom.filter(flightFilterTest);

		assertTrue(founds.size() == 1);
	}

	@Test
	public void testFlightFilterByDates() {

		FlightFilter flightFilterTest = new FlightFilter();

		flightFilterTest.setFirstDate(LocalDateTime.of(2017, Month.AUGUST, 04, 0, 0));
		flightFilterTest.setLastDate(LocalDateTime.of(2017, Month.AUGUST, 06, 0, 0));

		Collection<Flight> founds = flightRepositoryCustom.filter(flightFilterTest);

		assertTrue(founds.size() == 1);
	}

	@Test
	public void testFlightFilterAllFields() {

		FlightFilter flightFilterTest = new FlightFilter();

		flightFilterTest.setCodeFlight("XX1111");
		flightFilterTest.setCityArrived("NYC");
		flightFilterTest.setCityDeparture("SJK");
		flightFilterTest.setFirstDate(LocalDateTime.of(2017, Month.AUGUST, 04, 0, 0));
		flightFilterTest.setLastDate(LocalDateTime.of(2017, Month.AUGUST, 06, 0, 0));

		Collection<Flight> founds = flightRepositoryCustom.filter(flightFilterTest);

		assertTrue(founds.size() == 1);
	}

	@Test
	public void testFlightFilterFoundPilot() {

		FlightFilter flightFilterTest = new FlightFilter();

		flightFilterTest.setCodeFlight("XX1111");

		List<Flight> founds = new ArrayList<>(flightRepositoryCustom.filter(flightFilterTest));

		List<Pilot> pilots = new ArrayList<>(founds.get(0).getPilots());

		assertTrue(pilots.get(0).getName() == "David");
	}

	@Test
	public void testFlightFilterFound0Record() {

		FlightFilter flightFilterTest = new FlightFilter();

		flightFilterTest.setCodeFlight("XX2222");
		flightFilterTest.setCityArrived("SJK");
		flightFilterTest.setCityDeparture("MEL");
		flightFilterTest.setFirstDate(LocalDateTime.of(2018, Month.AUGUST, 06, 0, 0));
		flightFilterTest.setLastDate(LocalDateTime.of(2018, Month.AUGUST, 06, 0, 0));

		Collection<Flight> founds = flightRepositoryCustom.filter(flightFilterTest);

		assertTrue(founds.size() == 0);
	}

	@Test
	public void testFlightFilterFindByCodeFlight() {

		Flight found = flightRepository.findByCodeFlight("XX1111");

		assertTrue(found != null);
	}
}

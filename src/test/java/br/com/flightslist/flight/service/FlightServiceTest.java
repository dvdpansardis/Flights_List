package br.com.flightslist.flight.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.flightslist.domain.flight.Flight;
import br.com.flightslist.domain.flight.dto.FlightFilter;
import br.com.flightslist.domain.flight.repository.FlightRepository;
import br.com.flightslist.domain.flight.repository.FlightRepositoryCustom;
import br.com.flightslist.domain.flight.repository.FlightRepositoryCustomImpl;
import br.com.flightslist.domain.flight.service.FlightService;
import br.com.flightslist.domain.flight.service.FlightServiceImpl;
import br.com.flightslist.domain.flight.type.StatusFlight;
import br.com.flightslist.domain.flight.vo.FullDescriptionFlight;
import br.com.flightslist.domain.flight.vo.SimpleFlight;
import br.com.flightslist.domain.pilot.Pilot;
import br.com.flightslist.domain.pilot.type.TypePilot;

@RunWith(SpringRunner.class)
public class FlightServiceTest {

	@TestConfiguration
	static class FlightServiceImplTestContextConfiguration {

		@Bean
		public FlightService flightService() {
			return new FlightServiceImpl();
		}

	}

	@TestConfiguration
	static class FlightRepositoryCustomImplTestContextConfiguration {

		@Bean
		public FlightRepositoryCustom flightRepositoryCustom() {
			return new FlightRepositoryCustomImpl();
		}

	}

	@MockBean
	private FlightRepository flightRepository;

	@MockBean
	private FlightRepositoryCustom flightRepositoryCustom;

	@Autowired
	private FlightService fligthService;

	private FlightFilter flightFilterTest = new FlightFilter();

	@Before
	public void setUp() {

		List<Flight> flights1 = new ArrayList<>();
		List<Flight> flights2 = new ArrayList<>();
		List<Flight> flights3 = new ArrayList<>();

		LocalDateTime timeDeparture1 = LocalDateTime.of(2017, Month.AUGUST, 05, 17, 0);
		LocalDateTime timeArrived1 = LocalDateTime.of(2017, Month.AUGUST, 05, 23, 0);
		LocalDateTime timeDeparture2 = LocalDateTime.of(2017, Month.AUGUST, 06, 10, 0);
		LocalDateTime timeArrived2 = LocalDateTime.of(2017, Month.AUGUST, 06, 12, 0);

		Set<Pilot> pilots1 = new HashSet<>();

		Pilot pilotTest1 = new Pilot("David", TypePilot.PILOT);
		Pilot pilotTest2 = new Pilot("Leticia", TypePilot.COPILOT);

		pilots1.add(pilotTest1);
		pilots1.add(pilotTest2);

		Flight flightTest1 = new Flight("XX1111", "01", timeDeparture1, timeArrived1, "SJK", "NYC", pilots1,
				StatusFlight.ON_TIME);

		Set<Pilot> pilots2 = new HashSet<>();

		Pilot pilotTest3 = new Pilot("Victor", TypePilot.PILOT);

		pilots1.add(pilotTest3);

		Flight flightTest2 = new Flight("YY2222", "02", timeDeparture2, timeArrived2, "NYC", "SJK", pilots2,
				StatusFlight.CANCELED);

		flights1.add(flightTest1);
		flights1.add(flightTest2);

		flights2.add(flightTest1);
		
		flights3.add(flightTest2);

		flightFilterTest.setCodeFlight("YY2222");
		flightFilterTest.setFirstDate(LocalDateTime.of(2017, Month.AUGUST, 06, 0, 0));
		flightFilterTest.setLastDate(LocalDateTime.of(2017, Month.AUGUST, 07, 0, 0));

		Mockito.when(flightRepository.findAll()).thenReturn(flights1);
		Mockito.when(flightRepository.findByCodeFlight("XX1111")).thenReturn(flightTest1);
		Mockito.when(flightRepositoryCustom.filter(flightFilterTest)).thenReturn(flights3);
	}

	@Test
	public void getCorrectlyDataFromSimpleFlight() {
		List<SimpleFlight> simpleFligts = (List<SimpleFlight>) fligthService.getAllSimpleFlights();

		SimpleFlight simpleFlight = simpleFligts.get(0);

		assertTrue(simpleFlight.getCodeFlight().equals("XX1111") && simpleFlight.getCityDeparture().equals("SJK")
				&& simpleFlight.getCityArrived().equals("NYC")
				&& simpleFlight.getDateTimeDeparture().equals("2017-08-05T17:00"));
	}

	@Test
	public void getCorrectlyDataFromFullDescriptionFlight() {
		FullDescriptionFlight fullDescriptionFlight = fligthService.getFullDescritionFlight("XX1111");

		assertTrue(fullDescriptionFlight.getCodeFlight().equals("XX1111")
				&& fullDescriptionFlight.getCityDeparture().equals("SJK")
				&& fullDescriptionFlight.getCityArrived().equals("NYC")
				&& fullDescriptionFlight.getDateTimeDeparture().equals("2017-08-05T17:00")
				&& fullDescriptionFlight.getDateTimeArrived().equals("2017-08-05T23:00"));
	}

	@Test
	public void getCorrectlyDataFromFilterFlight() {
		List<SimpleFlight> simpleFligts = (List<SimpleFlight>) fligthService.filter(flightFilterTest);

		SimpleFlight simpleFlight = simpleFligts.get(0);

		assertTrue(simpleFlight.getCodeFlight().equals("YY2222"));
	}
}

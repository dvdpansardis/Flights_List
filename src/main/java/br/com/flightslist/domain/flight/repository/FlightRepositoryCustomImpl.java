package br.com.flightslist.domain.flight.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.flightslist.domain.flight.Flight;
import br.com.flightslist.domain.flight.dto.FlightFilter;
import br.com.flightslist.domain.flight.type.StatusFlight;
import br.com.flightslist.util.DateUtil;

/**
 * Implementation of <code>FlightRepositoryCustom</code>.
 * 
 * @see <code>br.com.flightslist.domain.flight.repository.FlightRepositoryCustom</code>
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
@Repository("flightRepositoryCustomImpl")
public class FlightRepositoryCustomImpl implements FlightRepositoryCustom {

	/**
	 *  Autowired of <code>EntityManager</code> to access the model persistence.
	 */
	@Autowired
	private EntityManager entityManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Flight> filter(final FlightFilter flightFilter) throws IllegalArgumentException {
		if (!flightFilter.hasDataToFilter())
			throw new IllegalArgumentException("Filter Flight is empty.");

		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Flight> criteriaQuery = criteriaBuilder.createQuery(Flight.class);
		final Root<Flight> flight = criteriaQuery.from(Flight.class);
		final Collection<Predicate> criteries = new ArrayList<>();

		if (!flightFilter.getCodeFlight().isEmpty()) {
			final Predicate codeFlight = criteriaBuilder.equal(flight.<String>get("codeFlight"),
					flightFilter.getCodeFlight());
			criteries.add(codeFlight);
		}

		if (flightFilter.getStatus() != null && flightFilter.getStatus() != StatusFlight.NONE) {
			final Predicate status = criteriaBuilder.equal(flight.<String>get("status"), flightFilter.getStatus());
			criteries.add(status);
		}

		if (!flightFilter.getCityArrived().isEmpty()) {
			final Predicate cityArrived = criteriaBuilder.equal(flight.<String>get("cityArrived"),
					flightFilter.getCityArrived());
			criteries.add(cityArrived);
		}

		if (!flightFilter.getCityDeparture().isEmpty()) {
			final Predicate cityDeparture = criteriaBuilder
					.equal(flight.<String>get("cityDeparture"), flightFilter.getCityDeparture());
			criteries.add(cityDeparture);
		}

		if (flightFilter.getFirstDate() != null) {
			final LocalDateTime startDateTimeDeparture = DateUtil
					.adjustDateToFirstHourOfDay(flightFilter.getFirstDate());

			final Predicate timeDeparture = criteriaBuilder
					.greaterThanOrEqualTo(flight.<LocalDateTime>get("timeDeparture"), startDateTimeDeparture);

			criteries.add(timeDeparture);
		}

		if (flightFilter.getLastDate() != null) {
			final LocalDateTime lastDateTimeDeparture = DateUtil.adjustDateToLastHourOfDay(flightFilter.getLastDate());

			final Predicate timeDeparture = criteriaBuilder
					.lessThanOrEqualTo(flight.<LocalDateTime>get("timeDeparture"), lastDateTimeDeparture);

			criteries.add(timeDeparture);
		}

		if (criteries.size() > 0) {
			criteriaQuery.where(criteriaBuilder.and(criteries.toArray(new Predicate[criteries.size()])));
		}

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}

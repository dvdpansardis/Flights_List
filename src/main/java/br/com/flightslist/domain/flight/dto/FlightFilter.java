package br.com.flightslist.domain.flight.dto;

import java.time.LocalDateTime;

import br.com.flightslist.domain.flight.type.StatusFlight;

/**
 * This class is used like a DTO Pattern, to transfer data of filter to
 * repository <code>FlightRepositoryCustom</code> on method filter.
 * 
 * @author David
 * @version 1.0
 *
 */
public class FlightFilter {

	private String codeFlight = "";

	private LocalDateTime firstDate;

	private LocalDateTime lastDate;

	private String cityDeparture = "";

	private String cityArrived = "";

	private StatusFlight status;

	public FlightFilter() {

	}

	public Boolean hasDataToFilter() {
		return (!this.codeFlight.isEmpty() || this.firstDate != null || this.lastDate != null
				|| !this.cityDeparture.isEmpty() || !this.cityArrived.isEmpty() || this.status != null);
	}

	public String getCodeFlight() {
		return this.codeFlight;
	}

	public void setCodeFlight(String codeFlight) {
		this.codeFlight = codeFlight.toUpperCase();
	}

	public LocalDateTime getFirstDate() {
		return this.firstDate;
	}

	public void setFirstDate(LocalDateTime firstDate) {
		this.firstDate = firstDate;
	}

	public LocalDateTime getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(LocalDateTime lastDate) {
		this.lastDate = lastDate;
	}

	public String getCityDeparture() {
		return this.cityDeparture;
	}

	public void setCityDeparture(String cityDeparture) {
		this.cityDeparture = cityDeparture.toUpperCase();
	}

	public String getCityArrived() {
		return this.cityArrived;
	}

	public void setCityArrived(String cityArrived) {
		this.cityArrived = cityArrived.toUpperCase();
	}

	public StatusFlight getStatus() {
		return this.status;
	}

	public void setStatus(StatusFlight status) {
		this.status = status;
	}
}

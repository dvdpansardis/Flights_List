package br.com.flightslist.domain.flight.vo;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.flightslist.util.View;

/**
 * This class is used like a VO Pattern, to transfer data of Front to Back and
 * contrary form. This is used to transfer the data of requisition filter.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public class FilterRequest {

	@JsonView(View.Summary.class)
	private String codeFlight;

	@JsonView(View.Summary.class)
	private String firstDate;

	@JsonView(View.Summary.class)
	private String lastDate;

	@JsonView(View.Summary.class)
	private String cityDeparture;

	@JsonView(View.Summary.class)
	private String cityArrived;

	@JsonView(View.Summary.class)
	private String status;

	public String getCodeFlight() {
		return codeFlight;
	}

	public void setCodeFlight(String codeFlight) {
		this.codeFlight = codeFlight;
	}

	public String getFirstDate() {
		return this.firstDate.isEmpty() ? "" : this.firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getLastDate() {
		return this.lastDate.isEmpty() ? "" : this.lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getCityDeparture() {
		return cityDeparture;
	}

	public void setCityDeparture(String cityDeparture) {
		this.cityDeparture = cityDeparture;
	}

	public String getCityArrived() {
		return cityArrived;
	}

	public void setCityArrived(String cityArrived) {
		this.cityArrived = cityArrived;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

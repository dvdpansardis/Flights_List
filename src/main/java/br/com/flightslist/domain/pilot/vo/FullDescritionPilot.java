package br.com.flightslist.domain.pilot.vo;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.flightslist.domain.pilot.Pilot;
import br.com.flightslist.util.View;

/**
 * This class is used like a VO Pattern, to transfer data of Front to Back and
 * contrary form. This is used to transfer the full description of a pilot.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public class FullDescritionPilot {

	@JsonView(View.Summary.class)
	private String namePilot;

	@JsonView(View.Summary.class)
	private String typePilot;

	public FullDescritionPilot(String namePilot, String typePilot) {
		this.namePilot = namePilot;
		this.typePilot = typePilot;
	}

	public FullDescritionPilot(Pilot pilot) {
		this.namePilot = pilot.getName();
		this.typePilot = pilot.getTypePilot().toString();
	}

	public String getNamePilot() {
		return namePilot;
	}

	public String getTypePilot() {
		return typePilot;
	}

	@Override
	public String toString() {
		return "[Name Pilot: " + this.namePilot + ", Type Pilot: " + this.typePilot + "]";
	}
}

package br.com.flightslist.domain.pilot.type;

/**
 * Enum with all types of a pilot.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public enum TypePilot {
	PILOT("PILOT"), COPILOT("COPILOT");

	private final String typePilot;

	private TypePilot(final String typePilot) {
		this.typePilot = typePilot;
	}

	public Boolean equalsTypePilot(final String typePilot) {
		return this.typePilot.equals(typePilot);
	}

	public String toString() {
		return this.typePilot;
	}
}

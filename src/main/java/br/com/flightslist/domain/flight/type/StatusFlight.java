package br.com.flightslist.domain.flight.type;

/**
 * Enum with all states of a flight.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public enum StatusFlight {
	ARRIVED("ARRIVED"), BOARDING("BOARDING"), CANCELED("CANCELED"), DELAY("DELAY"), DEPARTED("DEPARTED"), FINAL_CALL(
			"FINAL CALL"), ON_TIME("ON TIME"), NONE("NONE");

	private final String status;

	private StatusFlight(final String status) {
		this.status = status;
	}

	public Boolean equalsStatus(final String status) {
		return this.status.equals(status);
	}

	public String toString() {
		return this.status;
	}

	public static StatusFlight fromString(final String status) {
		for (StatusFlight statusFlight : StatusFlight.values()) {
			if (statusFlight.toString().equalsIgnoreCase(status)) {
				return statusFlight;
			}
		}
		return null;
	}
}

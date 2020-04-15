package entities;

/**
 * This is the concrete class for the flights.
 */

public class FlightEntity {

    private String departure, arrival, from;
    private String to, plane, airline;

    public FlightEntity(String departure, String arrival, String from,
            String to, String plane, String airline) {

        this.departure = departure;
        this.arrival = arrival;
        this.from = from;
        this.to = to;
        this.plane = plane;
        this.airline = airline;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String fromAirport) {
        this.from = fromAirport;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String toAirport) {
        this.to = toAirport;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}

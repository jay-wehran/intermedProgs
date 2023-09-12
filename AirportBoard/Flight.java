/***
 * 
 * Author: Jason Wehran
 * Date created: June 22, 2023
 */
public class Flight {
    private String flight;
    private Time departure;
    private Time arrival;
 
    /**
     * three-arg constructor
     * @param flight
     * @param departure
     * @param arrival
     */
    public Flight(String flight, Time departure, Time arrival) {
        this.flight = flight;
        this.departure = departure;
        this.arrival = arrival;
    }

    /**
     * getter for flights
     * @return
     */
    public String getFlight() {
        return flight;
    }

    /**
     * getter for departures
     * @return
     */
    public Time getDeparture() {
        return departure;
    }

    /**
     * getter for arrivals
     * @return
     */
    public Time getArrival() {
        return arrival;
    }

    /**
     * setter for flights
     * @param flight
     */
    public void setFlight(String flight) {
        this.flight = flight;
    }

    /**
     * setter for depatures
     * @param departure
     */
    public void setDeparture(Time departure) {
        this.departure = departure;
    }

    /**
     * setter for arrivals
     * @param arrival
     */
    public void setArrival(Time arrival) {
        this.arrival = arrival;
    }

    /**
     * Returns string representation of flight object
     */
    @Override
    public String toString(){
        return flight + "\t" + departure + "\t" + arrival + "\t";
    }

}

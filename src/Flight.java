import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Flight {

    // Attributes
    private int flightID;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private int capacity;
    private int routeID;

    // Main Constructor
    public Flight(int flightID, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, int capacity, int routeID){

        this.flightID = flightID;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.capacity = capacity;
        this.routeID = routeID;
    }
}

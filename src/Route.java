public class Route {

    // Attributes
    private int routeID;
    private String departFrom;
    private String arriveAt;
    private String midStopOne;
    private String midStopTwo;

    // Main Constructor
    public Route(int routeID, String departFrom, String arriveAt, String midStopOne, String midStopTwo){

        this.routeID = routeID;
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.midStopOne = midStopOne;
        this.midStopTwo = midStopTwo;
    }
}
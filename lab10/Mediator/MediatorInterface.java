package Mediator;

public interface MediatorInterface {

    public void registerRunway(Runway runway);

    public void registerFlight(Flight flight);

    public boolean isRunwayClearToLand();

    public boolean isRunwayClearToTakeOff();

    public void setRunwayStatus(boolean status);
}

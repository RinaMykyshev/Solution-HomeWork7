package aircraft;

public class PassengerPlane extends Aircraft {

    public PassengerPlane(String id, boolean landing) {
        super(id, landing);
    }

    @Override
    public void receive(String msg) {
        System.out.println("[PassengerPlane " + id + "]: Received - " + msg);
    }
}

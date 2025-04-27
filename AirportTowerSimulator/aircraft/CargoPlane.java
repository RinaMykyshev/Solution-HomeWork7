package aircraft;

public class CargoPlane extends Aircraft {

    public CargoPlane(String id, boolean landing) {
        super(id, landing);
    }

    @Override
    public void receive(String msg) {
        System.out.println("[CargoPlane " + id + "]: Received - " + msg);
    }
}

package aircraft;

public class Helicopter extends Aircraft {

    public Helicopter(String id, boolean landing) {
        super(id, landing);
    }

    @Override
    public void receive(String msg) {
        System.out.println("[Helicopter " + id + "]: Received - " + msg);
    }
}

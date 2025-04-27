package aircraft;

import mediator.TowerMediator;

public abstract class Aircraft {
    protected String id;
    protected boolean landing;

    public Aircraft(String id, boolean landing) {
        this.id = id;
        this.landing = landing;
    }

    public String getId() {
        return id;
    }

    public boolean isLanding() {
        return landing;
    }

    public abstract void receive(String msg);

    public void send(String msg, TowerMediator mediator) {
        mediator.broadcast(msg, this);
    }
}

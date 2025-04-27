package mediator;

import aircraft.Aircraft;

import java.util.LinkedList;
import java.util.Queue;

public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new LinkedList<>();
    private final Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private boolean runwayAvailable = true;

    @Override
    public synchronized void broadcast(String msg, Aircraft sender) {
        System.out.println("[Broadcast from " + sender.getId() + "]: " + msg);

        if (msg.equalsIgnoreCase("MAYDAY")) {
            System.out.println("[ControlTower]: EMERGENCY! Clearing runway for " + sender.getId());
            landingQueue.clear();
            takeoffQueue.clear();
            landingQueue.add(sender);
            runwayAvailable = true;
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft aircraft) {
        if (runwayAvailable) {
            runwayAvailable = false;
            System.out.println("[ControlTower]: " + aircraft.getId() + " cleared for runway.");
            return true;
        } else {
            if (aircraft.isLanding()) {
                landingQueue.add(aircraft);
                System.out.println("[ControlTower]: " + aircraft.getId() + " added to landing queue.");
            } else {
                takeoffQueue.add(aircraft);
                System.out.println("[ControlTower]: " + aircraft.getId() + " added to takeoff queue.");
            }
            return false;
        }
    }

    public synchronized void releaseRunway() {
        runwayAvailable = true;

        if (!landingQueue.isEmpty()) {
            Aircraft next = landingQueue.poll();
            System.out.println("[ControlTower]: " + next.getId() + " cleared to land.");
            runwayAvailable = false;
        } else if (!takeoffQueue.isEmpty()) {
            Aircraft next = takeoffQueue.poll();
            System.out.println("[ControlTower]: " + next.getId() + " cleared to take off.");
            runwayAvailable = false;
        }
    }
}

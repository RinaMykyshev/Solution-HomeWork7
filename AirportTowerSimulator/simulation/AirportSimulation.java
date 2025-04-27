package simulation;

import mediator.ControlTower;
import mediator.TowerMediator;
import aircraft.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class AirportSimulation {
    public static void main(String[] args) throws InterruptedException {
        TowerMediator tower = new ControlTower();
        List<Aircraft> aircraftList = new ArrayList<>();
        Random random = new Random();


        for (int i = 1; i <= 10; i++) {
            boolean landing = random.nextBoolean();
            int type = random.nextInt(3);
            Aircraft aircraft;
            if (type == 0) {
                aircraft = new PassengerPlane("P-" + i, landing);
            } else if (type == 1) {
                aircraft = new CargoPlane("C-" + i, landing);
            } else {
                aircraft = new Helicopter("H-" + i, landing);
            }
            aircraftList.add(aircraft);
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(() -> {
            if (!aircraftList.isEmpty()) {
                Aircraft aircraft = aircraftList.remove(0);


                if (new Random().nextInt(10) == 0) {
                    aircraft.send("MAYDAY", tower);
                } else {
                    boolean cleared = tower.requestRunway(aircraft);
                    if (cleared) {
                        tower.releaseRunway();
                    }
                }
            } else {
                System.out.println("All aircrafts processed.");
                executor.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}

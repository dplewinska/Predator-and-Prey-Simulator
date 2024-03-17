package org.brain;

import org.javatuples.Pair;

import static java.lang.Thread.sleep;

/**
 * class for creating hideouts restoring health and reproducing Prey, inherits from location
 */
public class Hideout extends Location {
    private Gui gui = Gui.getInstance();
    private World world = World.getInstance();

    public Hideout(String n, Pair<Integer, Integer> p, int c) {
        setName(n);
        setPosition(p);
        setCapacity(c);
        for (int i=0; i<getCapacity(); i++) {
            semaphore.release();
        }
    }

    public String toString() {
        return String.format("hideout\nname: %s\ncapacity: %d\ninside: %d\nwaiting: %d",
                getName(), getCapacity(), getCapacity() - semaphore.availablePermits(), getWaiting());
    }

    /**
     * restores health and creates a new Prey (with a random probability)
     * @param prey prey that uses the hideout
     * @throws InterruptedException
     */
    public void use(Prey prey) throws InterruptedException {
        boolean entered = tryEnter();
        if (!entered) {
            increaseWaiting();
            while (true) {
                entered = tryEnter();
                if (entered) {
                    decreaseWaiting();
                    break;
                }
            }
        }
        prey.setTarget(null);
        sleep(7000);
        prey.setHealth(100);
        if ((int)(10.0*Math.random()) > 5) {
            new Prey(prey.getName(), "prey", prey.getSpeed(), prey.getStrength(),
                    new Pair<>(getPosition().getValue0(), getPosition().getValue1()), 100, 100, 100);
            world.addOccupiedPositions(getPosition());
            world.addPopulation();
            gui.updatePopulation();
            gui.buttons.get(getPosition()).setIcon(gui.prey);
        }
        leave();
    }
}

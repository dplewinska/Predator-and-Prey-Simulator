package org.brain;

import org.javatuples.Pair;

import java.util.Objects;
import static java.lang.Thread.sleep;

/**
 * inherits from Location, Prey can either eat or drink
 */
public class Source extends Location {
    private String type;
    private int speed;
    private boolean active = false;
    private Gui gui = Gui.getInstance();
    private World world = World.getInstance();

    public Source(String n, Pair<Integer, Integer> p, int c, String t, int s) {
        setName(n);
        setPosition(p);
        setCapacity(c);
        setType(t);
        setSpeed(s);
        for (int i=0; i<getCapacity(); i++) {
            semaphore.release();
        }
    }

    public String toString() {
        return String.format("source\nname: %s\ntype: %s\ncapacity: %d\ninside: %d\nwaiting: %d",
                getName(), getType(), getCapacity(), getCapacity() - semaphore.availablePermits(), getWaiting());
    }

    public String getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setType(String t) {
        type = t;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    /**
     * makes the source active
     */
    public void activate() {
        active = true;
        if (Objects.equals(getType(), "water")) {
            world.addWaterSources(this);
            gui.activateWaterSource(getPosition());
        } else if (Objects.equals(getType(), "food")) {
            world.addFoodSources(this);
            gui.activateFoodSource(getPosition());
        }
    }

    /**
     * deactivates the source
     */
    public void deactivate() {
        active = false;
        if (Objects.equals(getType(), "water")) {
            world.removeWaterSource(this);
        } else if (Objects.equals(getType(), "food")) {
            world.removeFoodSource(this);
        }
        gui.deactivate(getPosition());
    }

    /**
     * refills water or energy of the Prey
     * @param prey Prey that uses the Source
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
        sleep(getSpeed());
        if (Objects.equals(getType(), "water")) {
            prey.setWater(100);
        } else if (Objects.equals(getType(), "food")) {
            prey.setEnergy(100);
        }
        leave();
    }
}
package org.brain;

import org.javatuples.Pair;
import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;

/**
 * abstract class, superclass of Source and Hideout
 */
public abstract class Location {
    String name;
    private Pair<Integer, Integer> position;
    private int capacity;
    private int waiting = 0;
    protected Semaphore semaphore = new Semaphore(0);
    private Semaphore waitingSemaphore = new Semaphore(1);

    public String getName() {return name;}
    public Pair<Integer, Integer> getPosition() {return position;}
    public int getCapacity() {return capacity;}
    public int getWaiting() {return waiting;}
    public void setName(String n) {name = n;}
    public void setPosition(Pair<Integer, Integer> p) {position = p;}
    public void setCapacity(int c) {capacity = c;}

    /**
     * increases number of Prey waiting to use the Location
     * @throws InterruptedException
     */
    public void increaseWaiting() throws InterruptedException {
        while (!waitingSemaphore.tryAcquire()) {
            sleep(1000);
        }
        waiting++;
        waitingSemaphore.release();
    }

    /**
     * decreases number of Prey waiting to use the Location
     * @throws InterruptedException
     */
    public void decreaseWaiting() throws InterruptedException {
        while (!waitingSemaphore.tryAcquire()) {
            sleep(1000);
        }
        waiting--;
        waitingSemaphore.release();
    }

    /**
     * if a semaphore is available Prey enters the location
     * @return success or no success
     * @throws InterruptedException
     */
    public boolean tryEnter() throws InterruptedException {
        sleep(100);
        return semaphore.tryAcquire();
    }

    /**
     * Prey leaves the Location and releases a semaphore
     */
    public void leave() {
        semaphore.release();
    }

    public String toString() {return "";}

    public void use(Prey prey) throws InterruptedException {}

}

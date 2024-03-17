package org.brain;

import org.javatuples.Pair;
import java.util.concurrent.Semaphore;

/**
 * a position on the map which can be occupied by only one Prey at a time
 */
public class Intersection {
    private Pair<Integer, Integer> position;
    private Semaphore semaphore = new Semaphore(1);

    public Intersection(Pair<Integer, Integer> p) {position = p;}

    public Pair<Integer, Integer> getPosition() {return position;}
    public void setPosition(Pair<Integer, Integer> p) {position = p;}

    /**
     * if a semaphore is available Prey enters the Intersection
     * @return success or no success
     */
    public boolean tryEnter() {return semaphore.tryAcquire();}

    /**
     * Prey leaves the Intersection releasing the semaphore
     */
    public void leave() {semaphore.release();}
}

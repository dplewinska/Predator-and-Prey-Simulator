package org.brain;

import org.javatuples.Pair;
import java.util.ArrayList;
import java.util.Collections;
import static java.lang.Thread.sleep;

/**
 * a runnable class inheriting from Animal which hunts for Prey
 */
public class Predator extends Animal implements Runnable {
    private Prey target;

    public Predator(String n, String sn, int s, int st, Pair<Integer, Integer> p, int h) throws InterruptedException {
        setName(n);
        setSpeciesName(sn);
        setSpeed(s);
        setStrength(st);
        setPosition(p);
        setHealth(h);
        world.addAnimal(this);
        world.addPredator(this);
        gui.updatePredator(String.format("\nbirth: %s", getName()));
        Thread thread = new Thread(this);
        thread.start();
    }

    public String toString() {
        return String.format("name: %s\nspecies: %s\nstrength: %d\nhealth: %d\ntarget: %s",
                getName(), getSpeciesName(), getStrength(), getHealth(), getTargetString());
    }

    public Prey getTarget() {return target;}
    public String getTargetString() {
        if (getTarget() != null) {
            return getTarget().getName();
        }
        return null;
    }
    public void setTarget(Prey p) {target = p;}

    /**
     * if Predator is at the same location as Prey both their health
     * decreases by strength they are attacked with
     * @throws InterruptedException
     */
    public void hunt() throws InterruptedException {
        boolean willDie = false;
        if (getTarget().getHealth() - getStrength() <= 0) {
            willDie = true;
        }
        setHealth(getHealth() - getTarget().getStrength());
        getTarget().setHealth(getTarget().getHealth() - getStrength());
        if (willDie) {relax();}
    }

    /**
     * waits for 5 seconds after a successful hunt
     * @throws InterruptedException
     */
    public void relax() throws InterruptedException {
        sleep(5000);
    }

    /**
     * in each turn the Predator either moves with its speed or dies
     */
    public void run() {
        while (true) {
            if (getHealth() <= 0 || isDead()) {
                try {
                    die();
                    gui.updatePredator(String.format("\ndeath: %s", getName()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            try {
                sleep(getSpeed());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                move();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * changes position of the Predator
     * @throws InterruptedException
     */
    public void move() throws InterruptedException {
        if (world.getPreys().size() > 0) {
            if (getTarget() == null) {
                ArrayList<Prey> preys = world.getPreys();
                Collections.shuffle(preys);
                setTarget(preys.get(0));
            }
        }
        int x1 = getPosition().getValue0();
        int y1 = getPosition().getValue1();
        if (getTarget() != null) {
            int x0 = getTarget().getPosition().getValue0();
            int y0 = getTarget().getPosition().getValue1();
            if (x0 == x1 && y0 == y1 && world.findLocation(getPosition()) == null) {hunt();}
            else if (x0 < x1) {x1--;}
            else if (x0 > x1) {x1++;}
            else if (y0 < y1) {y1--;}
            else if (y0 > y1) {y1++;}
        }
        world.removeOccupiedPositions(getPosition());
        gui.updateIcon(getPosition());
        setPosition(new Pair<>(x1,y1));
        world.addOccupiedPositions(getPosition());
        gui.updateIcon(getPosition());
    }
}
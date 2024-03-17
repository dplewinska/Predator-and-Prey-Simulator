package org.brain;

import org.javatuples.Pair;
import java.util.ArrayList;
import java.util.Objects;

/**
 * abstract class, superclass of Prey and Predator
 */
public abstract class Animal {

    private String name;
    private String speciesName;
    private int speed;
    private int strength;
    private Pair<Integer, Integer> position;
    private int health;
    private boolean dead = false;
    public ArrayList<Pair<Integer, Integer>> availablePositions = new ArrayList<>();
    World world = World.getInstance();
    Gui gui = Gui.getInstance();

    public String getName() {return name;}
    public String getSpeciesName() {return speciesName;}
    public int getSpeed() {return speed;}
    public int getStrength() {return strength;}
    public Pair<Integer, Integer> getPosition() {return position;}
    public int getHealth() {return health;}
    public void setName(String n) {name = n;}
    public void setSpeciesName(String sn) {speciesName = sn;}
    public void setSpeed(int s) {speed = s;}
    public void setStrength(int st) {strength = st;}
    public void setPosition(Pair<Integer, Integer> p) {position = p;}
    public void setHealth(int h) {health = h;}
    public boolean isDead() {return dead;}

    /**
     * marks animal as dead, so it gets removed in the next iteration of its run() method
     */
    public void makeDead() {dead = true;}
    public String toString() {return " ";}

    /**
     * removes animal from world
     * @throws InterruptedException
     */
    public void die() throws InterruptedException {
        world.removeOccupiedPositions(getPosition());
        gui.updateIcon(getPosition());
        world.removePopulation();
        world.removeAnimal(this);
        if (Objects.equals(getSpeciesName(), "prey")) {
            world.removePrey((Prey) this);
        } else {
            world.removePredator((Predator) this);
        }
    }
}

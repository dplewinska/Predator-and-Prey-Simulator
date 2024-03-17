package org.brain;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;
import static org.brain.Main.*;

/**
 * singleton class containing information and methods concerning everything happening on the grid
 */
public class World {
    private static World instance = null;
    private ArrayList<Pair<Integer, Integer>> occupiedPositions;
    private ArrayList<Pair<Integer, Integer>> allPaths;
    private ArrayList<Animal> animals;
    private ArrayList<Prey> preys;
    private ArrayList<Predator> predators;
    private ArrayList<Intersection> intersections;
    private ArrayList<Source> waterSources;
    private ArrayList<Source> foodSources;
    private ArrayList<Hideout> hideouts;
    private ArrayList<Location> locations;
    private int population = 0;
    private Semaphore semaphore = new Semaphore(1);
    private Semaphore occupiedPositionsSemaphore = new Semaphore(1);
    private Semaphore animalsSemaphore = new Semaphore(1);
    private Semaphore preysSemaphore = new Semaphore(1);
    private Semaphore predatorsSemaphore = new Semaphore(1);
    Gui gui = Gui.getInstance();


    public World() {
        occupiedPositions = new ArrayList<>();
        allPaths = new ArrayList<>();
        animals = new ArrayList<>();
        preys = new ArrayList<>();
        predators = new ArrayList<>();
        intersections = new ArrayList<>();
        waterSources = new ArrayList<>();
        foodSources = new ArrayList<>();
        hideouts = new ArrayList<>();
        locations = new ArrayList<>();

        allPaths.add(new Pair<>(0, 0));
        allPaths.add(new Pair<>(2, 0));
        allPaths.add(new Pair<>(3, 0));
        allPaths.add(new Pair<>(4, 0));
        allPaths.add(new Pair<>(6, 0));
        allPaths.add(new Pair<>(7, 0));
        allPaths.add(new Pair<>(8, 0));

        allPaths.add(new Pair<>(0, 1));
        allPaths.add(new Pair<>(3, 1));
        allPaths.add(new Pair<>(8, 1));

        allPaths.add(new Pair<>(0, 2));
        allPaths.add(new Pair<>(1, 2));
        allPaths.add(new Pair<>(2, 2));
        allPaths.add(new Pair<>(3, 2));
        allPaths.add(new Pair<>(4, 2));
        allPaths.add(new Pair<>(5, 2));
        allPaths.add(new Pair<>(6, 2));
        allPaths.add(new Pair<>(7, 2));
        allPaths.add(new Pair<>(8, 2));

        allPaths.add(new Pair<>(1, 3));
        allPaths.add(new Pair<>(3, 3));
        allPaths.add(new Pair<>(8, 3));

        allPaths.add(new Pair<>(1, 4));
        allPaths.add(new Pair<>(3, 4));
        allPaths.add(new Pair<>(5, 4));
        allPaths.add(new Pair<>(7, 4));
        allPaths.add(new Pair<>(8, 4));

        allPaths.add(new Pair<>(0, 5));
        allPaths.add(new Pair<>(1, 5));
        allPaths.add(new Pair<>(3, 5));
        allPaths.add(new Pair<>(5, 5));
        allPaths.add(new Pair<>(6, 5));
        allPaths.add(new Pair<>(7, 5));

        allPaths.add(new Pair<>(1, 6));
        allPaths.add(new Pair<>(2, 6));
        allPaths.add(new Pair<>(3, 6));
        allPaths.add(new Pair<>(4, 6));
        allPaths.add(new Pair<>(5, 6));
        allPaths.add(new Pair<>(7, 6));

        allPaths.add(new Pair<>(0, 7));
        allPaths.add(new Pair<>(1, 7));
        allPaths.add(new Pair<>(3, 7));
        allPaths.add(new Pair<>(7, 7));
        allPaths.add(new Pair<>(8, 7));

        allPaths.add(new Pair<>(0, 8));
        allPaths.add(new Pair<>(3, 8));
        allPaths.add(new Pair<>(4, 8));
        allPaths.add(new Pair<>(5, 8));
        allPaths.add(new Pair<>(6, 8));


        intersections.add(new Intersection(new Pair<>(3, 0)));
        intersections.add(new Intersection(new Pair<>(1, 2)));
        intersections.add(new Intersection(new Pair<>(3, 2)));
        intersections.add(new Intersection(new Pair<>(8, 2)));
        intersections.add(new Intersection(new Pair<>(1, 5)));
        intersections.add(new Intersection(new Pair<>(5, 5)));
        intersections.add(new Intersection(new Pair<>(7, 5)));
        intersections.add(new Intersection(new Pair<>(1, 6)));
        intersections.add(new Intersection(new Pair<>(3, 6)));
    }

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public ArrayList<Pair<Integer, Integer>> getOccupiedPositions() {
        return occupiedPositions;
    }

    public ArrayList<Pair<Integer, Integer>> getAllPositions() {
        return allPaths;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Prey> getPreys() {
        return preys;
    }

    public ArrayList<Predator> getPredators() {
        return predators;
    }

    public ArrayList<Intersection> getIntersections() {
        return intersections;
    }

    public int getPopulation() throws InterruptedException {
        return population;
    }

    public void addPopulation() {
        while (true) {
            if (semaphore.tryAcquire()) {
                break;
            }
        }
        population++;
        semaphore.release();
        activateSources();
    }

    public void removePopulation() {
        while (true) {
            if (semaphore.tryAcquire()) {
                break;
            }
        }
        population--;
        semaphore.release();
        activateSources();
    }

    public void addOccupiedPositions(Pair<Integer, Integer> position) {
        occupiedPositions.add(position);
    }

    public void removeOccupiedPositions(Pair<Integer, Integer> position) throws InterruptedException {
        while (!occupiedPositionsSemaphore.tryAcquire()) {}
        for (int i = 0; i < occupiedPositions.size(); i++) {
            if (position.compareTo(occupiedPositions.get(i)) == 0) {
                occupiedPositions.remove(i);
                break;
            }
        }
        occupiedPositionsSemaphore.release();
    }

    public int countOccupiedPositions(Pair<Integer, Integer> position) throws InterruptedException {
        while (!occupiedPositionsSemaphore.tryAcquire()) {}
        int number = 0;
        for (int i = 0; i < occupiedPositions.size(); i++) {
            if (position.compareTo(occupiedPositions.get(i)) == 0) {
                number++;
            }
        }
        occupiedPositionsSemaphore.release();
        return number;
    }

    public boolean inAllPaths(Pair<Integer, Integer> position) {
        boolean x = false;
        for (Pair<Integer, Integer> p : allPaths) {
            if (position.compareTo(p) == 0) {
                x = true;
                break;
            }
        }
        return x;
    }

    public void addAnimal(Animal animal) throws InterruptedException {
        while (!animalsSemaphore.tryAcquire()) {}
        animals.add(animal);
        gui.updatePopulation();
        animalsSemaphore.release();
    }

    public void removeAnimal(Animal animal) throws InterruptedException {
        while (!animalsSemaphore.tryAcquire()) {}
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i) == animal) {
                animals.remove(i);
                break;
            }
        }
        gui.updatePopulation();
        animalsSemaphore.release();
    }

    public ArrayList<Animal> getAnimalsPosition(Pair<Integer, Integer> position) throws InterruptedException {
        while (!animalsSemaphore.tryAcquire()) {}
        ArrayList<Animal> animalsPosition = new ArrayList<>();
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getPosition().compareTo(position) == 0) {
                animalsPosition.add(animals.get(i));
            }
        }
        animalsSemaphore.release();
        return animalsPosition;
    }

    public void addPrey(Prey prey) throws InterruptedException {
        while (!preysSemaphore.tryAcquire()) {}
        preys.add(prey);
        preysSemaphore.release();
    }

    public void removePrey(Prey prey) throws InterruptedException {
        while (!preysSemaphore.tryAcquire()) {}
        for (int i = 0; i < preys.size(); i++) {
            if (preys.get(i) == prey) {
                preys.remove(i);
                break;
            }
        }
        preysSemaphore.release();
        while (!predatorsSemaphore.tryAcquire()) {}
        if (getPredators().size() > 0) {
            for (Predator predator : getPredators()) {
                if (predator.getTarget() == prey) {
                    predator.setTarget(null);
                }
            }
        }
        predatorsSemaphore.release();
    }

    public void addPredator(Predator predator) throws InterruptedException {
        while (!predatorsSemaphore.tryAcquire()) {}
        predators.add(predator);
        predatorsSemaphore.release();
    }

    public void removePredator(Predator predator) throws InterruptedException {
        while (!predatorsSemaphore.tryAcquire()) {}
        for (int i = 0; i < predators.size(); i++) {
            if (predators.get(i) == predator) {
                predators.remove(i);
                break;
            }
        }
        predatorsSemaphore.release();
    }

    public ArrayList<Prey> getPreysPosition(Pair<Integer, Integer> position) throws InterruptedException {
        while (!preysSemaphore.tryAcquire()) {}
        ArrayList<Prey> preysPosition = new ArrayList<>();
        for (Prey prey : preys) {
            if (prey.getPosition().compareTo(position) == 0) {
                preysPosition.add(prey);
            }
        }
        preysSemaphore.release();
        return preysPosition;
    }

    public Pair<Integer, Integer> getAvailablePosition(Pair<Integer, Integer> position) {
        ArrayList<Pair<Integer, Integer>> availablePositions = new ArrayList<>();
        int x = position.getValue0();
        int y = position.getValue1();
        Pair<Integer, Integer> pair0 = new Pair<>(x + 1, y);
        Pair<Integer, Integer> pair1 = new Pair<>(x - 1, y);
        Pair<Integer, Integer> pair2 = new Pair<>(x, y + 1);
        Pair<Integer, Integer> pair3 = new Pair<>(x, y - 1);
        if (inAllPaths(pair0)) {
            availablePositions.add(pair0);
        }
        if (inAllPaths(pair1)) {
            availablePositions.add(pair1);
        }
        if (inAllPaths(pair2)) {
            availablePositions.add(pair2);
        }
        if (inAllPaths(pair3)) {
            availablePositions.add(pair3);
        }
        Collections.shuffle(availablePositions);
        return availablePositions.get(0);
    }

    public int isIntersection(Pair<Integer, Integer> position) {
        if (position.compareTo(new Pair<>(3, 0)) == 0) {
            return 0;
        }
        if (position.compareTo(new Pair<>(1, 2)) == 0) {
            return 1;
        }
        if (position.compareTo(new Pair<>(3, 2)) == 0) {
            return 2;
        }
        if (position.compareTo(new Pair<>(8, 2)) == 0) {
            return 3;
        }
        if (position.compareTo(new Pair<>(1, 5)) == 0) {
            return 4;
        }
        if (position.compareTo(new Pair<>(5, 5)) == 0) {
            return 5;
        }
        if (position.compareTo(new Pair<>(7, 5)) == 0) {
            return 6;
        }
        if (position.compareTo(new Pair<>(1, 6)) == 0) {
            return 7;
        }
        if (position.compareTo(new Pair<>(3, 6)) == 0) {
            return 8;
        }
        return -1;
    }

    /**
     * finds the Location at a given position
     * @param position position of the Location
     * @return Location at the position
     */
    public Location findLocation(Pair<Integer, Integer> position) {
        for (Location l : locations) {
            if (l.getPosition().compareTo(position) == 0) {
                return l;
            }
        }
        return null;
    }

    public ArrayList<Source> getWaterSources() {
        return waterSources;
    }

    public ArrayList<Source> getFoodSources() {
        return foodSources;
    }

    public ArrayList<Hideout> getHideouts() {
        return hideouts;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void addWaterSources(Source s) {
        waterSources.add(s);
        locations.add(s);
    }

    public void addFoodSources(Source s) {
        foodSources.add(s);
        locations.add(s);
    }

    public void addHideouts(Hideout h) {
        hideouts.add(h);
        locations.add(h);
    }

    public void removeLocation(Location location) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i) == location) {
                locations.remove(i);
                break;
            }
        }
        for (Prey prey : getPreys()) {
            if (prey.getTarget() == location) {
                prey.setTarget(null);
            }
        }
    }

    public void removeWaterSource(Source source) {
        for (int i = 0; i < waterSources.size(); i++) {
            if (waterSources.get(i) == source) {
                waterSources.remove(i);
                break;
            }
        }
        removeLocation(source);
    }

    public void removeFoodSource(Source source) {
        for (int i = 0; i < foodSources.size(); i++) {
            if (foodSources.get(i) == source) {
                foodSources.remove(i);
                break;
            }
        }
        removeLocation(source);
    }

    public void removeHideout(Hideout hideout) {
        for (int i = 0; i < hideouts.size(); i++) {
            if (hideouts.get(i) == hideout) {
                hideouts.remove(i);
                break;
            }
        }
        removeLocation(hideout);
    }

    /**
     * activates sources after reaching a certain population
     */
    public void activateSources() {
        if (population > 25) {
            water4.activate();
            food4.activate();
        } else if (population > 18) {
            water3.activate();
            food3.activate();
        } else if (population > 9) {
            water2.activate();
            food2.activate();
        } else if (population > 3) {
            water1.activate();
            food1.activate();
        }
    }
}
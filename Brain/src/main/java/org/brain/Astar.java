package org.brain;

import org.javatuples.Pair;
import java.util.*;

/**
 * an implementation of a-star algorithm for Prey pathfinding
 */
public class Astar {
    /**
     * node used in a-star algorithm
     */
    static class Node {
        int x, y;
        int f, g, h;
        Node parent = null;

        Node(Pair<Integer,Integer> position) {
            this.x = position.getValue0();
            this.y = position.getValue1();
        }
    }

    static World world = World.getInstance();

    /**
     * heuristic function for a-star algorithm returning manhattan distance between two nodes
     * @param a first node
     * @param b second node
     * @return manhattan distance
     */
    static int heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    /**
     * a-star algorithm
     * @param start starting node
     * @param goal target node
     * @return list of next moves
     */
    static ArrayList<Pair<Integer,Integer>> aStar(Node start, Node goal) {
        int m = 9, n = 9;
        Set<Node> closedSet = new HashSet<>();
        PriorityQueue<Node> openSet = new PriorityQueue<>((a, b) -> a.f - b.f);

        start.g = 0;
        start.h = heuristic(start, goal);
        start.f = start.g + start.h;
        openSet.offer(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.x == goal.x && current.y == goal.y) {
                ArrayList<Pair<Integer,Integer>> path = new ArrayList<>();
                while (current != null) {
                    path.add(new Pair<Integer,Integer>(current.x, current.y));
                    current = current.parent;
                }
                Collections.reverse(path);
                path.remove(0);
                return path;
            }

            closedSet.add(current);

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;

                    int x = current.x + i, y = current.y + j;
                    if (!getAvailablePositions(new Pair<Integer,Integer>(current.x, current.y)).contains(new Pair<Integer,Integer>(x,y))) continue;
                    if (closedSet.contains(new Node(new Pair<Integer,Integer>(x,y)))) continue;

                    Node neighbor = new Node(new Pair<Integer,Integer>(x,y));
                    neighbor.g = current.g + 1;
                    neighbor.h = heuristic(neighbor, goal);
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.parent = current;

                    openSet.offer(neighbor);
                }
            }
        }
        return null;
    }

    /**
     * function to find available paths directly next to a given position
     * @param position checked position
     * @return an ArrayList of available positions
     */
    public static ArrayList<Pair<Integer, Integer>> getAvailablePositions(Pair<Integer, Integer> position) {
        ArrayList<Pair<Integer, Integer>> availablePositions = new ArrayList<>();
        int x = position.getValue0();
        int y = position.getValue1();
        Pair<Integer, Integer> pair0 = new Pair<>(x + 1, y);
        Pair<Integer, Integer> pair1 = new Pair<>(x - 1, y);
        Pair<Integer, Integer> pair2 = new Pair<>(x, y + 1);
        Pair<Integer, Integer> pair3 = new Pair<>(x, y - 1);
        if (world.inAllPaths(pair0)) {
            availablePositions.add(pair0);
        }
        if (world.inAllPaths(pair1)) {
            availablePositions.add(pair1);
        }
        if (world.inAllPaths(pair2)) {
            availablePositions.add(pair2);
        }
        if (world.inAllPaths(pair3)) {
            availablePositions.add(pair3);
        }
        return availablePositions;
    }
}

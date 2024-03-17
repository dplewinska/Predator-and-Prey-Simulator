package org.brain;

import org.javatuples.Pair;
//import jaco.mp3.player.MP3Player;
import java.io.File;

/**
 * main class of the program
 */
public class Main {
    static Source water0 = new Source("bubble", new Pair<>(5, 4), 1, "water", 5000);
    static Source water1 = new Source("waterfall", new Pair<>(1, 3), 2, "water", 6000);
    static Source water2 = new Source("pond", new Pair<>(7, 7), 3, "water", 7000);
    static Source water3 = new Source("sprinkler", new Pair<>(6, 0), 4, "water", 8000);
    static Source water4 = new Source("swamp", new Pair<>(0, 8), 5, "water", 9000);
    static Source food0 = new Source("indian", new Pair<>(3, 3), 2, "food", 5000);
    static Source food1 = new Source("kfc", new Pair<>(4, 8), 1, "food", 6000);
    static Source food2 = new Source("mcdonald's", new Pair<>(8, 4), 3, "food", 7000);
    static Source food3 = new Source("kebab", new Pair<>(0, 5), 4, "food", 8000);
    static Source food4 = new Source("pizza", new Pair<>(3, 0), 5, "food", 9000);
    public static void main(String[] args) {
        Gui gui = Gui.getInstance();
        World world = World.getInstance();
        water0.activate();
        food0.activate();
        world.addHideouts(new Hideout("retreat", new Pair<>(0,0), 3));
        world.addHideouts(new Hideout("hideaway", new Pair<>(7,2), 2));
        world.addHideouts(new Hideout("lair", new Pair<>(3,4), 4));
        world.addHideouts(new Hideout("sanctum", new Pair<>(1,7), 1));
        world.addHideouts(new Hideout("shelter", new Pair<>(5,8), 5));

//        try {
//            File f = new File("music.mp3");
//            MP3Player mp3Player = new MP3Player(f);
//            mp3Player.play();
//            while (!mp3Player.isStopped()) {
//                Thread.sleep(5000);
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
    }
}
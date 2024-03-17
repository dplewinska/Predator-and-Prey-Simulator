package org.brain;

import org.javatuples.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

/**
 * graphical user interface of the program
 */
public class Gui extends JFrame {
    private static Gui instance = null;
    static World world = World.getInstance();
    HashMap<Pair<Integer, Integer>, JButton> buttons = new HashMap<>();
    ImageIcon prey = new ImageIcon("prey.png");
    ImageIcon prey2 = new ImageIcon("prey2.png");
    ImageIcon prey3 = new ImageIcon("prey3.png");
    ImageIcon prey4 = new ImageIcon("prey4.png");
    Animal currentAnimal = null;
    Pair <Integer, Integer> currentPosition = null;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton preyButton;
    private JButton predatorButton;
    private JPanel sideBottomPanel;
    private JPanel topTopPanel;
    private JPanel topBottomPanel;
    private JButton hideoutButton;
    private JButton foodButton;
    private JButton waterButton;
    private JButton killButton;
    private JButton x08;
    private JButton x18;
    private JButton x28;
    private JButton x38;
    private JButton x48;
    private JButton x58;
    private JButton x68;
    private JButton x78;
    private JButton x88;
    private JButton x07;
    private JButton x06;
    private JButton x05;
    private JButton x04;
    private JButton x03;
    private JButton x02;
    private JButton x01;
    private JButton x17;
    private JButton x16;
    private JButton x15;
    private JButton x14;
    private JButton x13;
    private JButton x12;
    private JButton x11;
    private JButton x10;
    private JButton x27;
    private JButton x26;
    private JButton x25;
    private JButton x24;
    private JButton x23;
    private JButton x22;
    private JButton x21;
    private JButton x20;
    private JButton x37;
    private JButton x36;
    private JButton x35;
    private JButton x34;
    private JButton x33;
    private JButton x32;
    private JButton x31;
    private JButton x30;
    private JButton x47;
    private JButton x46;
    private JButton x45;
    private JButton x44;
    private JButton x43;
    private JButton x42;
    private JButton x41;
    private JButton x40;
    private JButton x57;
    private JButton x56;
    private JButton x55;
    private JButton x54;
    private JButton x53;
    private JButton x52;
    private JButton x51;
    private JButton x50;
    private JButton x67;
    private JButton x66;
    private JButton x65;
    private JButton x64;
    private JButton x63;
    private JButton x62;
    private JButton x61;
    private JButton x60;
    private JButton x77;
    private JButton x76;
    private JButton x75;
    private JButton x74;
    private JButton x73;
    private JButton x72;
    private JButton x71;
    private JButton x70;
    private JButton x87;
    private JButton x86;
    private JButton x85;
    private JButton x84;
    private JButton x83;
    private JButton x82;
    private JButton x81;
    private JButton x80;
    private JButton x00;
    private JTextField nameField;
    private JLabel nameLabel;
    private JPanel sideTopPanel;
    private JTextArea infoTextArea;
    private JLabel populationLabel;
    private JPanel predatorPanel;
    private JPanel preyPanel;
    private JTextArea predatorTextArea;
    private JTextArea preyTextArea;
    private JLabel predatorLabel;
    private JLabel preyLabel;

    public void addHashMap() {
        buttons.put(new Pair<>(0, 0), x00);
        buttons.put(new Pair<>(1, 0), x10);
        buttons.put(new Pair<>(2, 0), x20);
        buttons.put(new Pair<>(3, 0), x30);
        buttons.put(new Pair<>(4, 0), x40);
        buttons.put(new Pair<>(5, 0), x50);
        buttons.put(new Pair<>(6, 0), x60);
        buttons.put(new Pair<>(7, 0), x70);
        buttons.put(new Pair<>(8, 0), x80);

        buttons.put(new Pair<>(0, 1), x01);
        buttons.put(new Pair<>(1, 1), x11);
        buttons.put(new Pair<>(2, 1), x21);
        buttons.put(new Pair<>(3, 1), x31);
        buttons.put(new Pair<>(4, 1), x41);
        buttons.put(new Pair<>(5, 1), x51);
        buttons.put(new Pair<>(6, 1), x61);
        buttons.put(new Pair<>(7, 1), x71);
        buttons.put(new Pair<>(8, 1), x81);

        buttons.put(new Pair<>(0, 2), x02);
        buttons.put(new Pair<>(1, 2), x12);
        buttons.put(new Pair<>(2, 2), x22);
        buttons.put(new Pair<>(3, 2), x32);
        buttons.put(new Pair<>(4, 2), x42);
        buttons.put(new Pair<>(5, 2), x52);
        buttons.put(new Pair<>(6, 2), x62);
        buttons.put(new Pair<>(7, 2), x72);
        buttons.put(new Pair<>(8, 2), x82);

        buttons.put(new Pair<>(0, 3), x03);
        buttons.put(new Pair<>(1, 3), x13);
        buttons.put(new Pair<>(2, 3), x23);
        buttons.put(new Pair<>(3, 3), x33);
        buttons.put(new Pair<>(4, 3), x43);
        buttons.put(new Pair<>(5, 3), x53);
        buttons.put(new Pair<>(6, 3), x63);
        buttons.put(new Pair<>(7, 3), x73);
        buttons.put(new Pair<>(8, 3), x83);

        buttons.put(new Pair<>(0, 4), x04);
        buttons.put(new Pair<>(1, 4), x14);
        buttons.put(new Pair<>(2, 4), x24);
        buttons.put(new Pair<>(3, 4), x34);
        buttons.put(new Pair<>(4, 4), x44);
        buttons.put(new Pair<>(5, 4), x54);
        buttons.put(new Pair<>(6, 4), x64);
        buttons.put(new Pair<>(7, 4), x74);
        buttons.put(new Pair<>(8, 4), x84);

        buttons.put(new Pair<>(0, 5), x05);
        buttons.put(new Pair<>(1, 5), x15);
        buttons.put(new Pair<>(2, 5), x25);
        buttons.put(new Pair<>(3, 5), x35);
        buttons.put(new Pair<>(4, 5), x45);
        buttons.put(new Pair<>(5, 5), x55);
        buttons.put(new Pair<>(6, 5), x65);
        buttons.put(new Pair<>(7, 5), x75);
        buttons.put(new Pair<>(8, 5), x85);

        buttons.put(new Pair<>(0, 6), x06);
        buttons.put(new Pair<>(1, 6), x16);
        buttons.put(new Pair<>(2, 6), x26);
        buttons.put(new Pair<>(3, 6), x36);
        buttons.put(new Pair<>(4, 6), x46);
        buttons.put(new Pair<>(5, 6), x56);
        buttons.put(new Pair<>(6, 6), x66);
        buttons.put(new Pair<>(7, 6), x76);
        buttons.put(new Pair<>(8, 6), x86);

        buttons.put(new Pair<>(0, 7), x07);
        buttons.put(new Pair<>(1, 7), x17);
        buttons.put(new Pair<>(2, 7), x27);
        buttons.put(new Pair<>(3, 7), x37);
        buttons.put(new Pair<>(4, 7), x47);
        buttons.put(new Pair<>(5, 7), x57);
        buttons.put(new Pair<>(6, 7), x67);
        buttons.put(new Pair<>(7, 7), x77);
        buttons.put(new Pair<>(8, 7), x87);

        buttons.put(new Pair<>(0, 8), x08);
        buttons.put(new Pair<>(1, 8), x18);
        buttons.put(new Pair<>(2, 8), x28);
        buttons.put(new Pair<>(3, 8), x38);
        buttons.put(new Pair<>(4, 8), x48);
        buttons.put(new Pair<>(5, 8), x58);
        buttons.put(new Pair<>(6, 8), x68);
        buttons.put(new Pair<>(7, 8), x78);
        buttons.put(new Pair<>(8, 8), x88);

    }


    public Gui() {
        addHashMap();
        this.setTitle("Brain");
        this.setContentPane(mainPanel);
        this.setSize(914, 577);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        predatorButton.setVisible(false);
        predatorButton.setEnabled(false);
        preyButton.setVisible(false);
        preyButton.setEnabled(false);
        killButton.setVisible(false);
        killButton.setEnabled(false);
        waterButton.setVisible(false);
        waterButton.setEnabled(false);
        foodButton.setVisible(false);
        foodButton.setEnabled(false);
        hideoutButton.setVisible(false);
        hideoutButton.setEnabled(false);
        ImageIcon rainbow = new ImageIcon("rainbow.png");
        this.setIconImage(rainbow.getImage());
        foodButton.setIcon(new ImageIcon("pizza.png"));
        killButton.setIcon(new ImageIcon("skull.png"));
        waterButton.setIcon((new ImageIcon("water.png")));
        hideoutButton.setIcon(new ImageIcon("bed.png"));
        nameField.setBorder(BorderFactory.createEmptyBorder());
        /**
         * creates a new Prey
         */
        preyButton.addActionListener(e -> {
            if (world.inAllPaths(currentPosition) && world.isIntersection(currentPosition) == -1) {
                String name = nameField.getText();
                try {
                    new Prey(name, "prey", (int) (1000.0 * Math.random() + 500), (int) (30.0 * Math.random()),
                            currentPosition, 100, 100, 100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                world.addOccupiedPositions(currentPosition);
                world.addPopulation();
                try {
                    updatePopulation();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                buttons.get(currentPosition).setIcon(prey);
            }
        });
        /**
         * creates a new Predator
         */
        predatorButton.addActionListener(e -> {
            String name = nameField.getText();
            try {
                new Predator(name, "predator", (int) (500.0 * Math.random() + 100), (int) (100.0 * Math.random()),
                        currentPosition, 100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            world.addOccupiedPositions(currentPosition);
            world.addPopulation();
            try {
                updatePopulation();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            buttons.get(currentPosition).setIcon(prey);
        });
        /**
         * kill selected Animal
         */
        killButton.addActionListener(e -> {
            if (currentAnimal != null) {
                currentAnimal.makeDead();
            }
        });
        /**
         * redirects Prey to a random water Source
         */
        waterButton.addActionListener(e -> {
            if (currentAnimal != null) {
                Prey currentPrey = (Prey) (currentAnimal);
                currentPrey.setTarget(world.getWaterSources().get((int) (world.getWaterSources().size() * Math.random())));
                currentPrey.setMoves();
            }
        });
        /**
         * redirects Prey to a random food Source
         */
        foodButton.addActionListener(e -> {
            if (currentAnimal != null) {
                Prey currentPrey = (Prey) (currentAnimal);
                currentPrey.setTarget(world.getFoodSources().get((int) (world.getFoodSources().size() * Math.random())));
                currentPrey.setMoves();
            }
        });
        /**
         * redirects Prey to a random hideout
         */
        hideoutButton.addActionListener(e -> {
            if (currentAnimal != null) {
                Prey currentPrey = (Prey) (currentAnimal);
                currentPrey.setTarget(world.getHideouts().get((int) (world.getHideouts().size() * Math.random())));
                currentPrey.setMoves();
            }
        });

        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++){
                Pair<Integer, Integer> position = new Pair<>(i,j);
                buttons.get(position).addActionListener(e -> {
                currentPosition = position;
                predatorButton.setVisible(true);
                predatorButton.setEnabled(true);
                killButton.setVisible(false);
                killButton.setEnabled(false);
                waterButton.setVisible(false);
                waterButton.setEnabled(false);
                foodButton.setVisible(false);
                foodButton.setEnabled(false);
                hideoutButton.setVisible(false);
                hideoutButton.setEnabled(false);
                if (world.inAllPaths(currentPosition)) {
                    preyButton.setVisible(true);
                    preyButton.setEnabled(true);
                } else {
                    preyButton.setVisible(false);
                    preyButton.setEnabled(false);
                }
                if (world.findLocation(position) != null) {
                    infoTextArea.setText(world.findLocation(position).toString());
                } else {
                    try {
                        if (world.countOccupiedPositions(position) == 0) {
                            currentAnimal = null;
                            infoTextArea.setText("this square is empty");
                        } else if (world.countOccupiedPositions(position) == 1) {
                            killButton.setVisible(true);
                            killButton.setEnabled(true);
                            currentAnimal = world.getAnimalsPosition(position).get(0);
                            if (currentAnimal != null) {
                                if (Objects.equals(currentAnimal.getSpeciesName(), "prey")) {
                                    waterButton.setVisible(true);
                                    waterButton.setEnabled(true);
                                    foodButton.setVisible(true);
                                    foodButton.setEnabled(true);
                                    hideoutButton.setVisible(true);
                                    hideoutButton.setEnabled(true);
                                }
                            }
                            infoTextArea.setText(currentAnimal.toString());
                        } else {
                            currentAnimal = null;
                            infoTextArea.setText(String.format("this square is occupied by %d animals",
                                    world.getAnimalsPosition(position).size()));
                        }
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            }
        }
    }

    public static Gui getInstance() {
        if (instance == null) {
            instance = new Gui();
        }
        return instance;
    }

    /**
     * updates icon to show the number of Animals occupying the position
     * @param position position to update
     * @throws InterruptedException
     */
    public void updateIcon(Pair<Integer, Integer> position) throws InterruptedException {
        int x = world.countOccupiedPositions(position);
        if (x == 1) {
            buttons.get(position).setIcon(prey);
        } else if (x == 2) {
            buttons.get(position).setIcon(prey2);
        } else if (x == 3) {
            buttons.get(position).setIcon(prey3);
        } else if (x > 3) {
            buttons.get(position).setIcon(prey4);
        } else {
            buttons.get(position).setIcon(null);
        }
    }

    /**
     * changes background of water Source after activation
     * @param position
     */
    public void activateWaterSource(Pair<Integer, Integer> position) {
        buttons.get(position).setBackground(new Color(0xB0D7DC));
    }

    /**
     * changes background of food Source after activation
     * @param position
     */
    public void activateFoodSource(Pair<Integer, Integer> position) {
        buttons.get(position).setBackground(new Color(0xF7E484));
    }

    /**
     * changes background of Source after deactivation
     * @param position
     */
    public void deactivate(Pair<Integer, Integer> position) {
        buttons.get(position).setBackground(new Color(0xFDA8B3));
    }

    /**
     * updates the cyrrent population
     * @throws InterruptedException
     */
    public void updatePopulation() throws InterruptedException {
        int population = world.getAnimals().size();
        if (world.getAnimals().size() <= world.getPopulation()) {
            populationLabel.setText(String.format("population: %d", population));
        } else {
            populationLabel.setText(String.format("population: %d", world.getPopulation()));
        }
    }

    /**
     * displays information about birth or death of a Predator
     * @param s information to display
     */
    public void updatePredator(String s) {
        if (predatorTextArea.getText().length() > 100) {
            predatorTextArea.setText("");
        }
        predatorTextArea.append(s);
    }
    /**
     * displays information about birth or death of a Prey
     * @param s information to display
     */
    public void updatePrey(String s) {
        if (preyTextArea.getText().length() > 100) {
            preyTextArea.setText("");
        }
        preyTextArea.append(s);
    }
}
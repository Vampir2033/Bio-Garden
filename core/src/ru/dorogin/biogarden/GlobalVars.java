package ru.dorogin.biogarden;

import com.badlogic.gdx.graphics.Color;

public class GlobalVars {
    // Grid
    public static final int GRID_WIDTH = 27*4;
    public static final int GRID_HEIGHT = 15*4;
    public static final Color GRID_COLOR = Color.GRAY;

    // Gameplay
    public static final float FPS = 30;
    public static final int  COUNT_ENTITIES_START = 100;
    public static final int  COUNT_GRASS_START = 500;
    public static final float PLANT_GENERATE_CHANCE = 0.001f;
    public static final int MAX_COUNT_PLANT = 50;
    public static final int BASE_DNA_LENGTH = 300;
    public static final int ANIMAL_START_ENERGY = 1000;

    // Entities
    public static final int ANIMAL_TACT_ENERGY = 1;
    public static final int MAX_NON_TERMINATE_COMMANDS = 20;
    public static final int GRASS_TEXTURE_QUALITY = 128;
    public static final int MEAT_TEXTURE_QUALITY = 128;

    // DNA
    public static final int RELATIVES_MIN_DNK_DIFF = 10;
    public static final int ONE_SPECIES_MIN_DNK_DIFF = 50;
    public static final int DIFFERENT_MIN_DNK_DIFF = Integer.MAX_VALUE;
    public static final float MUTATE_PROBABILITY = 0.001f;
    public static final int MIN_REPRODUCTION_ENERGY = 1500;
    public static final float BASE_PERCENT_OF_ENERGY_FOR_CHILDREN = 0.5f;
    public static final int BASE_MAX_AGE = 1000;
    public static final int MAX_COMMAND_VALUE = 100;
    public static final int ATACK_COST = 30;
    public static final int ATACK_SIZE = 500;
    public static final int MOVE_ENERGY = 10;
    public static final int MEAT_ENERGY = 500;
    public static final int PLANT_ENERGY = 200;
    public static final int BASE_REPRODUCTION_ENERGY = MEAT_ENERGY;

    // plants
    public static final float PLANT_REPRODUCTION_PROBABILITY = 0.01f;

}

package io.github.team10.escapefromuni;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;

/**
 * NEW CLASS
 * AchievementManager class that handles the loading, saving and updating of game achievements.
 */
public class AchievementManager {

    private final ArrayList<Achievement> achievements = new ArrayList<>();

    /**
     * Initialises all achievements, setting their complete status to false.
     */
    public void initAchievements() {
        System.out.println("Initialising Achievements...");
        if (!achievements.isEmpty()) {
            System.out.println("Achievements are already initialised!");
        } else {
            for (AchievementType type : AchievementType.values()) {
                achievements.add(new Achievement(type, false));
            }
            System.out.println("Initialised " + achievements.size() + "/" + AchievementType.values().length + " achievements!");
        }
    }

    /**
     * Loads achievements from file, 'achievements.txt'.
     * If achievements are already loaded, does nothing.
     * If it cannot find the file, then initialises achievements instead.
     */
    public void loadAchievements() {
        System.out.println("Loading Achievements...");
        if (!achievements.isEmpty()) {
            System.out.println("Achievements are already loaded!");
        } else {
            try {
                FileHandle file = Gdx.files.local("achievements.txt");
                String[] text = file.readString().split("\n");
                for (String substring : text) {
                    String[] values = substring.split("\\|");
                    achievements.add(new Achievement(AchievementType.valueOf(values[0]), Boolean.parseBoolean(values[1])));
                }
            } catch (Exception e) { // if 'achievements.json' not found i.e. first time playing
                System.out.println(e.getMessage());
                initAchievements();
            }
            System.out.println("Loaded " + achievements.size() + "/" + AchievementType.values().length + " achievements!");
        }
    }

    /**
     * Stores achievements to file, 'achievements.txt', overwriting any data.
     */
    public void saveAchievements() {
        System.out.println("Saving Achievements...");
        int total = 0;
        StringBuilder text = new  StringBuilder();
        for (Achievement a : achievements) {
            text.append(a).append("\n");
            total++;
        }

        FileHandle file = Gdx.files.local("achievements.txt");
        file.writeString(text.toString(), false);
        System.out.println("Saved " + total + "/" + AchievementType.values().length + " achievements!");
    }

    /**
     * Prints all achievements to terminal, primarily used for debugging.
     */
    public void printAchievements() {
        System.out.println("Printing Achievements...");
        if  (achievements.isEmpty()) {
            System.out.println("There are no achievements.");
        } else {
            int total = 0;
            StringBuilder output = new StringBuilder();
            for (Achievement a : achievements) {
                output.append(a).append("\n");
                total++;
            }
            System.out.println(output);
            System.out.println("Printed " + total + "/" + AchievementType.values().length + " achievements!");
        }
    }

    /**
     * check_ENUM_TYPE_NAME methods.
     * probably a much easier and more modular way to do this, but we don't have that many achievements so who really cares :).
     * used to set their respective achievement to complete once their condition is met.
     */

    public void check_POSITIVE_EVENTS(float count, float total) { // achievements[0]
        if (count >= total) { achievements.get(0).setComplete(true); }
    }

    public void check_NEGATIVE_EVENTS(float count, float total) { // achievements[1]
        if (count >= total) { achievements.get(1).setComplete(true); }
    }

    public void check_HIDDEN_EVENTS(float count, float total) { // achievements[2]
        if (count >= total) { achievements.get(2).setComplete(true); }
    }

    public void check_ALL_EVENTS(float count, float total) { // achievements[3]
        if (count >= total) { achievements.get(3).setComplete(true); }
    }

    public void check_ZERO_TIMER() { // achievements[4]
        achievements.get(4).setComplete(true);
    }

    public void check_PASS() { // achievements[5]
        achievements.get(5).setComplete(true);
    }

    public void check_FAIL() { // achievements[6]
        achievements.get(6).setComplete(true);
    }

    public void check_TWENTY_FIVE_SECONDS() { // achievements[7]
        achievements.get(7).setComplete(true);
    }

    // getters and setters
    public ArrayList<Achievement> getAchievements() { return achievements; }
}

package io.github.team10.escapefromuni;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Achievement class.
 * Testing formatting, enum fetching, initialisation etc.
 */
public class AchievementTest extends HeadlessTestRunner {

    private Achievement achievement;

    @Before
    public void setUp() {
        // Incomplete achievement to test on.
        achievement = new Achievement(AchievementType.POSITIVE_EVENTS, false);
    }

    @Test
    public void achievementInit() {
        // Attributes set up correctly + achievement incomplete.
        assertNotNull("Achievement should be initialised", achievement);
        assertEquals("Achievement type should be POSITIVE_EVENTS", AchievementType.POSITIVE_EVENTS, achievement.getType());
        assertFalse("Achievement should not be complete initially", achievement.isComplete());
    }

    @Test
    public void achievementInitComplete() {
        // Achievement done.
        Achievement completeAchievement = new Achievement(AchievementType.PASS, true);
        assertTrue("Achievement should be complete", completeAchievement.isComplete());
        assertEquals("Achievement type should be PASS", AchievementType.PASS, completeAchievement.getType());
    }

    @Test
    public void getDetails() {
        // gets the achievement type, name and description
        assertEquals("getType should return POSITIVE_EVENTS", AchievementType.POSITIVE_EVENTS, achievement.getType());
        assertEquals("getName should return the type's name", AchievementType.POSITIVE_EVENTS.getName(), achievement.getName());
        assertEquals("getDescription should return the type's description", AchievementType.POSITIVE_EVENTS.getDescription(), achievement.getDescription());

    }

    @Test
    public void setCompleteToTrue() {
        // Achievement attained
        achievement.setComplete(true);
        assertTrue("isComplete should return true after setComplete(true)", achievement.isComplete());
    }

    @Test
    public void toStringCheck() {
        // checks toString for incomplete achievement
        // checks toString for complete achievement
        // checks toString is formatted correctly
        String result = achievement.toString();
        assertEquals("toString should format correctly for incomplete achievement", "POSITIVE_EVENTS|false", result);

        achievement.setComplete(true);
        result = achievement.toString();
        assertEquals("toString should format correctly for complete achievement", "POSITIVE_EVENTS|true", result);

        Achievement testAchievement = new Achievement(AchievementType.ZERO_TIMER, false);
        result = testAchievement.toString();
        assertTrue("toString should contain pipe separator", result.contains("|"));
        assertTrue("toString should start with type name", result.startsWith("ZERO_TIMER"));
        assertTrue("toString should end with completion status", result.endsWith("false"));
    }

    @Test
    public void allAchievementTypes() {
        // Check enum setup
        AchievementType[] types = AchievementType.values();
        for (AchievementType type : types) {
            Achievement a = new Achievement(type, false);
            assertEquals("Type should match", type, a.getType());
            assertNotNull("Name should exist", a.getName());
            assertNotNull("Description should exist", a.getDescription());
        }
    }

    @Test
    public void toggleCompletion() {
        // Multiple state changes
        assertFalse("Should start incomplete", achievement.isComplete());

        achievement.setComplete(true);
        assertTrue("Should be complete", achievement.isComplete());

        achievement.setComplete(false);
        assertFalse("Should be incomplete again", achievement.isComplete());

        achievement.setComplete(true);
        assertTrue("Should be complete again", achievement.isComplete());
    }
}

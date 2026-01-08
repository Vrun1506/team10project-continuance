package io.github.team10.escapefromuni;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the AchievementType enum.
 * This set of tests checks that all achievement types and enums are correctly set up.

 */
public class AchievementTypeTest extends HeadlessTestRunner {

    @Test
    public void EnumValues() {
        // Checks 8 achievement types.
        AchievementType[] types = AchievementType.values();
        assertEquals("AchievementType should have 8 values", 8, types.length);
    }

    @Test
    public void allEventsExist() {
        // checks that all events exist
        AchievementType type = AchievementType.POSITIVE_EVENTS;
        assertNotNull("POSITIVE_EVENTS should exist", type);
        assertEquals("POSITIVE_EVENTS should equal itself", AchievementType.POSITIVE_EVENTS, type);

        type = AchievementType.NEGATIVE_EVENTS;
        assertNotNull("NEGATIVE_EVENTS should exist", type);
        assertEquals("NEGATIVE_EVENTS should equal itself", AchievementType.NEGATIVE_EVENTS, type);

        type = AchievementType.HIDDEN_EVENTS;
        assertNotNull("HIDDEN_EVENTS should exist", type);
        assertEquals("HIDDEN_EVENTS should equal itself", AchievementType.HIDDEN_EVENTS, type);

        type = AchievementType.ALL_EVENTS;
        assertNotNull("ALL_EVENTS should exist", type);
        assertEquals("ALL_EVENTS should equal itself", AchievementType.ALL_EVENTS, type);

        type = AchievementType.ZERO_TIMER;
        assertNotNull("ZERO_TIMER should exist", type);
        assertEquals("ZERO_TIMER should equal itself", AchievementType.ZERO_TIMER, type);

        type = AchievementType.PASS;
        assertNotNull("PASS should exist", type);
        assertEquals("PASS should equal itself", AchievementType.PASS, type);

        type = AchievementType.FAIL;
        assertNotNull("FAIL should exist", type);
        assertEquals("FAIL should equal itself", AchievementType.FAIL, type);

        type = AchievementType.TWENTY_FIVE_SECONDS;
        assertNotNull("TWENTY_FIVE_SECONDS should exist", type);
        assertEquals("TWENTY_FIVE_SECONDS should equal itself", AchievementType.TWENTY_FIVE_SECONDS, type);
    }

    @Test
    public void enumVals() {
        // checks all enum value converts work
        assertEquals(AchievementType.POSITIVE_EVENTS, AchievementType.valueOf("POSITIVE_EVENTS"));
        assertEquals(AchievementType.NEGATIVE_EVENTS, AchievementType.valueOf("NEGATIVE_EVENTS"));
        assertEquals(AchievementType.HIDDEN_EVENTS, AchievementType.valueOf("HIDDEN_EVENTS"));
        assertEquals(AchievementType.ALL_EVENTS, AchievementType.valueOf("ALL_EVENTS"));
        assertEquals(AchievementType.ZERO_TIMER, AchievementType.valueOf("ZERO_TIMER"));
        assertEquals(AchievementType.PASS, AchievementType.valueOf("PASS"));
        assertEquals(AchievementType.FAIL, AchievementType.valueOf("FAIL"));
        assertEquals(AchievementType.TWENTY_FIVE_SECONDS, AchievementType.valueOf("TWENTY_FIVE_SECONDS"));
    }

    @Test
    public void getTypeNames() {
        // checks all type names are correct
        assertEquals("Living the life", AchievementType.POSITIVE_EVENTS.getName());
        assertEquals("Poor you...", AchievementType.NEGATIVE_EVENTS.getName());
        assertEquals("Eagle eyes", AchievementType.HIDDEN_EVENTS.getName());
        assertEquals("I'm flattered...", AchievementType.ALL_EVENTS.getName());
        assertEquals("Are you still alive?", AchievementType.ZERO_TIMER.getName());
        assertEquals("Library camper", AchievementType.PASS.getName());
        assertEquals("Who's surprised?", AchievementType.FAIL.getName());
        assertEquals("Please, touch grass", AchievementType.TWENTY_FIVE_SECONDS.getName());
    }

    @Test
    public void getTypeDescriptions() {
        assertEquals("Interact with all positive events in one game.", AchievementType.POSITIVE_EVENTS.getDescription());
        assertEquals("Interact with all negative events in one game.", AchievementType.NEGATIVE_EVENTS.getDescription());
        assertEquals("Interact with all hidden events in one game.", AchievementType.HIDDEN_EVENTS.getDescription());
        assertEquals("Interact with all events in one game.", AchievementType.ALL_EVENTS.getDescription());
        assertEquals("Let the timer reach 0.", AchievementType.ZERO_TIMER.getDescription());
        assertEquals("Pass the THE3 exam.", AchievementType.PASS.getDescription());
        assertEquals("Fail the THE3 exam.", AchievementType.FAIL.getDescription());
        assertEquals("Find the exit in under 25 seconds.", AchievementType.TWENTY_FIVE_SECONDS.getDescription());
    }
}

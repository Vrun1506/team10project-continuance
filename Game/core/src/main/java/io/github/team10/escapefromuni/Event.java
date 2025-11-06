package io.github.team10.escapefromuni;

/**
 * Abstract base event class.
 * Events are contained within a {@link Room}, where there can be up to 1 event per room.
 */
public abstract class Event {
    public final EventType type;
    protected boolean eventFinished;
    protected Player player;
    protected EscapeGame game;

    /**
     * Create a new event, of a given type.
     */
    public Event(EventType type, Player player, EscapeGame game)
    {
        this.type = type;
        this.player = player;
        this.game = game;
    }

    /**
     * Called when the event starts (e.g. the player enters the room).
     */
    public abstract void startEvent();

    /**
     * Called when the event should end. 
     * 
     * Disposes of resources and restores normal gameplay.
     */
    public abstract void endEvent();

    /**
     * Called every frame to update the event's logic.
     * @param delta The time elapsed since the last frame in seconds.
     */
    public abstract void update(float delta);

    /**
     * Draws world-space graphics related to the event.
     */
    public abstract void draw();

    /**
     * Draws UI-space graphics for the event.
     */
    public abstract void drawUI();
}

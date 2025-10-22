package io.github.team10.escapefromuni;

public class RoomManager {
    private Room startingRoom;
    private Room currentRoom;
    private Door[] doors = new Door[4];

    public void initialiseMap() {
        // Iniitalise all the rooms (Create room objects)
    }

    public void changeRoom(DoorDirection direction) {
        // Will unload current room and load next room
        // Will update which doors are visible. Note that only 4 door objects are used -
        // they can be made visible or invisible.
    }

    private void unloadCurrentRoom() {
        // Called from changeRoom()
    }

    private void initialiseDoors() {
        // Will create the four doors at the start of the game
    }

    private void updateDoors() {
        // Called from changeRoom()
        // Will update which doors are visible based on the new room
    }

    private void updateEventIndicators() {
        // Called from changeRoom()
        // Will update the event type indicators by the doors
    }

}

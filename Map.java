package org.uob.a2.gameobjects;

//PASSED*

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

import org.uob.a2.utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {

    private ArrayList<Room> rooms;
    private Room currentRoom;

    public Map()
    {
        rooms = new ArrayList<Room>();
        //this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void addRoom(Room room)
    {
        rooms.add(room);
    }

    public void setCurrentRoom(String roomId)
    {
        for (Room room : rooms)
        {
            if (room.getId().equals(roomId))
            {
                currentRoom = room;
            }
        }
    }

    public Room getRoom(String roomId)
    {
        for (Room room : rooms)
        {
            if (room.getId().equals(roomId))
            {
                return room;
            }
        }
        return null;
    }

    public ArrayList<Room> getRooms()
    {
        return rooms;
    }
    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}


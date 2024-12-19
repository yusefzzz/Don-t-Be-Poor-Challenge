package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.uob.a2.gameobjects.*;

/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileParser {

    public GameStateFileParser(){}

    public static GameState parse(String filename)
    {
        Player player = new Player();
        Map map = new Map();
        GameState gameState = new GameState(map, player);
        String currentRoomID = ""; 
        String roomToAddID = "";
        ArrayList<GameObject> allUsables = new ArrayList<GameObject>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();
            String[] subparts;
            while (line != null)
            {
                line = line.trim();
                String[] parts = line.split(":");
                for (String part: parts)
                {
                    part = part.trim();
                }
                switch (parts[0])
                {
                    case "player": player = new Player(parts[1]);
                        gameState.setPlayer(player); break;
                    case "map": map = new Map(); 
                        currentRoomID = parts[1];break;
                    case "room": subparts = parts[1].split(","); 
                        Room room = new Room(subparts[0], subparts[1], subparts[2], Boolean.valueOf(subparts[3].trim()));
                        roomToAddID = room.getId();
                        map.addRoom(room);
                        if (currentRoomID != "" && room.getId().equals(currentRoomID))
                        {
                            map.setCurrentRoom(room.getId());
                        }
                        subparts = null; break;
                    case "item": subparts = parts[1].split(","); 
                        Item item = new Item(subparts[0], subparts[1], subparts[2], Boolean.valueOf(subparts[3].trim()));
                        map.getRoom(roomToAddID).addItem(item);
                        allUsables.add(item);
                        subparts = null; break;
                    case "equipment": subparts = parts[1].split(","); 
                        Equipment equipment = new Equipment(subparts[0], subparts[1], subparts[2], Boolean.valueOf(subparts[3]), 
                        new UseInformation(false, subparts[4], subparts[5], subparts[6], subparts[7].trim()));
                        map.getRoom(roomToAddID).addEquipment(equipment); 
                        allUsables.add(equipment);
                        subparts = null; break;
                    case "container": subparts = parts[1].split(","); 
                        Container container = new Container(subparts[0], subparts[1], subparts[2], Boolean.valueOf(subparts[3].trim()));
                        map.getRoom(roomToAddID).addFeature(container);
                        subparts = null; break;
                    case "feature": subparts = parts[1].split(","); 
                        Feature feature = new Feature(subparts[0], subparts[1], subparts[2], Boolean.valueOf(subparts[3].trim()));
                        map.getRoom(roomToAddID).addFeature(feature);
                        subparts = null; break;
                    case "exit": subparts = parts[1].split(","); 
                        Exit exit = new Exit(subparts[0], subparts[1], subparts[2], subparts[3], Boolean.valueOf(subparts[4].trim()));
                        map.getRoom(roomToAddID).addExit(exit);
                        subparts = null; break;
                }
                line = reader.readLine();
            }
            gameState.setMap(map);
            //gameState = new GameState(map, player);
        } catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        //GameState gameState = new GameState();
        return gameState;
    }

   
}

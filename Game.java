package org.uob.a2;

import java.util.Scanner;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

import java.util.Random;
import java.util.ArrayList;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {

    public static GameState parseGameState()
    {
        GameState g = GameStateFileParser.parse("data/game.txt");
        return g;
    }

    public static boolean parseCommand(String s, GameState g)
    {
        Command command;
        Tokeniser t = new Tokeniser();
        Parser p = new Parser();
        t.tokenise(t.sanitise(s));
        try
        {
            command = p.parse(t.getTokens());
            System.out.println(command.execute(g));
            if (command.commandType == CommandType.QUIT)
            {
                System.out.println("Final balance: " + g.getBalance());
                return true;
            }
        } catch (CommandErrorException e)
        {
            System.out.println("Invalid command");
        }
        return false;
    }
    public static void main(String[] args)
    {
        Scanner inputDevice = new Scanner(System.in);
        System.out.println("Welcome to Don't Be Poor Challenge");
        System.out.println("Each chapter has a puzzle that you can solve to ");
        System.out.println("Press ENTER to start playing");
        String choice = inputDevice.nextLine();
        if (choice.contains(""))
        {
            GameState gameState = parseGameState();
            playGame(gameState, inputDevice);
        }
    }

    public static void blackMarket(GameState g)
    {
        System.out.println("You are in the black market");
        System.out.println("Press ENTER to sell");
        Scanner inputDevice = new Scanner(System.in);
        String choice = inputDevice.nextLine();
        if (choice.contains(""))
        {
            Status c = new Status("inventory");
            System.out.println(c.execute(g));
            System.out.println("Choose an item to sell (used equipment = $50, items = $75, unused equipment = $100)");
            choice = inputDevice.nextLine().trim();
            for (Item item: g.getPlayer().getInventory())
            {
                if (item.getName().equals(choice))
                {
                    choice = item.getName();
                    g.updateBalance(75);
                }
            }
            for (Equipment equip: g.getPlayer().getEquipment())
            {
                if (equip.getName().equals(choice))
                {
                    choice = equip.getName();
                    if (equip.getUseInformation().isUsed())
                    {
                        g.updateBalance(50);
                    }
                    else
                    {
                        g.updateBalance(100);
                    }
                }
            }
            g.getPlayer().removeItem(choice);
            System.out.println("You sold " + choice);
            System.out.println("Your new balance is: " + g.getBalance());
        }
    }

        
    

    public static void displayMap(GameState g)
    {
        String display = "\n[   ][   ][r4 ][   ]";
        display +=       "\n[r6 ][r8 ][r3 ][r5 ]";
        display +=       "\n[r10][r1 ][r2 ][r7 ]";
        display +=       "\n[   ][   ][r9 ][   ]";
        display +=       "\n";

        System.out.println(display);
        
        //String[][] mapArray = new String[][];
        //ArrayList<ArrayList<String>> mapArray = new ArrayList<ArrayList<String>>();
        //String mapString = "";
        //ArrayList<String> roomsToAdd = new ArrayList<String>();
        //for (Room room: g.getMap().getRooms())
        //{
        //    roomsToAdd.add(room.getId());
        //}
        
        //for (Room room : g.getMap().getRooms())
        //{
        //    for (Exit exit: room.getExits())
        //    {
        //        switch (exit.getDirection())
        //        {
        //            case "north": 
        //                mapArray.add
        //            case "south":
        //            case "east":
        //            case "west":
        //        }
        //    }
        //}

    }

    public static void playGame(GameState g, Scanner i)
    {   
        Player player = g.getPlayer();
        Map map = g.getMap();
        boolean finished = false;
        System.out.println(player.toString());
        while (!finished)
        {
            // Update game state by also updating player and map
            player = g.getPlayer();
            map = g.getMap();
            if (player.hasItem("network-signal")) // Paying for sim card frequently
            {
                g.updateBalance(-2);
            }
            for (Item item: player.getInventory())
            {
                if (item.getId().equals("#cash"))
                {
                    String cashString = item.getName().substring(1);
                    int cash = Integer.parseInt(cashString);
                    g.updateBalance(cash);
                    player.getInventory().remove(item);
                    System.out.println("New balance: $" + g.getBalance());
                    break;
                }
            }
            System.out.println("You are currently in: " + map.getCurrentRoom().getId() + ": " + map.getCurrentRoom().getName());
            System.out.println("Your current balance: " + g.getBalance());
            System.out.println();
            String choice = "";
            while (choice.isEmpty())
            {
                g.setMap(map);
                g.setPlayer(player);
                System.out.println("Enter command...\n");
                choice = i.nextLine().trim();
            }
            if (choice.contains("access blackmarket"))

                if (player.hasItem("network-signal"))
                {
                    blackMarket(g);
                }
                else
                {
                    System.out.println("You need to be connected to the internet to access the black market.");
                }
            else if (choice.contains("display map"))
            {
                displayMap(g);
            }
            else
            {
                System.out.println();
                finished = parseCommand(choice.trim(), g);
            }
        }
    }
}

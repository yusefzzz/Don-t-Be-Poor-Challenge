package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

//PASSED

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {


    public Help(String topic)
    {
        this.value = topic;
    }

    public String execute(GameState gameState)
    {
        String returnedString = "";
        if (value == null)
        {
            returnedString += "Welcome to the game!\n";
            returnedString += "Available commands:\n";
            returnedString += "- HELP <topic> - Displays information about a specific topic (IMPORTANT - WRITE TOPIC BETWEEN <>)\n";
            returnedString += "- MOVE <direction> - Moves the player in the specified direction\n";
            returnedString += "- USE <item> - Uses the specificed piece of equipment on a target\n";
            returnedString += "- GET <item> - Picks up the specified item\n";
            returnedString += "- LOOK <item> - Displays more information about a specific feature/item\n";
            returnedString += "- DROP <item> - Drops the specified item\n";
            returnedString += "- STATUS <topic> - Retrieves information about the player's inventory, specific items, or their overall status\n";
            returnedString += "- QUIT - Exits the game\n";

        }
        else
        {
            switch(value)
            {
                case "<help>": returnedString += "HELP command - Use the 'help' command + <topic> to display information about a specific topic";break;
                case "<move>": returnedString += "MOVE Command: Use the 'move' command + <direction> to move towards a specific direction";break;
                case "<use>": returnedString += "USE command - Use the 'use' command + <item> to use a specific piece of equipment on a target";break;
                case "<get>": returnedString += "GET command - Use the 'get'command + <item> to pick up a specific item";break;
                case "<look>": returnedString += "LOOK command - Use the 'look' command + <item> to display more info about a specific item/feature";break;
                case "<drop>": returnedString += "DROP command - Use the 'drop' command + <item> to drop a specific item";break;
                case "<status>": returnedString += "STATUS command - Use the 'status' command + <topic> to retrieve the status of the player, inventory, or a specific item";break;
                case "<quit>": returnedString += "QUIT command - Use the 'quit' command to exit";break;
                default: returnedString += "No help available for the topic: " + value ;break;
            }
        }
        return returnedString;
    }

    public String toString()
    {
        return "Topic is " + value + ", HELP: " + execute(null);
    }

  
}

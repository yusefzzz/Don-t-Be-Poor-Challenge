package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

//PASSED

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {

    public Status(String topic)
    {
        this.value = topic;
    }

    public String execute(GameState gameState)
    {
        String returnedString = "";
        if (value.equals("inventory"))
        {
            returnedString += "Your inventory contains: \n";
            for (Item item: gameState.getPlayer().getInventory())
            {
                returnedString += "- " + item.getName() + ": " + item.getDescription() + "\n";
            }
            returnedString += "Equipment: \n";
            for (Equipment equip: gameState.getPlayer().getEquipment())
            {
                returnedString += "- " + equip.getName() + ": " + equip.getDescription() + "\n";
            }
        }
        else if (value.equals("player"))
        {
            returnedString += gameState.getPlayer().toString();
        }
        else
        {
            GameObject gameObject = gameState.getPlayer().findGameObject(value);
            if (gameObject != null)
            {
                returnedString = gameObject.getDescription();
            }
        }
        return returnedString;
    }

    @Override
    public String toString()
    {
        return "This command retrieves info about the player's inventory, a specific item or their overall status";
    }
}

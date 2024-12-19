package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

//PASSED

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {


    public Get(String item)
    {
        this.value = item;
    }

    public String execute(GameState gameState)
    {
        // If the item is in neither the player's equipment nor their inventory
        if (!gameState.getPlayer().hasEquipment(value) && !gameState.getPlayer().hasItem(value)) 
        {
            if (gameState.getMap().getCurrentRoom().hasItem(value))
            {
                // Remove item from room & add to inventory
                Item itemGot = gameState.getMap().getCurrentRoom().getItemByName(value);
                gameState.getMap().getCurrentRoom().getItems().remove(itemGot);
                itemGot.setHidden(false);
                gameState.getPlayer().addItem(itemGot);
            }
            else if (gameState.getMap().getCurrentRoom().hasEquipment(value))
            {
                // Remove item from room & add to equipment
                Equipment equipGot = gameState.getMap().getCurrentRoom().getEquipmentByName(value);
                gameState.getMap().getCurrentRoom().getEquipments().remove(equipGot);
                equipGot.setHidden(false);
                gameState.getPlayer().addEquipment(equipGot);
            }
            // If the item is not in the room, not in the player's inventory/equipment
            else
            {
                return "No " + value + " to get.";
            }
        }
        // If the item is either in the player's equipment or their inventory
        else
        {
            return "You already have " + value;
        }
        return "You pick up: " + value;
    }

    @Override
    public String toString()
    {
        return "Get " + value;
    }
}

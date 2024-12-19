package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

//PASSED

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {


    public Drop(String item)
    {
        this.value = item;
    }

    public String execute(GameState gameState)
    {
        if (gameState.getPlayer().hasItem(value)) // If gameObject item is to be dropped
        {
            
            for (Item inventItem: gameState.getPlayer().getInventory()) // For every item in the player's inventory
            {
                if (inventItem.getName().equals(value)) 
                {
                    // Item removed from inventory & added in room
                    gameState.getPlayer().getInventory().remove(inventItem); 
                    gameState.getMap().getCurrentRoom().addItem(inventItem);
                    return "You drop: " + value;
                }
            }
        }
        else if (gameState.getPlayer().hasEquipment(value)) //If gameObject equipment is to be dropped
        {
            
            for (Equipment equipItem: gameState.getPlayer().getEquipment()) // For every piece of equipment in the player's equipment
            {
                if (equipItem.getName().equals(value))
                {
                    // Piece of equipment removed from equipment & added in room
                    gameState.getPlayer().getEquipment().remove(equipItem);
                    gameState.getMap().getCurrentRoom().addEquipment(equipItem);
                    return "You drop: " + value;
                }
            }
        }
        return "You cannot drop " + value;
    }

    @Override
    public String toString()
    {
        return "This command drops an item/piece of equipment, causing it to be removed from the player's inventory/equipment and added to the room";
    }
   
}

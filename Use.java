package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

////PASSED

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command {

    private String equipmentName;
    private String target;

    public Use(String equipmentName, String target)
    {
        this.equipmentName = equipmentName;
        this.target = target;
    }

    /** 
    public String execute(GameState gameState)
    {
        // Check if the player has the specified equipment
        Equipment equipUsed = gameState.getPlayer().getEquipment(equipmentName);
        if (equipUsed != null)
        {
            // If equipment hasn't been used
            if (!equipUsed.getUseInformation().isUsed())
            {
                // Look through every item within the room to find a target
                for (GameObject objectTarget: gameState.getMap().getCurrentRoom().getAll())
                {
                    if (target.equals(objectTarget.getName()) && !objectTarget.getHidden())
                    {
                        return equipUsed.use(objectTarget, gameState);
                    }
                }
                return "Invalid use target";
            }
            else
            {
                return "You have already used " + equipmentName;
            }
        }
        // If equipment not found within player
        return "You do not have " + equipmentName;
    }
    */
    /* */
    public String execute(GameState gameState)
    {
        Equipment equipToUse = gameState.getPlayer().getEquipment(equipmentName);
        if (!gameState.getPlayer().hasEquipment(equipmentName))
        {
            return "You do not have " + equipmentName + " or you are using an item instead of a piece of equipment";
        }
        else if (equipToUse.getUseInformation().isUsed())
        {
            return "You have already used " + equipmentName;
        }
        else
        {
            for (GameObject item: gameState.getMap().getCurrentRoom().getAll())
            {
                if (item.getName().equals(target) && !item.getHidden())
                {   
                    
                    return equipToUse.use(item, gameState);
                }
            }
            for (Item item: gameState.getPlayer().getInventory())
            {
                if (item.getName().equals(target) && !item.getHidden())
                {
                    return equipToUse.use(item, gameState);
                }
            }
        }
        return "Invalid use target";
    }

    public void setEquipment(String equipmentName)
    {
        this.equipmentName = equipmentName;
    }

    public void setTarget(String targetName)
    {
        this.target = targetName;
    }

    public String toString()
    {
        return "Use " + equipmentName + " on " + target;
    }
}

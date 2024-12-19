package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

//PASSED 

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {

    public Look(String target)
    {
        this.value = target;
    }
    
    public String execute(GameState gameState)
    {
        String returnedString = "";
        if (value.equals("room"))
        {
            returnedString = gameState.getMap().getCurrentRoom().getName() + ": " + gameState.getMap().getCurrentRoom().getDescription();
            returnedString += "\nIn this room you can see:";
            for (Item item: gameState.getMap().getCurrentRoom().getItems())
            {
                if (!item.getHidden())
                {
                    returnedString += "\n" + item.getName() + ": " + item.getDescription();
                }
            }
            for (Equipment equip: gameState.getMap().getCurrentRoom().getEquipments())
            {
                if (!equip.getHidden())
                {
                    returnedString += "\n" + equip.getName() + ": " + equip.getDescription();
                }
            }
            returnedString += "\n";
            returnedString += "\nTo display containers/chests type look features";
        }
        else if (value.equals("exits"))
        {
            returnedString = "The available exits are: ";
            for (Exit exit: gameState.getMap().getCurrentRoom().getExits())
            {
                returnedString += "\n" + exit.getDescription() + ", direction " + exit.getName();
            }
            returnedString += "\n";
        }
        else if (value.equals("features"))
        {
            returnedString = "You also see: ";
            for (Feature feature: gameState.getMap().getCurrentRoom().getFeatures())
            {
                if (!feature.getHidden())
                {
                    returnedString += "\n" + feature.getName() + ": " + feature.getDescription();
                }
            }
            returnedString += "\n";
        }
        else 
        {
            GameObject gameObject = gameState.getMap().getCurrentRoom().findGameObject(value);
            if (gameObject != null && !gameObject.getHidden())
            {
                returnedString = gameObject.getDescription();
            }
            else
            {
                gameObject = gameState.getPlayer().findGameObject(value);
                if (gameObject != null && !gameObject.getHidden())
                {
                    returnedString = gameObject.getDescription();
                }
            }
        }
        return returnedString;
    }

    public String toString()
    {
        return "This command allows the player to examine the current room/visible exits/visible features/visible items/visible equipment";
    }
}

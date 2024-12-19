package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

//PASSED

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {

    public Move(String direction)
    {
        this.value = direction;
    }

    public String execute(GameState gameState)
    {
        for (Exit exit: gameState.getMap().getCurrentRoom().getExits())
        {
            if (value.equals(exit.getName()))
            {
                gameState.getMap().setCurrentRoom(exit.getNextRoom());
                return "Moving towards " + value + "\n";
            }
        }
        return "No exit found in that direction.";
    }

    @Override
    public String toString()
    {
        return "Move " + value;
    }
}

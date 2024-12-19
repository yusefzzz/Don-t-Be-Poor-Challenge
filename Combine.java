package org.uob.a2.commands;

import org.uob.a2.gameobjects.Equipment;
import org.uob.a2.gameobjects.GameState;
import org.uob.a2.gameobjects.UseInformation;

public class Combine extends Command{

    private String item1;
    private String item2;

    public Combine(String item1, String item2) 
    {
        this.item1 = item1;
        this.item2 = item2;
    }

    public String execute(GameState gameState)
    {
        String returnedString = "";
        String combinedString = item1 + "," + item2;
        String newItemName = "";
        Equipment newItem = null;
        if (!gameState.getPlayer().hasPiece(item1) && !gameState.getPlayer().hasPiece(item2))
        {
            return "One or more items missing - cannot combine";
        }
        if (combinedString.contains("wood") && combinedString.contains("blade"))
        {
            newItemName = "knife";
            newItem = new Equipment("knife1",
                                                newItemName, 
                                                "A blunt knife", 
                                                false, new UseInformation(false, "kill", "cow1", "beef1", "You kill the cow and obtain some beef"));
        }
        else if (combinedString.contains("match") && combinedString.contains("wood"))
        {
            newItemName = "campfire";
            newItem = new Equipment("campfire1",
                                                 newItemName, 
                                                "A campfire to keep warm or cook some food (one of these is a lie)", 
                                                false, new UseInformation(false, "cook", "beef1", "cookedbeef1", "You cook some beef"));
        }
        else if (combinedString.contains("plank") && combinedString.contains("wood"))
        {
            newItemName = "shield";
            newItem = new Equipment("log1",
                                                newItemName, 
                                                "A one-time use shield", 
                                                false, new UseInformation(false, "use", "bullet", "iqpoints", "You protect youself by using the shielf"));
        }
        if (newItem != null)
        {
            gameState.getPlayer().removeItem(item1);
            gameState.getPlayer().removeItem(item2);
            gameState.getPlayer().addEquipment(newItem);
            returnedString = "You combine the " + item1 + " and " + item2 +" to create a " + newItemName;
        }
        //returnedString = "You combine the " + item1 + " and " + item2 +" to create a " + newItemName;
        else
        {
            return returnedString;
        }
        return "";
    }

    @Override
    public String toString()
    {
        return "This command combines 2 items from the player's equipment/inventory if the combination is possible";
    }

}

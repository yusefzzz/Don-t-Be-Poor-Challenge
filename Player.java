package org.uob.a2.gameobjects;

import java.util.ArrayList;

//PASSED*

/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry items and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {

    private String name;
    private ArrayList<Item> inventory;
    private ArrayList<Equipment> equipment;
    
    public Player(String name)
    {
        this.name = name;
        inventory = new ArrayList<Item>();
        equipment = new ArrayList<Equipment>();
    }

    public Player()
    {

    }

    public String getName() 
    {
        return name;
    }

    public ArrayList<Item> getInventory()
    {
        return inventory;
    }

    public boolean hasItem(String itemName)
    {
        for (Item item: inventory)
        {
            if (itemName.equals(item.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public Item getItem(String itemName)
    {
        for (Item item: inventory)
        {
            if (itemName == item.getName())
            {
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public ArrayList<Equipment> getEquipment()
    {
        return equipment;
    }

    public boolean hasEquipment(String equipmentName)
    {
        for (Equipment equip: equipment)
        {
            if (equipmentName.equals(equip.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public Equipment getEquipment(String equipmentName)
    {
        for (Equipment equip: equipment)
        {
            if (equipmentName.equals(equip.getName()))
            {
                return equip;
            }
        }
        return null;
    }

    public void addEquipment(Equipment equip)
    {
        equipment.add(equip);
    }

    public GameObject findGameObject(String name)
    {
        // Look through all features, items, equipment
        //for (Feature feature: features)
        GameObject gameObject = getEquipment(name);
        if (gameObject == null)
        {
            gameObject = getItem(name);
        }
        return gameObject;
    }

    public void removeItem(String itemName)
    {
        if (hasItem(itemName))
        {
            inventory.remove(getItem(itemName));
        }
        else if (hasEquipment(itemName))
        {
            equipment.remove(getEquipment(itemName));
        }
    }

    public boolean hasPiece(String piece)
    {
        if (hasItem(piece) || hasEquipment(piece))
        {
            return true;
        }
        return false;
    }
    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}

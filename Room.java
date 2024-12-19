package org.uob.a2.gameobjects;

//PASSED*

import java.util.ArrayList;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {

    private ArrayList<Exit> exits;
    private ArrayList<Item> items;
    private ArrayList<Feature> features;
    private ArrayList<Equipment> equipment;

    public Room(String id, String name, String description, boolean hidden)
    {
        super(id, name, description, hidden);
        exits = new ArrayList<Exit>();
        items = new ArrayList<Item>();
        features = new ArrayList<Feature>();
        equipment = new ArrayList<Equipment>();

    }

    //public Room()
    //{
    //    super();
    //}

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public ArrayList<Exit> getExits()
    {
        return exits;
    }

    public void addExit(Exit exit)
    {
        exits.add(exit);
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public Item getItem(String id)
    {
        for (int i = 0; i < items.size(); i++) 
        {
            if (items.get(i).getId().equals(id)) {
                return items.get(i);
            }
        }
        return null;
    }

    public Item getItemByName(String name)
    {
        for (int i = 0; i < items.size(); i++) 
        {
            if (items.get(i).getName().equals(name)) {
                return items.get(i);
            }
        }
        return null;
    }

    public Feature getFeatureByName(String name)
    {
        for (int i = 0; i < features.size(); i++) 
        {
            if (features.get(i).getName().equals(name)) {
                return features.get(i);
            }
        }
        return null;
    }

    public ArrayList<Equipment> getEquipments()
    {
        return equipment;
    }

    public Equipment getEquipmentByName(String name)
    {
        for (int i = 0; i < equipment.size(); i++) 
        {
            if (equipment.get(i).getName().equals(name)) {
                return equipment.get(i);
            }
        }
        return null;
    }

    public Equipment getEquipment(String id)
    {
        for (int i = 0; i < equipment.size(); i++) 
        {
            if (equipment.get(i).getId().equals(id)) {
                return equipment.get(i);
            }
        }
        return null;
    }

    public Exit getExit(String id)
    {
        for (int i = 0; i < exits.size(); i++) 
        {
            if (exits.get(i).getId().equals(id)) {
                return exits.get(i);
            }
        }
        return null;
    }

    public void addEquipment(Equipment equipmentItem) // equipmentItem WAS equipment (NOT TEMPLATE CODE BUT WAS IN THE WEBSITE)
    {
        equipment.add(equipmentItem);
    }

    public Feature getFeature(String id)
    {
        for (int i = 0; i < features.size(); i++) 
        {
            if (features.get(i).getId().equals(id)) {
                return features.get(i);
            }
        }
        return null;
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public ArrayList<Feature> getFeatures()
    {
        return features;
    }

    public ArrayList<GameObject> getAll()
    {
        ArrayList<GameObject> allGameObjects = new ArrayList<>();
        allGameObjects.addAll(items);
        allGameObjects.addAll(equipment);
        allGameObjects.addAll(features);
        allGameObjects.addAll(exits);
        return allGameObjects; 
    }

    public void addFeature(Feature feature)
    {
        features.add(feature);
    }

    public boolean hasItem(String itemName)
    {
        if (getItemByName(itemName) != null)
        {
            return true;
        }
        return false;

    }

    public boolean hasEquipment(String name)
    {
        if (getEquipmentByName(name) != null)
        {
            return true;
        }
        return false;
    }

    //public ArrayList<Feature> getFeaturesArray()
    //{
    //    return features;
    //}

    //public ArrayList<Item> getItemsArray()
    //{
    //    return items;
    //}

    //public ArrayList<Exit> getExitsArray()
    //{
    //    return exits;
    //}

    //public ArrayList<Equipment> getEquipmentArray()
    //{
    //    return equipment;
    //}

    public GameObject findGameObject(String name)
    {
        // Look through all features, items, equipment
        //for (Feature feature: features)
        //GameObject gameObject = getFeatureByName(name);
        //if (gameObject == null)
        //{
        //    gameObject = getItemByName(name);
        //    if (gameObject == null)
        //    {
        //        gameObject = getEquipmentByName(name);
        //    }

        //}

        for (GameObject gameObject: getAll())
        {
            if (gameObject.getName().equals(name))
            {
                return gameObject;
            }
        }
        return null;
    }


    /**
     * Returns a string representation of the room, including its contents and description.
     *
     * @return a string describing the room
     */
    @Override
    public String toString() {
        String out = "[" + id + "] Room: " + name + "\nDescription: " + description + "\nIn the room there is: ";
        for (Item i : this.items) {
            out += i + "\n";
        }
        for (Equipment e : this.equipment) {
            out += e + "\n";
        }
        for (Feature f : this.features) {
            out += f + "\n";
        }
        for (Exit e : this.exits) {
            out += e + "\n";
        }
        return out + "\n";
    }
}

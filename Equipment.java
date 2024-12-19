package org.uob.a2.gameobjects;

//PASSED

public class Equipment extends GameObject implements Usable {

    protected UseInformation useInformation;

    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation)
    {
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }

    public void setUseInformation(UseInformation useInformation)
    {
        this.useInformation = useInformation;
    }

    public UseInformation getUseInformation()
    {
        return useInformation;
    }

    public String use(GameObject target, GameState gameState)
    {
        if (target.getId().equals(useInformation.getTarget()))
        {
            Room currentRoom = gameState.getMap().getCurrentRoom();
            for (int i = 0; i < currentRoom.getAll().size(); i ++)    
            {
                GameObject gameObject = currentRoom.getAll().get(i);
                if (useInformation.getResult().equals(gameObject.getId()))
                {
                    gameObject.setHidden(false);
                }
            }        
            gameState.updateBalance(5); // Every time a piece of equippment is used add $5 as a reward
            useInformation.setUsed(true);
            return useInformation.getMessage();
        }
        return "You can't use that on " + target.getName() + ".";

    }


   
    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }
}

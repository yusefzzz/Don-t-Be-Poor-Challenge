package org.uob.a2.gameobjects;

//PASSED

/**
 * Represents the current state of the game, including the map and the player.
 * 
 * <p>
 * The game state contains all necessary information about the game's progress, such as
 * the player's status and the state of the game map.
 * </p>
 */
public class GameState {

    private Map map;
    private Player player;
    private double balance; // This acts as a score


    public GameState()
    {
        
    }
    
    public GameState(Map map, Player player)
    {
        this.map = map;
        this.player = player;
        this.balance = 10;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void updateBalance(int i)
    {
        this.balance += i;
    }

    public Map getMap()
    {
        return map;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void setMap(Map map)
    {
        this.map = map;
    }
   
    /**
     * Returns a string representation of the game state, including the map and player details.
     *
     * @return a string describing the game state
     */
    @Override
    public String toString() {
        return "GameState {" +
               "map=" + (map != null ? map.toString() : "null") + ", " +
               "player=" + (player != null ? player.toString() : "null") +
               '}';
    }
}

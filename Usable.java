package org.uob.a2.gameobjects;

//PASSED

/**
 * Represents an interface for objects that can be used within the game.
 * 
 * <p>
 * Objects implementing this interface must define methods to manage their use
 * information and provide their name.
 * </p>
 */
public interface Usable {
    
    void setUseInformation(UseInformation useInfo);

    UseInformation getUseInformation();

    String getName();
}

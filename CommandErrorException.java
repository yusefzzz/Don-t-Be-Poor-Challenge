package org.uob.a2.commands;

//PASSED*

/**
 * Represents an exception thrown when an unrecognized or invalid command is encountered.
 * 
 * <p>
 * This exception is used to indicate parsing or processing errors related to commands entered by the player.
 * </p>
 */
public class CommandErrorException extends Exception {

    private String error;

    public CommandErrorException(String error)
    {
        this.error = error;
    }

    @Override
    public String getMessage()
    {
        return error;
    }

    /**
     * Returns a string representation of the exception, including its message.
     *
     * @return a string describing the command error
     */
    @Override
    public String toString() {
        return "CommandError: " + getMessage();
    }
}

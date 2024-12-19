package org.uob.a2.parser;

import java.util.ArrayList;

import org.uob.a2.commands.*;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {

    public Command parse(ArrayList<Token> tokens) throws CommandErrorException
    {
        TokenType commandTokenType = null;
        Command command = null;
        // Check if the format of the command is correct
        for (TokenType type: TokenType.values())
        {
            if (type == tokens.get(0).getTokenType())
            {
                if (type != TokenType.PREPOSITION && type != TokenType.EOL && type != TokenType.VAR)
                {
                    commandTokenType = type;
                }
                else
                {
                    throw new CommandErrorException("Invalid start of command");
                }
            }
        }
        Token tokenToRemove = null;
        for (Token token: tokens)
        {
            if (token.getTokenType() == TokenType.EOL)
            {
                tokenToRemove = token;
            }
        }
        if (tokenToRemove != null)
        {
            tokens.remove(tokenToRemove);
        }
        if (tokens.size() == 0)
        {
            throw new CommandErrorException("Empty command");
        }
        else
        {
            if (tokens.size() == 1) //&& (tokenType == TokenType.HELP || tokenType == TokenType.QUIT || tokenType == TokenType.STATUS))
            {   
                switch (commandTokenType)
                {
                    case HELP: Help help = new Help(null); command = help; command.commandType = CommandType.HELP; break;
                    case QUIT: Quit quit = new Quit(); command = quit; command.commandType = CommandType.QUIT; break;
                    default: throw new CommandErrorException("Invalid command or command doesn't take 0 arguments");
                }
            }
            else
            {
                TokenType tokenType1 = tokens.get(1).getTokenType();
                String item1 = tokens.get(1).getValue();
                if (tokens.size() == 2 && tokenType1 == TokenType.VAR)
                {
                    switch (commandTokenType)
                    {
                        case GET: Get get = new Get(item1); command = get; command.commandType = CommandType.GET; break;
                        case DROP: Drop drop = new Drop(item1); command = drop; command.commandType = CommandType.DROP; break;
                        case LOOK: Look look = new Look(item1); command = look; command.commandType = CommandType.LOOK; break;
                        case STATUS: Status status = new Status(item1); command = status; command.commandType = CommandType.STATUS; break;
                        case HELP: Help help = new Help(item1); command = help; command.commandType = CommandType.HELP; break;
                        case MOVE: Move move = new Move(item1); command = move; command.commandType = CommandType.MOVE; break;
                        default: throw new CommandErrorException("Invalid command or command doesn't take 1 argument");
                    }
                }
                else if (tokens.size() == 4 && 
                    (commandTokenType == TokenType.USE || commandTokenType == TokenType.COMBINE))
                {
                    TokenType tokenType2 = tokens.get(2).getTokenType();
                    TokenType tokenType3 = tokens.get(3).getTokenType();
                    String item2 = tokens.get(3).getValue();
                    if (tokenType1 == TokenType.VAR && tokenType3 == TokenType.VAR && tokenType2 == TokenType.PREPOSITION)
                    {
                        if (tokens.get(2).getValue().equals("on") && commandTokenType == TokenType.USE)
                        {
                            Use use = new Use(item1, item2);
                            command = use;
                            command.commandType = CommandType.USE;
                        }
                        else if ((tokens.get(2).getValue().equals("and") || (tokens.get(2).getValue().equals("with"))) && commandTokenType == TokenType.COMBINE)
                        {
                            Combine combine = new Combine(item1, item2);
                            command = combine;
                            command.commandType = CommandType.COMBINE;
                        }
                        else
                        {
                            throw new CommandErrorException("Incompatible USE/COMBINE command with given preposition");
                        }
                    }
                    else 
                    {
                        throw new CommandErrorException("Invalid combination of variables + preposition");
                    }
                }
                
            }
        }
        return command;
    }

}

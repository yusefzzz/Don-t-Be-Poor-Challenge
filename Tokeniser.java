package org.uob.a2.parser;

import java.util.ArrayList;

//PASSED

/**
 * The {@code Tokeniser} class is responsible for converting a string input into a list of tokens
 * that can be parsed into commands by the game.
 * 
 * <p>
 * The tokeniser identifies keywords, variables, and special symbols, assigning each a {@code TokenType}.
 * </p>
 */
public class Tokeniser {

    private ArrayList<Token> tokens;

    public Tokeniser()
    {
        tokens = new ArrayList<Token>();
    }
    
    public ArrayList<Token> getTokens()
    {
        return tokens;
    }

    public String sanitise(String s)
    {
        String newString = "";
        boolean whitespace = true;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) != ' ')
            {
                newString += s.charAt(i);
                whitespace = false;
            }
            else
            {
                if (!whitespace && i<s.length() - 1 && s.charAt(i+1) != ' ')
                {
                        newString += s.charAt(i);
                        whitespace = true;
                }
            }
        }
        newString = newString.toLowerCase();
        return newString;

    }

    public void tokenise(String s)
    {
        String[] parts = s.trim().split(" ");
        ArrayList<String> partsList = new ArrayList<String>();
        for (String part : parts)
        {
            partsList.add(part.toUpperCase());
        }
        partsList.add("EOL");
        for (String part: partsList)
        {
            TokenType tokenType = TokenType.VAR;
            for (TokenType type : TokenType.values())
            {
                if (type.toString().equals(part))
                {
                    tokenType = type;
                }
                else if (part.equals("ON")||part.equals("WITH")||part.equals("USING")||part.equals("AND"))
                {
                    tokenType = TokenType.PREPOSITION;
                }
            }
            
            tokens.add(new Token(tokenType, part.toLowerCase()));
        }
    }
   
}

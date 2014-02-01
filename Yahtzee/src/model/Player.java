package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Player, human or otherwise, is represented by a name, 
 * its total number of wins, and a score card.
 * 
 * @author Cornell Daly
 * @version 1.0
 */

public class Player
{
    private String myName;
    private int myNumberOfWins;
    private ScoreCard myScoreCard;

    /**
     * Constructs a Player with the given name, initializing myNumbersOfWins
     * and myScoreCard to their default values. 
     * 
     * @param name The player's name
     */
    public Player(String name)
    {
        myName = name;
        
        myNumberOfWins = 0;
        myScoreCard = new ScoreCard();
    }

    /**
     * Increases myNumberOfWins by 1.
     */
    public void incrementWins()
    {
        myNumberOfWins++;
    }

    /**
     * Sets myNumberOfWins to 0.
     */
    public void resetWins()
    {
        myNumberOfWins = 0;
    }
    
    public void setName(String name)
    {
        myName = name;
    }

    public String getName()
    {
        return myName;
    }

    public int getNumberOfWins()
    {
        return myNumberOfWins;
    }

    public void setScoreCard(ScoreCard card)
    {
        myScoreCard = card;
    }

    public ScoreCard getScoreCard()
    {
        return myScoreCard;
    }

    public String toString()
    {
        return String.format("%s: %d wins", myName, myNumberOfWins);
    }

    public Object clone()
    {
        Player copy = new Player(myName);
        
        copy.myNumberOfWins = this.myNumberOfWins;
        copy.myScoreCard = (ScoreCard) this.myScoreCard.clone();
        
        return copy;
    }
    
    /**
     * Checks if the name passed in contains only alphanumeric characters.
     * 
     * @param name The name whose validity is being checked.
     * @return whether the name is valid or not
     */
    public static boolean validateName(String name)
    {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(name);
        
        return matcher.matches();
    }
}
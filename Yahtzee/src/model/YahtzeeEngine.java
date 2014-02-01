/**
 * <p>The YahtzeeEnginee class is designed to handle the logic for the Yahtzee game play.<p>
 * 
 * @author Jacob Koko
 */
package model;

import java.util.Vector;

public class YahtzeeEngine
{
    public static final int MAX_ROLLS = 3;

    private Vector<Player> myPlayers;
    private int myPlayerUp;
    private String myPlayerName = "";

    private Roller myRoller;
    private int myNumberRollsUsed;

    public YahtzeeEngine(int numPlayers)
    {  
        /*
         * base case from test cases - must set vector length equal to 2 if > 6, or <= 0.
         */
        if(numPlayers > 6 || numPlayers <= 0)
        {
            myPlayers = new Vector<Player>(2);
        }
        else
        {
            myPlayers = new Vector<Player>(numPlayers);
        }
        
//        for(int i = 0 ; i < myPlayers.capacity(); i++)
//        {
//            myPlayers.add(new Player("Player"));
//        }
        myNumberRollsUsed = 0;
        myRoller = null;
        myPlayerUp = -1;
    }
   
    /**
     * <p>This method is used to jump start the entire yahtzee game.<p>
     */
    public void startGame()
    {   
        myPlayers.clear();
        
        /*
         * Manually add the players for now. Later, a start screen or JOptionPane should be implemented
         * to allow dynamic game play.
         */
        myPlayers.add(new Player(myPlayerName));
        myPlayers.add(new Player("AI"));
        
        myPlayerUp = 0;
        myNumberRollsUsed = 0;
        myRoller = new Roller(5);
    }
    
    /**
     * <p>Utility method for ending the game at any time.<p>
     */
    public void endGame()
    {
        System.exit(0);
    }
    
    /**
     * <p>This method is used to set the current player who is up. It loops thru the myPlayers Vector setting myPlayerUp to
     *    the player passed in as a parameter to the method. <p>
     *    
     * @param player
     */
    public void selectPlayerUp(Player player)
    {
        for(int i = 0; i < myPlayers.size(); i++)
        {
            if(myPlayers.get(i) == player)
            {
                myPlayerUp = i;
                break;
            }
        }
    }
   
    /**
     * <p>This method is used to alternate between the different player's turns. We increment myPlayerUp in order to
     *    choose the new player up.<p>
     */
    public void switchPlayerUp()
    {
        myPlayerUp++;
        
        if(myPlayerUp >= myPlayers.size())
        {
            myPlayerUp %= myPlayers.size();
        }
    }

    /**
     * <p>Utility method for incrementing the number of rolls used. It returns true if the number of rolls used is less than
     *    the maximum number of rolls, otherwise it will return false.<p>
     */
    public boolean incrementRollsUsed()
    {
        if(myNumberRollsUsed <= MAX_ROLLS)
        {
           myNumberRollsUsed++;
           return true;
        } 
        
        return false;
    }
    
    /**
     * <p>Utility method for resetting the number of rolls used to zero.<p>
     */
    public void resetNumberRollsUsed()
    {
        myNumberRollsUsed = 0;
    }

    public Roller getRoller()
    {
        return myRoller;
    }
    
    /**
     * <p>If myPlayerUp up is less than zero, then the game has not yet started and the player up is null. Otherwise,
     *    return the current player up.<p>
     * 
     * @return myPlayerUp
     */
    public Player getPlayerUp()
    {
        if(myPlayerUp < 0)
        {
            return null;
        }
        
        return myPlayers.get(myPlayerUp);
    }

    public Vector<Player> getPlayers()
    {
        return myPlayers;
    }

    public int getNumberRollsUsed()
    {
        return myNumberRollsUsed;
    }
    
    public void setPlayerName(String name)
    {
    	myPlayerName = name;
    }

    public String toString()
    {
        return "The current player up is " + getPlayerUp().getName() + " and they have used " + getNumberRollsUsed() + 
               " rolls out of 3,";
    }
}
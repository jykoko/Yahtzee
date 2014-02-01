package model;

import java.util.Vector;

/**
 * Class is made to roll multiple dice for the game Yahtzee
 * 
 * @author Joenise Province
 */

public class Roller
{
    private Vector<Die> myDice;
    private int myNumberOfDice;

    /**
     * Constructor assigns values to variables, creates myDice vector and
     * adds dice within each slot within the vector.
     * 
     * @param numDice The number of dice to create
     */
    public Roller(int numDice)
    {
        myNumberOfDice = numDice;
        myDice = new Vector<Die>(myNumberOfDice);
        for (int i = 0; i < myNumberOfDice; i++)
        {
            myDice.add(new Die());
        }
    }

    public Vector<Die> getDice()
    {
        return myDice;
    }

    /**
     * Returns the face values of the dice
     * 
     * @return the values of the dice
     */
    public int[] getDiceValues()
    {
        int[] diceValues = new int[myNumberOfDice];
        for (int i = 0; i < myNumberOfDice; i++)
        {
            diceValues[i] = myDice.get(i).getFaceValue();
        }
        return diceValues;
    }

    /**
     * This method rolls all of the dice within our vector
     */
    public void roll()
    {
        for (int i = 0; i < myNumberOfDice; i++)
        {
            myDice.get(i).roll();
        }
    }

    /**
     * This method rolls some of the dice within the vector
     * 
     * @param toRoll the dice that should be rolled
     */
    public void rollSome(boolean[] toRoll)
    {
        for (int i = 0; i < myNumberOfDice; i++)
        {
            if (toRoll[i])
            {
                myDice.get(i).roll();
            }
        }
    }

    public int getNumberOfDice()
    {
        return myNumberOfDice;
    }

    public String toString()
    {
        return "Number of dice equals " + myNumberOfDice;
    }
}
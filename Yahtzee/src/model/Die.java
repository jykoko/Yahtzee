package model;

import java.util.Random;

/**
 * This class has method that structures a die and keep track of it being rolled
 * 
 * @author Joenise Province, Cornell Daly 
 */

public class Die
{
    public final static int NO_VALUE = -1;
    private boolean myIsRolled;
    private Random myRandom;
    private int myFaceValue;
    private boolean myIsChosen;

    /**
     * Constructor assigns values to variables
     */
    public Die()
    {
        myFaceValue = NO_VALUE;
        myRandom = new Random();
        myIsRolled = false;
        myIsChosen = false;
    }

    /**
     * Rolls the die
     * 
     * @return myFaceValue
     */
    public int roll()
    {
        myFaceValue = myRandom.nextInt(6) + 1;
        myIsRolled = true;
        
        return myFaceValue;
    }

    public boolean getIsRolled()
    {
        return myIsRolled;
    }
    
    public void setNotRolled()
    {
        myIsRolled = false;
    }

    public int getFaceValue()
    {
        return myFaceValue;
    }
    
    public boolean getIsChosen()
    {
        return myIsChosen;
    }
    
    public void setIsChosen(boolean isChosen)
    {
        myIsChosen = isChosen;
    }
    
    public void toggleIsChosen()
    {
        myIsChosen = !myIsChosen;
    }

    public String toString()
    {
        return "Your face value is " + myFaceValue;
    }

    /**
     * Creates a clone of the die class
     */
    public Object clone()
    {
        Die copyDie = new Die();
        
        copyDie.myFaceValue = this.myFaceValue;
        copyDie.myIsRolled = this.myIsRolled;
        copyDie.myIsChosen = this.myIsChosen;
        
        return copyDie;

    }
}
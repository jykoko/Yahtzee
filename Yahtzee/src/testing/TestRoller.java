/**
 * Test cases for the Roller class. 
 * 
 */
package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import model.Die;
import model.Roller;

public class TestRoller

{
    /**
     * Creates new roller and a Die array checks to make sure that the dice size
     * is equal the amount specified in roller
     * 
     */
    @Test
    public void defaultConstructor()
    {
        Roller roll = new Roller(5);
        Vector<Die> dice = roll.getDice();
        assertEquals(dice.size(), 5);
    }

    /**
     * Creates a new roller object and checks to make sure that the number of
     * dice is equal to what was assigned in the constructor
     */
    @Test
    public void numberOfDice()
    {
        Roller roll = new Roller(4);
        int number = roll.getNumberOfDice();

        assertTrue(number == 4);
    }

    /**
     * Creates a roller,a boolean array, and a die vector then it loops through
     * both the boolean array and the vector and checks for equality.
     */
    @Test
    public void checkRollSome()
    {
        Roller roll = new Roller(5);
        boolean[] booleanArray = new boolean[]
        { false, true, true, false, false };
        roll.rollSome(booleanArray);
        Vector<Die> dice = roll.getDice();
        for (int i = 0; i < dice.size(); i++)
        {
            Die die = dice.get(i);
            assertTrue(die.getIsRolled() == booleanArray[i]);

            if (booleanArray[i] == true)
            {
                assertTrue(die.getFaceValue() != Die.NO_VALUE);
            }
            else
            {
                assertTrue(die.getFaceValue() == Die.NO_VALUE);
            }
        }
    }

    /**
     * Makes sure that all the dices have been rolled and making sure that they
     * are not equal to no value.
     */
    @Test
    public void checkRoll()
    {
        Roller roll = new Roller(5);
        Vector<Die> dice = roll.getDice();
        roll.roll();
        for (Die die : dice)
        {
            assertTrue(die.getIsRolled());
            assertTrue(die.getFaceValue() != Die.NO_VALUE);
        }
    }
    
    /**
     * Also tests getDice(), getDiceValues(), and getNumberOfDice().
     * 
     */
    @Test
    public void constructorTest()
    {
        Roller r;
        Vector< Die > dice;
        int value;
        int[] faceValues;
        int size;
        
        r = new Roller( 5 );
        
        /*
         * Roller has just been created, so dice should have size 5, but the
         * values should be equal to NO_VALUE.
         */
        dice = r.getDice();
        
        size = dice.size();
        
        Assert.assertTrue( size == 5 );
        
        for (Die d : dice)
        {
            value = d.getFaceValue();
            Assert.assertTrue( value == Die.NO_VALUE );
        }
        
        /*
         * The getDiceValues() method should do the same thing as above.
         */
        faceValues = r.getDiceValues();
        
        size = r.getNumberOfDice();
        
        Assert.assertTrue( size == 5 );
        
        for (int faceValue : faceValues)
        {
            Assert.assertTrue( faceValue == Die.NO_VALUE );
        }
    }
    
    /**
     * Also tests rollSome() and getDiceValues().
     * 
     */
    @Test
    public void rollTest()
    {
        Roller r;
        int value;
        int[] faceValues;
        boolean[] toRoll = { true, false, false, false, false };
        
        r = new Roller( 5 );
        
        /*
         * Since we rolled the roller, NO dice should be equal to NO_VALUE.
         */
        r.roll();
        
        faceValues = r.getDiceValues();
        
        for (int faceValue : faceValues)
        {
            Assert.assertFalse( faceValue == Die.NO_VALUE );
        }
        
        /*
         * If we only roll the first die, then only the first die should not be
         * equal to NO_VALUE.
         */
        // This essentially resets the roller.
        r = new Roller( 5 );
        
        r.rollSome( toRoll );
        
        faceValues = r.getDiceValues();
        
        value = faceValues[ 0 ];
        Assert.assertFalse( value == Die.NO_VALUE );
        
        for (int i = 1; i < 5; i++)
        {
            value = faceValues[ i ];
            Assert.assertTrue( value == Die.NO_VALUE );
        }
    }
}

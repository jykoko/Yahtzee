/**
 * Test Cases for the Test Die class.
 */
package testing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Die;

public class TestDie
{
    /**
     * default constructor asserts that the face value of the die is equal to no
     * value.
     */
    @Test
    public void defaultConstructor()
    {
        Die die = new Die();
        assertTrue(die.getFaceValue() == Die.NO_VALUE);
    }

    /**
     * creates a new die and makes sure that the value being rolled is the same
     * as the face value.
     */
    @Test
    public void testRoll()
    {
        Die die = new Die();
        int value = die.roll();

        assertTrue(die.getFaceValue() == value);
    }

    /**
     * creates new die and checks to see if it has been rolled
     */
    @Test
    public void hasBeenRolled()
    {
        Die die = new Die();

        assertTrue(die.getIsRolled() == false);

        die.roll();

        assertTrue(die.getIsRolled() == true);

        die.setNotRolled();

        assertTrue(die.getIsRolled() == false);
    }

    /**
     * checks to see if is chosen has been toggled
     */
    @Test
    public void toggleIsChosen()
    {
        Die die = new Die();

        assertTrue(die.getIsChosen() == false);

        die.toggleIsChosen();

        assertTrue(die.getIsChosen() == true);
    }

    /**
     * creates a copy of die and checks to make sure that the values are equal
     */
    @Test
    public void testClone()
    {
        Die die = new Die();

        int faceValue = die.roll();
        die.toggleIsChosen();

        Die copy = (Die) die.clone();

        assertTrue(die.getFaceValue() == copy.getFaceValue());
        assertTrue(die.getIsChosen() == copy.getIsChosen());
        assertTrue(die.getIsRolled() == copy.getIsRolled());

        assertTrue(copy.getFaceValue() == faceValue);

    }

}

package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Player;

/**
 * Tests all of the methods within the Player class to ensure
 * they run properly. These tests make certain assumptions about
 * the behavior of the methods, all of which should work if the
 * methods are implemented correctly.
 * 
 */
public class TestPlayer
{
    /**
     * Tests the primary constructor. This constructor requires a name
     * (variable of type String) to be passed in. Expected output is 
     * for the name to be set properly, and the number of wins variable
     * to be initialized to 0.
     */
    @Test
    public void nameConstructor()
    {
        Player player = new Player("Timothy");
        
        assertTrue(player.getName().equals("Timothy"));
        assertTrue(player.getNumberOfWins() == 0);
    }
    
    /**
     * Tests the setName method. Starts by initializing the player variable
     * with the name "Timothy." Expected output is for the name to be
     * properly set to "John."
     */
    @Test
    public void nameChange()
    {
        Player player = new Player("Timothy");
        player.setName("John");
        
        assertTrue(player.getName().equals("John"));
    }
    
    /**
     * Tests the validateName static method. Names should only contain
     * alphanumeric characters. Tests "Timothy," which should return true,
     * and "*Tim Berners-Lee*," which should fail.
     */
    @Test
    public void validateGoodName1()
    {
        assertTrue(Player.validateName("Timothy"));
    }
    
    @Test
    public void validateGoodName2()
    {
        assertTrue(Player.validateName("1bob"));
    }
    
    @Test
    public void validateBadName1()
    {
        assertFalse(Player.validateName("Tim Lee"));
    }
    
    @Test
    public void validateBadName2()
    {
        assertFalse(Player.validateName("Tim-Lee"));
    }
    
    @Test
    public void validateBadName3()
    {
        assertFalse(Player.validateName("Tim!"));
    }
    
    /**
     * Tests the getNumberOfWins, incrementWins, and resetWins methods.
     * The default value of numberOfWins should be 0. incrementWins should
     * increase this value by 1. resetWins should bring the value back to 0.
     */
    @Test
    public void testWins()
    {
        Player player = new Player("John");
        
        assertTrue(player.getNumberOfWins() == 0);
        
        player.incrementWins();
        
        assertTrue(player.getNumberOfWins() == 1);
        
        player.resetWins();
        
        assertTrue(player.getNumberOfWins() == 0);
    }
    
    /**
     * This test ensures that clones are created properly. This is done by
     * creating a player object with a name of "Thomas" and 5 wins. The created
     * clone should have the same name and same number of wins.
     */
    @Test
    public void testClone()
    {
        Player player = new Player("Thomas");
        
        for (int i = 0; i < 5; ++i)
        {
            player.incrementWins();
        }
        
        Player copy = (Player) player.clone();
        
        assertTrue(player.getName().equals(copy.getName()));
        assertTrue(player.getNumberOfWins() == copy.getNumberOfWins());
        
        assertTrue(copy.getName().equals("Thomas"));
        assertTrue(copy.getNumberOfWins() == 5);
    }
}

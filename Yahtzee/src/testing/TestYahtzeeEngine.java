package testing;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Player;
import model.YahtzeeEngine;


/**
 * <p>This class is built to test the methods within the YahtzeeEngine class.Each test case should
 * pass if the code is properly implemented.<p>
 * 
 */
public class TestYahtzeeEngine 
{
	/**
	 * <p>Tests that the Category keeps track of the proper amount of rolls that have been used.<p>
	 */
	@Test
	public void testIncrement() 
	{
		YahtzeeEngine ye = new YahtzeeEngine(2);
		
		for(int i = 0; i < 3; i++)
		{
		   ye.incrementRollsUsed();
		}
		
		assertTrue(ye.getNumberRollsUsed() == 3);
	}
	
	
	/**
	 * <p>Tests if the number of rolls is reset to zero when resetRolls() has been called.<p>
	 */
	@Test
	public void testResetRolls() 
	{
		YahtzeeEngine ye = new YahtzeeEngine(1);
		
		for(int i = 0; i < 3; i++)
		{
		   ye.incrementRollsUsed();
		}
		
		ye.resetNumberRollsUsed();
		
		assertTrue(ye.getNumberRollsUsed() == 0);
	}
	
	/**
	 * <p>Tests if the current player up will rotate properly when the switchPlayerUp() method
	 * is invoked.<p>
	 */
	@Test
	public void testPlayerUp() 
	{
		YahtzeeEngine ye = new YahtzeeEngine(2);
		Player player1 = ye.getPlayers().get(0);
		
		ye.selectPlayerUp(player1);
		ye.switchPlayerUp();
		
		assertFalse(ye.getPlayerUp().equals(player1));
	}	
	
	/**
	 * <p>Tests if the player vector is the correct length for min number of players. <p>
	 */
	@Test
	public void testMinPlayerVector() 
	{
		YahtzeeEngine ye = new YahtzeeEngine(1);
		
		assertTrue(ye.getPlayers().size() == 1);
	}
	
	/**
	 * <p>Tests if the player vector is the correct length for max number of players. <p>
	 */
	@Test
	public void testMaxPlayerVector() 
	{
		YahtzeeEngine ye = new YahtzeeEngine(6);
		
		assertTrue(ye.getPlayers().size() == 6);
	}
	
	/**
	 * <p>Test that bad number of players passed in defaults to 2 players. <p>
	 */
	@Test
	public void testTooFewPlayerVector1() 
	{
		YahtzeeEngine ye = new YahtzeeEngine(0);
		
		assertTrue(ye.getPlayers().size() == 2);
	}
	
	@Test
	public void testTooFewPlayerVector2() 
	{
		YahtzeeEngine ye = new YahtzeeEngine(7);
		
		assertTrue(ye.getPlayers().size() == 2);
	}
	
	/**
	 * <p>Tests that the game is properly instantiated after startGame() has been called.<p>
	 */
	@Test
	public void testStartOfGame() 
	{
		YahtzeeEngine ye = new YahtzeeEngine(2);
		
		ye.startGame();
		
		assertTrue(ye.getNumberRollsUsed() == 0);
		assertTrue(ye.getPlayerUp() != null);
		assertTrue(ye.getPlayers().get(0) != null);
		assertTrue(ye.getPlayers().get(1) != null);
		assertTrue(ye.getRoller() != null);
	}
	
	/**
	 * <p>Testing values from YahtzeeEngine on default construction.<p>
	 */
	@Test
	public void testYahtzeeEngineConstructor()
	{
		YahtzeeEngine ye = new YahtzeeEngine(1);
		
		assertTrue(ye.getNumberRollsUsed() == 0);
		assertTrue(ye.getPlayerUp() == null);
		assertTrue(ye.getRoller() == null);
		assertTrue(ye.getPlayers().size() == 1);
	}
}
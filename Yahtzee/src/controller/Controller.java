package controller;

/**
 * <p>The controller class acts as a traffic director between the model and the view. It's job is to update the view based on any 
 *    changes that are made to the model, firing all actions.<p>
 *    
 * @author Jacob Koko
 */
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import view.View;
import model.Category;
import model.CategoryType;
import model.ComputerPlayer;
import model.Player;
import model.Roller;
import model.ScoreCard;
import model.YahtzeeEngine;

public class Controller 
{
	private View myView;
	private YahtzeeEngine myEngine;
	private int myArgs;
	private Player myPlayer;
	private ComputerPlayer myCompPlayer;
	private Roller myRoller;
	private ScoreCard playerCard, computerPlayerCard;
	private String myCurrentPlayerUp;
	private int myCounter;
	private Vector<Category> myOptionsToFill;
	private boolean myGameInPlay;
	private int[] myDieVals = {};
	private boolean myCategoryOnesChosen, myCategoryTwosChosen, myCategoryThreesChosen, myCategoryFoursChosen, myCategoryFivesChosen,
					myCategorySixesChosen, myCategoryFullHouseChosen, myCategorySmlStrtChosen, myCategoryLrgStrt, myCategoryYahtzee,
					myCategoryChanceChosen, myThreeOfKindChosen, myFourOfKindChosen,myDie1Chosen = true, myDie2Chosen= true, myDie3Chosen= true, myDie4Chosen= true, 
					myDie5Chosen = true;
	private boolean[] myChosenDice = {myDie1Chosen, myDie2Chosen, myDie3Chosen, myDie4Chosen, myDie5Chosen};
	private boolean myCategoryHasBeenFilled;
	private int myRollsUsed;
	
	public Controller()
	{
	   myArgs = 0;
	   myRollsUsed = 0;
	   myCounter = 2;
	   myOptionsToFill = new Vector<Category>();
	   myGameInPlay = false;
	   myCategoryOnesChosen = false;
	   myCategoryHasBeenFilled = false;
	   
	   for(int i = 0 ; i < myChosenDice.length;i++)
	   {
		   myChosenDice[i] = true;
	   }
	   
	   String name = JOptionPane.showInputDialog(null, "What is your name player 1?");
	   
	   if(validateName(name))
	   {
		  System.out.print("Name validated: " + name);
	   }
	   
	   while(!validateName(name))
	   {
		  JOptionPane.showMessageDialog(null, "Error. Your name had improper characters. Please try again.");
		  name = JOptionPane.showInputDialog(null, "What is your name player 1?");
		  if(validateName(name)) break;
	   }
	   
	   myPlayer = new Player(name);
	   myPlayer.setScoreCard(playerCard = new ScoreCard());
	   
	   myCompPlayer = new ComputerPlayer();
	   myCompPlayer.setScoreCard(computerPlayerCard = new ScoreCard());
	   
	   myEngine = new YahtzeeEngine(2);
	   myView = new View(this);
	   
	   myEngine.setPlayerName(name);
	   
	   myView.hideButtonsFromView();
	   
	   startNewGame();
	   
	   myCurrentPlayerUp = myEngine.getPlayerUp().getName();
	}
	
	/**
	 * <p>Utility method for ending game.<p>
	 */
	public void endGame(Integer params)
	{
		myEngine.endGame();
	}
	
	/**
	 * <p>Utility method for validating a player's name by calling the validateName method in the model.<p>
	 */
	public boolean validateName(String name)
	{
		if(Player.validateName(name))
		{
			return true;
		}
		
		return false;
	 }
	
	/**
	 * <p>Utility method for starting a new game.<p>
	 */
	public void startNewGame()
	{
		myGameInPlay = true;
		myView.setDiceEnabled(true);
	    myEngine.startGame();
	}
	
	/**
	 * <p>Utility method for resetting the game's score card.<p>
	 */
	public void resetScoreCard(Integer params)
	{
		playerCard.resetScoreCard();
		computerPlayerCard.resetScoreCard();
		myEngine.resetNumberRollsUsed();
	}

	/**
	 * <p>The roll dice method rolls the Yahtzee dice and updates the view based on the dice face values. Additionally, If a dice has been selected to
	 * keep, the roll some method is leveraged to roll only non selected die.<p>
	 * 
	 */
	public void rollDice(Integer params)
	{
		boolean rollSome = false;
		myView.refreshButtonsForRoll();
		
		boolean[] tempArray = {myDie1Chosen, myDie2Chosen, myDie3Chosen, myDie4Chosen,myDie5Chosen};
		
		for(int i = 0; i < tempArray.length; i++)
		{
			if(tempArray [i] = false)
			{
              rollSome = true;
			}
		}
		
		myOptionsToFill.clear();
		myRoller = myEngine.getRoller();
		myView.setBtnText("Roll Dice");
		myView.showButtonsFromView();
		
		if(rollSome)
		{
		   myRoller.rollSome(tempArray);
		   myDieVals = myRoller.getDiceValues();
		   System.out.println("rolling some");
		   
		   for(int i = 0 ; i < myDieVals.length; i++)
		   {
			   System.out.println("roll some Die Vals are: " + myDieVals[i]);
		   }
		}
		else
		{
			myRoller.roll();
			myDieVals = myRoller.getDiceValues();
		   
			for(int i = 0 ; i < myDieVals.length; i++)
			{
			   System.out.println("roll ALL Die Vals are: " + myDieVals[i]);
			}
		} 
		
		myEngine.incrementRollsUsed();
		int rollsUsed = myEngine.getNumberRollsUsed();
		myRollsUsed = rollsUsed;
		System.out.println("Rolls used: " + rollsUsed);
		
		/*
		 * If any dice are true(are chosen), then call on roll some. Else call normal roll all.
		 */
		if(rollsUsed <= 3)
		{
			myCurrentPlayerUp = myEngine.getPlayerUp().getName();
			myView.updateGameStatusLabel(myCurrentPlayerUp);
			
			if(myEngine.getPlayerUp().getName().equals("AI"))
			{
				if(computerPlayerCard.getNumberCategoriesFilled() <= 12)
				{
					myView.hideButtonsFromView();
				    myCompPlayer.rollAgain(myDieVals);
					myView.showDieFaces(myDieVals);
					myCategoryHasBeenFilled = false;
					
					myOptionsToFill = myEngine.getPlayerUp().getScoreCard().getValidCategoriesToFill(myDieVals);
					myView.categoriesToFill(myOptionsToFill);
				
				    myView.updateScoreCard();
				    myEngine.resetNumberRollsUsed();
					myEngine.switchPlayerUp();
					
					JOptionPane.showMessageDialog(null, "1. The computer player has filled a category." + "\n" 
					                                  + "2. Switching player turns..." + "\n"
							                          + "3. It is now " + myEngine.getPlayerUp().getName() + "'s turn to roll.");
					
					myCurrentPlayerUp = myEngine.getPlayerUp().getName();
					myView.updateGameStatusLabel(myCurrentPlayerUp);
				    
				    rollDice(1);
				    
				    return;
				}
				else if(computerPlayerCard.getGrandTotal() > playerCard.getGrandTotal())
				{
					JOptionPane.showMessageDialog(null, "Sorry, the Computer Player is the winner!");
					myCompPlayer.incrementWins();
					myEngine.endGame();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Player 1 is the winner of the game, great-job!");
					myPlayer.incrementWins();
					myEngine.endGame();
				}
			}
			else
			{
				if(playerCard.getNumberCategoriesFilled() <= 12)
				{
					myView.showDieFaces(myDieVals);

					myOptionsToFill = myEngine.getPlayerUp().getScoreCard().getValidCategoriesToFill(myDieVals);
				
					for(int i = 0; i < myOptionsToFill.size(); i++)
					{
						System.out.println(myOptionsToFill.get(i).getName());
					}
					
					myView.categoriesToHighLight(myOptionsToFill);
					
					myView.updateScoreCard();
					
					System.out.println("Player card cats filled: " + playerCard.getNumberCategoriesFilled());
				}
				else if(playerCard.getGrandTotal() > computerPlayerCard.getGrandTotal())
				{
					JOptionPane.showMessageDialog(null, "Player 1 is the winner, great-job!");
					myPlayer.incrementWins();
					myEngine.endGame();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Sorry, the computer-player won this time. Please Try-again!");
					myCompPlayer.incrementWins();
					myEngine.endGame();
				}
			}
		}
		else if(rollsUsed > 3 && !myCategoryHasBeenFilled)
		{
			JOptionPane.showMessageDialog(null, "You must fill a category before the computer player can take a turn!");
		}
		else
		{
			myCategoryHasBeenFilled = false;
			myEngine.resetNumberRollsUsed();
			myEngine.switchPlayerUp();
			
			JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
					                           + "'s turn...");
			
			System.out.println("It is now " + myEngine.getPlayerUp() +  " turn.");
			
			myCurrentPlayerUp = myEngine.getPlayerUp().getName();
			myView.updateGameStatusLabel(myCurrentPlayerUp);
		}
	}
	
	
	/**
	 * <p>This method is leveraged to act as a connection between the category buttons in the view, and an action. Based
	 *    on the parameter passed in, a certain category is filled.<p>
	 *    
	 * @param index of category to fill
	 */
	public void selectCategoriesToFill(Integer params)
	{
		myArgs = params.intValue();
		System.out.println("My args are : " + myArgs);
		myCategoryHasBeenFilled = false;
		
		if(myArgs == 0)
		{
			if(!myCategoryOnesChosen )
			{
				myCategoryOnesChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.ONES, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 1)
		{
			if(!myCategoryTwosChosen )
			{
				myCategoryTwosChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.TWOS, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 2)
		{
			if(!myCategoryThreesChosen )
			{
				myCategoryThreesChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.THREES, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 3)
		{
			if(!myCategoryFoursChosen )
			{
				myCategoryFoursChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.FOURS, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 4)
		{
			if(!myCategoryFivesChosen )
			{
				myCategoryFivesChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.FIVES, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 5)
		{
			if(!myCategorySixesChosen )
			{
				myCategorySixesChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.SIXES, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 6)
		{
			if(!myCategorySmlStrtChosen )
			{
				myCategorySmlStrtChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.SMALL_STRAIGHT, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 7)
		{
			if(!myCategoryLrgStrt)
			{
				myCategoryLrgStrt = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.LARGE_STRAIGHT, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 8)
		{
			if(!myCategoryChanceChosen)
			{
				myCategoryChanceChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.CHANCE, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 9)
		{
			if(!myThreeOfKindChosen)
			{
				myThreeOfKindChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.THREE_OF_KIND, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 10)
		{
			if(!myFourOfKindChosen)
			{
				myFourOfKindChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.FOUR_OF_KIND, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 11)
		{
			if(!myCategoryYahtzee)
			{
				myCategoryYahtzee = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.YAHTZEE, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
			}
		}
		else if(myArgs == 12)
		{
			if(!myCategoryFullHouseChosen)
			{
				myCategoryFullHouseChosen = true;
				myCategoryHasBeenFilled = true;
				
				playerCard.fillCategory(CategoryType.FULL_HOUSE, myDieVals);
				myView.updateScoreCard();
				
				myEngine.resetNumberRollsUsed();
				myEngine.switchPlayerUp();
				JOptionPane.showMessageDialog(null, "Switching player turns! It is now " + myEngine.getPlayerUp().getName()
						                           + "'s turn...");
				
				myCurrentPlayerUp = myEngine.getPlayerUp().getName();
				myView.updateGameStatusLabel(myCurrentPlayerUp);
				rollDice(1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This category has already been filled, please select a different one.");
		    }
		}
	}
	
	/**
	 * <p>This method is used to allow the user to toggle dice between chosen and unchosen. At the end of the method, the chosen dice array
	 *    is updated to store the new selection of dice values.<p>
	 * @param Integer Value of The Selected Die Button
	 */
	public void selectDiceToKeep(Integer params)
	{
		myArgs = params.intValue();
		System.out.println("MY ARGS:" + myArgs);
		System.out.println("counter: " + myCounter);
		
		if(myArgs == 0)
		{
			myDie1Chosen = !myDie1Chosen;
		}
		else if(myArgs == 1)
		{
			myDie2Chosen = !myDie2Chosen;
		}
		else if(myArgs == 2)
		{
			myDie3Chosen = !myDie3Chosen;
		}
		else if(myArgs == 3)
		{
			myDie4Chosen = !myDie4Chosen;
		}
		else
		{
			myDie5Chosen = !myDie5Chosen;
		}

		myChosenDice[0] = myDie1Chosen;
		myChosenDice[1] = myDie2Chosen;
		myChosenDice[2] = myDie3Chosen;
		myChosenDice[3] = myDie4Chosen;
		myChosenDice[4] = myDie5Chosen;
		
		for(int i = 0; i < myChosenDice.length; i++)
		{
			System.out.println("Chosen die " + i + ": " + myChosenDice[i]);
		}
	}
	
	/**
	 * <p>This method sums up the grand total of the player's score card.<p>
	 * @param Player to select
	 * @return selected player's grand total
	 */
	public int getPlayerGrandTotal(Integer params)
	{
		if(params == 1)
		{
			return myPlayer.getScoreCard().getGrandTotal();
		}
		
		return myCompPlayer.getScoreCard().getGrandTotal();
	}
	
	/**
	 * <p>This method sums up the lower total of the player's score card.<p>
	 * @param Player to select
	 * @return selected player's lower total
	 */
	public int getPlayerLowerTotal(Integer params)
	{
		if(params == 1)
		{
			return myPlayer.getScoreCard().getLowerTotal();
		}
		
		return myCompPlayer.getScoreCard().getLowerTotal();
	}
	
	/**
	 * <p>This method sums up the upper total of the player's score card.<p>
	 * @param Player to select
	 * @return selected player's upper total
	 */
	public int getPlayerUpperTotal(Integer params)
	{
		if(params == 1)
		{
			return myPlayer.getScoreCard().getUpperTotal();
		}
		
		return myCompPlayer.getScoreCard().getUpperTotal();
	}
	
	/**
	 * <p>This method sums up the upper score of the player's score card.<p>
	 * @param Player to select
	 * @return selected player's upper score
	 */
	public int getPlayerUpperScore(Integer params)
	{
		if(params == 1)
		{
			return myPlayer.getScoreCard().getUpperScore();
		}
		
		return myCompPlayer.getScoreCard().getUpperScore();
	}
	
	/**
	 * <p>This method is used in the drop-down menu to allow the user to easily view the official 
	 *    Yahtzee game rules on the hasbro website. It is a PDF and can be accessed without any connection to Wifi,
	 *    making it very accessible for the users.<p>
	 */
	public void openGameRules(Integer params)
	{
		try
		{
			String url = "http://www.hasbro.com/common/instruct/Yahtzee.pdf";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Do you have a weak internet connection? We are having trouble accessing the yahtzee game rules."
											  + " Please check your connection and try again, thank-you!");
		}
	}
	
	public Vector<Category> getPlayerUpperCats()
	{
		return playerCard.getUpperCats();
	}
	
	public Vector<Category> getPlayerLowerCats()
	{
		return playerCard.getLowerCats();
	}
	
	public Vector<Category> getComputerPlayerUpperCats()
	{
		return computerPlayerCard.getUpperCats();
	}
	
	public Vector<Category> getComputerPlayerLowerCats()
	{
		return computerPlayerCard.getLowerCats();
	}
	
	public String getCurrentPlayerUp()
	{
		return myCurrentPlayerUp;
	}
	
	public Player getCurrentPlayerUpObject()
	{
		return myEngine.getPlayerUp();
	}
	
	public String getPlayerName()
	{
		return myPlayer.getName();
	}
	
	public ScoreCard getCompPlayerCard()
	{
		return computerPlayerCard;
	}
	
	/**
	 * <p>Method for storing the number of rolls left per player.<p>
	 * @return number of rolls left
	 */
	public int turnsLeftToRoll()
	{
		int turnsLeft = (3 - myRollsUsed); 
		return turnsLeft;
	}
	
	public boolean getGameInPlay()
	{
		return myGameInPlay;
	}
}
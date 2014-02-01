package model;

import java.util.Vector;

/**
 * ScoreCard is used to keep track of a player's score throughout the game.
 * It contains all of the categories, the total number of categories that are
 * filled, and the individual section scores (upper, lower, and grand).
 * 
 * @author Cornell Daly
 * @version 1.0
 */

public class ScoreCard
{
    public static final int NUMBER_TOP_CATEGORIES = 6;
    public static final int NUMBER_BOTTOM_CATEGORIES = 7;
    public static final int NUMBER_CATEGORIES_TOTAL = 13;
    public static final int UPPER_BONUS_VALUE = 35;
    public static final int UPPER_BONUS = 63;

    private Vector<Category> myUpperCategories;
    private Vector<Category> myLowerCategories;

    private int myUpperScore;
    private int myUpperTotal;
    private int myLowerTotal;
    private int myGrandTotal;

    private int myNumberCategoriesFilled;

    /**
     * Constructs a new ScoreCard, initializing the variables to their
     * default values. myUpperCategories and myLowerCategories are filled with
     * their proper Categories, and scores are set to 0.
     */
    public ScoreCard()
    {
        myUpperCategories = new Vector<Category>(NUMBER_TOP_CATEGORIES);
        myLowerCategories = new Vector<Category>(NUMBER_BOTTOM_CATEGORIES);
        
        myUpperCategories.add(new Category(CategoryType.ONES));
        myUpperCategories.add(new Category(CategoryType.TWOS));
        myUpperCategories.add(new Category(CategoryType.THREES));
        myUpperCategories.add(new Category(CategoryType.FOURS));
        myUpperCategories.add(new Category(CategoryType.FIVES));
        myUpperCategories.add(new Category(CategoryType.SIXES));
        
        myLowerCategories.add(new Category(CategoryType.THREE_OF_KIND));
        myLowerCategories.add(new Category(CategoryType.FOUR_OF_KIND));
        myLowerCategories.add(new Category(CategoryType.SMALL_STRAIGHT));
        myLowerCategories.add(new Category(CategoryType.LARGE_STRAIGHT));
        myLowerCategories.add(new Category(CategoryType.FULL_HOUSE));
        myLowerCategories.add(new Category(CategoryType.YAHTZEE));
        myLowerCategories.add(new Category(CategoryType.CHANCE));
        
        myUpperScore = 0;
        myUpperTotal = 0;
        myLowerTotal = 0;
        myGrandTotal = 0;
        
        myNumberCategoriesFilled = 0;
    }

    /**
     * Resets the ScoreCard to its default values. Section scores are set to 0,
     * number of categories filled is set to 0, and the upper and lower categories
     * are filled with new instances of the proper Categories.
     */
    public void resetScoreCard()
    {
        myUpperCategories.clear();
        myLowerCategories.clear();
        
        myUpperCategories.add(new Category(CategoryType.ONES));
        myUpperCategories.add(new Category(CategoryType.TWOS));
        myUpperCategories.add(new Category(CategoryType.THREES));
        myUpperCategories.add(new Category(CategoryType.FOURS));
        myUpperCategories.add(new Category(CategoryType.FIVES));
        myUpperCategories.add(new Category(CategoryType.SIXES));
        
        myLowerCategories.add(new Category(CategoryType.THREE_OF_KIND));
        myLowerCategories.add(new Category(CategoryType.FOUR_OF_KIND));
        myLowerCategories.add(new Category(CategoryType.SMALL_STRAIGHT));
        myLowerCategories.add(new Category(CategoryType.LARGE_STRAIGHT));
        myLowerCategories.add(new Category(CategoryType.FULL_HOUSE));
        myLowerCategories.add(new Category(CategoryType.YAHTZEE));
        myLowerCategories.add(new Category(CategoryType.CHANCE));
        
        myUpperScore = 0;
        myUpperTotal = 0;
        myLowerTotal = 0;
        myGrandTotal = 0;
        
        myNumberCategoriesFilled = 0;
    }

    /**
     * Finds the Category that matches the passed in type and calls its
     * fillCategoryValue method, returning whether or not it was successful.
     * 
     * @param type The type of Category to be filled.
     * @param values The values of the current player's dice.
     * @return whether or not the category was successfully filled
     */
    public boolean fillCategory(CategoryType type, int[] values)
    {
        boolean fillSuccess = false;
        
        for (Category category : myUpperCategories)
        {
            if (category.getType() == type)
            {
                fillSuccess = category.fillCategoryValue(values);
                
                if (fillSuccess)
                {
                    myNumberCategoriesFilled++;
                }
                
                break;
            }
        }
        for (Category category : myLowerCategories)
        {
            if (category.getType() == type)
            {
                fillSuccess = category.fillCategoryValue(values);
                
                if (fillSuccess)
                {
                    myNumberCategoriesFilled++;
                }
                
                break;
            }
        }
        
        this.calculateGrandTotal();
        
        return fillSuccess;
    }

    /**
     * Searches for an returns the Category matching the passed in type,
     * or null if a match is not found.
     * 
     * @param type The type of Category to get.
     * @return the Category that matches the type, or null if it wasn't found
     */
    public Category getCategory(CategoryType type)
    {
        for (Category category : myUpperCategories)
        {
            if (category.getType() == type)
            {
                return category;
            }
        }
        for (Category category : myLowerCategories)
        {
            if (category.getType() == type)
            {
                return category;
            }
        }
        
        return null;
    }

    public int getNumberCategoriesFilled()
    {
        return myNumberCategoriesFilled;
    }

    /**
     * Calculates the upper score based on the upper categories.
     * 
     * @return the calculated upper score
     */
    public int calculateUpperScore()
    {
        myUpperScore = 0;
        
        for (Category category : myUpperCategories)
        {
            if (category.getValue() == Category.NO_VALUE)
            {
                continue;
            }
            
            myUpperScore += category.getValue();
        }
        
        return myUpperScore;
    }

    /**
     * Calculates the upper total based on the upper categories and the
     * upper section bonus.
     * 
     * @return the calculated upper total
     */
    public int calculateUpperTotal()
    {
        this.calculateUpperScore();
        
        myUpperTotal = myUpperScore;
        
        if (myUpperTotal >= UPPER_BONUS)
        {
            myUpperTotal += UPPER_BONUS_VALUE;
        }
        
        return myUpperTotal;
    }

    /**
     * Calculates the upper total based on the lower categories.
     * 
     * @return the calculated lower total
     */
    public int calculateLowerTotal()
    {
        myLowerTotal = 0;
        
        for (Category category : myLowerCategories)
        {
            if (category.getValue() == Category.NO_VALUE)
            {
                continue;
            }
            
            myLowerTotal += category.getValue();
        }
        
        return myLowerTotal;
    }

    /**
     * Calculates the grand total based on the upper and lower totals.
     * 
     * @return the calculated grand total
     */
    public int calculateGrandTotal()
    {
        this.calculateUpperTotal();
        this.calculateLowerTotal();
        
        myGrandTotal = (myUpperTotal + myLowerTotal);
        
        return myGrandTotal;
    }

    public int getUpperScore()
    {
        return myUpperScore;
    }

    public int getUpperTotal()
    {
        return myUpperTotal;
    }

    public int getLowerTotal()
    {
        return myLowerTotal;
    }

    public int getGrandTotal()
    {
        return myGrandTotal;
    }
    
    public Vector<Category> getUpperCats()
    {
    	return myUpperCategories;
    }
    
    public Vector<Category> getLowerCats()
    {
    	return myLowerCategories;
    }
    
    /**
     * <p>This method returns a list of valid categories that can be filled based on the dice face values.<p>
     * 
     * @author Jacob Koko
     * @param faceVals
     * @return categories that may be filled
     */
    public Vector<Category> getValidCategoriesToFill(int[] faceVals)
    {
    	Vector<Category> toFill = new Vector<Category>(13);
    	toFill.clear();
    	
    	for(int i = 0; i < myUpperCategories.size(); i++)
    	{
    		if(myUpperCategories.get(i).possibleCategoriesToFill(faceVals))
    		{
    			System.out.println(myUpperCategories.get(i));
    			toFill.add(myUpperCategories.get(i));
    		}
    	}
    	for(int i = 0; i < myLowerCategories.size(); i++)
    	{
    		if(myLowerCategories.get(i).possibleCategoriesToFill(faceVals))
    		{
    			System.out.println(myLowerCategories.get(i));
    			toFill.add(myLowerCategories.get(i));
    		}
    	}
    	
    	return toFill;
    }

    public String toString()
    {
        String out = ("Upper score:" + myUpperScore + "\n");
        out += ("Upper total: " + myUpperTotal + "\n");
        out += ("Lower total: " + myLowerTotal + "\n");
        out += ("Grand total: " + myGrandTotal);
        
        return out;
    }

    public Object clone()
    {
        ScoreCard copy = new ScoreCard();
        
        for (int i = 0; i < myUpperCategories.size(); ++i)
        {
            myUpperCategories.set(i, (Category) myUpperCategories.get(i).clone());
        }
        for (int i = 0; i < myLowerCategories.size(); ++i)
        {
            myLowerCategories.set(i, (Category) myLowerCategories.get(i).clone());
        }
        
        copy.myUpperScore = this.myUpperScore;
        copy.myUpperTotal = this.myUpperTotal;
        copy.myLowerTotal = this.myLowerTotal;
        copy.myGrandTotal = this.myGrandTotal;
        
        copy.myNumberCategoriesFilled = this.myNumberCategoriesFilled;
        
        return copy;
    }
}
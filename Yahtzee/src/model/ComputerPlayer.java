package model;
/**
 * <p>The computer player is a very basic AI which is intended to get more complex as development time continues. As of now,
 *    it makes simple decisions to fill categories based on the vector of possible options to fill. Later, I plan to implement
 *    a weighted scale which will allow the AI to make moves based upon priority of weight.<p>
 *    
 * @author Jacob Koko
 */
import java.util.Vector;

public class ComputerPlayer extends Player
{
    public ComputerPlayer()
    {
        super("AI");
    }
    
    /**
     * <p>This method is very basic for now. The computer player simply fills the next category available at the time of a single 
     *    roll. If no category is available, it fills a category with a zero. Later, I plan to implement a weighted scale which
     *    will allow the computer player to make decisions based upon priority of weight. Giving lower categories higher weights
     *    than those of the upper categories.<p>
     * @param vals
     */
    public void takeTurn(int[] vals)
    {
    	Vector<Category>myOptionsToFill = getScoreCard().getValidCategoriesToFill(vals);
    	
    	/*
    	 * If lower category is available, fill it first. Otherwise, fill the first option in the vector of 
    	 * the potential categories.
    	 */
    	for(int i = 0 ; i < myOptionsToFill.size();i++)
    	{
	    	if(myOptionsToFill.size() > 0)
	    	{
	    		if(getScoreCard().getLowerCats().contains(myOptionsToFill.get(i).getType()))
	    		{
	    			getScoreCard().fillCategory(myOptionsToFill.get(i).getType(), vals);
	    			return;
	    		}
	    	}
    	}
    	
    	boolean isTrue = getScoreCard().fillCategory(myOptionsToFill.get(0).getType(), vals);
    	
    	if(isTrue)
    	{
    		getScoreCard().fillCategory(myOptionsToFill.get(0).getType(), vals);
    	}
    	else
    	{
			for(int i = 0; i < getScoreCard().getUpperCats().size(); i++)
			{
				if(getScoreCard().getUpperCats().get(i).getValue() == -1)
				{
					getScoreCard().fillCategory(getScoreCard().getUpperCats().get(i).getType(), vals);
					System.out.println("Filling up the upper category: " + getScoreCard().getUpperCats().get(i).getType());
					break;
				}
				else if(getScoreCard().getLowerCats().get(i).getValue() == -1)
				{
					getScoreCard().fillCategory(getScoreCard().getLowerCats().get(i).getType(), vals);
					break;
				}
			  }
    	  }
     }
    
    /**
     * <p>This method is used to fill a top category, then a bottom category, followed by another top category, etc.<p>
     * @param die face values
     * @return fill category on score card
     */
    public boolean rollAgain(int[] vals)
    {
        for(int i = 0; i < getScoreCard().getUpperCats().size(); i++)
		{
        	int temp = i + 1;
        	if(getScoreCard().getLowerCats().get(temp).getType() == CategoryType.CHANCE)
        	{
        		getScoreCard().fillCategory(CategoryType.CHANCE, vals);
        		break;
        	}	
        	else if(getScoreCard().getUpperCats().get(i).getValue() == -1)
			{
				getScoreCard().fillCategory(getScoreCard().getUpperCats().get(i).getType(), vals);
				System.out.println("Filling up the upper category: " + getScoreCard().getUpperCats().get(i).getType());
				break;
			}
			else if(getScoreCard().getLowerCats().get(i).getValue() == -1)
			{
				getScoreCard().fillCategory(getScoreCard().getLowerCats().get(i).getType(), vals);
				break;
			}
		}
    	return false;
    }

    public static boolean[] selectDiceToReroll(int[] values)
    {
        return null;
    }

    public static CategoryType selectCategoryToFill(int[] values)
    {
        return null;
    }
}
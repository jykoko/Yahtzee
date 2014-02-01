/**
 * <p>The Category Class stores the logic for filling a specific category. (ie. Three Of Kind, Full House, etc.)<p>
 *  
 * @author Jacob Koko
 */
package model;

import java.util.Arrays;

public class Category
{
    public static final int NO_VALUE = -1;

    private CategoryType myType;
    private String myName;
    private int myValue, myCount;
    private boolean myIsFilled;

    public Category(CategoryType type)
    {
        myType = type;
        myCount = 1;
        myName = myType.getName();
        myIsFilled = false;
        myValue = NO_VALUE;
    }
    
    /**
     * <p>This method is the core focus of this specific class. Its job is to return true if a specific category can be filled.
     * However, if the category has already been filled it must return false and NO_VALUE(-1). It leverages several private
     * methods in order to separate some of the logic of the more complex algorithms; such as small & large straight, full-
     * house, four-of-a-kind, three-of-a-kind, and yahtzee.<p>
     *
     * @param nums
     * @return Whether or not category has been filled or not
     */
    public boolean fillCategoryValue(int[] nums)
    {
        for(int i = 0; i < nums.length; i++)
        {
            if (nums[i] < 1 || nums[i] > 6)
            {
                myIsFilled = true;
                myValue = 0;
                
                return false;
            }
        }
        
      int sum = 0;
      boolean hasThreeOfKind = isThreeOfKind(nums);
      boolean hasFourOfKind = isFourOfKind(nums);
      boolean hasYahtzee = isYahtzee(nums);
      boolean hasFullHouse = isFullHouse(nums);
      boolean hasLargeStrt = isLargeStraight(nums);
      boolean hasSmallStrt = isSmallStraight(nums);
      
      if(!myIsFilled && myCount <= 1)
      {
    	myCount++;
        for(int i = 0; i < nums.length; i++)
        {
           if(myType == CategoryType.ONES)
           {
               if(nums[i] == 1)
               {
                   sum ++;
               }
           }
           else if(myType == CategoryType.TWOS)
           {
               if(nums[i] == 2)
               {
                   sum += 2;
               }
           }
           else if(myType == CategoryType.THREES)
           {
               if(nums[i] == 3)
               {
                   sum += 3;
               }
           }
           else if(myType == CategoryType.FOURS)
           {
               if(nums[i] == 4)
               {
                   sum += 4;
               }
           }
           else if(myType == CategoryType.FIVES)
           {
               if(nums[i] == 5)
               {
                   sum += 5;
               }
           }
           else if(myType == CategoryType.SIXES)
           {
               if(nums[i] == 6)
               {
                   sum += 6;
               } 
           }
           else if(myType == CategoryType.THREE_OF_KIND)
           {
               if(hasThreeOfKind != true)
               {
                   break;
               }
               else
               {
                  sum += nums[i];
               }
           }
           else if(myType == CategoryType.FOUR_OF_KIND)
           {
               if(hasFourOfKind != true)
               {
                   break;
               }
               else
               {
                   sum += nums[i];
               }
           }
           else if(myType == CategoryType.FULL_HOUSE)
           {
               if(hasFullHouse != true)
               {
                   break;
               }
               else
               {
                   sum = 25;
               }
           }
           else if(myType == CategoryType.SMALL_STRAIGHT)
           {
               if(hasSmallStrt != true)
               {
                   break;
               }
               else
               {
                   sum = 30;
               }
           }
           else if(myType == CategoryType.LARGE_STRAIGHT)
           {
               if(hasLargeStrt != true)
               {
                   break;
               }
               else
               {
                   sum = 40;
               }
           }
           else if(myType == CategoryType.YAHTZEE)
           {
               if(hasYahtzee != true)
               {
                   break;
               }
               else
               {
                   sum = 50;
               }
           }
           else if(myType == CategoryType.CHANCE)
           {
               sum += nums[i];
           }
         }
        
        myValue = sum;
        myIsFilled = true; 
      }
      else
      {
          myIsFilled = false;
      }
        
      return myIsFilled;
    }

    public boolean getIsFilled()
    {
        return myIsFilled;
    }

    public int getValue()
    {
        return myValue;
    }

    public String getName()
    {
        return myName;
    }

    public CategoryType getType()
    {
        return myType;
    }
    
    /**
     * <p>This method is used to check whether or not there is four of a particular Integer within the current dice values. If
     *    the counter does reach four, then the method returns true verifying that a valid four of a kind exists.<p>
     *    
     * @param nums
     * @return True if four-of-kind exists
     */
    private boolean isFourOfKind(int[] nums)
    {
        for (int i = 0; i < nums.length; i++) 
        {
          int counter = 0;
            for (int j = 0; j < nums.length; j++) 
            {
                if (nums[i] == nums[j]) 
                {
                   counter++;
                }
                
                if (counter >= 4) 
                {
                  System.out.println(nums[i] + " is found " + counter + " times.");
                  return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * <p>This method is used to check whether or not there is three of a particular Integer within the current dice values. If
     *    the counter does reach three, then the method returns true verifying that a valid three of a kind exists.<p>
     *    
     * @param nums
     * @return True if three-of-kind exists
     */
    private boolean isThreeOfKind(int[] nums)
    {
        for (int i = 0; i < nums.length; i++) 
        {
          int counter = 0;
            for (int j = 0; j < nums.length; j++) 
            {
                if (nums[i] == nums[j]) 
                {
                   counter++;
                }
                
                if (counter >= 3) 
                {
                  System.out.println(nums[i] + " is found " + counter + " times.");
                  return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * <p>This method is used to check whether or not there is five of a particular Integer within the current dice values. If
     *    the counter is greater than four, then the method returns true verifying that a valid yahtzee exists.<p>
     *    
     * @param nums
     * @return True if Yahtzee exists
     */
    private boolean isYahtzee(int[] nums)
    {
        for (int i = 0; i < nums.length; i++) 
        {
          int counter = 0;
            for (int j = 0; j < nums.length; j++) 
            {
                if (nums[i] == nums[j]) 
                {
                   counter++;
                }
                
                if (counter > 4) 
                {
                  System.out.println("Yatzee!");
                  return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * <p>This method is used to check whether or not there are five consecutive Integers in a row. If the counter does reach five, 
     *    then the method returns true verifying that a valid large straight exists.<p>
     *    
     * @param nums
     * @return True if large straight exists
     */
    private boolean isLargeStraight(int[] nums)
    {
        int count = 0;
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 1; i++)
        {
            if((nums[i] + 1) == nums[i+1])
            {
                count++;
                System.out.println("Count at: " + count +  " Numb: " + nums[i] + " is equal to " +  nums[i+1]);
            }
            
            if(count > 3)
            {
                System.out.println("is large straight");
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * <p>This method is used to check whether or not there are four consecutive Integers in a row. If the counter does reach four, 
     *    then the method returns true verifying that a valid small straight exists.<p>
     *    
     * @param nums
     * @return True if small straight exists
     */
    private boolean isSmallStraight(int[] nums)
    {
        int count = 0;
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 1; i++)
        {
            if((nums[i] + 1) == nums[i+1])
            {
                count++;
                System.out.println("Count at: " + count +  " Numb: " + nums[i] + " is equal to " +  nums[i+1]);
            }
            
            if(count > 2)
            {
                System.out.println("is small straight");
                return true;
            }
        }
        return false;
    }
    
    /**
     * <p> This method is used to check whether or not there is a pair of three particular Integers, and a pair of two different Integers,
     *     within the current dice values. If the method returns true it verifies that a valid Full House exists within the current dice
     *     values.<p>
     *     
     * @param nums
     * @return True if full house exists
     */
    private boolean isFullHouse(int[] nums)
    {
        Arrays.sort(nums);
        int a1 = nums[0];
        int a2 = nums[1];
        int a3 = nums[2];
        int a4 = nums[3];
        int a5 = nums[4];
        
        if(a1 == a2 && a3 == a4 && a3 == a5)
        {
            System.out.println("["+ a1 + "," + a2 + "," + a3 +"," + a4 + "," + a5 + "]");
            return true;
        }
        else if(a1 == a2 && a1 == a3 && a4 == a5)
        {
            System.out.println("["+ a1 + "," + a2 + "," + a3 +"," + a4 + "," + a5 + "]");
            return true;
        }
        
        return false;
    }
    
    /**
     * <p>This method is used to return what categories may or may not be filled at any given time.<p>
     * 
     * @param dice face values
     * @return possible categories to fill
     */
    public boolean possibleCategoriesToFill(int[] nums)
    {
      boolean canBeFilled = false;
        
      boolean hasThreeOfKind = isThreeOfKind(nums);
      boolean hasFourOfKind = isFourOfKind(nums);
      boolean hasYahtzee = isYahtzee(nums);
      boolean hasFullHouse = isFullHouse(nums);
      boolean hasLargeStrt = isLargeStraight(nums);
      boolean hasSmallStrt = isSmallStraight(nums);
      
        for(int i = 0; i < nums.length; i++)
        {
        	 if(!myIsFilled)
             {
	           System.out.println("I: " + i);
	           if(myType == CategoryType.ONES)
	           {
	               if(nums[i] == 1)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.TWOS)
	           {
	               if(nums[i] == 2)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.THREES)
	           {
	               if(nums[i] == 3)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.FOURS)
	           {
	               if(nums[i] == 4)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.FIVES)
	           {
	               if(nums[i] == 5)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.SIXES)
	           {
	               if(nums[i] == 6)
	               {
	            	   canBeFilled = true;
	               } 
	           }
	           else if(myType == CategoryType.THREE_OF_KIND)
	           {
	               if(hasThreeOfKind)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.FOUR_OF_KIND)
	           {
	               if(hasFourOfKind)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.FULL_HOUSE)
	           {
	               if(hasFullHouse)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.SMALL_STRAIGHT)
	           {
	               if(hasSmallStrt)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.LARGE_STRAIGHT)
	           {
	               if(hasLargeStrt)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.YAHTZEE)
	           {
	               if(hasYahtzee)
	               {
	            	   canBeFilled = true;
	               }
	           }
	           else if(myType == CategoryType.CHANCE)
	           {
	        	   canBeFilled = true;
	           }
             }
         }

      return canBeFilled;
    }
    
    
   /**
    * <p>Utility method for printing out a categories information.<p>
    */
    public String toString()
    {
        return "The Category is named " + getName() +  ". It is of Type " + getType() + ". It has a Value of " + getValue() + 
                ", and it's " + getIsFilled() + " that it has been filled.";
    }
    
    /**
     * <p>Utility method for creating copies of the Category Object.<p>
     */
    public Object clone()
    {
        Category copy = new Category(myType);
        copy.myValue = this.myValue;
        copy.myIsFilled = this.myIsFilled;
        
        return copy;
    }
}
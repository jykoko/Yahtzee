/**
 * <p>The CategoryType enum types are used to easily access the various categories found within the yahtzee game.
 *    There are 13 categories all together, and each one has its own unique criteria.<p>
 *    
 * @author Jacob Koko
 */
package model;

public enum CategoryType
{
    ONES("Ones"),
    TWOS("Twos"),
    THREES("Threes"),
    FOURS("Fours"),
    FIVES("Fives"),
    SIXES("Sixes"),
    THREE_OF_KIND("Three of a kind"),
    FOUR_OF_KIND("Four of a kind"),
    SMALL_STRAIGHT("Small Straight"),
    LARGE_STRAIGHT("Large Straight"),
    FULL_HOUSE("Full House"),
    YAHTZEE("Yahtzee"),
    CHANCE("Chance");

    private final String myName;
    
    CategoryType(String name)
    {
    	myName = name;
    }

    public String getName()
    {
        return myName;
    }
}
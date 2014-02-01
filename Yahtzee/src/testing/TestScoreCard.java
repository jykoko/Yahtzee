package testing;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import model.Category;
import model.ScoreCard;
import model.CategoryType;

/**
 * Tests all of the methods within the ScoreCard class to ensure
 * they run properly. These tests make certain assumptions about
 * the behavior of the methods, all of which should work if the
 * methods are implemented correctly.
 * 
 */
public class TestScoreCard
{
    /**
     * Tests the values of a default ScoreCard. All values should be equal to 0.
     */
    @Test
    public void testDefaults()
    {
        ScoreCard scoreCard = new ScoreCard();

        assertTrue(scoreCard.getNumberCategoriesFilled() == 0);
        assertTrue(scoreCard.getUpperScore() == 0);
        assertTrue(scoreCard.getUpperTotal() == 0);
        assertTrue(scoreCard.getLowerTotal() == 0);
        assertTrue(scoreCard.getGrandTotal() == 0);
    }
    
    /**
     * This test ensures that ScoreCards are reset properly. This is done by creating
     * a default ScoreCard and assigning values to some of the categories.
     * Calling the resetScoreCard method should result in all values being set
     * to 0, despite the previous assignments.
     */
    @Test
    public void reset()
    {
        ScoreCard scoreCard = new ScoreCard();

        scoreCard.fillCategory(CategoryType.ONES, new int[]
        { 1, 1, 1, 1, 1 });
        scoreCard.fillCategory(CategoryType.FOURS, new int[]
        { 4, 4, 4, 4, 4 });
        scoreCard.fillCategory(CategoryType.SMALL_STRAIGHT, new int[]
        { 1, 2, 3, 4, 6 });
        
        scoreCard.resetScoreCard();
        
        assertTrue(scoreCard.getNumberCategoriesFilled() == 0);
        assertTrue(scoreCard.getUpperScore() == 0);
        assertTrue(scoreCard.getUpperTotal() == 0);
        assertTrue(scoreCard.getLowerTotal() == 0);
        assertTrue(scoreCard.getGrandTotal() == 0);
    }

    /**
     * Tests the fillCategory method. It should return true the first time, as
     * the category is empty, and false the second time, as it is full.
     */
    @Test
    public void fillCategory()
    {
        ScoreCard scoreCard = new ScoreCard();

        boolean success = scoreCard.fillCategory(CategoryType.ONES, new int[]
        { 1, 1, 1, 1, 1 });

        assertTrue(success);

        success = scoreCard.fillCategory(CategoryType.ONES, new int[]
        { 1, 1, 1, 1, 1 });

        assertFalse(success);
    }

    /**
     * Tests the getNumberCategoriesFilled method. Fills up 3 categories
     * and ensures the value in the ScoreCard is the proper number (3) when
     * getNumberCategoriesFilled is called.
     */
    @Test
    public void numberOfCategoriesFilled()
    {
        ScoreCard scoreCard = new ScoreCard();

        scoreCard.fillCategory(CategoryType.ONES, new int[]
        { 1, 1, 1, 1, 1 });
        scoreCard.fillCategory(CategoryType.FOURS, new int[]
        { 4, 4, 4, 4, 4 });
        scoreCard.fillCategory(CategoryType.SMALL_STRAIGHT, new int[]
        { 1, 2, 3, 4, 6 });

        assertTrue(scoreCard.getNumberCategoriesFilled() == 3);
    }

    /**
     * Tests the getUpperScore method. This value should only change when
     * categories within the upper section are filled. Tests involve
     * filling upper sections and ensuring the new upperScore is
     * the right value.
     */
    @Test
    public void upperScore()
    {
        ScoreCard scoreCard = new ScoreCard();

        scoreCard.fillCategory(CategoryType.FOURS, new int[]
        { 4, 4, 1, 1, 1 });

        assertTrue(scoreCard.getUpperScore() == 8);

        scoreCard.fillCategory(CategoryType.ONES, new int[]
        { 1, 1, 1, 6, 6 });

        assertTrue(scoreCard.getUpperScore() == 11);
    }

    /**
     * Tests getUpperTotal. upperTotal should include upperScore and
     * the bonus if the proper number of points have been accumulated.
     */
    @Test
    public void upperTotal()
    {
        ScoreCard scoreCard = new ScoreCard();

        scoreCard.fillCategory(CategoryType.FOURS, new int[]
        { 4, 4, 4, 4, 4 });
        scoreCard.fillCategory(CategoryType.FIVES, new int[]
        { 5, 5, 5, 5, 5 });
        scoreCard.fillCategory(CategoryType.SIXES, new int[]
        { 6, 6, 6, 6, 6 });

        assertTrue(scoreCard.getUpperScore() == 75);
        assertTrue(scoreCard.getUpperTotal() == 138);
    }

    /**
     * Tests the getLowerTotal method. This value should only change when
     * categories within the lower section are filled. Tests involve
     * filling lower sections and ensuring the new lowerScore is
     * the right value.
     */
    @Test
    public void lowerScore()
    {
        ScoreCard scoreCard = new ScoreCard();

        scoreCard.fillCategory(CategoryType.SMALL_STRAIGHT, new int[]
        { 1, 2, 3, 4, 6 });
        scoreCard.fillCategory(CategoryType.YAHTZEE, new int[]
        { 5, 5, 5, 5, 5 });

        assertTrue(scoreCard.getLowerTotal() == 90);
    }

    /**
     * Tests getGrandTotal. The value of grandTotal should reflect the upperTotal
     * (including the bonus) and the lowerScore.
     */
    @Test
    public void grandTotal()
    {
        ScoreCard scoreCard = new ScoreCard();

        scoreCard.fillCategory(CategoryType.FOURS, new int[]
        { 4, 4, 4, 4, 4 });
        scoreCard.fillCategory(CategoryType.FIVES, new int[]
        { 5, 5, 5, 5, 5 });
        scoreCard.fillCategory(CategoryType.SIXES, new int[]
        { 6, 6, 6, 6, 6 });

        scoreCard.fillCategory(CategoryType.SMALL_STRAIGHT, new int[]
        { 1, 2, 3, 4, 6 });
        scoreCard.fillCategory(CategoryType.YAHTZEE, new int[]
        { 5, 5, 5, 5, 5 });

        assertTrue(scoreCard.getGrandTotal() == 228);
    }

    /**
     * This test ensures that clones are created properly. This is done by
     * creating a ScoreCard and filling various categories. A clone is then created,
     * and the values in the clone are tested to ensure they are what they
     * should be.
     */
    @Test
    public void testClone()
    {
        ScoreCard scoreCard = new ScoreCard();

        scoreCard.fillCategory(CategoryType.FOURS, new int[]
        { 4, 4, 4, 4, 4 });
        scoreCard.fillCategory(CategoryType.FIVES, new int[]
        { 5, 5, 5, 5, 5 });
        scoreCard.fillCategory(CategoryType.SIXES, new int[]
        { 6, 6, 6, 6, 6 });

        scoreCard.fillCategory(CategoryType.SMALL_STRAIGHT, new int[]
        { 1, 2, 3, 4, 6 });
        scoreCard.fillCategory(CategoryType.YAHTZEE, new int[]
        { 5, 5, 5, 5, 5 });

        ScoreCard copy = (ScoreCard) scoreCard.clone();

        assertTrue(scoreCard.getNumberCategoriesFilled() == copy.getNumberCategoriesFilled());
        assertTrue(scoreCard.getUpperScore() == copy.getUpperScore());
        assertTrue(scoreCard.getUpperTotal() == copy.getUpperTotal());
        assertTrue(scoreCard.getLowerTotal() == copy.getLowerTotal());
        assertTrue(scoreCard.getGrandTotal() == copy.getGrandTotal());

        assertTrue(scoreCard.getNumberCategoriesFilled() == 5);
        assertTrue(scoreCard.getUpperScore() == 75);
        assertTrue(scoreCard.getUpperTotal() == 138);
        assertTrue(scoreCard.getLowerTotal() == 90);
        assertTrue(scoreCard.getGrandTotal() == 228);
    }
    
    /**
     * Also tests resetScoreCard(), fillCategory(), and calculateGrandTotal().
     * 
     */
    @Test
    public void constructorTest()
    {
        ScoreCard sc1;
        ScoreCard sc2;
        int[][] values =
                         { { 1, 1, 1, 2, 2 }, { 2, 2, 2, 3, 3 },
                          { 3, 3, 3, 4, 4 }, { 4, 4, 4, 5, 5 },
                          { 5, 5, 5, 6, 6 }, { 6, 6, 6, 1, 1 } };
        int grandTotal1;
        int grandTotal2;
        
        sc1 = new ScoreCard();
        sc2 = new ScoreCard();
        
        /*
         * A new ScoreCard should be equal to a ScoreCard that has just been
         * reset.
         */
        sc1.fillCategory( CategoryType.ONES, values[ 0 ] );
        sc1.fillCategory( CategoryType.FULL_HOUSE, values[ 0 ] );
        
        sc1.resetScoreCard();
        
        /*
         * sc1 and sc2 (reset score card and new score card should be equal).
         */
        sc1.calculateGrandTotal();
        sc2.calculateGrandTotal();
        
        grandTotal1 = sc1.getGrandTotal();
        grandTotal2 = sc2.getGrandTotal();
        
        Assert.assertTrue( grandTotal1 == grandTotal2 );
    }
    
    /**
     * Tests resetScoreCard(), fillCategory(), getUpperScore(), getUpperTotal(),
     * getLowerTotal(), getGrandTotal(), calculateUpperScore(),
     * calculateUpperTotal(), calculateLowerTotal(), and calculateGrandTotal().
     * 
     */
    @Test
    public void allScoresTest()
    {
        ScoreCard sc;
        int upperScore;
        int upperTotal;
        int lowerTotal;
        int grandTotal;
        int[] values = { 1, 3, 2, 3, 3 };
        
        sc = new ScoreCard();
        
        /*
         * The top categories have no scores, so upperScore should be 0. Also,
         * upperTotal should be 0; lowerTotal and grandTotal should be equal.
         */
        sc.fillCategory( CategoryType.THREE_OF_KIND, values );
        
        sc.calculateUpperScore();
        sc.calculateUpperTotal();
        sc.calculateLowerTotal();
        sc.calculateGrandTotal();
        
        upperScore = sc.getUpperScore();
        upperTotal = sc.getUpperTotal();
        lowerTotal = sc.getLowerTotal();
        grandTotal = sc.getGrandTotal();
        
        Assert.assertTrue( upperScore == 0 );
        Assert.assertTrue( upperTotal == 0 );
        Assert.assertTrue( lowerTotal == grandTotal );
        
        /*
         * Reseting the score card to test the upper section.
         */
        sc.resetScoreCard();
        
        /*
         * The FOURS should add 0 points to the upper section.
         */
        sc.fillCategory( CategoryType.FOURS, values );
        
        sc.calculateUpperTotal();
        
        upperTotal = sc.getUpperTotal();
        
        Assert.assertTrue( upperTotal == 0 );
        
        /*
         * The TWOS should add 2 points to the upper section.
         */
        sc.fillCategory( CategoryType.TWOS, values );
        
        sc.calculateUpperTotal();
        
        upperTotal = sc.getUpperTotal();
        
        Assert.assertTrue( upperTotal == 2 );
    }
    
    /**
     * Also tests fillCategory(), getUpperScore(), getUpperTotal(),
     * getGrandTotal(), calculateUpperScore(), calculateUpperTotal(),
     * calculateLowerTotal(), and calculateGrandTotal().
     * 
     */
    @Test
    public void upperScoreTest()
    {
        ScoreCard sc;
        int upperScore;
        int upperTotal;
        int grandTotal;
        int[][] values =
                         { { 1, 1, 1, 2, 2 }, { 2, 2, 2, 3, 3 },
                          { 3, 3, 3, 4, 4 }, { 4, 4, 4, 5, 5 },
                          { 5, 5, 5, 6, 6 }, { 6, 6, 6, 1, 1 } };
        
        sc = new ScoreCard();
        
        /*
         * We will put exactly 63 points into the upper section so that
         * upperScore should equal UPPER_BONUS. Thus, upperTotal and grandTotal
         * will equal 98 (63 + 35 = 98).
         */
        sc.fillCategory( CategoryType.ONES, values[ 0 ] );
        sc.fillCategory( CategoryType.TWOS, values[ 1 ] );
        sc.fillCategory( CategoryType.THREES, values[ 2 ] );
        sc.fillCategory( CategoryType.FOURS, values[ 3 ] );
        sc.fillCategory( CategoryType.FIVES, values[ 4 ] );
        sc.fillCategory( CategoryType.SIXES, values[ 5 ] );
        
        sc.calculateUpperScore();
        sc.calculateUpperTotal();
        sc.calculateLowerTotal();
        sc.calculateGrandTotal();
        
        upperScore = sc.getUpperScore();
        upperTotal = sc.getUpperTotal();
        grandTotal = sc.getGrandTotal();
        
        Assert.assertTrue( upperScore == ScoreCard.UPPER_BONUS );
        Assert.assertTrue( upperTotal == 98 );
        Assert.assertTrue( upperTotal == grandTotal );
    }
    
    /**
     * Also tests Category.getValue(), Category.NO_VALUE, and fillCategory().
     * 
     */
    @Test
    public void getCategoryTest()
    {
        ScoreCard sc;
        Category c;
        int[][] values =
                         { { 1, 1, 1, 2, 2 }, { 2, 2, 2, 3, 3 },
                          { 3, 3, 3, 4, 4 }, { 4, 4, 4, 5, 5 },
                          { 5, 5, 5, 6, 6 }, { 6, 6, 6, 1, 1 } };
        int value;
        
        sc = new ScoreCard();
        
        /*
         * First, we get an empty category. The value should be equal to
         * NO_VALUE.
         */
        c = sc.getCategory( CategoryType.ONES );
        
        value = c.getValue();
        
        Assert.assertTrue( value == Category.NO_VALUE );
        
        /*
         * Now, we get the same category after filling it. The value should be
         * equal to 3.
         */
        sc.fillCategory( CategoryType.ONES, values[ 0 ] );
        
        c = sc.getCategory( CategoryType.ONES );
        
        value = c.getValue();
        
        Assert.assertTrue( value == 3 );
    }
    
    /**
     * Also tests fillCategory() and resetScoreCard().
     * 
     */
    @Test
    public void getNumberCategoriesFilledTest()
    {
        ScoreCard sc;
        int categoriesFilled;
        int[][] values =
                         { { 1, 1, 1, 2, 2 }, { 2, 2, 2, 3, 3 },
                          { 3, 3, 3, 4, 4 }, { 4, 4, 4, 5, 5 },
                          { 5, 5, 5, 6, 6 }, { 6, 6, 6, 1, 1 } };
        
        sc = new ScoreCard();
        
        categoriesFilled = 0;
        
        sc.fillCategory( CategoryType.ONES, values[ 2 ] );
        sc.fillCategory( CategoryType.CHANCE, values[ 3 ] );
        sc.fillCategory( CategoryType.YAHTZEE, values[ 5 ] );
        
        categoriesFilled = sc.getNumberCategoriesFilled();
        
        Assert.assertTrue( categoriesFilled == 3 );
        
        sc.resetScoreCard();
        
        categoriesFilled = sc.getNumberCategoriesFilled();
        
        Assert.assertTrue( categoriesFilled == 0 );
    }
    
}

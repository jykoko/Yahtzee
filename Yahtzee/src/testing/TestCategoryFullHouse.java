package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Category;
import model.CategoryType;

public class TestCategoryFullHouse extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {3, 2, 3, 2, 3};
		int [] bad = {3, 2, 3, 2, 4};
		myType = CategoryType.FULL_HOUSE;
		myDescription = "Full House";
		myGoodValuesSum = 25;
		myGoodValues = good;
		myZeroValues = bad;
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling a full house. <p>
	 */
	@Test
	public void testMoreGoodValues1()
	{
		 int [] values = {4, 4, 1, 1, 1};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, myGoodValuesSum);
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling a full house with Yahtzee
	 * since 5 of kind can be viewed as 3 of kind plus another 2 of kind. <p>
	 */
	@Test
	public void testMoreGoodValues2()
	{
		 int [] values = {5, 5, 5, 5, 5};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, myGoodValuesSum);
	}
	
}

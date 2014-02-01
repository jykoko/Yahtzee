package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Category;
import model.CategoryType;

public class TestCategorySmallStraight extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {4, 1, 3, 2, 3};
		int [] bad = {5, 1, 4, 4, 2};
		myType = CategoryType.SMALL_STRAIGHT;
		myDescription = "Small Straight";
		myGoodValuesSum = 30;
		myGoodValues = good;
		myZeroValues = bad;
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling a small straight 
	 * even when have a large straight.<p>
	 */
	@Test
	public void testMoreGoodValues()
	{
		 int [] values = {4, 1, 3, 2, 5};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, myGoodValuesSum);
	}
	
}

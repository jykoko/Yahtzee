package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Category;
import model.CategoryType;

public class TestCategoryLargeStraight extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {4, 1, 3, 2, 5};
		int [] bad = {1, 2, 3, 4, 6};
		myType = CategoryType.LARGE_STRAIGHT;
		myDescription = "Large Straight";
		myGoodValuesSum = 40;
		myGoodValues = good;
		myZeroValues = bad;
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling a large straight 
	 * for values 2 - 6.<p>
	 */
	@Test
	public void testMoreGoodValues()
	{
		 int [] values = {4, 6, 3, 2, 5};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, myGoodValuesSum);
	}
	
}

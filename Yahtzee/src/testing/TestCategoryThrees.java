package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Category;
import model.CategoryType;

public class TestCategoryThrees extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {2, 3, 2, 3, 1};
		int [] bad = {5, 1, 4, 4, 6};
		myType = CategoryType.THREES;
		myDescription = "Threes";
		myGoodValuesSum = 6;
		myGoodValues = good;
		myZeroValues = bad;
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling threes category.<p>
	 */
	@Test
	public void testMoreGoodValues()
	{
		 int [] values = {1, 3, 3, 3, 1};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, 9);
	}
	
}

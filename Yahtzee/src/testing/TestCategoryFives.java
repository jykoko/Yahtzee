package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Category;
import model.CategoryType;

public class TestCategoryFives extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {1, 5, 5, 4, 1};
		int [] bad = {2, 3, 2, 3, 1};
		myType = CategoryType.FIVES;
		myDescription = "Fives";
		myGoodValuesSum = 10;
		myGoodValues = good;
		myZeroValues = bad;
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling fives category.<p>
	 */
	@Test
	public void testMoreGoodValues()
	{
		 int [] values = {1, 5, 5, 5, 5};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, 20);
	}
	
}

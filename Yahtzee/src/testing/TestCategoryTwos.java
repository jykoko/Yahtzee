package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Category;
import model.CategoryType;

public class TestCategoryTwos extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {2, 5, 2, 3, 3};
		int [] bad = {5, 3, 4, 4, 6};
		myType = CategoryType.TWOS;
		myDescription = "Twos";
		myGoodValuesSum = 4;
		myGoodValues = good;
		myZeroValues = bad;
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling twos category.<p>
	 */
	@Test
	public void testMoreGoodValues()
	{
		 int [] values = {3, 2, 2, 4, 2};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, 6);
	}
	
}

package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Category;
import model.CategoryType;

public class TestCategoryOnes extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {6, 5, 4, 3, 1};
		int [] bad = {5, 3, 4, 4, 2};
		myType = CategoryType.ONES;
		myDescription = "Ones";
		myGoodValuesSum = 1;
		myGoodValues = good;
		myZeroValues = bad;
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling ones category.<p>
	 */
	@Test
	public void testMoreGoodValues()
	{
		 int [] values = {4, 1, 1, 2, 1};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, 3);
	}
	
}

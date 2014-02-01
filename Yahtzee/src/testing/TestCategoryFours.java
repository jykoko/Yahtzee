package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Category;
import model.CategoryType;

public class TestCategoryFours extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {1, 1, 1, 4, 1};
		int [] bad = {2, 3, 2, 3, 1};
		myType = CategoryType.FOURS;
		myDescription = "Fours";
		myGoodValuesSum = 4;
		myGoodValues = good;
		myZeroValues = bad;
	}
	
	/**
	 * <p>Testing that the proper values are returned when filling fours category.<p>
	 */
	@Test
	public void testMoreGoodValues()
	{
		 int [] values = {4, 4, 4, 4, 4};

		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(values);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, 20);
	}
	
}

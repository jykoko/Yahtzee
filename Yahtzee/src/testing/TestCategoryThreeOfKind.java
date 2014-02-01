package testing;

import org.junit.Before;

import model.CategoryType;

public class TestCategoryThreeOfKind extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {5, 1, 3, 3, 3};
		int [] bad = {5, 1, 4, 4, 2};
		myType = CategoryType.THREE_OF_KIND;
		myDescription = "Three of a kind";
		myGoodValuesSum = 15;
		myGoodValues = good;
		myZeroValues = bad;
	}
}

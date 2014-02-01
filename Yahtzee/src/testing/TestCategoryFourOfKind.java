package testing;

import org.junit.Before;

import model.CategoryType;

public class TestCategoryFourOfKind extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {4, 1, 4, 4, 4};
		int [] bad = {4, 1, 4, 4, 2};
		myType = CategoryType.FOUR_OF_KIND;
		myDescription = "Four of a kind";
		myGoodValuesSum = 17;
		myGoodValues = good;
		myZeroValues = bad;
	}
}

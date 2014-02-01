package testing;

import org.junit.Before;

import model.CategoryType;

public class TestCategoryYahtzee extends TestSingleCategoryTemplate {

	@Before
	public void initialize()
	{
		int [] good = {6, 6, 6, 6, 6};
		int [] bad = {7, 7, 7, 7, 7};
		myType = CategoryType.YAHTZEE;
		myDescription = "Yahtzee";
		myGoodValuesSum = 50;
		myGoodValues = good;
		myZeroValues = bad;
	}
}

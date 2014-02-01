package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCategoryChance.class, TestCategoryFives.class,
		TestCategoryFourOfKind.class, TestCategoryFours.class,
		TestCategoryFullHouse.class, TestCategoryLargeStraight.class,
		TestCategoryOnes.class, TestCategorySixes.class,
		TestCategorySmallStraight.class, TestCategoryThreeOfKind.class,
		TestCategoryThrees.class, TestCategoryTwos.class,
		TestCategoryYahtzee.class, TestDie.class, TestPlayer.class,
		TestRoller.class, TestScoreCard.class, TestYahtzeeEngine.class })
public class AllTests {

}

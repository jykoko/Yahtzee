package testing;

import static org.junit.Assert.*;
import java.util.Vector;
import model.Category;
import model.CategoryType;
import org.junit.Test;

/**
 * <p>This class is built to test all of the methods within the Category class for case of
 * Four of a Kind.<em>Each test case should pass if the code is properly implemented.<em><p>
 * 
 */
public class TestSingleCategoryTemplate 
{
	/*
	 * Properties
	 */
	protected CategoryType myType;
	protected String myDescription;
	protected int myGoodValuesSum;
	protected int[] myGoodValues;
	protected int[] myZeroValues;

	/**
	 * <p>Testing that the Category returns the correct Category name associated with its current type.<p>
	 */
	@Test
	public void testCategoryName()
	{
        Vector<Category> categoryVector = new Vector<Category>();
		
		categoryVector.add(new Category(myType));
		
		assertTrue(categoryVector.elementAt(0).getName().equals(myDescription));
	}
	
	/**
	 * <p>Testing that the category is empty before it has been filled by a player. <p>
	 */
	@Test
	public void testNotFilledCategory()
	{
       Category cat = new Category(myType);
   
       boolean isFilled = cat.getIsFilled();
       
       assertEquals(isFilled, false);
	}
	
	/**
	 * <p>Testing that the category returns true after being filled once<p>
	 */
	@Test
	public void testFilledOnce()
	{
       Category cat = new Category(myType);
       
       boolean filled = cat.fillCategoryValue(myGoodValues);
       
       assertEquals(filled, true);
	}
	
	/**
	 * <p>Testing that the category returns true after being filled once, but returns false after
	 * being filled a second time.<p>
	 */
	@Test
	public void testFilledTwice()
	{
       Category cat = new Category(myType);
       
       boolean filled = cat.fillCategoryValue(myGoodValues);
       filled = cat.fillCategoryValue(myGoodValues);
    		   
       assertEquals(filled, false);
	}
	
	/**
	 * <p>Testing that the proper values are returned after a category has been filled by a player. <p>
	 */
	@Test
	public void testValues()
	{
		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(myGoodValues);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, myGoodValuesSum);
	}
	
	/**
	 * <p>Testing that the proper values are returned after a category has been filled by a player. <p>
	 */
	@Test
	public void testFillZero()
	{
		 Category cat = new Category(myType);
	     
	     cat.fillCategoryValue(myZeroValues);
	     
	     int value = cat.getValue();
	     
	     assertEquals(value, 0);
	}
	
	/**
	 * <p>Testing that the category filled with 0 when out of range values passed in. <p>
	 */
	@Test
	public void testFillOutOfRange1()
	{
		int[] bad = {4, 3, 0, 1, 2};
		Category cat = new Category(myType);
	     
	    cat.fillCategoryValue(bad);
	     
	    int value = cat.getValue();
	     
	    assertEquals(value, 0);
	}
	
	/**
	 * <p>Testing that the category filled with 0 when out of range values passed in. <p>
	 */
	@Test
	public void testFillOutOfRange2()
	{
		int[] bad = {4, 7, 2, 1, 2};
		Category cat = new Category(myType);
	     
	    cat.fillCategoryValue(bad);
	     
	    int value = cat.getValue();
	     
	    assertEquals(value, 0);
	}
	
	/**
	 * <p>Testing that the clone is created properly. This is done by creating a copy and comparing the values to
	 * the original reference for equivalence. <p>
	 */
	@Test
	public void testClone()
	{
        Category category = new Category(myType);
        boolean isFull = category.fillCategoryValue(myGoodValues);
	    int value = category.getValue();
        
        Category copy = (Category) category.clone();
        
	    assertTrue(value == copy.getValue());
        assertEquals(category.getType(), copy.getType());
        assertTrue(category.getName().equals(copy.getName()));
        assertTrue(isFull == copy.getIsFilled());
	}
}
package hello;

import org.junit.Assert;
import org.junit.Test;


public class AsiEquationSolverTests {
	
	@Test
	public void test1(){
		AsiEquationSolver equation = new AsiEquationSolver(2 , 2 , -59 , -29);
		Root root = equation.findRootsInRangeAndRoundTo1Decimal(0, 100);
		Assert.assertEquals(root.getRoots().size(), 1);
		Assert.assertEquals(root.getRoots().get(0), 30.0, 0.00001);
	}
	
	@Test
	public void test2(){
		AsiEquationSolver equation = new AsiEquationSolver(3 , 4 , 5 , -6);
		Root root = equation.findRootsInRangeAndRoundTo1Decimal(0, 100);
		Assert.assertEquals(root.getRoots().size(), 1);
		Assert.assertEquals(root.getRoots().get(0), 0.6, 0.00001);
		
		Root root2 = equation.findRootsInRangeAndRoundTo1Decimal(-10, 0);
		Assert.assertEquals(root2.getRoots().size(), 1);
		Assert.assertEquals(root2.getRoots().get(0), -2.2, 0.00001);

		Root root3 = equation.findRootsInRangeAndRoundTo1Decimal(-10, 100);
		Assert.assertEquals(root3.getRoots().size(), 2);
		Assert.assertEquals(root3.getRoots().get(0), -2.2, 0.00001);
		Assert.assertEquals(root3.getRoots().get(1), 0.6, 0.00001);
	}
	
	@Test
	public void test3(){
		AsiEquationSolver equation = new AsiEquationSolver(0 , 4 , 5 , -6);
		Root root = equation.findRootsInRangeAndRoundTo1Decimal(0, 100);
		Assert.assertEquals(root.getRoots().size(), 1);
		Assert.assertEquals(root.getRoots().get(0), 0.8, 0.00001);	
		
		Root root2 = equation.findRootsInRangeAndRoundTo1Decimal(-10, 0);
		Assert.assertEquals(root2.getRoots().size(), 1);
		Assert.assertEquals(root2.getRoots().get(0), -2, 0.00001);
		
		Root root3 = equation.findRootsInRangeAndRoundTo1Decimal(-10, 10);
		Assert.assertEquals(root3.getRoots().size(), 2);
		Assert.assertEquals(root3.getRoots().get(0), 0.8, 0.00001);
		Assert.assertEquals(root3.getRoots().get(1), -2, 0.00001);
	}
	
	@Test
	public void test4(){
		AsiEquationSolver equation = new AsiEquationSolver(7.1 , 1.4 , 5 , -6);
		Root root = equation.findRootsInRangeAndRoundTo1Decimal(-2.5, 100);
		Assert.assertEquals(root.getRoots().size(), 1);
		Assert.assertEquals(root.getRoots().get(0), 0.5, 0.00001);	
		
		Root root2 = equation.findRootsInRangeAndRoundTo1Decimal(-10, -2.5);
		Assert.assertEquals(root2.getRoots().size(), 1);
		Assert.assertEquals(root2.getRoots().get(0), -3.8, 0.00001);
		
		Root root3 = equation.findRootsInRangeAndRoundTo1Decimal(-10, 10);
		Assert.assertEquals(root3.getRoots().size(), 2);
	}
}

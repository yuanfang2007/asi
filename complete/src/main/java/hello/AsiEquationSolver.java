package hello;

import java.util.ArrayList;

import hello.tool.DoubleTool;

/**
 * @author yuan
 *
  Build a root finding solver for an equation as described below:
  a sin X + bX^2 + cX + d = 0
  where X is the variables, a, b, c are coefficients and d is a constant.
 */

public class AsiEquationSolver extends EquationSolver{
	private static final int NUM_TEST_VALUES = 1000;
	private static final double MIN_ERROR = 0.000000000001;
	
	private double a;
	private double b;
	private double c;
	private double d;
	
	public AsiEquationSolver(double a, double b, double c, double d){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	public Root findRootsInRangeAndRoundTo1Decimal(double lowerRange, double upperRange){
		return roundTo1Decimal(findRootsInRange(lowerRange, upperRange));
	}
	
	@Override
	public Root findRootsInRange(double lowerRange, double upperRange){
		if(a==0){
			return findSepcialRoot(lowerRange, upperRange);
		}

		Root root = new Root("", null);
		
		double lowerCut=lowerRange;
		for(int i=1; i<NUM_TEST_VALUES; i++){
			double upperCut = lowerRange + i* ((upperRange-lowerRange)/NUM_TEST_VALUES);
			root.combine(findOneRoot(lowerCut, upperCut));
			lowerCut = upperCut;
		}
		
		return root;
	}
	
	//find roots when a==0
	private Root findSepcialRoot(double lowerRange, double upperRange){
		if(b==0){
			if(c==0){
				return new Root("Error: Not a valid equation, all coefficients are 0!", null);
			}else{
				double root = -d/c;
				ArrayList<Double> roots = new ArrayList<>();
				if(root>=lowerRange && root<upperRange){
					roots.add(root);
				}
				return new Root("", roots);
			}
		}else{
			double delta = c*c-4*b*d;
			if(delta < 0){
				return new Root("Error: No valid solutions!", null);
			}else{
				ArrayList<Double> roots = new ArrayList<>();					
				if(delta==0){
					double root = -c/(2*b);
					if(root>=lowerRange && root<upperRange){
						roots.add(root);
					}
				}else{
					double root1 = (-c + Math.sqrt(c*c-4*b*d))/(2*b);
					double root2 = (-c - Math.sqrt(c*c-4*b*d))/(2*b);
					if(root1>=lowerRange && root1<upperRange){
						roots.add(root1);
					}
					if(root2>=lowerRange && root2<upperRange){
						roots.add(root2);
					}
				}
				return new Root("", roots);
			}
		}
	}
	
	private Root findOneRoot(double lowerRange, double upperRange){
		if(Math.abs(f(lowerRange)) < MIN_ERROR){
			ArrayList<Double> roots = new ArrayList<>();
			roots.add(lowerRange);
			return new Root("", roots); 
		}
		
		if(f(lowerRange) * f(upperRange) > 0){
			return new Root("", null);
		}
		
		double mid = (lowerRange+upperRange)/2;
		if(f(lowerRange)*f(mid)<0){
			return findOneRoot(lowerRange, mid);
		}else{
			return findOneRoot(mid, upperRange);
		}
	}
	
	private double f(double x){
		return Math.sin(x) + b*x*x/a + c*x/a + d/a;
	}
	
	private Root roundTo1Decimal(Root root){
		if(root.getRoots()!=null){
			ArrayList<Double> roots = new ArrayList<>();
			Root newRoot = new Root(root.getComment(),roots);
			
			for(Double r: root.getRoots()){
				roots.add(DoubleTool.round(r, 1));
			}
			return newRoot;
		}
		return root;
	}
	
//	public static void main(String[] args) {
//		AsiEquationSolver equation = new AsiEquationSolver(2 , 2 , -59 , -29);
//		Root root = equation.findRootsInRangeAndRoundTo1Decimal(0, 100);
//		
//		System.out.println(root.getRoots());
//		System.out.println(equation.f(30.0));
//		System.out.println(equation.f(30.015908));
//		
//		AsiEquationSolver equation2 = new AsiEquationSolver(3 , 4 , 5 , -6);
//		Root root2 = equation2.findRootsInRangeAndRoundTo1Decimal(-10, 100);
//		
//		System.out.println(root2.getRoots());
//
//	}

}

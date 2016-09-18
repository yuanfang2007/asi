package hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.AsiEquationSolver;
import hello.Root;

@RestController
public class AsiEquationSolverController {
	@RequestMapping("/v1/solver")
	public String greeting(@RequestParam("coefficients") String coefficients, @RequestParam("searchRange") String searchRange) {
		//return new Greeting(counter.incrementAndGet(), String.format(template, coefficients + " | " + searchRange));
		String[] coefficientsStrings = coefficients.substring(1, coefficients.length()-1).split(",");
		String[] searchRangeStrings = searchRange.substring(1, searchRange.length()-1).split(",");
		Root root = new AsiEquationSolver(Double.parseDouble(coefficientsStrings[0]), Double.parseDouble(coefficientsStrings[1]), 
				Double.parseDouble(coefficientsStrings[2]), 
				Double.parseDouble(coefficientsStrings[3])).findRootsInRangeAndRoundTo1Decimal(Double.parseDouble(searchRangeStrings[0]), 
						Double.parseDouble(searchRangeStrings[1]));
		if(root.getRoots()!=null && root.getRoots().size()>0){
			return "{ root: " + root.getRoots().get(0) +"}";
		}else{
			return "{ root: }";
		}
	}
}



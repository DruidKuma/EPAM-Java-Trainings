public class MyMathMethodsTest {
	public static void main(String[] args) {
		
		// Numbers for testing tasks 1-2
		int[] numbers = {10,123,1111,0,-12345};
		
		// Numbers for testing tasks 3-7
		int[] tinyNumbers = {1,2,3,4,5};
		int[] permutations = {1,2,3,4};
		int[] powers = {0,2,-2,1,5};
		int[] xPoints = {5,10,15,9,4};
		
		// Test for task 1
		System.out.println("Task 1: Counting the digits");
		System.out.println("===========================");
		for(int number : numbers) {
			System.out.println("Number: "+ number);
			System.out.println("Digits: "+ MyMathMethods.countDigits(number));
			System.out.println("");
		}
		
		System.out.println("===========================");
		
		// Test for task 2
		System.out.println("Task 2: Reversing the number");
		for(int number : numbers) {
			System.out.println("Original: "+ number);
			System.out.println("Reversed: "+ MyMathMethods.reverseNumber(number));
			System.out.println("");
		}
		
		System.out.println("===========================");
		
		// Test for task 3
		System.out.println("Task 3: Raising to the power with Recursion");
		for(int power : powers) {
			System.out.println("Base and Power: " + numbers[0] + ", " + power);
			System.out.println("Reversed: "+ MyMathMethods.powRecur(numbers[0],power));
			System.out.println("");
		}
		
		System.out.println("===========================");
		
		// Test for task 4
		System.out.println("Task 4: Factorial with Recursion");
		for(int number : tinyNumbers) {
			System.out.println("Number: " + number);
			System.out.println("Factorial: "+ MyMathMethods.factRecur(number));
			System.out.println("");
		}
		
		System.out.println("===========================");
		
		// Test for task 5
		System.out.println("Task 5: Fibonacci with Recursion");
		for(int number : tinyNumbers) {
			System.out.println("Number: " + number);
			System.out.println("Fibonacci: "+ MyMathMethods.fibRecur(number));
			System.out.println("");
		}
		
		System.out.println("===========================");
		
		// Test for task 6
		System.out.println("Task 6: Computing Function");
		for(int x : xPoints) {
			System.out.println("X: " + x);
			System.out.println("Y: "+ MyMathMethods.computeFunc(x, xPoints[0]));
			System.out.println("");
		}
		
		System.out.println("===========================");
		
		// Test for task 7
		System.out.println("Task 7: Permutations");
		for(int n : permutations) {
			System.out.println("N: " + n);
			System.out.println("Permutations: " + MyMathMethods.factRecur(n));
			MyMathMethods.makePermut(n);
			System.out.println("");
		}
		
		System.out.println("===========================");
	}
}
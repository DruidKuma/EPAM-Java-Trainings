/** Some Personal interesting math functions
* all methods are static
* 
* @author Yuriy Miedviediev
* @version 1.0 Build Mar 28, 2014.
*/
public class MyMathMethods {
	
	/** <b>Task 1</b><br>
	* returns the number of digits of the input
	* 
	* @param number		negative or natural integer or zero
	*
	* @return	integer, number of digits in the number
	*/
	public static int countDigits(long number) {
		int count = 0;
		long num = Math.abs(number);
		
		// base case for the zero
		if(num == 0) {
			return 1;
		}
		// count the digits one by one, moving it to left by one decimal place
		// until number reaches zero
		while(num > 0) {
			num /= 10;
			count++;
		}
		return count;
	} 
	
	/** <b>Task 2</b><br>
	* returns reversed version of the input
	* 
	* @param number		negative or natural integer or zero
	*
	* @return	integer, mirror-based number of the input 
	*/ 
	public static int reverseNumber(int number) {
		int num = Math.abs(number);
		int result = 0;
		
		// reverse the number one digit at a time
		// and place it in the correct decimal order
        while(num > 0) {
            result = result * 10 + num % 10;
            num /= 10;
        }
        
        // return correctly signed result 
        if(number < 0) {
        	return -result;
        }
        else return result;
	}

	/** <b>Task 3</b><br>
	* returns number raised to the given power using recursion
	* 
	* @param base		negative or natural integer or zero
	* @param power		negative or natural integer or zero
	*
	* @return	float point number, base raised to the given power
	*/
	
	// raises number to the given power (both negative and nonnegative)
	public static float powRecur(int base, int power) {
		if(power < 0) {
			return 1.0f / (float) posPowRecur(base, Math.abs(power));
		}
		else return posPowRecur(base, power);
	}
	
	// Helper method (assumes power to be nonnegative)
	private static float posPowRecur(int base, int power) {
		// base case
		if(power < 1) {
			return 1;
		}
		
		// recursion step
		else return base * powRecur(base, power - 1);
	}
	
	/** <b>Task 4</b><br>
	* returns factorial of the given number using recursion
	* assumes given number is nonnegative
	*
	* @param n		natural integer or zero
	*
	* @return	integer, factorial of the given number
	*/
	public static int factRecur(int n) {
		
		// base case
		if (n < 2) {
			return 1;
		}
		
		// recursion step
		else return n * factRecur(n - 1);
	}
	
	/** <b>Task 5</b><br>
	* returns fibonacci number of the given input using recursion
	* assumes given number is nonnegative
	*
	* @param n		natural integer or zero
	*
	* @return	integer, fibonacci number of the given input
	*/
	public static int fibRecur(int n) {
		
		// base case
		if (n < 2) {
			return 1;
		}
		
		// recursion step
		else return fibRecur(n - 1) + fibRecur(n - 2);
	}

	/** <b>Task 6</b><br>
	* computes a special function y=log2(x-4)+exp(2a-x)
	* range of permissible values: x greater than 4 (log functions can be
	* taken only from nonnegative values)
	*
	* @param x		natural integer greater than 4
	* @param a		negative or natural integer or zero
	*
	* @return float point number, y function of the given x
	*/
	public static float computeFunc(int x, int a) {
		if (x <= 4) {
			System.out.println("X is out of permissible values");
			return 0.0f;
		}
		return (float) (log2(x-4)+Math.exp(2*a-x));
	}
		
	// Helper method to compute log base 2, using basic logarithmic formula 
	// for base changing 
	private static float log2(int x) {
		return (float) (Math.log(x) / Math.log(2));
	}
	
	/** <b>Task 7</b><br>
	* prints all permutations of numbers 1..n
	* n! possible combinations
	*
	* Works only for 1-digit numbers (1-9)
	*
	* Caution! Don't use numbers greater than 5
	* unless you need a really big sequence of combinations
	*
	* @param n		nonnegative integer
	*/
	public static void makePermut(int n) {
		permute(makeSequence(n), 0);
	}
	
	// Helper methods
	
	/* 
	Special helper method for raising number to the certain power
	assumes power is nonnegative 
	*/
	private static long myPow(int base, int power) {
		if(power < 1) {
			return 1;
		}
		else return base * myPow(base, power - 1);
	}
	
	// make a number-sequence of digits from 1..n
	private static long makeSequence(int n) {
		int result = 0;
		for(int i=1; i<=n; i++) {
			result += i * myPow(10, n-i);
		}
		return result;
	}
	 
	// returns digit at position i from number n
	private static int getDigitAtPos(long n, int i) {
		int length = countDigits(n);
		int power = length - 1;
		for (int pos=0; pos<length; pos++) {
			int digit = (int) ((n - (n % myPow(10,power)))) / (int) (myPow(10,power));
			if(pos == i) {
				return digit;
			}
			else {
				n -= digit * myPow(10,power);
				power--;
			}
		}
		return 0;
	}
	
	// returns number n with digits at positions i and j swapped
	private static long swap(long n, int i, int j) {
		long result = 0;
		int length = countDigits(n);
		int power = length - 1;
		int x;
		for(int pos=0; pos<length; pos++) {
			if(pos==i) {
				x = getDigitAtPos(n,j);
			}
			else if(pos==j) {
				x = getDigitAtPos(n,i);
			}
			else {
				x = getDigitAtPos(n,pos);
			}
			result += x * myPow(10,power);
			power--;
		}
		return result;
	}
	
	// print all permutations
	private static void permute(long sequence, int i) {
		int length = countDigits(sequence);
		if(i==length) {
			System.out.println(sequence);
			return;
		}
		for(int j=i; j<length; j++) {
			sequence = swap(sequence, i, j);
			permute(sequence, i+1);
			sequence = swap(sequence, i, j);
		}
	}
	
	// End of Helper Methods
}
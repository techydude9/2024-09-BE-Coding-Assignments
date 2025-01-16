package com.promineotech;

import java.util.Random;

public class TestDemo {
	
	public int addPositive(int a, int b) {
		
		if (a > 0 && b > 0) {
			return (a + b);
		} else {
			throw new IllegalArgumentException ("Both parameters must be positive!");
		}
	} // end of addPositive method

// Section 3 code
	public int multiplyPositive(int a, int b) {
		
		if (a > 0 && b > 0) {
			return (a * b);
		} else {
			throw new IllegalArgumentException ("Both parameters must be positive!");
		}
	} // end of addPositive method

// Section 4 code
	public int randomNumberSquared() {
		int sqd = getRandomInt();
		
		return (sqd * sqd);
	} // end of randomNumberSquared method

	public int getRandomInt() {
		Random random = new Random();

		return random.nextInt(10) + 1;
	} // end of getRandomInt method
	
}  // end of TestDemo class

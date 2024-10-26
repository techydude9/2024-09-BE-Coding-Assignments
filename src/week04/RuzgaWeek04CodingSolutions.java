package week04;

import java.util.Scanner;

public class RuzgaWeek04CodingSolutions {

	static Scanner userIn = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Ruzga Week 04 Coding Solutions");
		System.out.println("---------------------------------------");
		System.out.println("***************************************");
	
/* *1. Create an array of int called ages that contains the following values: 3, 9, 23, 64, 2, 8, 28, 93.
*	a. Programmatically subtract the value of the first element in the array from the value in the last element of the array (i.e. do not use ages[7] in your code). Print the result to the console.  
*
*	b. Create a new array of int called ages2 with 9 elements (ages2 will be longer than the ages array, and have more elements).  
*
*		i. Make sure that there are 9 elements of type int in this new array.  
*
*		ii. Repeat the subtraction from Step 1.a. (Programmatically subtract the value of the first element in the new array ages2 from the last element of ages2). 
*
*		iii. Show that using the index values for the elements is dynamic (works for arrays of different lengths).
*
*	c. Use a loop to iterate through the array and calculate the average age. Print the result to the console.
*		
*/
	// Question 1 Solution
		System.out.println("\nQ1 Solution:");
		System.out.println("------------------");
		
		// Q1a
		int[] ages = {3, 9, 23, 64, 2, 8, 28, 93};
		System.out.println("First subtracted from last element = " + (ages[ages.length - 1] - ages[0]) +" using ages array\n");
		
		// Q1b
		int[] ages2 = {21, 2, 43, 37, 53, 88, 7, 43, 72};
		System.out.println("First subtracted from last element = " + (ages2[ages2.length - 1] - ages2[0]) +" using ages2 array\n");
		System.out.println("The calculate last entry is ages2.length - 1 which is: " + (ages2.length - 1));  
		
		// Q1c
		float avgAge = 0;
		
		for (int i = 0; i < ages2.length; i++) {
			avgAge += ages2[i];
		}
		
		System.out.println("Average Age for Age2 array is: " + (avgAge / ages2.length) + "\n");
		
/*
 * 	2. Create an array of String called names that contains the following values: “Sam”, “Tommy”, “Tim”, “Sally”, “Buck”, “Bob”.
*	a. Use a loop to iterate through the array and calculate the average number of letters per name. Print the result to the console.
*
*	b. Use a loop to iterate through the array again and concatenate all the names together, separated by spaces, and print the result to the console.
 */
		// Question 2 Solution		
		System.out.println("\nQ2 Solution:");
		System.out.println("------------------");
		
		String[] names = {"Sam", "Tommy", "Tim", "Sally", "Buck", "Bob"};
		
		float numLetters = 0;
		for (String name : names) {
			numLetters += name.length();
		}
		System.out.println("2a: Average number of letters per name in the names array is: " + (numLetters / names.length));
		
		String allNames = " ";
		for (String name : names) {
			allNames += name;
			allNames += " ";
		}
		System.out.println("2b: Concat Names together including space using the names array is: " + allNames + "\n");
		
/*
 * 	3. How do you access the last element of any array?	
 */		
	// Question 3 Solution		
		System.out.println("\nQ3 Solution:");
		System.out.println("------------------");
		System.out.println("You access the last element of any array by using the length of the array minus 1 since arrays start with 0 as first index \n");
	
/*
 *  4. How do you access the first element of any array?
 */
	// Question 4 Solution
		System.out.println("\nQ4 Solution:");
		System.out.println("------------------");
		System.out.println("You access the first element of any array by using 0 as first index value \n");

/*
 * 5. Create a new array of int called nameLengths. Write a loop to iterate over the previously created names array and add the length of each name to the nameLengths array.
 */
	// Question 5 Solution
		System.out.println("\nQ5 Solution:");
		System.out.println("------------------");
		
		int[] nameLengths = new int[names.length];
		
		for (int i = 0; i < names.length; i++) {
			nameLengths[i] = names[i].length();
		}
		
		for (int i = 0; i < nameLengths.length; i++) {
		System.out.println("Names Array Index: " + i + " " + names[i] + " Length array value: " + nameLengths[i]);
		}
		
/*
 * 	6. Write a loop to iterate over the nameLengths array and calculate the sum of all the elements in the array. Print the result to the console.	
 */
	// Question 6 Solution
		System.out.println("\nQ6 Solution:");
		System.out.println("------------------");
		
		int sumOfNames = 0;
		for (int i = 0; i < nameLengths.length; i++) {
			sumOfNames += nameLengths[i];
		}
		System.out.println("The sum of all the letters using the nameLength array values is: " + sumOfNames + "\n");

/*
*  7. Write a method that takes a String, word, and an int, n, as arguments and returns the word concatenated to itself n number of times. (i.e. if I pass in “Hello” and 3, I expect the method to return “HelloHelloHello”).
 */
	// Question 7 Solution
		System.out.println("\nQ7 Solution:");
		System.out.println("------------------");
		
		String word = "Winner";
		int numTimes = 5;
		
		System.out.println("Using " + word + " concatenated to itself " + numTimes + "times results in:");
		System.out.println(concatTheWord(word, numTimes));
		
/*
 * 	8. Write a method that takes two Strings, firstName and lastName, and returns a full name (the full name should be the first and the last name as a String separated by a space).	
 */
	// Question 8 Solution
		System.out.println("\nQ8 Solution:");
		System.out.println("------------------");
		
		System.out.println(createFullName("Robert", "Ruzga"));
/*
 * 9. Write a method that takes an array of int and returns true if the sum of all the ints in the array is greater than 100.		
 */
	// Question 9 Solution
		System.out.println("\nQ9 Solution:");
		System.out.println("------------------");
		
		System.out.print("The sum of ages2 array greater than 100 is: ");
		System.out.println(sumTheAges2Array(ages2));
		
		int[] testArray = {2, 4, 6, 8};
		System.out.print("The sum of testArray greater than 100 is: ");
		System.out.println(sumTheAges2Array(testArray));
		
/*
*10. Write a method that takes an array of double and returns the average of all the elements in the array.
*	
 */
	// Question 10 Solution
		System.out.println("\nQ10 Solution:");
		System.out.println("------------------");

		double[] productCosts = {12.99, 23.45, 16.74, 1.99, 5.00, .64};
		System.out.println("The average product cost is " + calcAvgCost(productCosts) + " for these " + productCosts.length + " items");
		
/*
*11. Write a method that takes two arrays of double and returns true if the average of the elements in the first array is greater than the average of the elements in the second array.
*	
 */
	// Question 11 Solution
		System.out.println("\nQ11 Solution:");
		System.out.println("------------------");
		
		double[] arrayQ11A = {10.24, 8.96, 74.12, 15.39, 46.89};
		double[] arrayQ11B = {1.06, 54.72, 37.11, 1.06, 26.46};
		
		System.out.println("The average of the elements in arrayQ11A  are GREATER than average of elemenst in arrayQ11B is: " + compareAvgOf2Arrays(arrayQ11A, arrayQ11B));
 
/*
*12. Write a method called willBuyDrink that takes a boolean isHotOutside, and a double moneyInPocket, and returns true if it is hot outside and if moneyInPocket is greater than 10.50.
*	
 */
	// Question 12 Solution
		System.out.println("\nQ12 Solution:");
		System.out.println("------------------");
		
		boolean isHotOutside = true;
		double moneyInPocket = 10.51;
		
		System.out.println("Is hot: " + isHotOutside + ". Money in Pocket = " + moneyInPocket);
		System.out.println("It's hot outside and I have more than $10.50 is: " + willBuyDrink(isHotOutside, moneyInPocket));

/*
*13. Create a method of your own that solves a problem. In comments, write what the method does and why you created it.
*	
 */
	// Question 13 Solution
		
		System.out.println("\nQ13 Solution:");
		System.out.println("------------------");
	// added Scanner userIn at top of class section and included the import		
		int onceMore = 1;
		
		// Run the calculate down payment amount while the user continues to enter 1 after each calc
		while(onceMore == 1) {
		
		double principle = 0.00;
		double percentDown = 0.00;
		
		System.out.println("Calculate Downpayment needed for Principle amount entered by User");
		System.out.print("How much is the principle? ");
		principle = userIn.nextDouble();
		
		System.out.print("\nEnter down payment percent eg 10.00 for 10% ");
		percentDown = userIn.nextDouble();
		
		System.out.println("-------------");
		System.out.println("Your principle of: $" + principle);
		System.out.println("Percent down of: " + percentDown + "%");
		System.out.println("-------------");
		
		// calculate the downpayment amouny by calling calcDownPaymentAmt method 
		System.out.println("You will need to put $" + calcDownPaymentAmt(principle, percentDown) + " down\n");
		
		// Ask user if they want to run this again
		System.out.println("\nEnter 1 to run again: ");
		onceMore = userIn.nextInt();
		
	
		if (onceMore != 1) {
			// User indicated they are done!  See ya later!
			System.out.println(" ---- Thank you! Good Bye! -------" );
			} 
		}	
		
// END of Coding Assignment Footer
		System.out.println("***************************************");	
		System.out.println("---------------------------------------");
		System.out.println("End of Ruzga Week 04 Coding Assignment");
		System.out.println("---------------------------------------");
	}  // end of main

	//Q7 Method Solution
	public static String concatTheWord(String word, int numTimes) {
		String result = "";
		
		for (int i = 1; i <= numTimes; i++) {
			result += word;
		}
		return result;
	}
	
	//Q8 Method Solution
	public static String createFullName(String firstName, String lastName) {
		return (firstName + " " + lastName);
	}
	
	//Q9 Method Solution
	public static boolean sumTheAges2Array (int[] ages2) {
		int sum = 0;
		
		for (int i = 0; i < ages2.length; i++) {
			sum += ages2[i];
		}
		
		if (sum > 100) {
			return true;
		} else {
			return false;
		}
	}
	
	//Q10 Method Solution
	public static double calcAvgCost (double[] productCosts) {
		double sumCosts = 0.00;
		
		for (int i = 0; i < productCosts.length; i++) {
			sumCosts += productCosts[i];
		}
		
		return (sumCosts / productCosts.length);
	}
	
	//Q11 Method Solution
	public static boolean compareAvgOf2Arrays(double[] array1, double[] array2) {
		double avgArray1 = 0.00;
		double avgArray2 = 0.00;
		
		for (int i = 0; i < array1.length; i++) {
			avgArray1 += array1[i];
		}
		
		for (int j = 0; j < array2.length; j++) {
			avgArray2 += array2[j];
		}
		
		System.out.println("arrayQ11A sum is: " + avgArray1 + " with " + array1.length + " Avg: " + (avgArray1 / array1.length));
		System.out.println("arrayQ11B sum is: " + avgArray2 + " with " + array2.length + " Avg: " + (avgArray2 / array2.length));
		
		return ((avgArray1 / array1.length) > (avgArray2 / array2.length));
	}
	
	//Q12 Method Solution
	public static boolean willBuyDrink(boolean isHotOutside, double moneyInPocket) {
		return (isHotOutside && moneyInPocket > 10.50);
	}
	
	
	//Q13 Method Solution
	//   This method takes a double for principle amount and a double for percentage down and returns a
	// down payment required amount which is also a double.
	//
	public static double calcDownPaymentAmt(double principle, double percentDown) {
		return (principle * (percentDown / 100));
	}
	
} // end of class RuzgaWeek04CodingSolutions


/*
*1. Create an array of int called ages that contains the following values: 3, 9, 23, 64, 2, 8, 28, 93.
*	a. Programmatically subtract the value of the first element in the array from the value in the last element of the array (i.e. do not use ages[7] in your code). Print the result to the console.  
*
*	b. Create a new array of int called ages2 with 9 elements (ages2 will be longer than the ages array, and have more elements).  
*
*		i. Make sure that there are 9 elements of type int in this new array.  
*
*		ii. Repeat the subtraction from Step 1.a. (Programmatically subtract the value of the first element in the new array ages2 from the last element of ages2). 
*
*		iii. Show that using the index values for the elements is dynamic (works for arrays of different lengths).
*
*	c. Use a loop to iterate through the array and calculate the average age. Print the result to the console.
*
** 2. Create an array of String called names that contains the following values: “Sam”, “Tommy”, “Tim”, “Sally”, “Buck”, “Bob”.
*	a. Use a loop to iterate through the array and calculate the average number of letters per name. Print the result to the console.
*
*	b. Use a loop to iterate through the array again and concatenate all the names together, separated by spaces, and print the result to the console.
*
*3. How do you access the last element of any array?
*
*4. How do you access the first element of any array?
*
*5. Create a new array of int called nameLengths. Write a loop to iterate over the previously created names array and add the length of each name to the nameLengths array.
*
*6. Write a loop to iterate over the nameLengths array and calculate the sum of all the elements in the array. Print the result to the console.
*
*7. Write a method that takes a String, word, and an int, n, as arguments and returns the word concatenated to itself n number of times. (i.e. if I pass in “Hello” and 3, I expect the method to return “HelloHelloHello”).
*
*8. Write a method that takes two Strings, firstName and lastName, and returns a full name (the full name should be the first and the last name as a String separated by a space).
*
*9. Write a method that takes an array of int and returns true if the sum of all the ints in the array is greater than 100.
*
*10. Write a method that takes an array of double and returns the average of all the elements in the array.
*
*11. Write a method that takes two arrays of double and returns true if the average of the elements in the first array is greater than the average of the elements in the second array.
*
*12. Write a method called willBuyDrink that takes a boolean isHotOutside, and a double moneyInPocket, and returns true if it is hot outside and if moneyInPocket is greater than 10.50.
*
*13. Create a method of your own that solves a problem. In comments, write what the method does and why you created it.
*
*
*
*/
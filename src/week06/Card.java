package week06;

/*
 * November 3 - 9, 2024 BE Coding Final
 * Author: Bob Ruzga
 */

public class Card {
	String name;   //Name should be value + suit. eg Ace of Diamonds
	int value;
	
	Card(String name, int value) {
		this.name = name;
		this.value = value;
	}

	// getters & setters section
	// name getter/setter
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//value getter/setter
	public int getValue() {
		return value;	
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	// describe() method
	public void describe() {
		System.out.println(this.name + " has a value of: " + this.value);
	}

}  // end of Card class

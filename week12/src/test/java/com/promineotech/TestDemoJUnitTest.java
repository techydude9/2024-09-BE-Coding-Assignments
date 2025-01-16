package com.promineotech;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestDemoJUnitTest {

	private TestDemo testDemo;
	
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> 
					testDemo.addPositive(a, b))
						.isInstanceOf(IllegalArgumentException.class);
		}
	} // end of assertThatTwoPositiveNumbersAreAddedCorrectly method
	
	static Stream<Arguments> argumentsForAddPositive() {
		
		return Stream.of(
			arguments(2,4,6,false),
			arguments(150,360,510,false),
			arguments(0,4,4,true),
			arguments(4,0,4,true),
			arguments(4,0,4,false),
			arguments(-1,-1,-2,true)
			);
	} // end of method argumentsForAddPositive
	
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
		assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
		assertThat(testDemo.addPositive(1, 2)).isEqualTo(3);
		assertThat(testDemo.addPositive(412, 536)).isEqualTo(948);
	} // end of assertThatPairsOfPositiveNumbersAreAddedCorrectly method
	
// Code for step 3 - Create Your Own Method & JUnit Test
// multiplyPositive method test
	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForMultiplyPositive")
	void assertThatTwoPositiveNumbersAreMultipliedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.multiplyPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> 
					testDemo.multiplyPositive(a, b))
						.isInstanceOf(IllegalArgumentException.class);
		}
	} // end of assertThatTwoPositiveNumbersAreAddedCorrectly method
	
	static Stream<Arguments> argumentsForMultiplyPositive() {
		
		return Stream.of(
			arguments(2,4,8,false),
			arguments(0,4,0,true),
			arguments(4,0,0,true),
			arguments(4,0,0,false),
			arguments(-1,-1,-1,true)
			);
	} // end of method argumentsForMultiplyPositive
// end of step 3 code
	
// Code for Section 4 - Mocking a Class - randomNumberSquared method
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
	
	} // end of assertThatNumberSquaredIsCorrect method
	
// end of Section 4 code
} // end of TestDemoUnitTest class

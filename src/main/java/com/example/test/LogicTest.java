package com.example.test;

import com.example.Logic.DateCheck;
import com.example.Logic.EmailCheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import com.example.Logic.PercentageCheck;
import com.example.Logic.URLCheck;

public class LogicTest {

    // Tests voor DateCheck klasse
    @Test
    public void testValidDate() {
        // arrange
        DateCheck dateCheck = new DateCheck();

        // act en assert
        assertTrue(dateCheck.isValidDate("2023-04-16"));
    }

    @Test
    public void testInvalidDate() {
        // arrange
        DateCheck dateCheck = new DateCheck();

        // act en assert
        assertFalse(dateCheck.isValidDate("2024-05-30"));
    }

    @Test
    public void testInvalidDateFormat() {
        // arrange
        DateCheck dateCheck = new DateCheck();

        // act en assert
        assertFalse(dateCheck.isValidDate("16-04-2023"));
    }

    // Tests voor PercentageCheck klasse:
    @Test
    public void testValidPercentage() {
        // Arrange
        PercentageCheck percentageCheck = new PercentageCheck();
        String validPercentage = "50 %";

        // Act
        boolean result = percentageCheck.correctPerentage(validPercentage);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testInvalidPercentage() {
        // Arrange
        PercentageCheck percentageCheck = new PercentageCheck();
        String invalidPercentage = "120 %";

        // Act
        boolean result = percentageCheck.correctPerentage(invalidPercentage);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testNegativePercentage() {
        // Arrange
        PercentageCheck percentageCheck = new PercentageCheck();
        String negativePercentage = "-10 %";

        // Act
        boolean result = percentageCheck.correctPerentage(negativePercentage);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testZeroPercentage() {
        // Arrange
        PercentageCheck percentageCheck = new PercentageCheck();
        String zeroPercentage = "0 %";

        // Act
        boolean result = percentageCheck.correctPerentage(zeroPercentage);

        // Assert
        assertTrue(result);
    }

    // Tests voor URLCheck klasse:

    @Test
    public void testCorrectURL() {
        // Arrange
        URLCheck urlCheck = new URLCheck();

        // Act
        boolean result = urlCheck.correctURL("https://www.example.com");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIncorrectURL() {
        // Arrange
        URLCheck urlCheck = new URLCheck();

        // Act
        boolean result = urlCheck.correctURL("example.com");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIncompleteURL() {
        // Arrange
        URLCheck urlCheck = new URLCheck();

        // Act
        boolean result = urlCheck.correctURL("https://www.example");

        // Assert
        assertFalse(result);
    }

    // Tests voor de EmailCheck klasse
    @Test
    public void testCorrectEmail() {
        // Arrange
        EmailCheck emailCheck = new EmailCheck();

        // Act
        boolean result = emailCheck.correctEmail("test@example.com");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIncorrectEmail() {
        // Arrange
        EmailCheck emailCheck = new EmailCheck();

        // Act
        boolean result = emailCheck.correctEmail("testexample.com");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIncompleteEmail() {
        // Arrange
        EmailCheck emailCheck = new EmailCheck();

        // Act
        boolean result = emailCheck.correctEmail("test@example");

        // Assert
        assertFalse(result);
    }

    // Tests voor de PostcodeCheck klasse:

    @Test
    public void testCorrectPostcode() {
        // Arrange
        PostcodeCheck postcodeCheck = new PostcodeCheck();

        // Act
        boolean result = postcodeCheck.correctPostcode("1234AB");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIncorrectPostcode() {
        // Arrange
        PostcodeCheck postcodeCheck = new PostcodeCheck();

        // Act
        boolean result = postcodeCheck.correctPostcode("12ABCD");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testPostcodeWithSpace() {
        // Arrange
        PostcodeCheck postcodeCheck = new PostcodeCheck();

        // Act
        boolean result = postcodeCheck.correctPostcode("1234 AB");

        // Assert
        assertTrue(result);
    }
}

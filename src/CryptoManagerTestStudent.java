import static org.junit.Assert.*;
import org.junit.Test;

/*
 * Class: CMSC203
 * Instructor: Professor Grinberg
 * Description: Program that implements four main cyptographic methods: Caesar cipher and bellaso cipher encryption/decryption
 * Due: 3/24/2025
 * Platform/compiler: Intellij
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Manuel Colindres
 */

public class CryptoManagerTestStudent {

    // Test isStringInBounds method
    @Test
    public void testStringInBounds() {
        // Valid strings within bounds (ASCII 32-95)
        assertTrue(CryptoManager.isStringInBounds("VALID_STRING"));
        assertTrue(CryptoManager.isStringInBounds(" 123ABC_"));
        assertTrue(CryptoManager.isStringInBounds("!@#$%"));

        // Invalid strings
        assertFalse(CryptoManager.isStringInBounds("lowercase")); // lowercase letters
        assertFalse(CryptoManager.isStringInBounds("{OUT_OF_RANGE")); // '{' is ASCII 123
        assertFalse(CryptoManager.isStringInBounds("\tTAB")); // tab is ASCII 9
    }

    // Test caesarEncryption method
    @Test
    public void testCaesarEncryption() {
        // Basic encryption tests
        assertEquals("DEF", CryptoManager.caesarEncryption("ABC", 3));
        assertEquals("KHOOR", CryptoManager.caesarEncryption("HELLO", 3));

        // Wrap-around cases
        assertEquals("!\"#", CryptoManager.caesarEncryption("ABC", 96)); // 96 % 64 = 32
        assertEquals("CDE", CryptoManager.caesarEncryption("ABC", 66)); // 66 % 64 = 2

        // Out of bounds string
        assertEquals("The selected string is not in bounds, Try again.",
                CryptoManager.caesarEncryption("outofbounds", 5));
    }

    // Test caesarDecryption method
    @Test
    public void testCaesarDecryption() {
        // Basic decryption tests
        assertEquals("ABC", CryptoManager.caesarDecryption("DEF", 3));
        assertEquals("HELLO", CryptoManager.caesarDecryption("KHOOR", 3));

        // Wrap-around cases
        assertEquals("ABC", CryptoManager.caesarDecryption("!\"#", 96));
        assertEquals("TEST", CryptoManager.caesarDecryption("3;:;", 105));

        // Extreme key values
        assertEquals("JAVA", CryptoManager.caesarDecryption("2=7=", 3000));
    }

    // Test bellasoEncryption method
    @Test
    public void testBellasoEncryption() {
        // Basic encryption tests
        assertEquals("WN#\\N &", CryptoManager.bellasoEncryption("TESTING", "CIPHER_IS_LONG"));
        assertEquals("UJ^^((HT^X[YYM\"", CryptoManager.bellasoEncryption("MERRY CHRISTMAS", "HELLO"));

        // Key shorter than text
        assertEquals("WU\\VR9F#N!RF88U-'HED", CryptoManager.bellasoEncryption("THIS IS ANOTHER TEST", "CMSC203"));

        // Key longer than text
        assertEquals("VYY[^U", CryptoManager.bellasoEncryption("CRYPTO", "SUPERSECUREKEY"));
    }

    // Test bellasoDecryption method
    @Test
    public void testBellasoDecryption() {
        // Basic decryption tests
        assertEquals("TESTING", CryptoManager.bellasoDecryption("WN#\\N &", "CIPHER_IS_LONG"));
        assertEquals("MERRY CHRISTMAS", CryptoManager.bellasoDecryption("UJ^^((HT^X[YYM\"", "HELLO"));

        // Key shorter than text
        assertEquals("THIS IS ANOTHER TEST", CryptoManager.bellasoDecryption("WU\\VR9F#N!RF88U-'HED", "CMSC203"));

        // Edge case with wrap-around
        assertEquals("SPECIAL_CHARS", CryptoManager.bellasoDecryption("3'(+*#.0*'#+)", "KEY"));
    }

    // Additional edge case tests
    @Test
    public void testEdgeCases() {
        // Empty string tests
        assertEquals("", CryptoManager.caesarEncryption("", 5));
        assertEquals("", CryptoManager.bellasoEncryption("", "KEY"));

        // Boundary characters
        assertTrue(CryptoManager.isStringInBounds(" _")); // Space and underscore
        assertEquals("! ", CryptoManager.caesarEncryption(" _", 1));
    }
}
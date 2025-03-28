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

public class CryptoManager {

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

    // Determines if a string is within the allowable bounds of ASCII codes
    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (c < LOWER_RANGE || c > UPPER_RANGE) {
                return false;
            }
        }
        return true;
    }

    // Encrypts a string according to the Caesar Cipher. The integer key specifies an offset
    public static String caesarEncryption(String plainText, int key) {
        if (!isStringInBounds(plainText)) {
            return "The selected string is not in bounds, Try again.";
        }

        StringBuilder encryptedText = new StringBuilder();

        // Adjust key if it's larger than our range
        key = key % RANGE;
        if (key < 0) {
            key += RANGE; // ensure positive offset
        }

        for (int i = 0; i < plainText.length(); i++) {
            char originalChar = plainText.charAt(i);
            int encryptedChar = originalChar + key;

            // Wrap around if necessary
            while (encryptedChar > UPPER_RANGE) {
                encryptedChar -= RANGE;
            }

            encryptedText.append((char) encryptedChar);
        }

        return encryptedText.toString();
    }

    // Encrypts a string according the Bellaso Cipher. Each character in plainText is offset
    public static String bellasoEncryption(String plainText, String bellasoStr) {
        StringBuilder encryptedText = new StringBuilder();
        int bellasoLength = bellasoStr.length();

        for (int i = 0; i < plainText.length(); i++) {
            char plainChar = plainText.charAt(i);
            char bellasoChar = bellasoStr.charAt(i % bellasoLength);

            int encryptedChar = plainChar + bellasoChar;

            // Wrap around if necessary
            while (encryptedChar > UPPER_RANGE) {
                encryptedChar -= RANGE;
            }

            encryptedText.append((char) encryptedChar);
        }

        return encryptedText.toString();
    }

    //Decrypts a string according to the Caesar Cipher. The integer key specifies an offset
    public static String caesarDecryption(String encryptedText, int key) {
        StringBuilder decryptedText = new StringBuilder();

        // Adjust key if it's larger than range
        key = key % RANGE;
        if (key < 0) {
            key += RANGE; // ensure positive offset
        }

        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedChar = encryptedText.charAt(i);
            int decryptedChar = encryptedChar - key;

            // Wrap around if necessary
            while (decryptedChar < LOWER_RANGE) {
                decryptedChar += RANGE;
            }

            decryptedText.append((char) decryptedChar);
        }

        return decryptedText.toString();
    }

    // Decrypts a string according the Bellaso Cipher.
    public static String bellasoDecryption(String encryptedText, String bellasoStr) {
        StringBuilder decryptedText = new StringBuilder();
        int bellasoLength = bellasoStr.length();

        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedChar = encryptedText.charAt(i);
            char bellasoChar = bellasoStr.charAt(i % bellasoLength);

            int decryptedChar = encryptedChar - bellasoChar;

            // Wrap around if necessary
            while (decryptedChar < LOWER_RANGE) {
                decryptedChar += RANGE;
            }

            decryptedText.append((char) decryptedChar);
        }

        return decryptedText.toString();
    }
}
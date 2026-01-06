package com.silamoney.client.testsutils;

import java.util.Random;

public class RandomSSNGenerator {

    /**
     * Generates a random, validly-formatted U.S. Social Security Number (SSN).
     *
     * @return A randomly generated SSN string in "XXX-XX-XXXX" format.
     */
    public static String generateRandomSSN() {
        Random random = new Random();

        int area;
        do {
            area = random.nextInt(899) + 100;
        } while (area == 666 || area >= 900);

        int group;
        do {
            group = random.nextInt(99);
        } while (group == 0); // Exclude 00 (represented as 0 when single digit)

        int serial;
        do {
            serial = random.nextInt(9999);
        } while (serial == 0); // Exclude 0000 (represented as 0 when single digit)

        // Use String.format for proper leading zeros and final formatting
        return String.format("%03d-%02d-%04d", area, group, serial);
    }

    public static void main(String[] args) {
        System.out.println("Generated Random SSNs for testing:");
        for (int i = 0; i < 5; i++) {
            System.out.println(generateRandomSSN());
        }
    }
}
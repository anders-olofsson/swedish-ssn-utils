package io.olofsson.ssnutil

class SsnChecksumCalculator {
    int computeChecksumDigitFor9SsnDigits(String first9Digits) {
        int sumOf9Digits = 0
        (0..8).each {
            def weightForIndex = 2 - it % 2
            def currentProduct = Integer.valueOf(first9Digits[it]) * weightForIndex
            sumOf9Digits += (currentProduct / 10) + (currentProduct % 10)
        }
        (10 - (sumOf9Digits % 10)) % 10
    }
}
